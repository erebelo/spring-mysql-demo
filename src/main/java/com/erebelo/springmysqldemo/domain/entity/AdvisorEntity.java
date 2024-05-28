package com.erebelo.springmysqldemo.domain.entity;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "advisor")
public class AdvisorEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String status;

    /*
     @ManyToMany relationship between AdvisorEntity and BrokerEntity
     */
    @OneToMany(mappedBy = "advisor", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<BrokerAdvisorEntity> advisorBrokers = new HashSet<>();

}
