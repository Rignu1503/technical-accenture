package com.rigo.dev.technical_accenture.infrastructure.mappers;

import com.rigo.dev.technical_accenture.api.dto.request.BranchRequest;
import com.rigo.dev.technical_accenture.api.dto.response.BranchResponse;
import com.rigo.dev.technical_accenture.domain.entities.BranchEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface BranchMapper {

    @Mapping(target = "id", ignore = true)
    BranchEntity toEntity(BranchRequest request);

    BranchResponse toResponse(BranchEntity entity);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "franchiseId", ignore = true)
    void updateBrach(BranchRequest request, @MappingTarget BranchEntity entity);
}
