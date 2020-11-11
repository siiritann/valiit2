package ee.bcs.valiit.tasks.BankController;

import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ClientRowMapper implements RowMapper {
    @Override
    public Client mapRow(ResultSet resultSet, int i) throws SQLException {
        // kõik väljad mida tahame kasutada
        Client client = new Client(); // kasuta sellist konstruktorit nagu sul endal olemas on
        client.setFirstName(resultSet.getString("first_name"));
        client.setLastName(resultSet.getString("last_name"));
        return client;
    }
}
