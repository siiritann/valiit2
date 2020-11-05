package ee.bcs.valiit.tasks;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import static ee.bcs.valiit.tasks.Lesson2.exercise9;

@SpringBootApplication
public class DemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(DemoApplication.class, args); // skännib kogu mu projekti java failid läbi
//        test(4);
    }

    public static void test(int n) {
        if (n <= 0) {
            System.out.println(0);
        }
        for (int i = 1; i <= n*2; i++) {
            if (i % 2 == 0) {
              if (i % 4 == 0) {
                System.out.println(Math.abs(i));}
              else System.out.println(-i);
            }
        }
    }
}
