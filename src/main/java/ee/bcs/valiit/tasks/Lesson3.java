package ee.bcs.valiit.tasks;

import org.yaml.snakeyaml.util.ArrayUtils;

import java.util.*;

public class Lesson3 {

    public static void main(String[] args) {
        int[] arr = new int[]{2, 1, 1, 1};
//        sort(arr);
//        uusSumma(-2,44);
//        sumForArray(arr);
//        factorial(5);
//        reverseString("qwerty");
//        isPrime(5);
//        isPrime(6);
//        isPrime(7);
//        isPrime(-13);
//        isPrime(11);
//        isPrime(12);
//        isPrime(13);
//        isPrime(89);
//        isPrime(10007);
//        isPrime(10008);
    }


    public static int uusSumma(int x, int y) {
        // TODO liida kokku ja tagasta x ja y väärtus
        return (x+y);
    }

    public static int sumForArray(int[] x) {
        // Todo liida kokku kõik numbrid massivis x
        int sum = x[0];
        for (int i = 1; i < x.length; i++) {
            sum += x[i];
        }
        System.out.println(sum);
        return sum;
    }

    public static int factorial(int x) {
        // TODO tagasta x faktoriaal.
        // Näiteks
        // x = 5
        // return 5*4*3*2*1 = 120

        int factorial = 1;
        for (int i = 1; i <= x; i++) {
            factorial = factorial * i;
        }
        System.out.println(factorial);
        return factorial;

    }

    public static int[] sort(int[] arr) {
        // TODO sorteeri massiiv suuruse järgi
        // Näiteks {2, 6, 8, 1}
        // Väljund {1, 2, 6, 8}
//        Arrays.sort(a); ÕIGE aga ei tohi kasutada

        int temp;
        for (int i = 1; i < arr.length; i++) {
            for (int j = i; j > 0; j--) {
                if (arr[j] < arr[j - 1]) {
                    temp = arr[j];
                    arr[j] = arr[j - 1];
                    arr[j - 1] = temp;
                }
            }
        }
        System.out.println(Arrays.toString(arr));
        return arr;
    }

    public static String reverseString(String a) {
        // TODO tagasta string tagurpidi

        String reversedString = "";
        for (int i = a.length() - 1; i >= 0; i--) {
            reversedString += a.charAt(i);
        }
        System.out.println(reversedString);
        return reversedString;
    }

    public static boolean isPrime(int x) {
        // TODO tagasta kas sisestatud arv on primaar arv (jagub ainult 1 ja iseendaga)

        if (x <= 1) {
            return false;
        }

        for (int i = 2; i < x/2; i++) {
            if (x % i == 0) {
                System.out.println(false);
                return false;
            }
        }
        System.out.println(true);
        return true;
    }
}
