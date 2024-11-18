package org.wellnessliving.homework04.repository;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.wellnessliving.homework04.entity.User;
import org.wellnessliving.homework04.util.HibernateUtil;

import java.util.Optional;

public class UserRepositoryImpl implements UserRepository {
    @Override
    public User create(User user) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.getTransaction();
            transaction.begin();
            session.save(user);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null && (transaction.isActive() || transaction.getRollbackOnly())) {
                transaction.rollback();
            }
        }
        return user;
    }

    @Override
    public User update(User user) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.getTransaction();
            transaction.begin();
            session.merge(user);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null && (transaction.isActive() || transaction.getRollbackOnly())) {
                transaction.rollback();
            }
        }
        return user;
    }

    @Override
    public Optional<User> findById(Long id) {
        User user;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            user = session.find(User.class, id);
        }
        return Optional.ofNullable(user);
    }
}
