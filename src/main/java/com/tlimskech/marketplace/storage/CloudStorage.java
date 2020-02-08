package com.tlimskech.marketplace.storage;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import com.tlimskech.marketplace.exception.ApplicationException;
import lombok.SneakyThrows;
import org.apache.commons.io.FileUtils;
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
// @Profile("prod")
@Profile("mysql")
public class CloudStorage implements FileStorageService {

    @Value("${upload.dir}")
    private String directory;
    private final String SLASH = File.separator;
    private final Cloudinary cloudinaryConfig;

    public CloudStorage(Cloudinary cloudinaryConfig) {
        this.cloudinaryConfig = cloudinaryConfig;
    }

    @Override
    @SneakyThrows
    public String singleUpload(MultipartFile file, String name) {
        try {
            File conFile = multipartToFile(file);
            Map upload = cloudinaryConfig.uploader().upload(conFile, ObjectUtils.emptyMap());
            FileUtils.deleteQuietly(conFile);
            return upload.get("url").toString();
        } catch (Exception e) {
            e.printStackTrace();
            throw new ApplicationException(e.getMessage());
        }


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

    private static File multipartToFile(MultipartFile multipart) throws IllegalStateException, IOException {
        File convFile = new File(String.format("%s/%s", System.getProperty("java.io.tmpdir"), multipart.getOriginalFilename()));
        multipart.transferTo(convFile);
        return convFile;
    }
}
