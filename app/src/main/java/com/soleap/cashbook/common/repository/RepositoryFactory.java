package com.soleap.cashbook.common.repository;

import java.util.HashMap;
import java.util.Map;

public class RepositoryFactory {

    public static RepositoryFactory factory = null;

    public static RepositoryFactory create() {
        if (factory == null) {
            factory = new RepositoryFactory();
        }
        return factory;
    }

    private Map<String, DocumentRepository> repositoryMap = new HashMap<>();

    public DocumentRepository get(String entity) {

        if (!repositoryMap.containsKey(entity)) {
            repositoryMap.put(entity, getRepository(entity));
        }
        return repositoryMap.get(entity);
    }

    private DocumentRepository getRepository(String entity) {
        return new DocumentRepository(entity);
    }
}
