package ee.bcs.valiit.tasks;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SiiriNotes {

    public static void main(String[] args) {
        // demonstreerime floati ebatäpsust:

        float number2 = 1.89f;
        int a = 11;
        System.out.println("float: " + number2 * a);
        BigDecimal number3 = new BigDecimal("1.89"); // STRINGINA INPUT
        BigDecimal number4 = new BigDecimal(1.89); // ARVUNA INPUT
        System.out.println("BigDecimal 1: " + number3.multiply(BigDecimal.valueOf(a)));
        System.out.println("BigDecimal 2: " + number4.multiply(BigDecimal.valueOf(a)));
    }

    // asjad mida võiks teada
// String.substring(1,2);
// String.split(""); -- sulgudesse tähemärk
// String.trim();

//    List<String> b = new ArrayList<>();
//    b.get(0);
//    b.add("midagi");
//    b.size();
//    b.remove(0);
//    b.set(0, "midagimuud");
//
//    Map<String, String> c = new HashMap<>();
//    c.put("key", "value");
//    c.get("key");



}
