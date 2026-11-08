package com.rigo.dev.technical_accenture.infrastructure.abstract_services.generic;

import reactor.core.publisher.Flux;

public interface ReadByIdService<Response, Id> {
    Flux<Response> getById(Id id);
}