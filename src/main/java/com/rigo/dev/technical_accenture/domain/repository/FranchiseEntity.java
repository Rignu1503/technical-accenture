package com.rigo.dev.technical_accenture.domain.repository;

import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface FranchiseEntity extends ReactiveCrudRepository<FranchiseEntity, UUID> {
}
