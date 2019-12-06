package com.tlimskech.marketplace.storage;

import com.cloudinary.Cloudinary;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
//@Profile("prod")
@Profile("mysql")
public class CloudStorage implements FileStorageService {

    @Value("${upload.dir}")
    private String directory;
    private final String SLASH = File.separator;
    private @Value("${cloudinary.apikey}") String key;
    private @Value("${cloudinary.apisecret}") String secret;
    @Value("${cloudinary.cloudname}")
    private String cloud;

    private Cloudinary getCloudinaryClient() {
        return new Cloudinary(com.cloudinary.utils.ObjectUtils.asMap(
                "cloud_name", cloud,
                "api_key", key,
                "api_secret", secret,
                "secure", true));
    }

    @Override
    @SneakyThrows
    public String singleUpload(MultipartFile file, String name) {
        Map params = com.cloudinary.utils.ObjectUtils.asMap("resource_type", "image");
        File convFile = multipartToFile(file);
        Map upload = getCloudinaryClient().uploader().upload(convFile, params);
        return upload.get("url").toString();
    }

    @Override
    @SneakyThrows
    public List<String> multipleUpload(MultipartFile[] file, String name) {
        List<String> fileNames = new ArrayList<>();
        for (MultipartFile multipartFile : file) {
            fileNames.add(singleUpload(multipartFile, name));
        }
        return fileNames;
    }

    @Override
    @SneakyThrows
    public InputStream stream(String key) {
        File file = new File(directory + SLASH + key);
        return new FileInputStream(file);
    }

    private static File multipartToFile(MultipartFile image) throws IllegalStateException, IOException {
        File convFile = Files.createTempFile("temp", image.getOriginalFilename()).toFile();
        image.transferTo(convFile);
        return convFile;
    }
}
