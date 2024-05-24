package com.erebelo.springmysqldemo.domain.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "broker_type")
public class BrokerTypeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    /*
     Since BrokerTypeEntity is not the owner of the @OneToMany relationship, there is no need to specify the association below unless it is
     bidirectional
     */
    @OneToMany(mappedBy = "brokerType")
    private List<BrokerEntity> brokers;

}
