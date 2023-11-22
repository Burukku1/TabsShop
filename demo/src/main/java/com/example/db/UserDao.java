package com.example.db;

import com.example.db.api.IUserDao;
import com.example.db.entity.Tabs;
import com.example.db.entity.User;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.NoResultException;
import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.*;

import java.util.List;
import java.util.Optional;
import java.util.Set;

public class UserDao implements IUserDao {

    private final EntityManagerFactory emf;

    public UserDao(EntityManagerFactory emf) {
        this.emf = emf;
    }

    @Override
    public void createUser(User user) {

        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();

        em.persist(user);
        em.getTransaction().commit();
        em.close();
    }

    @Override
    public Optional<User> checkUserLog(User user) {
        EntityManager em = emf.createEntityManager();
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<User> cQuery = cb.createQuery(User.class);
        Root<User> c = cQuery.from(User.class);
        ParameterExpression<String> paramLogin = cb.parameter(String.class);
        cQuery.select(c).where(cb.equal(c.get("login"), paramLogin));

        TypedQuery<User> query = em.createQuery(cQuery);
        String logToCompare = user.getLogin();
        query.setParameter(paramLogin, logToCompare);

        try {
            User uRes = query.getSingleResult();
            return Optional.of(uRes);
        } catch (NoResultException e) {
            return Optional.empty();
        }
    }

    @Override
    public boolean checkUserPass(User user) {
        EntityManager em = emf.createEntityManager();
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<User> cQuery = cb.createQuery(User.class);
        Root<User> c = cQuery.from(User.class);
        ParameterExpression<String> paramPass = cb.parameter(String.class);
        cQuery.select(c).where(cb.equal(c.get("password"), paramPass));

        TypedQuery<User> query = em.createQuery(cQuery);
        String PassToCompare = user.getPassword();
        query.setParameter(paramPass, PassToCompare);
        List<User> uRes = query.getResultList();
        //   System.out.println("List of coincidences: " + uRes);
        if (!uRes.isEmpty()) {
            System.out.println("This password already exists");
            return true;
        } else return false;
    }

    public void printAllUserTabs() {
        EntityManager em = emf.createEntityManager();
        try {
            CriteriaBuilder cb = em.getCriteriaBuilder();
            CriteriaQuery<User> userQuery = cb.createQuery(User.class);
            Root<User> userRoot = userQuery.from(User.class);
            userRoot.fetch("tabs", JoinType.LEFT);

            userQuery.select(userRoot);
            userQuery.distinct(true);

            List<User> users = em.createQuery(userQuery).getResultList();

            for (User user : users) {
                System.out.println("User: " + user);
                Set<Tabs> tabs = user.getTabs();
                for (Tabs tab : tabs) {
                    System.out.println("  Tab: " + tab);
                }
            }
        } finally {
            em.close();
        }
    }

    @Override
    public List<Tabs> showSearchResult(String name) {
        return null;
    }

    @Override
    public List<Tabs> showMyTabs(User user) {
        return null;
    }
}
