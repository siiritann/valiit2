package ee.bcs.valiit.tasks.controllerTest;

import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@CrossOrigin
@RestController
public class vuetest {

    @GetMapping("register")
    public String register(String email){
        System.out.println(email);
        return "THUBLI";
    }

    @PostMapping("registerpost")
    public List<User> registerPost(@RequestBody User user){
        System.out.println(user);
        List<User> userList = new ArrayList<>();
        userList.add(user);
        userList.add(new User("mari", "mari@mailinator.com", 33));
        userList.add(new User("jüri", "jüri@mailinator.com", 13));
//        System.out.println("REGISTER POST THUBLI");
//        return "REGISTER POST THUBLI";
        return userList;
    }



}
