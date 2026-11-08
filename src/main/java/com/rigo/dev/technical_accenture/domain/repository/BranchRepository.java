package com.rigo.dev.technical_accenture.domain.repository;

import com.rigo.dev.technical_accenture.domain.entities.BranchEntity;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface BranchRepository extends ReactiveCrudRepository<BranchEntity, UUID> {
}
