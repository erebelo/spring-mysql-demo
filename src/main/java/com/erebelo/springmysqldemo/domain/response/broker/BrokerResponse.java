package com.erebelo.springmysqldemo.domain.response.broker;

import com.erebelo.springmysqldemo.domain.response.address.AddressResponse;
import com.erebelo.springmysqldemo.domain.response.brokertype.BrokerTypeLazyResponse;
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
public class BrokerResponse {

    private Long id;
    private String name;
    private String description;
    private AddressResponse address;
    private BrokerTypeLazyResponse brokerType;
    private List<BrokerRelationshipResponse> relationships;
    private List<AssociatedBrokerResponse> associatedBrokers;

}
