package ee.bcs.valiit.tasks.randomGame;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.Scanner;

@Service
public class ExService {


    @Autowired
    private NamedParameterJdbcTemplate jdbcTemplate;

    public static int createRandomNumber() {
        Random random = new Random();
        int i = random.nextInt(100);
        return i;
    }


/*    public void updateCount(){

    }*/



}
