package com.rigo.dev.technical_accenture.infrastructure.service;

import com.rigo.dev.technical_accenture.api.dto.request.FranchiseRequest;
import com.rigo.dev.technical_accenture.api.dto.response.FranchiseResponse;
import com.rigo.dev.technical_accenture.api.exeptions.BadRequestException;
import com.rigo.dev.technical_accenture.domain.entities.FranchiseEntity;
import com.rigo.dev.technical_accenture.domain.repository.FranchiseRepository;
import com.rigo.dev.technical_accenture.infrastructure.abstract_services.IFranchiseService;
import com.rigo.dev.technical_accenture.infrastructure.mappers.FranchiseMapper;
import com.rigo.dev.technical_accenture.utils.enums.ErrorMessages;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.UUID;

@Service
@AllArgsConstructor
public class FranchiseService implements IFranchiseService {

    @Autowired
    private FranchiseRepository franchiseRepository;

    @Autowired
    private FranchiseMapper  franchiseMapper;

    @Override
    public Flux<FranchiseResponse> getAll() {

        return this.franchiseRepository.findAll()
                .map(this.franchiseMapper::toResponse);
    }

    @Override
    public Mono<FranchiseResponse> create(FranchiseRequest request) throws BadRequestException {

        FranchiseEntity franchiseEntity = franchiseMapper.toEntity(request);

        return franchiseRepository.save(franchiseEntity).map(franchiseMapper::toResponse);
    }

    @Override
    public Mono<FranchiseResponse> update(UUID id, FranchiseRequest request) throws BadRequestException {

        return this.franchiseRepository.findById(id)
                .switchIfEmpty(Mono.error(new BadRequestException(ErrorMessages.IdNotFound("Franchise"))))
                .flatMap(existingFranchise -> {
                    existingFranchise.setName(request.getName());
                    return this.franchiseRepository.save(existingFranchise);
                })
                .map(this.franchiseMapper::toResponse);
    }
}
