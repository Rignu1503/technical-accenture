package com.rigo.dev.technical_accenture.infrastructure.abstract_services;

import com.rigo.dev.technical_accenture.api.dto.request.ProductRequest;
import com.rigo.dev.technical_accenture.api.dto.request.update.ProductNameUpdateRequest;
import com.rigo.dev.technical_accenture.api.dto.request.update.ProductStockUpdateRequest;
import com.rigo.dev.technical_accenture.api.dto.response.ProductResponse;
import com.rigo.dev.technical_accenture.infrastructure.abstract_services.generic.CreateService;
import com.rigo.dev.technical_accenture.infrastructure.abstract_services.generic.DeleteService;
import com.rigo.dev.technical_accenture.infrastructure.abstract_services.generic.ReadAllService;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.UUID;

public interface IProductService extends
        ReadAllService<ProductResponse>,
        CreateService<ProductRequest, ProductResponse>,
        DeleteService<UUID>
{

    Mono<ProductResponse> updateName(UUID id, ProductNameUpdateRequest request);
    Mono<ProductResponse> updateStock(UUID id, ProductStockUpdateRequest request);
    Flux<ProductResponse> getTopStockByFranchise(UUID id);
}
