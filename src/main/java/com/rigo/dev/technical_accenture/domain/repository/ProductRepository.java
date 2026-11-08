package com.rigo.dev.technical_accenture.domain.repository;

import com.rigo.dev.technical_accenture.domain.entities.ProductEntity;
import org.springframework.data.r2dbc.repository.Query;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;

import java.util.UUID;

@Repository
public interface ProductRepository extends ReactiveCrudRepository<ProductEntity, UUID> {

    // Consulta para el producto con más stock por sucursal en PostgreSQL:
    @Query("SELECT DISTINCT ON (p.branch_id) p.* " +
            "FROM products p " +
            "INNER JOIN branches b ON p.branch_id = b.id " +
            "WHERE b.franchise_id = :franchiseId " +
            "ORDER BY p.branch_id, p.stock DESC")
    Flux<ProductEntity> findTopStockProductsByFranchiseId(UUID franchiseId);
}
