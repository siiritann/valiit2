package ee.bcs.valiit.tasks;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Scanner;

public class Lesson2 {

    public static void main(String[] args) {
//        exercise1();
//        exercise2(1);
//        exercise3(4,5);
//        exercise3(3,3);
/*        fibonacci(3);
        fibonacci(4);
        fibonacci(5);
        fibonacci(6);
        fibonacci(7);
        fibonacci(8);*/
        exercise5(22);
    }

    public static void exercise1() {
        // TODO loo 10 elemendile täisarvude massiv
        // TODO loe sisse konsoolist 10 täisarvu
        // TODO trüki arvud välja vastupidises järiekorras

        int[] arr = new int[10];
        Scanner scanner = new Scanner(System.in);
        for (int i = 0; i < arr.length; i++) {
            System.out.println("Please insert a number: ");
            arr[i] = scanner.nextInt();
        }

        for (int i = arr.length-1; i >= 0; i--) {
            System.out.println(arr[i]);
        }

    }

    public static void exercise2(int x) {
        // TODO prindi välja x esimest paaris arvu
        // Näide:
        // Sisend 5
        // Väljund 2 4 6 8 10
        int limit = x*2;
        for (int i = 1; i <= limit; i++) {
            if (i % 2 == 0) {
                System.out.println(i);
            }
        }
    }

    public static void exercise3(int x, int y) {
        // TODO trüki välja korrutustabel mis on x ühikut lai ja y ühikut kõrge
        // TODO näiteks x = 3 y = 3
        // TODO väljund
        // 1 2 3
        // 2 4 6
        // 3 6 9

        for (int i = 1; i <= y; i++) {
            for (int j = 1; j <= x; j++) {
                System.out.print(j*i + " ");
            }
            System.out.print("\n");
        }
    }

    public static int fibonacci(int n) {
        // TODO
        // Fibonacci jada on fib(n) = fib(n-1) + fib(n-2);
        // 0, 1, 1, 2, 3, 5, 8, 13, 21
        // Tagasta fibonacci jada n element

        if (n <= 0) {
            System.out.println(0);
            return 0;
        } else if (n == 1) {
            System.out.println(n);
            return n;
        }

        int fib = 1;
        int previousFib = 1;
        for (int i = 2; i < n; i++){
            int temp = fib;
            fib+= previousFib;
            previousFib = temp;
        }

        System.out.println(fib);
        return fib;
    }

    public static void exercise5(int n) {
        // https://onlinejudge.org/index.php?option=onlinejudge&Itemid=8&page=show_problem&problem=36

        // TODO 1 - alamfun mis leiab 3n+1 sequence-i pikkuse e kogu see jada

        // TODO 2 - tee tsükkel mis leiab i -> j kõige suurema tsükli pikkuse

       // tee sequence

        while (n != 1) {
            if (n % 2 == 0) {
                n = n/2;
                System.out.println("n is even: " + n);
            } else {
                n = 3*n + 1;
                System.out.println("n isn't even: " + n);

            }
        }
        System.out.println("STOP with 1 + " + n);



    }

    public static void exercise6() {
        /*
            Kirjutada Java programm, mis loeb failist visits.txt sisse looduspargi külastajad erinevatel jaanuari päevadel ning
            a) sorteerib külastuspäevad külastajate arvu järgi kasvavalt ning prindib tulemuse konsoolile;
            b) prindib konsoolile päeva, mil külastajaid oli kõige rohkem.
            Faili asukoht tuleb programmile ette anda käsurea parameetrina.
         */
    }

    public static void exercise7() {
        // TODO arvuta kasutades BigDecimali 1.89 * ((394486820340 / 15 ) - 4 )
        BigDecimal a = new BigDecimal("1.89");
        BigDecimal b = new BigDecimal("394486820345");
        BigDecimal c = new BigDecimal("15");
        BigDecimal d = new BigDecimal("4");

        System.out.println(b.divide(c, 4, RoundingMode.HALF_UP));
    }

    public static void exercise8() {
        /*
        Failis nums.txt on üksteise all 150 60-kohalist numbrit.

        Kirjuta programm, mis loeks antud numbrid failist sisse ja liidaks need arvud kokku ning kuvaks ekraanil summa.
        Faili nimi tuleb programmile ette anda käsurea parameetrina.

        VASTUS:
        Õige summa: 77378062799264987173249634924670947389130820063105651135266574
         */
    }

    public static void exercise9() {
        /* TODO
        Sama mis eelmises ülesandes aga ära kasuta BigInt ega BigDecimal klassi
         */
        /* Vihje: on kasutatud liste, stringe, arraysid, paned igasse sellesse ühe elemendi ja siis liidad omavhael läbi
         */
    }

}
