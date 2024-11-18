package org.wellnessliving.homework04.repository;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.wellnessliving.homework04.entity.Role;
import org.wellnessliving.homework04.util.HibernateUtil;

import java.util.Optional;

public class RoleRepositoryImpl implements RoleRepository {
    @Override
    public Role create(Role role) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.getTransaction();
            transaction.begin();
            session.merge(role);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null && (transaction.isActive() || transaction.getRollbackOnly())) {
                transaction.rollback();
            }
        }
        return role;
    }

    @Override
    public Optional<Role> findById(Long id) {
        Role role;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            role = session.find(Role.class, id);
        }
        return Optional.ofNullable(role);
    }

}
