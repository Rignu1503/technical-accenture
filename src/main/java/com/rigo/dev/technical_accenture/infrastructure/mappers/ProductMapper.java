package com.rigo.dev.technical_accenture.infrastructure.mappers;

import com.rigo.dev.technical_accenture.api.dto.request.ProductRequest;
import com.rigo.dev.technical_accenture.api.dto.response.ProductResponse;
import com.rigo.dev.technical_accenture.domain.entities.ProductEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface ProductMapper {

    @Mapping(target = "id", ignore = true)
    ProductEntity toEntity(ProductRequest request);

    ProductResponse toResponse(ProductEntity entity);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "branchId", ignore = true)
    void updateProduct(ProductRequest request, @MappingTarget ProductEntity entity);
}
