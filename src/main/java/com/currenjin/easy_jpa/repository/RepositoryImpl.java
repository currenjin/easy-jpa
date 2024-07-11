package com.currenjin.easy_jpa.repository;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;
import java.util.Optional;

public class RepositoryImpl<T, ID> implements Repository<T, ID> {

    private EntityManager entityManager;
    private final Class<T> domainClass;

    public RepositoryImpl(Class<T> domainClass) {
        this.domainClass = domainClass;
    }

    public void setEntityManager(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    @Transactional
    public <S extends T> S save(S entity) {
        entityManager.persist(entity);
        return entity;
    }

    @Override
    @Transactional
    public Optional<T> findById(ID id) {
        T entity = entityManager.find(domainClass, id);
        return Optional.ofNullable(entity);
    }

    @Override
    @Transactional
    public Iterable<T> findAll() {
        return entityManager.createQuery("from " + domainClass.getName(), domainClass).getResultList();
    }

    @Override
    @Transactional
    public void deleteById(ID id) {
        T entity = entityManager.find(domainClass, id);
        if (entity != null) {
            entityManager.remove(entity);
        }
    }
}

