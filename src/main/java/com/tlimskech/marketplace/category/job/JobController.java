package com.tlimskech.marketplace.category.job;

import com.tlimskech.marketplace.storage.FileStorageService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/api/jobs")
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
        List<String> images = fileStorageService.multipleUpload(file, job.getTitleDescription().getTitle());
        job.setImages(images);
        return ResponseEntity.ok(jobService.create(job));
    }
}
