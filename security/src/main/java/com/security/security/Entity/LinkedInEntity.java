package com.security.security.Entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class LinkedInEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long jobId;
    @Column(unique = true, nullable = false)
    private String jobName;
    private String companyName;
    private String location;
    @Enumerated(EnumType.STRING)
    private Role role;

//    public LinkedInEntity(Long jobId, String javaDeveloper, String google, String pune) {
//    }

    //  public LinkedInEntity(String string, String javaDeveloper, String google, String pune) {
   // }
}
