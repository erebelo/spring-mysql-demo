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
import java.util.HashSet;
import java.util.Set;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Avoid using `@Data` as it generates `toString()`, `equals()`, and
 * `hashCode()` methods that can cause circular references and lead to
 * `StackOverflowError`.
 */
@Getter
@Setter
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
     * Since BrokerEntity is the owner from the @OneToOne relationship, it needs to
     * specify the @JoinColumn It can be unidirectional or bidirectional if the
     * latter is specified in AddressEntity
     */
    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "address_id")
    private AddressEntity address;

    /*
     * Since BrokerEntity is the owner from the @ManyToOne relationship, it needs to
     * specify the @JoinColumn It can be unidirectional or bidirectional if the
     * latter is specified in BrokerTypeEntity
     */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "broker_type_id")
    private BrokerTypeEntity brokerType;

    /*
     * @ManyToMany relationship between BrokerEntity and AdvisorEntity
     */
    @Builder.Default // Initialize collections
    @OneToMany(mappedBy = "broker", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<BrokerAdvisorEntity> brokerAdvisors = new HashSet<>();

    /*
     * Self-referential ManyToMany relationship
     */
    @Builder.Default // Initialize collections
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "broker_broker", joinColumns = @JoinColumn(name = "broker_id"), inverseJoinColumns = @JoinColumn(name = "associated_broker_id"))
    private Set<BrokerEntity> associatedBrokers = new HashSet<>();

    /*
     * Reverse side of the self-referential ManyToMany relationship to ensure
     * bidirectionality
     */
    @Builder.Default // Initialize collections
    @ManyToMany(mappedBy = "associatedBrokers", fetch = FetchType.LAZY)
    private Set<BrokerEntity> brokersAssociatedWithMe = new HashSet<>();

    @Override
    public String toString() {
        return "BrokerEntity{" + "id=" + id + ", name='" + name + '\'' + ", description='" + description + '\''
                + ", address=" + address + ", brokerType="
                + String.format("BrokerTypeEntity{id=%d, name='%s'}", brokerType.getId(), brokerType.getName())
                + ", brokerAdvisors="
                + brokerAdvisors.stream()
                        .map(brokerAdvisor -> String.format(
                                "{BrokerAdvisorEntity{id=%d, status='%s', "
                                        + "startDate='%s', endDate='%s', AdvisorEntity{id=%d, name='%s', status='%s'}}",
                                brokerAdvisor.getId(), brokerAdvisor.getStatus(), brokerAdvisor.getStartDate(),
                                brokerAdvisor.getEndDate(), brokerAdvisor.getAdvisor().getId(),
                                brokerAdvisor.getAdvisor().getName(), brokerAdvisor.getAdvisor().getStatus()))
                        .toList()
                + ", associatedBrokers="
                + associatedBrokers.stream()
                        .map(broker -> String.format("{BrokerEntity{id=%d, name='%s', description='%s'}}",
                                broker.getId(), broker.getName(), broker.getDescription()))
                        .toList()
                + ", brokersAssociatedWithMe="
                + brokersAssociatedWithMe.stream()
                        .map(broker -> String.format("{BrokerEntity{id=%d, name='%s', description='%s'}}",
                                broker.getId(), broker.getName(), broker.getDescription()))
                        .toList()
                + '}';
    }
}
