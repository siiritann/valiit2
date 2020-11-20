package ee.bcs.valiit.tasks.contoller;

import ee.bcs.valiit.tasks.service.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin
@RestController
public class ClientController {

    @Autowired
    private ClientService clientService;

    //    createClient WITH QUERY PARAMS RETURN clientId
    @PostMapping("/createclient")
    public Integer createClientWithQueryParamsWithReturn(@RequestParam ("firstName") String firstName, @RequestParam("lastName") String lastName){
        Integer result = clientService.createClientWithQueryParamsWithReturn(firstName, lastName);
        System.out.println(result);
        return result;
    }
}
