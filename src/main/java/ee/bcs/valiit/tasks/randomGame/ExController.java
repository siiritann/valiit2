package ee.bcs.valiit.tasks.randomGame;

import ee.bcs.valiit.tasks.randomGame.ExService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

import static ee.bcs.valiit.tasks.Lesson3Hard.morseCode;

@RestController
public class ExController {

    @Autowired
    private NamedParameterJdbcTemplate jdbcTemplate;

    // 1. create new game
    @PostMapping("addnewgame")
    public String createGame(){
        String sql = "INSERT INTO game (random_number, count) VALUES (:randomNumber, :count)";
        int randomNumber = ExService.createRandomNumber();
        Map<String, Object> paramMap = new HashMap<>();
        paramMap.put("randomNumber", randomNumber);
        paramMap.put("count", 0);
        jdbcTemplate.update(sql, paramMap);
        return "New game created";
    }

    // 2. Post guess to existing game
    @PostMapping("randomgame/{id}/{g}")
    public String postGuess(@PathVariable("id") int id,
                            @PathVariable("g") int guess){

        // get game by id
        String sql = "SELECT * FROM game WHERE id = :id";
        Map<String, Object> paramMap = new HashMap<>();
        paramMap.put("id", id);
        Game game = jdbcTemplate.queryForObject(sql, paramMap, new GameRowMapper());


        // compare guess and correct number, send response

        if (guess == game.getRandomNumber()) {
            return "guess  " + guess + ", randomNumber " + game.getRandomNumber() + ". ÕIGE VASTUS, SINU VÕIT !  \n Katsete arv õige numbri arvamiseks: \" + count";
        }

        String response = "";
        if (guess > game.getRandomNumber()) {
            response = "guess  " + guess + ", randomNumber "+  game.getRandomNumber() + ". Õige number on väiksem";
        } else {
            response = "guess  " + guess + ", randomNumber "+  game.getRandomNumber() + ". Õige number on suurem";
        }

        // if wrong answer, update attempts
        String sqlUpdateAttempt = "UPDATE game SET count = :count WHERE id = :id";
        Map<String, Object> paramMapUpdate = new HashMap<>();
        paramMapUpdate.put("id", id);
        paramMapUpdate.put("count", game.getCount()+1);
        jdbcTemplate.update(sqlUpdateAttempt, paramMapUpdate);

        return response;

    }


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
