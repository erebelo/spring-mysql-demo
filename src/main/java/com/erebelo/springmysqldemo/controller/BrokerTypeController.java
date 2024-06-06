package com.erebelo.springmysqldemo.controller;

import com.erebelo.springmysqldemo.domain.request.BrokerTypeRequest;
import com.erebelo.springmysqldemo.domain.response.brokertype.BrokerTypeResponse;
import com.erebelo.springmysqldemo.service.BrokerTypeService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.util.List;

@RestController
@RequestMapping("broker-type")
@RequiredArgsConstructor
public class BrokerTypeController {

    private final BrokerTypeService service;

    private static final Logger LOGGER = LoggerFactory.getLogger(BrokerTypeController.class);

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<BrokerTypeResponse>> findAll() {
        LOGGER.info("Getting all broker types");
        return ResponseEntity.ok(service.findAll());
    }

    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<BrokerTypeResponse> findById(@PathVariable Long id) {
        LOGGER.info("Getting broker type by id: {}", id);
        return ResponseEntity.ok(service.findById(id));
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<BrokerTypeResponse> insert(@Valid @RequestBody BrokerTypeRequest request) {
        LOGGER.info("Inserting broker type: {}", request);
        var response = service.insert(request);
        var uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(response.getId()).toUri();
        return ResponseEntity.created(uri).body(response);
    }

    @PutMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<BrokerTypeResponse> update(@PathVariable Long id, @Valid @RequestBody BrokerTypeRequest request) {
        LOGGER.info("Updating broker type by id: {} {}", id, request);
        return ResponseEntity.ok(service.update(id, request));
    }

    @DeleteMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        LOGGER.info("Deleting broker type by id: {}", id);
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}
