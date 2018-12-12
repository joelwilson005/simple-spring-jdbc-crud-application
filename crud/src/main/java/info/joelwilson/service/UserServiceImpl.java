package info.joelwilson.service;

import info.joelwilson.model.User;
import info.joelwilson.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("userService")
public class UserServiceImpl implements UserService {

    private UserRepository userRepository;

    @Autowired
    public UserServiceImpl(UserRepository userRepository) {

        this.userRepository = userRepository;
    }

    @Override
    public User addUser(User user) {

        return this.userRepository.addUser(user);
    }

    @Override
    public User getUserById(String id) {

        return this.userRepository.getUserById(id);
    }

    @Override
    public List<User> getAllUsers() {

        return this.userRepository.getAllUsers();
    }

    @Override
    public User updateUser(User user) {

        return this.userRepository.updateUser(user);
    }

    @Override
    public void deleteUser(String id) {

        this.userRepository.deleteUser(id);

    }
}
