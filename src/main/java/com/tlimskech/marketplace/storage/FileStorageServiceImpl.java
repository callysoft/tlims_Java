package com.tlimskech.marketplace.storage;

import lombok.SneakyThrows;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.springframework.util.Assert.notNull;

@Service
@Profile("dev")
public class FileStorageServiceImpl implements FileStorageService {

    @Value("${upload.dir}")
    private String directory;
    private final String SLASH = File.separator;

    @SneakyThrows
    public String singleUpload(MultipartFile file, String name) {
        if (ObjectUtils.isEmpty(file)) {
            return "default.png";
        }
        String newFileName = System.currentTimeMillis() + file.getOriginalFilename();
        notNull(file.getInputStream(), "Inputstream must not be null to build file");

        InputStream inputStream = file.getInputStream();
        File newfile = new File(directory + SLASH + newFileName);
        OutputStream outputStream = FileUtils.openOutputStream(newfile);
        IOUtils.copyLarge(inputStream, outputStream);
        IOUtils.closeQuietly(inputStream);
        IOUtils.closeQuietly(outputStream);
        return newFileName;

    }

    @SneakyThrows
    public List<String> multipleUpload(MultipartFile[] file, String name) {
        if (ObjectUtils.isEmpty(file)) {
            return Collections.singletonList("default.png");
        }
        List<String> fileNames = new ArrayList<>();
        for (MultipartFile multipartFile : file) {
            String filename = singleUpload(multipartFile, name);
            fileNames.add(filename);
        }
        return fileNames;
    }

    @SneakyThrows
    @Override
    public InputStream stream(String key) {
        File file = new File(directory + SLASH + key);
        return new FileInputStream(file);
    }

}
