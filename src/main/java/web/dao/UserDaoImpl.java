package web.dao;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import web.model.User;

@Repository
public class UserDaoImpl implements UserDao {
    private final EntityManagerFactory emf;

    @Autowired
    public UserDaoImpl(EntityManagerFactory emf) {
        this.emf = emf;
    }

    public EntityManager getEntityManager() {
        EntityManager entityManager = this.emf.createEntityManager();
        return entityManager;
    }

    @Transactional
    public void addUser(User user) {
        EntityManager em = this.getEntityManager();
        em.getTransaction().begin();
        em.persist(user);
        em.getTransaction().commit();
    }

    @Transactional
    public User getUser(long id) {
        EntityManager em = this.getEntityManager();
        User user = (User)em.find(User.class, id);
        em.detach(user);
        return user;
    }

    @Transactional
    public void deleteUser(long id) {
        EntityManager em = this.getEntityManager();
        em.getTransaction().begin();
        em.remove(em.find(User.class, id));
        em.getTransaction().commit();
    }

    @Transactional
    public void editUser(User user, long id) {
        EntityManager em = this.getEntityManager();
        em.getTransaction().begin();
        User user1 = this.getUser(id);
        em.detach(user1);
        user1.setFirstName(user.getFirstName());
        user1.setLastName(user.getLastName());
        user1.setAge(user.getAge());
        user1.setEmail(user.getEmail());
        em.merge(user1);
        em.getTransaction().commit();
    }

    public List<User> getAllUsers() {
        return this.getEntityManager().createQuery("select user from User user").getResultList();
    }
}