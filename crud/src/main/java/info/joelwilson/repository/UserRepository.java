package info.joelwilson.repository;

import info.joelwilson.model.User;

import java.util.List;

public interface UserRepository {

    User addUser(User user);

    User getUserById(String id);

    List<User> getAllUsers();

    User updateUser(User user);

    void deleteUser(String id);
}
