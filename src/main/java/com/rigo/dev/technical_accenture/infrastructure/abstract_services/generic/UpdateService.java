package com.rigo.dev.technical_accenture.infrastructure.abstract_services.generic;

import com.rigo.dev.technical_accenture.api.exeptions.BadRequestException;
import reactor.core.publisher.Mono;

public interface UpdateService<Request, Response, Id> {
    Mono<Response> update(Id id, Request request) throws BadRequestException;
}
