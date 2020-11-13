package ee.bcs.valiit.tasks.exception;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class Econtroller {

    @GetMapping("exception")
    public int exceptionTest(@RequestParam("i") Integer i) {
        return fib(i);
    }

    private int fib(Integer i) {

        if (i == null){
            throw new ApplicationException(("i peab olema määratud"));
        }
        if (i < 1) {
            throw new ApplicationException("i peab olema suurem kui 0");
        }
        if (i == 1) {
            return 0;
        }
        if (i == 2) {
            return 1;
        }
        return fib(i-1) + fib(i - 2);
    }
}
