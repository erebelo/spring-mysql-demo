package com.erebelo.springmysqldemo.domain.response.brokertype;

import com.erebelo.springmysqldemo.domain.response.broker.BrokerLazyResponse;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import java.util.List;
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
public class BrokerTypeResponse {

    private Long id;
    private String name;
    private List<BrokerLazyResponse> brokers;

}
