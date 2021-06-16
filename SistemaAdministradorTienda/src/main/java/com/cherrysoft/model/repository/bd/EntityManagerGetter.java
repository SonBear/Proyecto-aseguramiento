package com.cherrysoft.model.repository.bd;

import java.util.Objects;
import javax.persistence.EntityManager;
import javax.persistence.Persistence;

/**
 *
 * @author Emmanuel Chable
 */
public final class EntityManagerGetter {

    private static EntityManager instance;
    private static String persistenceUnitName = "com.cherrysoft.bd";

    private EntityManagerGetter() {
    }

    public static synchronized EntityManager getInstance() {
        if (Objects.isNull(instance)) {
            instance = Persistence.createEntityManagerFactory(persistenceUnitName).createEntityManager();
        }
        return instance;
    }
}
