package com.erebelo.springmysqldemo.domain.response.broker;

import com.erebelo.springmysqldemo.domain.response.advisor.AdvisorLazyResponse;
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
public class BrokerRelationshipResponse {

    private Long id;
    private String status;
    private LocalDate startDate;
    private LocalDate endDate;
    private AdvisorLazyResponse advisor;

}
