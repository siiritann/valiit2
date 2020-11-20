package ee.bcs.valiit.tasks.service;

import ee.bcs.valiit.tasks.BankController.Client;
import ee.bcs.valiit.tasks.repository.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClientService {
    @Autowired
    private NamedParameterJdbcTemplate jdbcTemplate;

    @Autowired
    private ClientRepository clientRepository;


    //    createClient WITH QUERY PARAMS RETURN clientId
    public Integer createClientWithQueryParamsWithReturn(String firstName, String lastName){
        return clientRepository.createClientWithQueryParamsWithReturn(firstName, lastName);
    }

    //    createClient WITH QUERY PARAMS
    public void createClientWithQueryParams(String firstName, String lastName) {
            clientRepository.createClientWithQueryParams(firstName, lastName);
    }


    //    createClient WITH REQUEST BODY
    public void createClientWithRequestBody(Client client) {
        clientRepository.createClientWithRequestBody(client);
    }


    // get list of clients
    public List getClientsList() {
        return clientRepository.getClientsList();
    }
}
