package com.rigo.dev.technical_accenture.infrastructure.abstract_services;

import com.rigo.dev.technical_accenture.api.dto.request.BranchRequest;
import com.rigo.dev.technical_accenture.api.dto.response.BranchResponse;
import com.rigo.dev.technical_accenture.infrastructure.abstract_services.generic.CreateService;
import com.rigo.dev.technical_accenture.infrastructure.abstract_services.generic.ReadAllService;
import com.rigo.dev.technical_accenture.infrastructure.abstract_services.generic.UpdateService;
import reactor.core.publisher.Flux;

import java.util.UUID;

public interface IBranchService extends
        ReadAllService<BranchResponse>,
        CreateService<BranchRequest, BranchResponse>,
        UpdateService<BranchRequest, BranchResponse, UUID>
{
    Flux<BranchResponse> getByFranchiseId(UUID id);

}
