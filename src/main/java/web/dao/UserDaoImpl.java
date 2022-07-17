package web.dao;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import web.model.User;

@Repository
public class UserDaoImpl implements UserDao {

    private EntityManager em;

    @PersistenceContext(name = "EntityPersistenceUnit")
    public void setEm(EntityManager em) {
        this.em = em;
    }

    @Transactional
    public void addUser(User user) {
        em.persist(user);
    }

    @Transactional
    public User getUser(long id) {
        User user = (User)em.find(User.class, id);
        em.detach(user);
        return user;
    }

    @Transactional
    public void deleteUser(long id) {
        em.remove(em.find(User.class, id));
    }

    @Transactional
    public void editUser(User user, long id) {
        User user1 = this.getUser(id);
        em.detach(user1);
        user1.setFirstName(user.getFirstName());
        user1.setLastName(user.getLastName());
        user1.setAge(user.getAge());
        user1.setEmail(user.getEmail());
        em.merge(user1);
    }

    public List<User> getAllUsers() {
        return em.createQuery("select user from User user").getResultList();
    }
}