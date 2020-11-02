package ee.bcs.valiit.tasks;

import java.util.Scanner;

public class Lesson1MathUtil {
    private String test;

    public Lesson1MathUtil(String test) {
        this.test = test;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Sisesta esimene täisarv: ");
        int a = scanner.nextInt();
        System.out.println("Sisesta teine täisarv: ");
        int b = scanner.nextInt();
        System.out.println(min(a, b));
        System.out.println(max(a, b));
        System.out.println(abs(a));
        System.out.println(abs(b));
        System.out.println(isEven(a));
        System.out.println(isEven(b));
        System.out.println("Sisesta kolmas täisarv: ");
        int c = scanner.nextInt();
        System.out.println(minFromThree(a,b,c));
        System.out.println(maxFromThree(a,b,c));

//        System.out.println(min(5,-7));

  /*      System.out.println("min väärtus " + min(-12,2));
        System.out.println("min väärtus " + min(12,2));
        System.out.println("max väärtus " + max(-12,2));
        System.out.println("max väärtus " + max(12,2));
        System.out.println("abs väärtus " + abs(-23312));
        System.out.println("abs väärtus " + abs(1212));
        System.out.println("isEven " + isEven(1));
        System.out.println("isEven " + isEven(12));
        System.out.println("isEven " + isEven(-12));
        System.out.println("isEven " + isEven(-13));
        System.out.println("minFromThree " + minFromThree(12,22, 111));
        System.out.println("maxFromThree " + maxFromThree(12,22, 11));*/
    }

    public void test(){
        System.out.println(test);
    }

    public static int min(int a, int b) {
        // TODO tagasta a ja b väikseim väärtus
//        return Math.min(a,b);
        return (a <= b ? a : b);
    }

    public static int max(int a, int b) {
        // TODO tagasta a ja b suurim väärtus
        return (a >= b ? a : b);
    }

    public static int abs(int a) {
        // TODO tagasta a absoluut arv
        if (a < 0) {
            return -a;
        } else {
            return a;
        }
//        return Math.abs(a);
    }

    public static boolean isEven(int a) {
        // TODO tagasta true, kui a on paaris arv
        // tagasta false kui a on paaritu arv
        return (a % 2 == 0);
    }

    public static int minFromThree(int a, int b, int c) {
        // TODO tagasta a, b ja c väikseim väärtus
        return min(a, min(b,c));
    }

    public static int maxFromThree(int a, int b, int c) {
        // TODO tagasta a, b ja c suurim väärtus
        return max(a, max(b,c));
    }

}
