package com.security.security.Controller;

import com.security.security.Entity.LinkedInEntity;
import com.security.security.Entity.Role;
import com.security.security.JWT.JWTService;
import com.security.security.Service.LinkedInService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/linkedIn")
public class LinkedInController {

    //Step1-> autowired service class
    private final LinkedInService linkedInService;
    public LinkedInController(LinkedInService linkedInService){
        this.linkedInService=linkedInService;
    }

    //autowired JWTSERVICE
    @Autowired
    private JWTService jwtService;

    @PostMapping("/login")
    public String login() {
        return jwtService.generateToken("Abhishek", Role.ADMIN);
    }


    //Step2-> perform REST APIs
    @PostMapping("/addJob")
     public LinkedInEntity addJob(@RequestBody LinkedInEntity linkedInEntity){
        System.out.println("As per your request I have added your Job Id and other details.");
        return linkedInService.saveJob(linkedInEntity);
    }

    @GetMapping("/findAll")
    public List<LinkedInEntity> getJobId(){
        System.out.println("As per your request here is your all jobs. I hope you find your dream job here!");
        return linkedInService.getAll();
    }

    @GetMapping("/findById/{jobId}")
    public Optional<LinkedInEntity> getJobById(@PathVariable Long jobId){
        return linkedInService.getJobById(jobId);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/delete/{id}")
    public String delete(@PathVariable Long jobId){
        return "As per your request we deleted your job id from your website." + jobId;
    }


}
