package com.rigo.dev.technical_accenture.infrastructure.service;

import com.rigo.dev.technical_accenture.api.dto.request.BranchRequest;
import com.rigo.dev.technical_accenture.api.dto.response.BranchResponse;
import com.rigo.dev.technical_accenture.api.exeptions.BadRequestException;
import com.rigo.dev.technical_accenture.domain.entities.BranchEntity;
import com.rigo.dev.technical_accenture.domain.repository.BranchRepository;
import com.rigo.dev.technical_accenture.domain.repository.FranchiseRepository;
import com.rigo.dev.technical_accenture.infrastructure.abstract_services.IBranchService;
import com.rigo.dev.technical_accenture.infrastructure.mappers.BranchMapper;
import com.rigo.dev.technical_accenture.utils.enums.ErrorMessages;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.UUID;

@Service
@AllArgsConstructor
public class BranchService implements IBranchService {

    private final BranchRepository branchRepository;
    private final FranchiseRepository franchiseRepository;
    private final BranchMapper branchMapper;

    @Override
    public Flux<BranchResponse> getAll() {
        return this.branchRepository.findAll().map(this.branchMapper::toResponse);
    }


    @Override
    public Mono<BranchResponse> create(BranchRequest request) throws BadRequestException {

        return this.franchiseRepository.findById(request.getFranchiseId())

                .switchIfEmpty(Mono.error(new BadRequestException(ErrorMessages.IdNotFound("branch"))))

                .flatMap(franchise -> {
                    BranchEntity entity = this.branchMapper.toEntity(request);

                    return this.branchRepository.save(entity);
                })
                .map(this.branchMapper::toResponse);

    }

    @Override
    public Mono<BranchResponse> update(UUID id, BranchRequest request) throws BadRequestException {

        return this.branchRepository.findById(id)
                .switchIfEmpty(Mono.error(new BadRequestException(ErrorMessages.IdNotFound("Brach"))))

                .flatMap(existingBranch -> {
                    this.branchMapper.updateBrach(request, existingBranch);
                    return this.branchRepository.save(existingBranch);
                })
                .map(this.branchMapper::toResponse);

    }

    @Override
    public Flux<BranchResponse> getByFranchiseId(UUID id) {
        return this.branchRepository.findByFranchiseId(id)
                .map(this.branchMapper::toResponse);
    }
}
