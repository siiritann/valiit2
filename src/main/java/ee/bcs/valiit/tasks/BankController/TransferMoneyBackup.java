/*
package ee.bcs.valiit.tasks.BankController;

import org.springframework.web.bind.annotation.RequestParam;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

public class TransferMoneyBackup {

    // transferMoney WITH SQL
    public String transferMoney(@RequestParam("fromAccount") String accNo1,
                                @RequestParam("toAccount") String accNo2,
                                @RequestParam("money") BigDecimal money) {

        // PART 1 - get current balance for both

        // get from Account balance
        String sqlGet1 = "SELECT balance FROM account WHERE acc_no = :accNo1";
        Map<String, Object> paramMapGet1 = new HashMap<>();
        paramMapGet1.put("accNo1", accNo1);
        BigDecimal oldValueFromAccount = jdbcTemplate.queryForObject(sqlGet1, paramMapGet1, BigDecimal.class);

        // get to Account balance
        String sqlGet2 = "SELECT balance FROM account WHERE acc_no = :accNo2";
        Map<String, Object> paramMapGet2 = new HashMap<>();
        paramMapGet2.put("accNo2", accNo2);
        BigDecimal oldValueToAccount = jdbcTemplate.queryForObject(sqlGet2, paramMapGet2, BigDecimal.class);

        // PART 2 - check if possible to make transfer
        // PART 3 - update balance

        if (money.compareTo(oldValueFromAccount) <= 0) {

            // from Account update
            String sqlSet1 = "UPDATE account SET balance = :balance WHERE acc_no = :accNo1";
            BigDecimal newValueFromAccount = oldValueFromAccount.subtract(money.abs());
            Map<String, Object> paramMapSet1 = new HashMap<>();
            paramMapSet1.put("accNo1", accNo1);
            paramMapSet1.put("balance", newValueFromAccount);
            jdbcTemplate.update(sqlSet1, paramMapSet1);

            // to Account update
            String sqlSet2 = "UPDATE account SET balance = :balance WHERE acc_no = :accNo2";
            BigDecimal newValueToAccount = oldValueToAccount.add(money.abs());
            Map<String, Object> paramMapSet2 = new HashMap<>();
            paramMapSet2.put("accNo2", accNo2);
            paramMapSet2.put("balance", newValueToAccount);
            jdbcTemplate.update(sqlSet2, paramMapSet2);

            return "ACCOUNT 1: previous amount was " + oldValueFromAccount + ", new amount is " + newValueFromAccount + "\n" + "ACCOUNT 2: previous amount was " + oldValueToAccount + ", new amount is " + newValueToAccount;
        } else {
            return "Not enough money";
        }
    }
}
*/
