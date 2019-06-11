package com.project.raluca.dao;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Transactional
@EnableTransactionManagement
public class AbstractDAO<E> {

    @PersistenceContext(name = "test")
    private EntityManager em;

    private final Class<E> entityClass;

    protected AbstractDAO(final Class<E> entityClass) {
        this.entityClass = entityClass;
    }

    @Transactional
    public EntityManager em() {
        return em;
    }

    @Transactional
    public void save(final E entityToBeSaved) {
        em.persist(entityToBeSaved);
    }

    @Transactional
    public void update(final E entityToBeUpdated) {
        em.merge(entityToBeUpdated);
    }

    @Transactional
    public void remove(final E entityToBeRemoved) {
        em.remove(entityToBeRemoved);
    }

    @Transactional
    public void delete(final Long id) {
        em.remove(findById(id));
    }

    @Transactional
    public E findById(final Long entityId) {
        return em.find(entityClass, entityId);
    }

}