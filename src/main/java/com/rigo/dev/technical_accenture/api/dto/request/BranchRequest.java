package com.rigo.dev.technical_accenture.api.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BranchRequest {

    @NotBlank(message = "The branch name is required")
    @Size(max = 255, message = "The name cannot exceed 255 characters")
    private String name;

    @NotNull(message = "The franchise is mandatory")
    private UUID franchiseId;
}
