package ee.bcs.valiit.tasks;

import java.io.File;
import java.lang.reflect.Array;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.math.RoundingMode;
import java.util.*;

import static ee.bcs.valiit.tasks.Lesson3.sort;

public class Lesson2 {

    public static void main(String[] args) {
//        exercise1();
//        exercise2(4);
//        exercise3(4,5);
//        exercise3(3,3);
//        test();
/*        fibonacci(3);
        fibonacci(4);
        fibonacci(5);
        fibonacci(6);
        fibonacci(7);
        fibonacci(8);*/
/*      exercise5(1, 10);
        exercise5(100, 200);
        exercise5(201, 210);
        exercise5(900, 1000);*/

//        exercise6();//
//        int[] arr = new int[]{1,34,6};
//        calcMinFromArr(arr);
//        calcMaxFromArr(arr);
//        exercise7();
//        exercise8();
        exercise9();

    }


    public static void test() {    // vaheta ära a ja b asukoht nii, et ei võta kasutusele kolmandat muutujat
//      hea tööintervjuu ül
        int a = 14;
        int b = 80;

        a = a + b;
        b = a - b;
        a = a - b;

        System.out.println(a + " / " + b);
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

        for (int i = arr.length - 1; i >= 0; i--) {
            System.out.println(arr[i]);
        }

    }

