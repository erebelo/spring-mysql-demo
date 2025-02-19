package com.erebelo.springmysqldemo.controller;

import static com.erebelo.springmysqldemo.constant.BusinessConstant.BROKERS_PATH;

import com.erebelo.springmysqldemo.domain.request.BrokerRequest;
import com.erebelo.springmysqldemo.domain.response.broker.BrokerResponse;
import com.erebelo.springmysqldemo.service.BrokerService;
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
@RequestMapping(BROKERS_PATH)
@RequiredArgsConstructor
@Tag(name = "Brokers API")
public class BrokerController {

    private final BrokerService service;

    @Operation(summary = "GET Brokers")
    @ResponseStatus(HttpStatus.OK)
    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public List<BrokerResponse> findAll() {
        log.info("GET {}", BROKERS_PATH);
        return service.findAll();
    }

    @Operation(summary = "GET Broker by Id")
    @ResponseStatus(HttpStatus.OK)
    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public BrokerResponse findById(@PathVariable Long id) {
        log.info("GET {}/{}", BROKERS_PATH, id);
        return service.findById(id);
    }

    @Operation(summary = "POST Brokers")
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public BrokerResponse insert(@Valid @RequestBody BrokerRequest request, HttpServletResponse httpServletResponse) {
        log.info("POST {}", BROKERS_PATH);
        BrokerResponse response = service.insert(request);
        httpServletResponse.setHeader(HttpHeaders.LOCATION, ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}").buildAndExpand(response.getId()).toUri().toString());
        return response;
    }

    @Operation(summary = "PUT Brokers")
    @ResponseStatus(HttpStatus.OK)
    @PutMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public BrokerResponse update(@PathVariable Long id, @Valid @RequestBody BrokerRequest request) {
        log.info("PUT {}/{}", BROKERS_PATH, id);
        return service.update(id, request);
    }

    @Operation(summary = "DELETE Brokers")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public void delete(@PathVariable Long id) {
        log.info("DELETE {}/{}", BROKERS_PATH, id);
        service.delete(id);
    }
}
