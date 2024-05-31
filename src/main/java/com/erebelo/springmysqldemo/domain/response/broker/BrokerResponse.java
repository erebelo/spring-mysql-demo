package com.erebelo.springmysqldemo.domain.response.broker;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@Getter
@Setter
@ToString
@Builder
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class BrokerResponse {

    private Long id;
    private String name;
    private String description;
    private BrokerAddressResponse address;
    private BrokerTypeResponse brokerType;
    private List<BrokerRelationshipResponse> relationships;
    private List<AssociatedBrokerResponse> associatedBrokers;

}
