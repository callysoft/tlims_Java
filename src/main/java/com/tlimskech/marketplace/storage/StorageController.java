package com.tlimskech.marketplace.storage;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.IOUtils;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;

@Controller
@Slf4j
public class StorageController {

    private final FileStorageService fileStorageService;

    public StorageController(FileStorageService fileStorageService) {
        this.fileStorageService = fileStorageService;
    }

    @GetMapping("/storage/{key}")
    public void download(@PathVariable("key") String key, HttpServletResponse response) {
        try {
            InputStream inputStream = fileStorageService.stream(key);
//            response.setContentType(MediaType.APPLICATION_OCTET_STREAM_VALUE);
            IOUtils.copy(inputStream, response.getOutputStream());
            IOUtils.closeQuietly(inputStream);
        } catch (IOException e) {
            log.error(e.getMessage(), e);
        }
    }
}
