package com.erebelo.springmysqldemo.repository;

import com.erebelo.springmysqldemo.domain.entity.AdvisorEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AdvisorRepository extends JpaRepository<AdvisorEntity, Long> {

}
