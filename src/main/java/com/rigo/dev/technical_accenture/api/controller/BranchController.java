package com.rigo.dev.technical_accenture.api.controller;

import com.rigo.dev.technical_accenture.api.dto.request.BranchRequest;
import com.rigo.dev.technical_accenture.api.dto.response.BranchResponse;
import com.rigo.dev.technical_accenture.infrastructure.abstract_services.IBranchService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.UUID;

@RestController
@RequestMapping("/branches")
@AllArgsConstructor
public class BranchController {

    private final IBranchService branchService;

    @GetMapping
    public Flux<BranchResponse> findAll() {
        return this.branchService.getAll();
    }

    @GetMapping("/franchise/{franchiseId}")
    public Flux<BranchResponse> findByFranchise(@PathVariable UUID franchiseId) {
        return this.branchService.getByFranchiseId(franchiseId);
    }

    @PostMapping
    public Mono<ResponseEntity<BranchResponse>> Create(
            @Validated @RequestBody BranchRequest request) {

        return this.branchService.create(request).map(ResponseEntity::ok);
    }

    @PutMapping("/{id}")
    public Mono<ResponseEntity<BranchResponse>> Update(
            @PathVariable UUID id, @Validated @RequestBody BranchRequest request){

        return this.branchService.update(id, request).map(ResponseEntity::ok);
    }
}
