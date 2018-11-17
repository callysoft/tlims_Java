package com.tlimskech.marketplace.storage;

import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

public interface FileStorageService {
	String singleUpload(MultipartFile file, String name) throws IOException;
	List<String> multipleUpload(MultipartFile[] file, String name) throws IOException;
	InputStream stream(String key);
}
