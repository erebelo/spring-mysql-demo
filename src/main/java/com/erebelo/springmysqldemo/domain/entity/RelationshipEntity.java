package com.erebelo.springmysqldemo.domain.entity;

import jakarta.persistence.MappedSuperclass;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import java.time.LocalDate;

@Getter
@Setter
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
