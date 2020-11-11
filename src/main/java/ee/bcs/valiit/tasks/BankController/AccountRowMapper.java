// see fail defineerib kuidas saab DB query tulemusest klass

package ee.bcs.valiit.tasks.BankController;


import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class AccountRowMapper implements RowMapper<Account> {

    @Override
    public Account mapRow(ResultSet resultSet, int i) throws SQLException {
        // kõik väljad mida tahame kasutada
        Account account = new Account(); // kasuta sellist konstruktorit nagu sul endal olemas on
        account.setAccNo(resultSet.getString("acc_no"));
        account.setBalance(resultSet.getBigDecimal("balance"));
        return account;
    }
}

