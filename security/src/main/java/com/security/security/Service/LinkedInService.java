package com.security.security.Service;

import com.security.security.Entity.LinkedInEntity;
import com.security.security.Repository.LinkedInRepo;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class LinkedInService {

    //Step1-> autowired repo class
    private LinkedInRepo linkedInRepo;
    public LinkedInService(LinkedInRepo linkedInRepo){
        this.linkedInRepo=linkedInRepo;
    }
    //implements all methods
    public LinkedInEntity saveJob(LinkedInEntity linkedInEntity) {
        return linkedInRepo.save(linkedInEntity);
    }

    public List<LinkedInEntity> getAll() {
        return linkedInRepo.findAll();
    }

    public Optional<LinkedInEntity> getJobById(Long jobId) {
        return linkedInRepo.findById(jobId);
    }
}
