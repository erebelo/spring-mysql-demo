package com.erebelo.springmysqldemo.repository;

import com.erebelo.springmysqldemo.domain.entity.BrokerEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BrokerRepository extends JpaRepository<BrokerEntity, Long> {

}
