package com.erebelo.springmysqldemo.domain.entity;

import jakarta.persistence.Entity;
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
@Table(name = "advisor", uniqueConstraints = {@UniqueConstraint(columnNames = "name")})
public class AdvisorEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "name is mandatory")
    @Size(min = 2, max = 30, message = "name must be between 2 to 30 characters")
    private String name;

    // Active, Pending, Terminated
    private String status;

    /*
     * @ManyToMany relationship between AdvisorEntity and BrokerEntity
     */
    @Builder.Default // Initialize collections
    @OneToMany(mappedBy = "advisor")
    private Set<BrokerAdvisorEntity> advisorBrokers = new HashSet<>();

    @Override
    public String toString() {
        return "AdvisorEntity{" + "id=" + id + ", name='" + name + '\'' + ", status='" + status + '\''
                + ", advisorBrokers="
                + advisorBrokers.stream()
                        .map(advisorBrokers -> String.format("{BrokerAdvisorEntity{id=%d, status='%s', "
                                + "startDate='%s', endDate='%s', BrokerEntity{id=%d, name='%s', description='%s'}}",
                                advisorBrokers.getId(), advisorBrokers.getStatus(), advisorBrokers.getStartDate(),
                                advisorBrokers.getEndDate(), advisorBrokers.getBroker().getId(),
                                advisorBrokers.getBroker().getName(), advisorBrokers.getBroker().getDescription()))
                        .toList()
                + '}';
    }
}
