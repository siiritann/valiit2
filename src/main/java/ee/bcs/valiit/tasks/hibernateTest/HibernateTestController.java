package ee.bcs.valiit.tasks.hibernateTest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.util.List;

@RestController
public class HibernateTestController {

    @Autowired
    private AccountRepository2 accountRepository2;

    @GetMapping("hibernate")
    public List<Account> getAccounts(){
        return accountRepository2.findAll();
    }

    @GetMapping("hibernate/{id}")
    public BigDecimal getAccounts2(@PathVariable("id") int id){
        return accountRepository2.getOne(id).getBalance();
    }
}
