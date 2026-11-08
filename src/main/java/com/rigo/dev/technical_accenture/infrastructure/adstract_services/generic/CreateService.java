package com.rigo.dev.technical_accenture.infrastructure.adstract_services.generic;

public interface CreateService<Request, Response> {
    Response create(Request request) throws RuntimeException;
}

