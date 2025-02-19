package com.erebelo.springmysqldemo.controller;

import static com.erebelo.springmysqldemo.constant.BusinessConstant.BROKER_TYPES_PATH;

import com.erebelo.springmysqldemo.domain.request.BrokerTypeRequest;
import com.erebelo.springmysqldemo.domain.response.brokertype.BrokerTypeResponse;
import com.erebelo.springmysqldemo.service.BrokerTypeService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

@Slf4j
@RestController
@RequestMapping(BROKER_TYPES_PATH)
@RequiredArgsConstructor
@Tag(name = "Broker Types API")
public class BrokerTypeController {

    private final BrokerTypeService service;

    @Operation(summary = "GET Broker Types")
    @ResponseStatus(HttpStatus.OK)
    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public List<BrokerTypeResponse> findAll() {
        log.info("GET {}", BROKER_TYPES_PATH);
        return service.findAll();
    }

    @Operation(summary = "GET Broker Type by Id")
    @ResponseStatus(HttpStatus.OK)
    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public BrokerTypeResponse findById(@PathVariable Long id) {
        log.info("GET {}/{}", BROKER_TYPES_PATH, id);
        return service.findById(id);
    }

    @Operation(summary = "POST Broker Types")
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public BrokerTypeResponse insert(@Valid @RequestBody BrokerTypeRequest request,
            HttpServletResponse httpServletResponse) {
        log.info("POST {}", BROKER_TYPES_PATH);
        BrokerTypeResponse response = service.insert(request);
        httpServletResponse.setHeader(HttpHeaders.LOCATION, ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}").buildAndExpand(response.getId()).toUri().toString());
        return response;
    }

    @Operation(summary = "PUT Broker Types")
    @ResponseStatus(HttpStatus.OK)
    @PutMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public BrokerTypeResponse update(@PathVariable Long id, @Valid @RequestBody BrokerTypeRequest request) {
        log.info("PUT {}/{}", BROKER_TYPES_PATH, id);
        return service.update(id, request);
    }

    @Operation(summary = "DELETE Broker Types")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public void delete(@PathVariable Long id) {
        log.info("DELETE {}/{}", BROKER_TYPES_PATH, id);
        service.delete(id);
    }
}
