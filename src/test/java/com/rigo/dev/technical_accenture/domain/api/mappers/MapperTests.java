package com.rigo.dev.technical_accenture.domain.api.mappers;

import com.rigo.dev.technical_accenture.domain.api.dtos.request.BranchRequest;
import com.rigo.dev.technical_accenture.domain.api.dtos.request.FranchiseRequest;
import com.rigo.dev.technical_accenture.domain.api.dtos.request.ProductRequest;
import com.rigo.dev.technical_accenture.domain.api.dtos.request.ProductStockUpdateRequest;
import com.rigo.dev.technical_accenture.domain.api.dtos.response.BranchResponse;
import com.rigo.dev.technical_accenture.domain.api.dtos.response.FranchiseResponse;
import com.rigo.dev.technical_accenture.domain.api.dtos.response.ProductResponse;
import com.rigo.dev.technical_accenture.domain.api.dtos.response.ProductTopStockResponse;
import com.rigo.dev.technical_accenture.domain.entities.BranchEntity;
import com.rigo.dev.technical_accenture.domain.entities.FranchiseEntity;
import com.rigo.dev.technical_accenture.domain.entities.ProductEntity;
import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

class MapperTests {

    private final FranchiseMapper franchiseMapper = Mappers.getMapper(FranchiseMapper.class);
    private final BranchMapper branchMapper = Mappers.getMapper(BranchMapper.class);
    private final ProductMapper productMapper = Mappers.getMapper(ProductMapper.class);

    @Test
    void shouldMapFranchiseRequestToEntityAndResponse() {
        FranchiseRequest request = FranchiseRequest.builder()
                .name("Franquicia Central")
                .build();

        FranchiseEntity entity = franchiseMapper.toEntity(request);
        assertNotNull(entity);
        assertEquals("Franquicia Central", entity.getName());

        UUID id = UUID.randomUUID();
        entity.setId(id);

        FranchiseResponse response = franchiseMapper.toResponse(entity);
        assertNotNull(response);
        assertEquals(id, response.getId());
        assertEquals("Franquicia Central", response.getName());
    }

    @Test
    void shouldMapBranchRequestToEntityAndResponse() {
        UUID franchiseId = UUID.randomUUID();
        BranchRequest request = BranchRequest.builder()
                .name("Sucursal Norte")
                .franchiseId(franchiseId)
                .build();

        BranchEntity entity = branchMapper.toEntity(request);
        assertNotNull(entity);
        assertEquals("Sucursal Norte", entity.getName());
        assertEquals(franchiseId, entity.getFranchiseId());

        UUID id = UUID.randomUUID();
        entity.setId(id);

        BranchResponse response = branchMapper.toResponse(entity);
        assertNotNull(response);
        assertEquals(id, response.getId());
        assertEquals("Sucursal Norte", response.getName());
        assertEquals(franchiseId, response.getFranchiseId());
    }

    @Test
    void shouldMapProductRequestToEntityAndResponse() {
        UUID branchId = UUID.randomUUID();
        ProductRequest request = ProductRequest.builder()
                .name("Laptop")
                .stock(15)
                .branchId(branchId)
                .build();

        ProductEntity entity = productMapper.toEntity(request);
        assertNotNull(entity);
        assertEquals("Laptop", entity.getName());
        assertEquals(15, entity.getStock());
        assertEquals(branchId, entity.getBranchId());

        UUID id = UUID.randomUUID();
        entity.setId(id);

        ProductResponse response = productMapper.toResponse(entity);
        assertNotNull(response);
        assertEquals(id, response.getId());
        assertEquals("Laptop", response.getName());
        assertEquals(15, response.getStock());
        assertEquals(branchId, response.getBranchId());
    }

    @Test
    void shouldUpdateProductStock() {
        ProductEntity entity = ProductEntity.builder()
                .id(UUID.randomUUID())
                .name("Laptop")
                .stock(10)
                .branchId(UUID.randomUUID())
                .build();

        ProductStockUpdateRequest updateRequest = ProductStockUpdateRequest.builder()
                .stock(25)
                .build();

        productMapper.updateStockFromRequest(updateRequest, entity);
        assertEquals(25, entity.getStock());
        assertEquals("Laptop", entity.getName());
    }

    @Test
    void shouldMapToTopStockResponse() {
        UUID franchiseId = UUID.randomUUID();
        UUID branchId = UUID.randomUUID();
        UUID productId = UUID.randomUUID();

        ProductEntity entity = ProductEntity.builder()
                .id(productId)
                .name("Monitor 4K")
                .stock(50)
                .branchId(branchId)
                .build();

        ProductTopStockResponse response = productMapper.toTopStockResponse(entity, "Sucursal Principal", franchiseId);
        assertNotNull(response);
        assertEquals(franchiseId, response.getFranchiseId());
        assertEquals(branchId, response.getBranchId());
        assertEquals("Sucursal Principal", response.getBranchName());
        assertEquals(productId, response.getProductId());
        assertEquals("Monitor 4K", response.getProductName());
        assertEquals(50, response.getStock());
    }
}
