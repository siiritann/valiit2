package ee.bcs.valiit.tasks.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import static ee.bcs.valiit.tasks.Lesson2.fibonacci;

//import static ee.bcs.valiit.tasks.Lesson2.fibonacci;

@RestController
public class TestController {

    @GetMapping(value = "/") // see on http GET päring
    // kui keegi teeb päringu home page-i pihta siis pane see asi tööle
    // testi ennast kusagil välja ei kutsuta
    public String getHelloWorld() {
        return "Hello world";
    }

    ;

    @GetMapping("hello")
    public String getMidagiMuud() {
        return "Midagi muud";
    }

    ;

    @GetMapping("?*ri")
    public String getSiiri() {
        return "Siiri";
    }

    ;
    // URL = http://localhost:8080/employee/midagi?employeeId=5
    @GetMapping("/employee/{midagi}")
    public String pathParTest(@PathVariable("midagi") String mingiTekst,
                              @RequestParam("employeeId") Long employeeId,
                              @RequestParam(value = "testID", required = false)
                                      Long optional) {
        return mingiTekst + " :) " + employeeId;
    }

    @GetMapping("/fibo")
    public int fibo(@RequestParam("nr") int nr) {
        return fibonacci(nr);
    }
}

