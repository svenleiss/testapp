package com.mhp.insideApp.persistence.insideApplications.repository;

import com.mhp.insideApp.persistence.insideApplications.entity.GreetingEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GreetingJpaRepository extends JpaRepository<GreetingEntity, Long> {


}
