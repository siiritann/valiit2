package ee.bcs.valiit.tasks;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;
import java.util.Scanner;

// Enne kui seda tegema hakkad tee ära Lesson 2 (välja arvatud ülesanded 6, 8, 9)
public class Lesson3Hard {
    public static void main(String[] args) {

//        evenFibonacci(10);
//        randomGame();
//        morseCode("tere");
        evenFibonacci(9);

    }

    public static int evenFibonacci(int x){
        x++;
        // TODO liida kokku kõik paaris fibonacci arvud kuni numbrini x
        // Fibonacci jada on fib(n) = fib(n-1) + fib(n-2);
        // 0, 1, 1, 2, 3, 5, 8, 13, 21, 34
        // nt 2 > 0
        // nt 3 > 2
        // nt 6 >  2+8 = 10
        // nt 9 > 2+8+34=44

        if (x <= 0) {
            System.out.println(0);
            return 0;
        } else if (x == 1) {
            System.out.println(x);
            return x;
        }

        int[] fiboNumbersUntilx = new int[x];
        int sumOfEvenNumbers = 0;

        fiboNumbersUntilx[0] = 0;
        fiboNumbersUntilx[1] = 1;

        for (int i = 2; i < x; i++) {
            fiboNumbersUntilx[i] = fiboNumbersUntilx[i - 1] + fiboNumbersUntilx[i - 2];
            if (fiboNumbersUntilx[i] % 2 == 0) {
                sumOfEvenNumbers += fiboNumbersUntilx[i];
            }
        }
        System.out.println("sumOfEvenNumbers " + sumOfEvenNumbers);

        return sumOfEvenNumbers;

    }


    public static void randomGame(){
        // TODO kirjuta mäng mis võtab suvalise numbri 0-100, mille kasutaja peab ära arvama
        // iga kord pärast kasutaja sisestatud täis arvu peab programm ütlema kas number oli suurem või väiksem
        // ja kasutaja peab saama uuesti arvata
        // numbri ära aramise korral peab programm välja trükkima mitu katset läks numbri ära arvamiseks
        Random random = new Random();
        int i = random.nextInt(100);
        System.out.println(i);

        boolean hasWon = false;
        int count = 0;

        while (hasWon != true) {
            System.out.println("Arva ära minu number vahemikus 0-100: ");
            Scanner scanner = new Scanner(System.in);
            int guess = scanner.nextInt();
            if (guess == i){
                hasWon = true;
                count++;
                System.out.println("ÕIGE VASTUS, SINU VÕIT");
                System.out.println("Katsete arv õige numbri arvamiseks: " +  count);
            } else if (guess > i){
                count++;
                System.out.println("Õige number on väiksem");
            } else {
                count++;
                System.out.println("Õige number on suurem");
            }
        }
    }

    public static String morseCode(String text){ //
        // TODO kirjuta programm, mis tagastab sisestatud teksti morse koodis (https://en.wikipedia.org/wiki/Morse_code)
        // Kasuta sümboleid . ja -
        // kasuta selleks hashmapi

        HashMap<String, String> morseSymbols = new HashMap<String, String>();

        morseSymbols.put("a", ".-");
        morseSymbols.put("b", "-...");
        morseSymbols.put("c",  "-.-");
        morseSymbols.put("d",  "-..");
        morseSymbols.put("e",    ".");
        morseSymbols.put("f", "..-.");
        morseSymbols.put("g",  "--.");
        morseSymbols.put("h", "....");
        morseSymbols.put("i",   "..");
        morseSymbols.put("j", ".---");
        morseSymbols.put("k",   "-.");
        morseSymbols.put("l", ".-..");
        morseSymbols.put("m",   "--");
        morseSymbols.put("n",   "-.");
        morseSymbols.put("o",  "---");
        morseSymbols.put("p", ".--.");
        morseSymbols.put("q", "--.-");
        morseSymbols.put("r", ".-.");
        morseSymbols.put("s",  "...");
        morseSymbols.put("t",   "-");
        morseSymbols.put("u",  "..-");
        morseSymbols.put("v", "...-");
        morseSymbols.put("w",  ".--");
        morseSymbols.put("x", "-..-");
        morseSymbols.put("y", "-.--");
        morseSymbols.put("z", "--..");

        System.out.println("morseSymbols: " + morseSymbols);

        String morse = "";

        for (int i = 0; i < text.length(); i++) {
            String a = text.substring(i, i+1);
            String b = morseSymbols.get(a);
            morse += b+"    ";
        }
        System.out.println("morse:"  + morse);

        return morse;
    }
}
