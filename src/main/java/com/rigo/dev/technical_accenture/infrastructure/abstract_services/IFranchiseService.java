package com.rigo.dev.technical_accenture.infrastructure.abstract_services;

import com.rigo.dev.technical_accenture.api.dto.request.FranchiseRequest;
import com.rigo.dev.technical_accenture.api.dto.response.FranchiseResponse;
import com.rigo.dev.technical_accenture.infrastructure.abstract_services.generic.CreateService;
import com.rigo.dev.technical_accenture.infrastructure.abstract_services.generic.ReadAllService;
import com.rigo.dev.technical_accenture.infrastructure.abstract_services.generic.UpdateService;

import java.util.UUID;

public interface IFranchiseService extends
        ReadAllService<FranchiseResponse>,
        CreateService<FranchiseRequest,FranchiseResponse>,
        UpdateService<FranchiseRequest, FranchiseResponse, UUID>
{
}
