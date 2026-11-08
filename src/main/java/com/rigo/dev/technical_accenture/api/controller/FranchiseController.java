package com.rigo.dev.technical_accenture.api.controller;

import com.rigo.dev.technical_accenture.api.dto.request.FranchiseRequest;
import com.rigo.dev.technical_accenture.api.dto.response.FranchiseResponse;
import com.rigo.dev.technical_accenture.infrastructure.abstract_services.IFranchiseService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.UUID;

@RestController
@RequestMapping("/franchises")
@AllArgsConstructor
public class FranchiseController {

    private final IFranchiseService franchiseService;

    @GetMapping
    public Flux<FranchiseResponse> findAll() {
        return this.franchiseService.getAll();
    }

    @PostMapping
    public Mono<ResponseEntity<FranchiseResponse>> create(
            @Valid @RequestBody FranchiseRequest request) throws RuntimeException {

        return this.franchiseService.create(request).map(ResponseEntity::ok);
    }

    @PutMapping("/{id}")
    public Mono<ResponseEntity<FranchiseResponse>> update(
            @PathVariable UUID id,
            @Valid @RequestBody FranchiseRequest request) {

        return this.franchiseService.update(id, request)
                .map(ResponseEntity::ok);
    }
}
