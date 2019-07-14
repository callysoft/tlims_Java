package com.tlimskech.marketplace.category.job;

import com.tlimskech.marketplace.ad.AdService;
import com.tlimskech.marketplace.core.service.BaseService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class JobService implements BaseService<Job, Long> {

    private final JobRepository jobRepository;
    private final AdService adService;

    public JobService(JobRepository jobRepository, AdService adService) {
        this.jobRepository = jobRepository;
        this.adService = adService;
    }

    @Override
    public Job create(Job job) {
        return (Job) adService.create(job);
    }

    @Override
    public Job update(Job job) {
        return null;
    }

    @Override
    public void delete(Job job) {

    }

    @Override
    public Page<Job> findAll(Job job, Pageable pageable) {
        return null;
    }

    @Override
    public Optional<Job> findById(Long aLong) {
        return Optional.empty();
    }
}
