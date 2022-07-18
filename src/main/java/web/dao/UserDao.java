package web.dao;

import java.util.List;

import web.model.User;

public interface UserDao {
    void addUser(User var1);

    User getUser(long var1);

    void deleteUser(long var1);

    void editUser(User var1, long var2);

    List<User> getAllUsers();
}
