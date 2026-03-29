package com.security.security.Repository;

import com.security.security.Entity.LinkedInEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LinkedInRepo extends JpaRepository<LinkedInEntity, Long>{
}
