package ee.bcs.valiit.tasks.controller;

import com.sun.jdi.request.StepRequest;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

import static ee.bcs.valiit.tasks.Lesson2.fibonacci;

//import static ee.bcs.valiit.tasks.Lesson2.fibonacci;

//@RequestMapping("test") // see rida paneb kõikide järgmiste urlide ette test ja alles siis tuleb see osa mis on iga mappingu juures kirjas
@RestController
public class TestController {

//    @GetMapping(value = "/") // see on http GET päring
//    // kui keegi teeb päringu home page-i pihta siis pane see asi tööle
//    // testi ennast kusagil välja ei kutsuta
//    public String getHelloWorld() {
//        return "Hello world";
//    }
//
//    ;

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

    @GetMapping("ex2")
    public List<Integer> test2(@RequestParam("nr") int nr) {
        List<Integer> resultList = new ArrayList<>();
        for (int i = 1; i <= nr; i++) {
            resultList.add(i * 2);
        }
        return resultList;
    }

//    @GetMapping("ex9")
//    public List<Integer> test2(@RequestParam("nr") int nr) {
//        List<Integer> resultList = new ArrayList<>();
//        for (int i = 1; i <= nr; i++) {
//            resultList.add(i * 2);
//        }
//        return resultList;
//    }

    // TODO TESTÜLESANNE: localhost:8080/users/5/contracts/8?filterBy=status
    // siit vaja kätte saada 5, 8, ja "status"

    @GetMapping("/users/{userId}/contracts/{nr}")
    public String testYl(@PathVariable("userId") int userId,
            @PathVariable("nr") int nr,
//            @RequestParam(value = "filterBy", required = false) String status ) {
            @RequestParam("filterBy") String status ) {
        return "userId " + userId + ", nr " + nr;
    }

    @GetMapping("/company/{companyId}/employee/{employeeId}/contract/{contractId}")
    public String test(@PathVariable("companyId") int companyId,
                       @PathVariable("employeeId") int employeeId,
                       @PathVariable("contractId") int contractId) {
        return "ÕIGE";
    }

    //    /?employeeId=8&somethingElse=tere

//    @GetMapping("/?employeeId={employeeId}&somethingElse=tere") >> see ei tohi olla sama mis getmappingu URL sest mul viib see avalehele ikkagi
    @GetMapping("/")
    public String test(@RequestParam("employeeId") int employeeId,
                       @RequestParam("somethingElse") String str) {
        return "YAY";
    }



//    Ül: kirjuta mapping sellele urlile:  /company/6?company=5&a=a&b=b

    @GetMapping("/company/{companyId1}") // sellesse ritta läheb ainult PATH ja mitte query parameetreid !!!
    public String test(@PathVariable("companyId1") int companyId1,
                       @RequestParam("company") int companyId2,
                       @RequestParam("a") String str1,
                       @RequestParam("b") String str2
    ) {return "YAY 2";}


    // ülesanne: kirjuta URL

    @GetMapping("a/*/a/{a}/{b}/c") // see on PATH, siin ei ole request parameetrite kohta
    public String test(@PathVariable("a") String a,
                     @RequestParam("a") String aa,
                     @RequestParam("b") Integer bb,
                     @PathVariable("b") Integer b
                     ) {
        return "ÕIGE";
    }

    // VASTUS: http://localhost:8080/a/a/a/string/12/c?a=string&b=2


}

