package ee.bcs.valiit.tasks.contoller;

import ee.bcs.valiit.tasks.service.ExService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import static ee.bcs.valiit.tasks.Lesson3Hard.morseCode;

@RestController
public class ExController {

    @GetMapping("randomgame")
    public String randomgameGreeting() {
        return "Arva ära minu number vahemikus 0-100: ";
    }

    @PostMapping("randomgame")
    public String randomgame(@RequestParam("nr") int guess){
        int randomNumber = ExService.createRandomNumber();

        if (guess == randomNumber) {
            return "guess  " + guess + ", randomNumber " + randomNumber + ". ÕIGE VASTUS, SINU VÕIT !  \n Katsete arv õige numbri arvamiseks: \" + count";
        } else if (guess > randomNumber) {
            return "guess  " + guess + ", randomNumber "+  randomNumber + ". Õige number on väiksem";
        } else {
            return "guess  " + guess + ", randomNumber "+  randomNumber + ". Õige number on suurem";
        }
    }

    //  TODO SIIA TAHAKS RANDOM GAME LOOPI  EHITADA
/*    @PostMapping("randomgame")
    public int randomGame(@RequestParam("nr") int guess) {
        return guess;
    }*/

    @GetMapping("morse")
    public String morse(@RequestParam("text") String text){
        return morseCode(text);
    }


}
