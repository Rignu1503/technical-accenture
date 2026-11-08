package com.rigo.dev.technical_accenture.infrastructure.abstract_services.generic;

import reactor.core.publisher.Mono;

public interface DeleteService <Id>{

    Mono<Void> delete(Id id) throws RuntimeException;
}