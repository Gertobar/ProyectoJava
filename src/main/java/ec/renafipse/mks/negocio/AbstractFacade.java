/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ec.renafipse.mks.negocio;

import java.util.List;
import javax.persistence.EntityManager;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author vicastoc
 * @param <T>
 */
public abstract class AbstractFacade<T> {
    private final Class<T> entityClass;

    public AbstractFacade(Class<T> entityClass) {
        this.entityClass = entityClass;
    }

    protected abstract EntityManager getEntityManager();

    @Transactional(readOnly = false)    
    public T create(T entity) {
        getEntityManager().persist(entity);
        return entity;
    }

    @Transactional(readOnly = false)
    public T edit(T entity) {
        //getEntityManager().find(entity.getClass(), entity);
        getEntityManager().merge(entity);
        return entity;
    }

    @Transactional(readOnly = false)
    public void remove(T entity) {
        getEntityManager().remove(entity);
        //getEntityManager().remove(getEntityManager().merge(entity));
    }

    @Transactional(readOnly = true)
    public T find(Object id) {
        return getEntityManager().find(entityClass, id);
    }

    @Transactional(readOnly = true)
    public List<T> findAll() {
        javax.persistence.criteria.CriteriaQuery cq = getEntityManager().getCriteriaBuilder().createQuery();
        cq.select(cq.from(entityClass));
        return getEntityManager().createQuery(cq).getResultList();
    }

   @Transactional(readOnly = true)
    public List<T> findRange(int[] range) {
        javax.persistence.criteria.CriteriaQuery cq = getEntityManager().getCriteriaBuilder().createQuery();
        cq.select(cq.from(entityClass));
        javax.persistence.Query q = getEntityManager().createQuery(cq);
        q.setMaxResults(range[1] - range[0] + 1);
        q.setFirstResult(range[0]);
        return q.getResultList();
    }

    @Transactional(readOnly = true)
    public int count() {
        javax.persistence.criteria.CriteriaQuery cq = getEntityManager().getCriteriaBuilder().createQuery();
        javax.persistence.criteria.Root<T> rt = cq.from(entityClass);
        cq.select(getEntityManager().getCriteriaBuilder().count(rt));
        javax.persistence.Query q = getEntityManager().createQuery(cq);
        return ((Long) q.getSingleResult()).intValue();
    }
    
    
    
}
