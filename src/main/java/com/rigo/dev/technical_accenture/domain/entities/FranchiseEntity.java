package com.rigo.dev.technical_accenture.domain.entities;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

import java.util.UUID;

@Table("franchises")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class FranchiseEntity {

    @Id
    private UUID id;
    private String name;
}
