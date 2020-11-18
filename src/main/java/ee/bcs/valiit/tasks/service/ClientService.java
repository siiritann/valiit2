package ee.bcs.valiit.tasks.service;

import ee.bcs.valiit.tasks.BankController.Account;
import ee.bcs.valiit.tasks.BankController.Client;
import ee.bcs.valiit.tasks.BankController.ClientRowMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class ClientService {
    @Autowired
    private NamedParameterJdbcTemplate jdbcTemplate;

    //    createClient WITH SQL
    public void createClient(@RequestParam("firstName") String firstName,
                             @RequestParam("lastName") String lastName) {
        String sql = "INSERT INTO client (first_name, last_name) " +
                "VALUES (:muutuja1, :muutuja2)";
        Map<String, String> paramMap = new HashMap<>();
        paramMap.put("muutuja1", firstName);
        paramMap.put("muutuja2", lastName);
        jdbcTemplate.update(sql, paramMap);
    }


    //    createClient WITH REQUEST BODY
    public void createClientWithRequestBody(@RequestBody Client client) {
        String sql = "INSERT INTO client (first_name, last_name) VALUES (:firstName, :lastName)";
        String firstName = client.getFirstName();
        String lastName = client.getLastName();
        Map<String, String> paramMap = new HashMap<>();
        paramMap.put("firstName", firstName);
        paramMap.put("lastName", lastName);
        jdbcTemplate.update(sql, paramMap);
    }


    // get list of clients
    public List getClientsList() {
        String sql = "SELECT * FROM client";
        Map<String, Object> paramMap = new HashMap<>();
        List<Account> clientsList = jdbcTemplate.query(sql, paramMap, new ClientRowMapper());
        return clientsList;
    }
}
