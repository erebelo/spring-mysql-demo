package com.erebelo.springmysqldemo.controller;

import static com.erebelo.springmysqldemo.constant.BusinessConstant.ADVISORS_PATH;

import com.erebelo.springmysqldemo.domain.request.AdvisorRequest;
import com.erebelo.springmysqldemo.domain.response.advisor.AdvisorResponse;
import com.erebelo.springmysqldemo.service.AdvisorService;
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
@RequestMapping(ADVISORS_PATH)
@RequiredArgsConstructor
@Tag(name = "Advisors API")
public class AdvisorController {

    private final AdvisorService service;

    @Operation(summary = "GET Advisors")
    @ResponseStatus(HttpStatus.OK)
    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public List<AdvisorResponse> findAll() {
        log.info("GET {}", ADVISORS_PATH);
        return service.findAll();
    }

    @Operation(summary = "GET Advisor by Id")
    @ResponseStatus(HttpStatus.OK)
    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public AdvisorResponse findById(@PathVariable Long id) {
        log.info("GET {}/{}", ADVISORS_PATH, id);
        return service.findById(id);
    }

    @Operation(summary = "POST Advisors")
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public AdvisorResponse insert(@Valid @RequestBody AdvisorRequest request, HttpServletResponse httpServletResponse) {
        log.info("POST {}", ADVISORS_PATH);
        AdvisorResponse response = service.insert(request);
        httpServletResponse.setHeader(HttpHeaders.LOCATION, ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}").buildAndExpand(response.getId()).toUri().toString());
        return response;
    }

    @Operation(summary = "PUT Advisors")
    @ResponseStatus(HttpStatus.OK)
    @PutMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public AdvisorResponse update(@PathVariable Long id, @Valid @RequestBody AdvisorRequest request) {
        log.info("PUT {}/{}", ADVISORS_PATH, id);
        return service.update(id, request);
    }

    @Operation(summary = "DELETE Advisors")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public void delete(@PathVariable Long id) {
        log.info("DELETE {}/{}", ADVISORS_PATH, id);
        service.delete(id);
    }
}
