package com.tlimskech.marketplace.storage;

import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;
import java.util.List;

public interface FileStorageService {
	String singleUpload(MultipartFile file, String name);
	List<String> multipleUpload(MultipartFile[] file, String name);
	InputStream stream(String key);
}
