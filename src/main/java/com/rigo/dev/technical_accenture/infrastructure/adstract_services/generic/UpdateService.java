package com.rigo.dev.technical_accenture.infrastructure.adstract_services.generic;

public interface UpdateService<Request, Response, Id> {
    Response update(Id id, Request request) throws RuntimeException;
}
