/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.shoppingbasket.dao;

import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.logging.Logger;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.LockModeType;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import org.shoppingbasket.annotation.Logging;

/**
 *
 * @author Naveen_P08
 * @param <T>
 */
public abstract class DataAccessDAO<T> {

    @PersistenceContext
    private EntityManager em;
    
    @Inject
    @Logging
    private Logger logger;

    private Class<T> type;

    public DataAccessDAO() {

    }

    public DataAccessDAO(Class<T> type) {
        this.type = type;
    }

    public T create(T t) {
        this.em.persist(t);
        this.em.flush();
        this.em.refresh(t);
        return t;
    }

    public T find(Object id) {
        return this.em.find(this.type, id);
    }

    public void delete(Object id) {
        Object ref = this.em.getReference(this.type, id);
        this.em.remove(ref);
    }

    public void deleteAll(T[] list) {
        for (T item : list) {
            em.remove(em.merge(item));
        }
    }

    public T update(T entity) {
        entity = this.em.merge(entity);
        this.em.flush();
        this.em.refresh(entity);
        return entity;
    }

    public List<T> updateAll(List<T> list) {
        list = this.em.merge(list);
        return list;
    }

    public List<T> findWithNamedQuery(String namedQuery) {
        return this.em.createNamedQuery(namedQuery, this.type).getResultList();
    }

    public List<T> findWithNamedQuery(String namedQuery, Map parameters) {
        return findWithNamedQuery(namedQuery, parameters, 0, 0);
    }

    public List<T> findWithNamedQuery(String namedQuery, Map parameters, int start, int end) {
        Set<Map.Entry<String, Object>> rawParams = parameters.entrySet();
        Query query = this.em.createNamedQuery(namedQuery);

        if (start > 0) {
            query.setFirstResult(start);
        }

        if (end > 0) {
            query.setMaxResults(end - start);
        }

        for (Map.Entry<String, Object> entry : rawParams) {
            query.setParameter(entry.getKey(), entry.getValue());
        }
        return query.getResultList();
    }

    public List<T> findWithNamedQuery(String namedQuery, int start, int end) {
        Query query = this.em.createNamedQuery(namedQuery);
        query.setMaxResults(end - start);
        query.setFirstResult(start);
        return query.getResultList();
    }

    public int countTotalRecord(String namedQueryName) {
        Query query = em.createNamedQuery(namedQueryName);
        Number result = (Number) query.getSingleResult();
        return result.intValue();
    }

    public List<T> findNativeQuery(String sql) {
        return this.em.createNativeQuery(sql, this.type).getResultList();
    }
    
    public void readLock(List<T> lists){
        this.em.lock(lists, LockModeType.READ);
    }
}
