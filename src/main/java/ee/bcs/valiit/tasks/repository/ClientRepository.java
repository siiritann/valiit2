package ee.bcs.valiit.tasks.repository;

import ee.bcs.valiit.tasks.BankController.Account;
import ee.bcs.valiit.tasks.BankController.Client;
import ee.bcs.valiit.tasks.BankController.ClientRowMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class ClientRepository {

    @Autowired
    private NamedParameterJdbcTemplate jdbcTemplate;

    //    createClient WITH QUERY PARAMS RETURN clientId
    public Integer createClientWithQueryParamsWithReturn(String firstName, String lastName) {
        String sql = "INSERT INTO client (first_name, last_name) " +
                "VALUES (:muutuja1, :muutuja2)";
        Map<String, String> paramMap = new HashMap<>();
        paramMap.put("muutuja1", firstName);
        paramMap.put("muutuja2", lastName);

        // tagastab äsja loodud konto data, ise ütlen välja nt iD millelel genereeriti autom.väärtus
        KeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcTemplate.update(sql, new MapSqlParameterSource(paramMap), keyHolder);
        System.out.println((Integer) keyHolder.getKeys().get("id"));
        return (Integer) keyHolder.getKeys().get("id");
    }


    //    createClient WITH REQUEST BODY
    public void createClientWithRequestBody(Client client) {
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