    public static void exercise2(int x) {
        // TODO prindi välja x esimest paaris arvu
        // Näide:
        // Sisend 5
        // Väljund 2 4 6 8 10
        for (int i = 1; i <= x * 2; i++) {
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
                System.out.print(j * i + " ");
            }
            System.out.print("\n");
        }
    }

    public static int fibonacci(int n) {
        // TODO
        // Fibonacci jada on fib(n) = fib(n-1) + fib(n-2);
        // 0, 1, 1, 2, 3, 5, 8, 13, 21
        // nt 8 > 21
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
        for (int i = 2; i < n; i++) {
            int temp = fib;
            fib += previousFib;
            previousFib = temp;
        }
        System.out.println(fib);
        return fib;
    }

    public static void exercise5(int i, int j) {
        // https://onlinejudge.org/index.php?option=onlinejudge&Itemid=8&page=show_problem&problem=36

        // TODO 1 - alamfun mis leiab 3n+1 sequence-i pikkuse e kogu see jada
        // TODO 2 - tee tsükkel mis leiab i -> j kõige suurema tsükli pikkuse

        int maxLength = 0;

        for (int k = i; k <= j; k++) {
            int lengthOfThisArray = calcCycleLength(k);
            if (lengthOfThisArray > maxLength) {
                maxLength = lengthOfThisArray;
            }
        }
        System.out.println("i: " + i + " , j: " + j + " , maxLength: " + maxLength);
    }

    public static int calcCycleLength(int k) {
        int countSeq = 1;
        while (k != 1) {
            if (k % 2 == 0) {
                k = k / 2;
                countSeq++;
            } else {
                k = 3 * k + 1;
                countSeq++;
            }
        }
        return countSeq;
    }

    public static void exercise6() {
        /*
            Kirjutada Java programm, mis loeb failist visits.txt sisse looduspargi külastajad erinevatel jaanuari päevadel ning
            a) sorteerib külastuspäevad külastajate arvu järgi kasvavalt ning prindib tulemuse konsoolile;
            b) prindib konsoolile päeva, mil külastajaid oli kõige rohkem.
            //  POLE VAJA - Faili asukoht tuleb programmile ette anda käsurea parameetrina.

            Küsimused
            1. a) prindin konsoolile mille - ainult kuupäevad? - mõlemad veerud
            2. Kuidas hoiustan date tüüpi? > string

         */

        //  tema näide
/*
        BigDecimal b1 = BigDecimal.ONE;
        BigDecimal b2 = BigDecimal.TEN;
        System.out.println(b1.compareTo(b2));
        if (b1.compareTo(b2) == -1){
            System.out.println("True");
        }
*/

        try {
            List<Visit> visits = readLinesFromFileToArr();
            visits.sort(new Comparator<Visit>() {
                @Override
                public int compare(Visit o1, Visit o2) {
//                    return o1.getVisits() - o2.getVisits();
                    if (o1.getVisits() < o2.getVisits()) {
//                        System.out.println(-1);
                        return -1;
                    } else if (o1.getVisits() > o2.getVisits()) {
//                        System.out.println(1);
                        return 1;
                    } else
//                        System.out.println(0);
                        return 0;
                } // TODO - compare vs Comparator
            });

            for (int i = 0; i < visits.size(); i++) {
                System.out.println(visits.get(i).getDate() + ", " + visits.get(i).getVisits());
            }

            String minVisitorsDate = visits.get(0).getDate();
            System.out.println("Päev, mil külastajaid on kõige vähem: " + minVisitorsDate);
            String maxVisitorsDate = visits.get(visits.size() - 1).getDate();
            System.out.println("Päev, mil külastajaid on kõige rohkem: " + maxVisitorsDate);

        } catch (Exception e) {
            System.out.println("Didn't find file");
        }
        ;
    }

    public static List readLinesFromFileToArr() throws Exception {
        File file = new File("C:\\Users\\opilane\\Desktop\\vali-it\\src\\main\\resources\\test_data\\visits.txt");
        Scanner scanner = new Scanner(file);
        ArrayList<Visit> visitsList = new ArrayList<>();
        while (scanner.hasNextLine()) {
            String line = scanner.nextLine();
            String[] stringArr = line.split(", ");
            Visit visit = new Visit(stringArr[0], Integer.parseInt(stringArr[1]));
            visitsList.add(visit);
        }

        // print out final array elements
/*
         for (int i = 0; i < visitsList.size(); i++) {
            System.out.println(" get visits " + visitsList.get(i).getVisits());
            System.out.println(" get date " + visitsList.get(i).getDate());
        }
*/
        // print out final array size
//        System.out.println("size: " + visitsList.size());

        return visitsList;
    }

    public static int findMaxFromArrList(ArrayList a) {
        return 0;
    }

    public static int calcMaxFromArr(int[] inputArray) {
        int maxValue = inputArray[0];
        for (int i = 0; i < inputArray.length; i++) {
            if (inputArray[i] > maxValue) {
                maxValue = inputArray[i];
            }
        }
        System.out.println(maxValue);
        return maxValue;
    }

    public static int calcMinFromArr(int[] inputArray) {
        int minValue = inputArray[0];
        for (int i = 0; i < inputArray.length; i++) {
            if (inputArray[i] < minValue) {
                minValue = inputArray[i];
            }
        }
        System.out.println(minValue);
        return minValue;
    }

    public static void exercise7() {
        // TODO arvuta kasutades BigDecimali 1.89 * ((394486820340 / 15 ) - 4 )

        BigDecimal a1 = new BigDecimal(1.89);
        BigDecimal a2 = BigDecimal.valueOf(1.89);
        BigDecimal a3 = new BigDecimal("1.89");

        BigDecimal b = new BigDecimal("394486820340");
        BigDecimal c = new BigDecimal("15");
        BigDecimal d = new BigDecimal("4");

        BigDecimal result1 = a1.multiply(b.divide(c).subtract(d));
        BigDecimal result2 = a2.multiply(b.divide(c).subtract(d));
        BigDecimal result3 = a3.multiply(b.divide(c).subtract(d));

        System.out.println(result1);
        System.out.println(result2);
        System.out.println(result3);
    }

    public static void exercise8() {
        /*
        Failis nums.txt on üksteise all 150 60-kohalist numbrit.

        Kirjuta programm, mis loeks antud numbrid failist sisse ja liidaks need arvud kokku ning kuvaks ekraanil summa.
        Faili nimi tuleb programmile ette anda käsurea parameetrina.

        VASTUS:
        Õige summa: 77378062799264987173249634924670947389130820063105651135266574

         */

        try {
            File file = new File("C:\\Users\\opilane\\Desktop\\vali-it\\src\\main\\resources\\test_data\\nums.txt");
            Scanner scanner = new Scanner(file);
            BigInteger sum = BigInteger.ZERO;
            while (scanner.hasNextLine()) {
                BigInteger lineSum = new BigInteger(scanner.nextLine());
                sum = sum.add(lineSum);
            }
            System.out.println(sum);

        } catch (Exception e) {
            System.out.println("Didn't find file");

        }


  /*      BigInteger a = new BigInteger("838552682653532063442707163856417161475719071047513455907864");
        BigInteger b = new BigInteger("648949689396999848781911817076973155576849197092442811518681");
        System.out.println(a);
        System.out.println(b);
        System.out.println(a.add(b));
        // ANSWER: 1487502372050531912224618980933390317052568268139956267426545*/
    }

    public static void exercise9() {
        /* TODO
        Sama mis eelmises ülesandes aga ära kasuta BigInt ega BigDecimal klassi
         */
        /* Vihje: on kasutatud liste, stringe, arraysid, paned igasse sellesse ühe elemendi ja siis liidad omavhael läbi
        string või int või long
        8872321823168954529444726936086760359964895464710509197 1 7 3 1 2
        6489496893969998487819118170769731555768491970924428115 1 8 6 8 1
                                                                3 5 9 9 3
        ja hakkad tagantpoolt numbreid kokku liitma

         */

        try {
            File file = new File("C:\\Users\\opilane\\Desktop\\vali-it\\src\\main\\resources\\test_data\\nums.txt");
            Scanner scanner = new Scanner(file);

            int noOfLines = 0;
            int lengthOfLine = 60;
            ArrayList finalAnswer = new ArrayList<>();
            while (scanner.hasNextLine()) {
                String line = (scanner.nextLine());
                noOfLines += 1;
            }
            System.out.println("noOfLines: " + noOfLines);
            while (scanner.hasNextLine()) {
                for (int i = lengthOfLine-1; i < lengthOfLine; i++) {
                    int lineSymbol = (scanner.nextInt());
                    finalAnswer.add(finalAnswer.size()-1);
                }
            }

//            for (int i = 0; i < ; i++) {
//
//            }
            //  lõppvastus
            // rida mida liidan otsa


        } catch (Exception e) {
            System.out.println("Didn't find file");

        }

    }

}
