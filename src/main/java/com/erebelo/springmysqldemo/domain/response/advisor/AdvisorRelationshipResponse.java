package com.erebelo.springmysqldemo.domain.response.advisor;

import com.erebelo.springmysqldemo.domain.response.broker.BrokerLazyResponse;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import java.time.LocalDate;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class AdvisorRelationshipResponse {

    private Long id;
    private String status;
    private LocalDate startDate;
    private LocalDate endDate;
    private BrokerLazyResponse broker;

}
