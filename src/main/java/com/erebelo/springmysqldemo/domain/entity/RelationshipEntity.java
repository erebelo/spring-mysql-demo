package com.erebelo.springmysqldemo.domain.entity;

import jakarta.persistence.MappedSuperclass;
import java.time.LocalDate;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@Getter
@Setter
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
