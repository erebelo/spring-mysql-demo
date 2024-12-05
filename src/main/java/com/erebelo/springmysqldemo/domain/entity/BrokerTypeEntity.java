package com.erebelo.springmysqldemo.domain.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
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
@Entity
@Table(name = "broker_type", uniqueConstraints = {@UniqueConstraint(columnNames = "name")})
public class BrokerTypeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "name is mandatory")
    @Size(min = 2, max = 30, message = "name must be between 2 to 30 characters")
    private String name;

    /*
     * Since BrokerTypeEntity is not the owner of the @OneToMany relationship, there
     * is no need to specify the association below unless it is bidirectional, as is
     * this case
     */
    @Builder.Default // Initialize collections
    @OneToMany(mappedBy = "brokerType", fetch = FetchType.LAZY)
    private Set<BrokerEntity> brokers = new HashSet<>();

    @Override
    public String toString() {
        return "BrokerTypeEntity{" + "id=" + id + ", name='" + name + '\'' + ", brokers="
                + brokers.stream().map(broker -> String.format("BrokerEntity{id=%d, name='%s', description='%s'}",
                        broker.getId(), broker.getName(), broker.getDescription())).toList()
                + '}';
    }
}
