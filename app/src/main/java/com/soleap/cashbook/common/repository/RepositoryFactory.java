package com.soleap.cashbook.common.repository;

import com.soleap.cashbook.document.DocumentInfo;
import com.soleap.cashbook.repository.AgileTaskRepository;

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

    private Map<String, DocumentSnapshotRepository> repositoryMap = new HashMap<>();

    public DocumentSnapshotRepository get(String entity) {

        if (!repositoryMap.containsKey(entity)) {
            repositoryMap.put(entity, getRepository(entity));
        }
        return repositoryMap.get(entity);
    }

    private DocumentSnapshotRepository getRepository(String entity) {
        if (entity.equals(DocumentInfo.AGILE_TASK)) {
            return new AgileTaskRepository();
        }
        return new DocumentSnapshotRepository(entity);
    }
}
