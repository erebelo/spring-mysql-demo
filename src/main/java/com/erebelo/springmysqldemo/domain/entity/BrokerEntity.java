package com.erebelo.springmysqldemo.domain.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

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
    @OneToOne
    @JoinColumn(name = "address_id")
    private AddressEntity address;

    /*
     Since BrokerEntity is the owner from the @ManyToOne relationship, it needs to specify the @JoinColumn
     It can be unidirectional or bidirectional if the latter is specified in BrokerTypeEntity
     */
    @ManyToOne
    @JoinColumn(name = "broker_type_id")
    private BrokerTypeEntity brokerType;

    /*
     Since BrokerEntity is the owner from the @ManyToMany relationship, it needs to specify the @JoinTable
     */
    @ManyToMany
    @JoinTable(name = "broker_advisor", joinColumns = @JoinColumn(name = "broker_id"), inverseJoinColumns = @JoinColumn(name = "advisor_id"))
    private List<AdvisorEntity> advisors;

}
