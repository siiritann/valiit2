/*
*
SEE TEST ON ILMA  SPRINGITA
*
* */


package ee.bcs.valiit;

import ee.bcs.valiit.tasks.Lesson1MathUtil;
import ee.bcs.valiit.tasks.Lesson2;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;


public class Lesson1MathUtilTest {

    @Test
    public void min(){
        assertEquals(2, Lesson1MathUtil.min(2,3));
        assertEquals(-2, Lesson1MathUtil.min(-2,3));
        assertEquals(-3, Lesson1MathUtil.min(-2,-3));
    }

    @Test
    public void max(){
        assertEquals(3, Lesson1MathUtil.max(2,3));
        assertEquals(3, Lesson1MathUtil.max(-2,3));
        assertEquals(-1, Lesson1MathUtil.max(-1,-3));
    }

    @Test
    public void abs(){
        assertEquals(2, Lesson1MathUtil.abs(2));
        assertEquals(2, Lesson1MathUtil.abs(-2));
    }

    @Test
    public void isEven(){
        assertEquals(true, Lesson1MathUtil.isEven(2));
        assertEquals(true, Lesson1MathUtil.isEven(-2));
        assertEquals(false, Lesson1MathUtil.isEven(3));
        assertEquals(false, Lesson1MathUtil.isEven(-3));
    }

    @Test
    public void excercise2Arr(){
        int[] arr = {2,4,6,8,10};
        assertArrayEquals(arr, Lesson2.exercise2Arr(5));
    }



//    @Test
//    public void double(){
//        double a = 1.0;
//        double b = 1.0;
//        assertEquals(a,b,0.00001); // lubame delta suurust erinevust
//    }
}
