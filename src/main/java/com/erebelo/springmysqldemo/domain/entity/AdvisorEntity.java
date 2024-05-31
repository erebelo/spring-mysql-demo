package com.erebelo.springmysqldemo.domain.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
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
@Entity
@Table(name = "advisor", uniqueConstraints = {@UniqueConstraint(columnNames = "name")})
public class AdvisorEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "name is mandatory")
    @Size(min = 2, max = 30, message = "name must be between 2 to 30 characters")
    @Column(name = "name", unique = true)
    private String name;

    // Active, Pending, Terminated
    private String status;

    /*
     @ManyToMany relationship between AdvisorEntity and BrokerEntity
     */
    @Builder.Default // Initialize collections
    @OneToMany(mappedBy = "advisor")
    private Set<BrokerAdvisorEntity> advisorBrokers = new HashSet<>();

}
