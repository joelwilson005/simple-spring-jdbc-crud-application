package info.joelwilson.service;

import info.joelwilson.model.User;

import java.util.List;
import java.util.UUID;

public interface UserService {

    User addUser(User user);

    User getUserById(String id);

    List<User> getAllUsers();

    User updateUser(User user);

    void deleteUser(String id);

    static String generateUId() {

        String id = UUID.randomUUID().toString();

        return id;
    }
}
