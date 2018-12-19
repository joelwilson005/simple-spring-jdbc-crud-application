package info.joelwilson.service;

import info.joelwilson.model.Address;
import info.joelwilson.model.User;
import info.joelwilson.repository.UserRepository;
import org.apache.commons.text.WordUtils;
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

        return this.userRepository.addUser(propertyCapitalizer(user));
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

        return this.userRepository.updateUser(propertyCapitalizer(user));
    }

    @Override
    public void deleteUser(String id) {

        this.userRepository.deleteUser(id);

    }

    private static User propertyCapitalizer(User user) {

        // Capitalize property values
        Address address = user.getAddress();
        user.setFirstName(WordUtils.capitalizeFully(user.getFirstName()));
        user.setLastName(WordUtils.capitalizeFully(user.getLastName()));

        address.setAddress1(WordUtils.capitalizeFully(address.getAddress1()));
        address.setAddress2(WordUtils.capitalizeFully(address.getAddress2()));
        address.setCity(WordUtils.capitalizeFully(address.getCity()));
        address.setCountry(WordUtils.capitalizeFully(address.getCountry()));

        return user;

    }
}
