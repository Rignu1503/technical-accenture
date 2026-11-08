package com.rigo.dev.technical_accenture.utils.enums;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Data
@SuperBuilder
@NoArgsConstructor
public class ErrorMessages {

    private final String mensaje = "";

    public static String IdNotFound(String entity) {
        final String message = "There are no records in the entity %s with the supplied id";
        return String.format(message, entity);
    }
}
