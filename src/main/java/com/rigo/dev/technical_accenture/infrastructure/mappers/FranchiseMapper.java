package com.rigo.dev.technical_accenture.infrastructure.mappers;

import com.rigo.dev.technical_accenture.api.dto.request.FranchiseRequest;
import com.rigo.dev.technical_accenture.domain.entities.FranchiseEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface FranchiseMapper {

    @Mapping(target = "id", ignore = true)
    FranchiseEntity toEntity(FranchiseRequest request);


}
