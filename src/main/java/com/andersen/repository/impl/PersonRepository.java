package com.andersen.repository.impl;

import com.andersen.entity.Person;
import com.andersen.enums.UserRole;
import com.andersen.repository.CrudRepository;
import com.andersen.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import javax.persistence.Query;
import java.util.List;

public class PersonRepository implements CrudRepository<Person> {
    private SessionFactory sessionFactory = HibernateUtil.sessionFactory;

    @Override
    public Person get(long id) {
        try (Session session = sessionFactory.openSession()) {
            return session.get(Person.class, id);
        }
    }

    @Override
    public void saveOrUpdate(Person o) {
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            session.saveOrUpdate(o);
            session.getTransaction().commit();
        }
    }

    @Override
    public void delete(long id) {
        try (Session session = sessionFactory.openSession()) {
            session.getTransaction().begin();
            Query q = session.createQuery("DELETE Person WHERE id = :id");
            q.setParameter("id", id);
            q.executeUpdate();
            session.getTransaction().commit();
        }
    }

    @Override
    public List<Person> getAll() {
        try (Session session = sessionFactory.openSession()) {
            return session.createQuery("FROM Person ").list();
        }
    }

    public List<Person> getPersonByRole(UserRole userRole) {
        try (Session session = sessionFactory.openSession()) {
            Query query = session.createQuery("FROM Person p JOIN FETCH p.roleSet role WHERE role.userRole=:userRole");
            query.setParameter("userRole", userRole);
            return query.getResultList();
        }
    }

}