package com.cherrysoft.model.repository;

import com.cherrysoft.model.repository.bd.EntityManagerGetter;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.Objects;
import java.util.Optional;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

/**
 *
 * @author Emmanuel Chable
 * @param <T>
 * @param <K>
 */
public abstract class CrudRepository<T, K> {

    private final EntityManager entityManager;
    private final Class<T> type;

    public CrudRepository() {
        Type t = this.getClass().getGenericSuperclass();
        ParameterizedType pt = (ParameterizedType) t;
        type = (Class) pt.getActualTypeArguments()[0];
        entityManager = EntityManagerGetter.getInstance();
    }

    public Iterable<T> findAll() {
        CriteriaBuilder cb = this.entityManager.getCriteriaBuilder();
        CriteriaQuery<T> criteriaQuery = cb.createQuery(type);
        Root<T> root = criteriaQuery.from(type);
        criteriaQuery.select(root);
        TypedQuery<T> query = entityManager.createQuery(criteriaQuery);
        return query.getResultList();
    }

    public Optional<T> findById(K id) {

        T res = entityManager.find(type, id);
        if (Objects.isNull(res)) {
            return Optional.empty();
        }

        return Optional.of(res);
    }

    public T delete(T obj) {
        entityManager.getTransaction().begin();
        entityManager.remove(obj);
        entityManager.getTransaction().commit();
        return obj;
    }

    public T deleteById(K id) {
        T res = this.findById(id).get();
        delete(res);
        return res;
    }

    public T save(T obj) {
        entityManager.getTransaction().begin();
        entityManager.persist(obj);
        entityManager.getTransaction().commit();
        return obj;
    }

    public T update(T obj) {
        entityManager.getTransaction().begin();
        entityManager.merge(obj);
        entityManager.getTransaction().commit();
        return obj;
    }

    public EntityManager getEntityManager() {
        return entityManager;
    }

}
