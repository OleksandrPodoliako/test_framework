package db.jpa.ldc_reserch_repository_hibernate;

import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.Transaction;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaDelete;
import java.io.Serializable;
import java.util.List;

public class GenericDAO<E> {

    private Session session;
    private Transaction transaction;
    private final Class<E> entityClass;

    private final Logger LOGGER = Logger.getLogger(this.getClass());

    public GenericDAO(Class<E> entityClass) {
        this.entityClass = entityClass;
    }

    public Session getSession() {
        try {
            return HibernateUtil.getInstance().getSessionFactory().getCurrentSession();
        } catch (Exception e) {
            return HibernateUtil.getInstance().getSessionFactory().openSession();
        }
    }

    public void beginTransaction() {
        getSession().beginTransaction();
    }

    public void commitTransaction() {
        try {
            getSession().getTransaction().commit();
        } catch (IllegalStateException e) {
            getSession().getTransaction().rollback();
        }
    }

    public void close() {
        getSession().close();
    }

    public E getEntityById(final Serializable id) {
        session = getSession();
        transaction = session.beginTransaction();
        E result = (E) session.get(entityClass, id);
        transaction.commit();
        return result;
    }

    public void addEntity(E entity) {
        session = getSession();
        transaction = session.beginTransaction();
        session.persist(entity);
        transaction.commit();
    }

    public Serializable saveEntity(E entity) {
        session = getSession();
        transaction = session.beginTransaction();
        Serializable result = session.save(entity);
        transaction.commit();
        return result;
    }

    public void updateEntity(E entity) {
        session = getSession();
        transaction = session.beginTransaction();
        session.saveOrUpdate(entity);
        transaction.commit();
    }

    public void deleteEntity(E entity) {
        session = getSession();
        transaction = session.beginTransaction();
        session.delete(entity);
        transaction.commit();
    }

    public void removeEntite(E entity) {
        session = getSession();
        transaction = session.beginTransaction();
        session.remove(entity);
        transaction.commit();
    }

    public List<E> getAll() {
        session = getSession();
        transaction = session.beginTransaction();
        List<E> listOfEntityTable = session.createCriteria(entityClass).list();
        transaction.commit();
        return listOfEntityTable;
    }

    public void deleteAllEntity() {
        session = getSession();
        transaction = session.beginTransaction();
        List<E> listOfEntityTable = session.createCriteria(entityClass).list();
        for (E element : listOfEntityTable) {
            session.delete(element);
        }
        transaction.commit();
    }

    public int truncateEntities() {
        int numberOfEntity = 0;
        session = getSession();
        transaction = session.beginTransaction();
        List<E> listOfEntityTable = session.createCriteria(entityClass).list();
        for (E element : listOfEntityTable) {
            CriteriaBuilder builder = session.getCriteriaBuilder();
            CriteriaDelete<E> query = builder.createCriteriaDelete(entityClass);
            query.from(entityClass);
            numberOfEntity = session.createQuery(query).executeUpdate();
        }
        transaction.commit();
        return numberOfEntity;
    }
}
