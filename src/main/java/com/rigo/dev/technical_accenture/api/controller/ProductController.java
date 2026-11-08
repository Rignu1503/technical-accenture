package com.rigo.dev.technical_accenture.api.controller;


import com.rigo.dev.technical_accenture.api.dto.request.ProductRequest;
import com.rigo.dev.technical_accenture.api.dto.request.update.ProductNameUpdateRequest;
import com.rigo.dev.technical_accenture.api.dto.request.update.ProductStockUpdateRequest;
import com.rigo.dev.technical_accenture.api.dto.response.ProductResponse;
import com.rigo.dev.technical_accenture.infrastructure.abstract_services.IProductService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.UUID;

@RestController
@RequestMapping("/products")
@AllArgsConstructor
public class ProductController {

    private final IProductService productService;

    @GetMapping
    public Flux<ProductResponse> findAll() {
        return this.productService.getAll();
    }

    @GetMapping("/top-stock/franchise/{id}")
    public Flux<ProductResponse> getTopStockByFranchise(
            @PathVariable UUID id) {

        return this.productService.getTopStockByFranchise(id);
    }

    @PostMapping
    public Mono<ResponseEntity<ProductResponse>> Create(
            @Valid @RequestBody ProductRequest request){

        return this.productService.create(request).map(ResponseEntity::ok);
    }

    @PutMapping("/{id}/name")
    public Mono<ResponseEntity<ProductResponse>> updateName(
            @PathVariable UUID id,
            @Valid @RequestBody ProductNameUpdateRequest request) {

        return this.productService.updateName(id, request)
                .map(ResponseEntity::ok);
    }

    @PutMapping("/{id}/stock")
    public Mono<ResponseEntity<ProductResponse>> updateStock(
            @PathVariable UUID id,
            @Valid @RequestBody ProductStockUpdateRequest request){

        return this.productService.updateStock(id, request)
                .map(ResponseEntity::ok);
    }

    @DeleteMapping("/{id}")
    public Mono<ResponseEntity<Void>> delete(@PathVariable UUID id) {
        return this.productService.delete(id)
                .then(Mono.just(ResponseEntity.noContent().build()));
    }


}
