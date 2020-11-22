package ee.bcs.valiit.tasks.contoller;

import ee.bcs.valiit.tasks.BankController.Client;
import ee.bcs.valiit.tasks.service.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
public class ClientController {

    @Autowired
    private ClientService clientService;

    //    createClient WITH QUERY PARAMS RETURN clientId
    @PostMapping("/createclient")
    public Integer createClientWithQueryParamsWithReturn(@RequestParam ("firstName") String firstName,
                                                         @RequestParam("lastName") String lastName){
        Integer result = clientService.createClientWithQueryParamsWithReturn(firstName, lastName);
        System.out.println(result);
        return result;
    }

    //    createClient WITH REQUEST BODY // TODO POOLELI - lisa firstname lastname kontroll et ei oleks duplikaate
    @PostMapping("newclient")
    public void createClient(@RequestBody Client client) {
        clientService.createClientWithRequestBody(client);
    }


    // get list of clients
    @GetMapping("clients")
    public List getClientsList() {
        return clientService.getClientsList();
    }


    // delete a client - // TODO POOLELI
    @DeleteMapping("client/{id}")
    public List deleteClient(@PathVariable("id") int id){
        return clientService.getClientsList();
    }

}
