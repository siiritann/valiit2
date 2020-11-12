/*
       DEPRECATED
 */

package ee.bcs.valiit.tasks.BankController;


import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;


public class HistoryRowMapper  implements RowMapper<BalanceHistory> {

    @Override
    public BalanceHistory mapRow(ResultSet resultSet, int i) throws SQLException {
        // kõik väljad mida tahame kasutada
        BalanceHistory history = new BalanceHistory();
        history.setFromAccNo(resultSet.getString("from_accno"));
        history.setToAccNo(resultSet.getString("to_accno"));
        history.setAmount(resultSet.getBigDecimal("amount"));
        history.setTransactionType(resultSet.getString("type"));
        return history;

    }
}
