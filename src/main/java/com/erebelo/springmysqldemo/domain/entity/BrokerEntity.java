package com.erebelo.springmysqldemo.domain.entity;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashSet;
import java.util.Set;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "broker")
public class BrokerEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String description;

    /*
     Since BrokerEntity is the owner from the @OneToOne relationship, it needs to specify the @JoinColumn
     It can be unidirectional or bidirectional if the latter is specified in AddressEntity
     */
    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "address_id")
    private AddressEntity address;

    /*
     Since BrokerEntity is the owner from the @ManyToOne relationship, it needs to specify the @JoinColumn
     It can be unidirectional or bidirectional if the latter is specified in BrokerTypeEntity
     */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "broker_type_id")
    private BrokerTypeEntity brokerType;

    /*
     @ManyToMany relationship between BrokerEntity and AdvisorEntity
     */
    @OneToMany(mappedBy = "broker", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<BrokerAdvisorEntity> brokerAdvisors = new HashSet<>();

    /*
     Self-referential ManyToMany relationship
     */
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "broker_broker",
            joinColumns = @JoinColumn(name = "broker_id"),
            inverseJoinColumns = @JoinColumn(name = "associated_broker_id"))
    private Set<BrokerEntity> associatedBrokers = new HashSet<>();

    /*
     Reverse side of the self-referential ManyToMany relationship to ensure bidirectionality
     */
    @ManyToMany(mappedBy = "associatedBrokers")
    private Set<BrokerEntity> brokersAssociatedWithMe = new HashSet<>();

}
