package com.rigo.dev.technical_accenture.infrastructure.abstract_services.generic;


import reactor.core.publisher.Flux;

public interface  ReadAllService<Response> {
    Flux<Response> getAll();
}
