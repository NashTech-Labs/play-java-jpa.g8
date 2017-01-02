package com.knoldus.dao.impl;

import com.knoldus.dao.GenericDao;
import play.db.jpa.JPAApi;

import javax.persistence.EntityManager;
import java.lang.reflect.ParameterizedType;
import java.util.List;

/**
 * Created by Harmeet Singh(Taara) on 27/12/16.
 */
public abstract class GenericDaoImpl<T> implements GenericDao<T> {

    private Class<T> entityClass;
    private JPAApi jpaApi;

    public GenericDaoImpl(JPAApi jpaApi) {
        this.jpaApi = jpaApi;
        ParameterizedType parameterizedType = (ParameterizedType) getClass().getGenericSuperclass();
        entityClass = (Class<T>) parameterizedType.getActualTypeArguments()[0];
    }

    protected EntityManager getEntityManager() {
        return this.jpaApi.em();
    }

    @Override
    public void save(T t) {
        getEntityManager().persist(t);
    }

    @Override
    public T findById(int id) {
        return getEntityManager().createQuery("SELECT em FROM Employee em WHERE em.id = :id", entityClass)
                .setParameter("id", id).getSingleResult();
    }

    @Override
    public List<T> findAll() {
        return getEntityManager().createQuery("SELECT em FROM Employee em", entityClass)
                .getResultList();
    }

    @Override
    public int updateById(int id) {
        return jpaApi.withTransaction(entityManager ->
                entityManager.createQuery("UPDATE Employee emp SET emp.age = :age WHERE emp.id = :id")
                        .setParameter("id", id).setParameter("age", 25).executeUpdate()
        );
    }
}
