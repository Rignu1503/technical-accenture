package com.rigo.dev.technical_accenture.api.errors;

import lombok.*;

import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Data
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
public class ErrorsResp extends BaseErrorResponse {
    private List<String> errors;
}