package com.erebelo.springmysqldemo.controller;

import static com.erebelo.springmysqldemo.constant.BusinessConstant.BROKER_TYPES_PATH;

import com.erebelo.springmysqldemo.domain.request.BrokerTypeRequest;
import com.erebelo.springmysqldemo.domain.response.brokertype.BrokerTypeResponse;
import com.erebelo.springmysqldemo.service.BrokerTypeService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
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

@Slf4j
@RestController
@RequestMapping(BROKER_TYPES_PATH)
@RequiredArgsConstructor
@Tag(name = "Broker Types API")
public class BrokerTypeController {

    private final BrokerTypeService service;

    @Operation(summary = "GET Broker Types")
    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<BrokerTypeResponse>> findAll() {
        log.info("GET {}", BROKER_TYPES_PATH);
        return ResponseEntity.ok(service.findAll());
    }

    @Operation(summary = "GET Broker Type by Id")
    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<BrokerTypeResponse> findById(@PathVariable Long id) {
        log.info("GET {}/{}", BROKER_TYPES_PATH, id);
        return ResponseEntity.ok(service.findById(id));
    }

    @Operation(summary = "POST Broker Types")
    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<BrokerTypeResponse> insert(@Valid @RequestBody BrokerTypeRequest request) {
        log.info("POST {}", BROKER_TYPES_PATH);
        var response = service.insert(request);

        return ResponseEntity.created(
                ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(response.getId()).toUri())
                .body(response);
    }

    @Operation(summary = "PUT Broker Types")
    @PutMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<BrokerTypeResponse> update(@PathVariable Long id,
            @Valid @RequestBody BrokerTypeRequest request) {
        log.info("PUT {}/{}", BROKER_TYPES_PATH, id);
        return ResponseEntity.ok(service.update(id, request));
    }

    @Operation(summary = "DELETE Broker Types")
    @DeleteMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        log.info("DELETE {}/{}", BROKER_TYPES_PATH, id);
        service.delete(id);

        return ResponseEntity.noContent().build();
    }
}
