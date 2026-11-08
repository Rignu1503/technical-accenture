package com.rigo.dev.technical_accenture.infrastructure.service;

import com.rigo.dev.technical_accenture.api.dto.request.ProductRequest;
import com.rigo.dev.technical_accenture.api.dto.request.update.ProductNameUpdateRequest;
import com.rigo.dev.technical_accenture.api.dto.request.update.ProductStockUpdateRequest;
import com.rigo.dev.technical_accenture.api.dto.response.ProductResponse;
import com.rigo.dev.technical_accenture.api.exeptions.BadRequestException;
import com.rigo.dev.technical_accenture.domain.entities.ProductEntity;
import com.rigo.dev.technical_accenture.domain.repository.BranchRepository;
import com.rigo.dev.technical_accenture.domain.repository.FranchiseRepository;
import com.rigo.dev.technical_accenture.domain.repository.ProductRepository;
import com.rigo.dev.technical_accenture.infrastructure.abstract_services.IProductService;
import com.rigo.dev.technical_accenture.infrastructure.mappers.ProductMapper;
import com.rigo.dev.technical_accenture.utils.enums.ErrorMessages;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.UUID;

@Service
@AllArgsConstructor
public class ProductService implements IProductService {

    private final ProductRepository productRepository;
    private final BranchRepository branchRepository;
    private final ProductMapper productMapper;
    private final FranchiseRepository franchiseRepository;

    @Override
    public Flux<ProductResponse> getAll() {
        return this.productRepository.findAll().map(productMapper::toResponse);
    }

    @Override
    public Mono<ProductResponse> create(ProductRequest request) throws BadRequestException {

        return this.branchRepository.findById(request.getBranchId())
                .switchIfEmpty(Mono.error(new BadRequestException(ErrorMessages.IdNotFound("branch"))))

                .flatMap(branch -> {

                    ProductEntity entity = this.productMapper.toEntity(request);
                    return this.productRepository.save(entity);

                }).map(productMapper::toResponse);
    }

    @Override
    public Mono<ProductResponse> updateName(UUID id, ProductNameUpdateRequest request) {

        return this.productRepository.findById(id)
                .switchIfEmpty(Mono.error(new BadRequestException(ErrorMessages.IdNotFound("product"))))

                .flatMap(existingProduct -> {

                    existingProduct.setName(request.getName());
                    return this.productRepository.save(existingProduct);

                }).map(productMapper::toResponse);
    }

    @Override
    public Mono<ProductResponse> updateStock(UUID id, ProductStockUpdateRequest request) {

        return this.productRepository.findById(id)
                .switchIfEmpty(Mono.error(new BadRequestException(ErrorMessages.IdNotFound("product"))))

                .flatMap(existingProduct -> {
                    existingProduct.setStock(request.getStock());
                    return this.productRepository.save(existingProduct);

                }).map(productMapper::toResponse);
    }

    @Override
    public Flux<ProductResponse> getTopStockByFranchise(UUID id) {

        return this.franchiseRepository.findById(id)
                .switchIfEmpty(Mono.error(new BadRequestException(ErrorMessages.IdNotFound("franchise"))))

                .flatMapMany(franchise ->
                        this.productRepository.findTopStockProductsByFranchiseId(id)
                ).map(this.productMapper::toResponse);
    }


    @Override
    public Mono<Void> delete(UUID id) throws RuntimeException {

        return this.productRepository.findById(id)
                .switchIfEmpty(Mono.error(new BadRequestException(ErrorMessages.IdNotFound("product"))))

                .flatMap(this.productRepository::delete);


    }

}
