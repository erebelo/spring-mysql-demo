package com.erebelo.springmysqldemo.repository;

import com.erebelo.springmysqldemo.domain.entity.BrokerTypeEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BrokerTypeRepository extends JpaRepository<BrokerTypeEntity, Long> {

}
