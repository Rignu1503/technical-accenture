package com.rigo.dev.technical_accenture.domain.entities;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

import java.util.UUID;

@Table("branches")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BranchEntity {

    @Id
    private UUID id;
    private String name;
    private UUID franchiseId;
}
