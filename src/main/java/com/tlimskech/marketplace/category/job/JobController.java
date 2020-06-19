package com.tlimskech.marketplace.category.job;

import com.tlimskech.marketplace.storage.FileStorageService;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/api/jobs")
@Log4j2
public class JobController {

    private final FileStorageService fileStorageService;
    private final JobService jobService;

    public JobController(FileStorageService fileStorageService, JobService jobService) {
        this.fileStorageService = fileStorageService;
        this.jobService = jobService;
    }

    @PostMapping("/create")
    public ResponseEntity<?> createFashion(@RequestPart("job") Job job,
                                           @RequestParam(required = false, name = "file") MultipartFile[] file) {
        log.info("Job to be created: {}", job);
        List<String> images = fileStorageService.multipleUpload(file, job.getTitleDescription().getTitle());
        job.setImages(images);
        Job saved = jobService.create(job);
        log.info("Job created: {}", saved);
        return ResponseEntity.ok(saved);
    }
}
