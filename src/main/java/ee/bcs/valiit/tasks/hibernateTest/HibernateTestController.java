package ee.bcs.valiit.tasks.hibernateTest;

import ee.bcs.valiit.tasks.repository3.Author;
import ee.bcs.valiit.tasks.repository3.AuthorRepository;
import ee.bcs.valiit.tasks.repository3.AuthorResponse;
import ee.bcs.valiit.tasks.service.AuthorService;
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

    @Autowired
    private AuthorRepository authorRepository;

    @Autowired
    private AuthorService authorService;

    @GetMapping("hibernate")
    public List<Account> getAccounts(){
        return accountRepository2.findAll();
    }

    @GetMapping("hibernate/{id}")
    public BigDecimal getAccounts2(@PathVariable("id") int id){
        return accountRepository2.getOne(id).getBalance();
    }


    @GetMapping("hibernate2")
    public AuthorResponse hibernateTest(int id){
        Author author = authorService.getById(id);
        return new AuthorResponse(author);
    }
}
