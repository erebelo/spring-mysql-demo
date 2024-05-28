package com.erebelo.springmysqldemo.domain.entity;

import jakarta.persistence.MappedSuperclass;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.time.LocalDate;

@Data
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
@MappedSuperclass
public class RelationshipEntity {

    // Active, Pending, Terminated
    private String relationshipStatus;
    private LocalDate relationshipStartDate;
    private LocalDate relationshipEndDate;

}
