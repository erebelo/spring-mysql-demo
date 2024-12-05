package com.erebelo.springmysqldemo.domain.entity;

import jakarta.persistence.MappedSuperclass;
import java.time.LocalDate;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.SuperBuilder;

/**
 * Avoid using `@Data` as it generates `toString()`, `equals()`, and
 * `hashCode()` methods that can cause circular references and lead to
 * `StackOverflowError`.
 */
@Getter
@Setter
@ToString
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
@MappedSuperclass
public class RelationshipEntity {

    // Active, Pending, Terminated
    private String status;
    private LocalDate startDate;
    private LocalDate endDate;

}
