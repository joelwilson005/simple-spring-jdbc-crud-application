package info.joelwilson.util;

import info.joelwilson.model.Address;
import info.joelwilson.model.User;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class UserRowMapper implements RowMapper<User> {
    @Override
    public User mapRow(ResultSet rs, int i) throws SQLException {

        User user = new User();
        Address address = new Address();

        user.setId(rs.getString("id"));
        user.setFirstName(rs.getString("first_name"));
        user.setLastName(rs.getString("last_name"));
        user.setEmail(rs.getString("email"));

        address.setAddress1(rs.getString("address1"));
        address.setAddress2(rs.getString("address2"));
        address.setCity(rs.getString("city"));
        address.setCountry(rs.getString("country"));

        user.setAddress(address);

        return user;
    }
}

