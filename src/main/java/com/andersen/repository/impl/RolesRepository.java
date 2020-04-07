package com.andersen.repository.impl;

import com.andersen.entity.Roles;
import com.andersen.repository.CrudRepository;
import com.andersen.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import java.util.List;

public class RolesRepository implements CrudRepository<Roles> {
    private SessionFactory sessionFactory = HibernateUtil.sessionFactory;

    @Override
    public Roles get(long id) {
        try (Session session = sessionFactory.openSession()) {
            return session.get(Roles.class, id);
        }
    }

    @Override
    public void saveOrUpdate(Roles role) {
        try (Session session = sessionFactory.openSession()) {
            session.getTransaction().begin();
            session.saveOrUpdate(role);
            session.getTransaction().commit();
        }
    }

    @Override
    public void delete(long id) {
        try (Session session = sessionFactory.openSession()) {
            session.getTransaction().begin();
            Roles role = session.get(Roles.class, id);
            session.delete(role);
            session.getTransaction().commit();
        }
    }

    @Override
    public List<Roles> getAll() {
        try (Session session = sessionFactory.openSession()) {
            return session.createQuery("FROM Roles").list();
        }
    }
}
