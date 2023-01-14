package org.trotsky.springmvc.dao;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.trotsky.springmvc.entity.User;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;
@Component
public class UserDaoImpl implements UserDao {

    public UserDaoImpl() {

    }


    //@Autowired
    @PersistenceContext
    private EntityManager em;


    @Override
    @Transactional
    public void saveUser(User user) {
        em.persist(user);
    }

    @Override
    @Transactional
    public void removeUserById(long id) {
        User user = em.find(User.class, id);
        em.remove(user);
    }

    @Override
    public List<User> getAllUsers() {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<User> cq = cb.createQuery(User.class);
        Root<User> root = cq.from(User.class);
        cq.select(root);
        return em.createQuery(cq).getResultList();
    }

    @Override
    public User show(long id) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<User> cq = cb.createQuery(User.class);
        Root<User> root = cq.from(User.class);
        cq.select(root).where(cb.equal(root.get("id"), id));
        return em.createQuery(cq).getSingleResult();
    }

    @Override
    @Transactional
    public void update(long id, User user) {
        User userToUpdate = em.find(User.class, id);
        userToUpdate.setName(user.getName());
        userToUpdate.setAge(user.getAge());
        userToUpdate.setPassword(user.getPassword());
        userToUpdate.setRoles(user.getRoles());
        em.merge(userToUpdate);
    }

}