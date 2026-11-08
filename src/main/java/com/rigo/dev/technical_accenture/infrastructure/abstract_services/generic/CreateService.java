package com.rigo.dev.technical_accenture.infrastructure.abstract_services.generic;

import com.rigo.dev.technical_accenture.api.exeptions.BadRequestException;
import reactor.core.publisher.Mono;

public interface CreateService<Request, Response> {
    Mono<Response> create(Request request) throws BadRequestException;
}

