package com.erebelo.springmysqldemo.controller;

import com.erebelo.springmysqldemo.domain.request.AdvisorRequest;
import com.erebelo.springmysqldemo.domain.response.advisor.AdvisorResponse;
import com.erebelo.springmysqldemo.service.AdvisorService;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
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

import java.util.List;

import static com.erebelo.springmysqldemo.constant.BusinessConstant.ADVISORS_PATH;

@Slf4j
@RestController
@RequestMapping(ADVISORS_PATH)
@RequiredArgsConstructor
@Tag(name = "Advisors API")
public class AdvisorController {

    private final AdvisorService service;

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<AdvisorResponse>> findAll() {
        log.info("Getting all advisors");
        return ResponseEntity.ok(service.findAll());
    }

    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<AdvisorResponse> findById(@PathVariable Long id) {
        log.info("Getting advisor by id: {}", id);
        return ResponseEntity.ok(service.findById(id));
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<AdvisorResponse> insert(@Valid @RequestBody AdvisorRequest request) {
        log.info("Inserting advisor: {}", request);
        var response = service.insert(request);
        var uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(response.getId())
                .toUri();
        return ResponseEntity.created(uri).body(response);
    }

    @PutMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE, produces =
            MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<AdvisorResponse> update(@PathVariable Long id, @Valid @RequestBody AdvisorRequest request) {
        log.info("Updating advisor by id: {} {}", id, request);
        return ResponseEntity.ok(service.update(id, request));
    }

    @DeleteMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        log.info("Deleting advisor by id: {}", id);
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}
