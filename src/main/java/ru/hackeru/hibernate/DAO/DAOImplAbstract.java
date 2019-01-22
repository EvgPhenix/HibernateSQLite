package ru.hackeru.hibernate.DAO;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.core.GenericTypeResolver;
import ru.hackeru.hibernate.Util.HibernateUtil;

import java.util.List;

public abstract class DAOImplAbstract<T> implements DAO<T>{

    private final Class<T> genericType;

    private final String RECORD_COUNT_HQL;
    private final String FIND_ALL_HQL;

    @SuppressWarnings("unchecked")
    public DAOImplAbstract()
    {
        this.genericType = (Class<T>) GenericTypeResolver.resolveTypeArgument(getClass(), DAOImplAbstract.class);
        this.RECORD_COUNT_HQL = "select count(*) from " + this.genericType.getName();
        this.FIND_ALL_HQL = "from " + this.genericType.getName() + " t ";
    }


    @Override
    public void add(T t) {
        Transaction trns = null;
        Session session = HibernateUtil.configureSessionFactory().openSession();
        try {
            trns = session.beginTransaction();
            session.save(t);
            session.getTransaction().commit();
        } catch (RuntimeException e) {
            if (trns != null) {
                trns.rollback();
            }
            e.printStackTrace();
        } finally {
            session.flush();
            session.close();
        }
    }

    @Override
    public List<T> getAll() {
        List<T> users = null;
        Session session = HibernateUtil.configureSessionFactory().openSession();
        try {
            String query = "from " + this.genericType.getName();
            users = session.createQuery(query).list();
        } catch (RuntimeException e) {
            e.printStackTrace();
        } finally {
            session.flush();
            session.close();
        }
        return users;
    }

    @Override
    public T getById(int id) {
        T contact = null;
        Session session = HibernateUtil.configureSessionFactory().openSession();
        try {
            String queryString = " from " + this.genericType.getName() + " where id = :id";
            Query query = session.createQuery(queryString);
            query.setInteger("id", id);
            contact = (T) query.uniqueResult();
        } catch (RuntimeException e) {
            e.printStackTrace();
        } finally {
            session.flush();
            session.close();
        }
        return contact;
    }

    @Override
    public void update(T t) {
        Transaction trns = null;
        Session session = HibernateUtil.configureSessionFactory().openSession();
        try {
            trns = session.beginTransaction();
            session.update(t);
            session.getTransaction().commit();
        } catch (RuntimeException e) {
            if (trns != null) {
                trns.rollback();
            }
            e.printStackTrace();
        } finally {
            session.flush();
            session.close();
        }
    }

    @Override
    public void deleteById(int id) {
        String classname = getClass().getName();
        deleteById(classname, id);
    }

    @Override
    public void deleteById(String className, int id) {

        Transaction trns = null;
        Session session = HibernateUtil.configureSessionFactory().openSession();
        try {
            trns = session.beginTransaction();
            T contact = (T) session.load(this.genericType.getName(), new Integer(id));
            session.delete(contact);
            session.getTransaction().commit();
        } catch (RuntimeException e) {
            if (trns != null) {
                trns.rollback();
            }
            e.printStackTrace();
        }
        finally {
            session.flush();
            session.close();
        }
    }
}
