package ee.bcs.valiit.tasks.service;

import org.springframework.stereotype.Service;

import java.util.Random;
import java.util.Scanner;

@Service
public class ExService {

    public static int createRandomNumber() {
        Random random = new Random();
        int i = random.nextInt(100);
        return i;
    }

}
