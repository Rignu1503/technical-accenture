package com.rigo.dev.technical_accenture.infrastructure.adstract_services.generic;

public interface DeleteService <Id>{

    void delete(Id id) throws RuntimeException;
}