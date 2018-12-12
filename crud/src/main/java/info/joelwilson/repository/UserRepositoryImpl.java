package info.joelwilson.repository;

import info.joelwilson.model.Address;
import info.joelwilson.model.User;
import info.joelwilson.util.UserRowMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("UserRepository")
public class UserRepositoryImpl implements UserRepository {


    private JdbcTemplate jdbcTemplate;

    @Autowired
    public UserRepositoryImpl(JdbcTemplate jdbcTemplate) {

        this.jdbcTemplate = jdbcTemplate;
    }

    // Persist single user object to db
    @Override
    public User addUser(User user) {

        // Extract Address object from user parameter
        Address address = user.getAddress();

        // Insert User object
        jdbcTemplate.update("INSERT INTO users(ID, FIRST_NAME, LAST_NAME, EMAIL) VALUES(?, ?, ?, ?)",
                user.getId(), user.getFirstName(),  user.getLastName(), user.getEmail());

        // Insert Address object extracted from user object
        jdbcTemplate.update("INSERT INTO user_address(ADDRESS1, ADDRESS2, CITY, COUNTRY, USER_ID) VALUES(?, ?, ?, ?, ?)",
                address.getAddress1(), address.getAddress2(), address.getCity(), address.getCountry(), user.getId());

        // Return User object persisted to db
        return getUserById(user.getId());

    }

    // Obtain single User object
    @Override
    public User getUserById(String id) {

        String query = "SELECT * " +
                "FROM users u " +
                "INNER JOIN user_address ua on u.id = ua.user_id " +
                "WHERE u.id = ?";

         User user = jdbcTemplate.queryForObject(query, new UserRowMapper(), id);

         return user;
    }

    // Obtain all user objects in db
    @Override
    public List<User> getAllUsers() {

        String query = "SELECT * " +
                "FROM users u " +
                "INNER JOIN user_address ua on u.id = ua.user_id ";

        List<User> users = jdbcTemplate.query(query, new UserRowMapper());

        return users;
    }

    // Update user object
    @Override
    public User updateUser(User user) {

        // Extract Address object from user object
        Address address = user.getAddress();

        String query =
                "UPDATE users " +
                        "SET first_name = ?, last_name = ?, email = ? " +
                        "WHERE id = ?";

        // Update user
        jdbcTemplate.update(query, user.getFirstName(), user.getLastName(), user.getEmail(), user.getId());

        String addressQuery = "UPDATE user_address " +
                "SET address1 = ?, address2 = ?, city = ?, country = ? " +
                "WHERE user_id = ?";
        // Update user's address
        jdbcTemplate.update(addressQuery, address.getAddress1(), address.getAddress2(), address.getCity(), address.getCountry(), user.getId());

        // Return updated user object
        return getUserById(user.getId());
    }

    // Delete User record by id provided
    @Override
    public void deleteUser(String id) {

        String query = "DELETE FROM users " +
                "WHERE id = ?";

        jdbcTemplate.update(query, id);
    }
}
