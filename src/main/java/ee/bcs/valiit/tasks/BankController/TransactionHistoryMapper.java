package ee.bcs.valiit.tasks.BankController;

import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class TransactionHistoryMapper implements RowMapper<TransactionHistory> {


    @Override
    public TransactionHistory mapRow(ResultSet resultSet, int i) throws SQLException {
        TransactionHistory th = new TransactionHistory();
        th.setFromAccountId(resultSet.getInt("from_account_id"));
        th.setToAccountId(resultSet.getInt("to_account_id"));
        th.setAmount(resultSet.getBigDecimal("amount"));
        th.setType(resultSet.getString("type"));
        return th;
    }
}
