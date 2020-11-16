/* SEE TEST ON KOOS SPRINGIGA
*
*
* */
package ee.bcs.valiit.tasks;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import ee.bcs.valiit.tasks.BankController.Account;
import ee.bcs.valiit.tasks.BankController.Client;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.test.autoconfigure.data.jdbc.DataJdbcTest;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import java.lang.runtime.ObjectMethods;
import java.math.BigDecimal;

import static org.hamcrest.core.StringContains.containsString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
public class AccountServiceTest {
    @Autowired
    private MockMvc mockMvc;

    @Test
    void getAllAccounts() throws Exception{
        mockMvc.perform(get("/accountslist")
                .contentType("application/json"))
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("EE")));
    }

    @Test
    void getBalanceForOneAccount() throws Exception{
        mockMvc.perform(get("/account/EE02"))
            .andExpect(content().string("500"));
    }


    @Test
    void getAllClients() throws Exception {
        mockMvc.perform(get("/clients")
                .contentType("json/application"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].lastName").value("Kass")) // jsonPath võtab array listi nullinda elemendi ja sealt elemendist selle fieldi
        ;
    }

    // testib kas kliendi loomine töötab
    @Test
    void createClient() throws Exception {
        ObjectMapper mapper = new ObjectMapper();
        Client client = new Client();
        client.setFirstName("A");
        client.setLastName("B");
        mockMvc.perform(put("/clients?firstName=A&lastName=B")
        .contentType("application/json").content(mapper.writeValueAsString(client)));
    }

}
