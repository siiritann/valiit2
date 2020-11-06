package ee.bcs.valiit.tasks.controller;

import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.math.BigDecimal;
import java.util.HashMap;

public class BankController {
    // createAccount (accountNr) // ei valideeri
    // depositMoney (accountNr, money) // sellele kontole lisa raha
    // withdrawMoney (accountNr, money) // sellelt kontolt raha maha, validatsioonid
    // transferMoney (fromAccount, toAccount, money) // validatsioonid
    // getAccountBalance (ccountNr) // kui palju raha mul on sellel kontol

    // Raskem osa
    // createClient(filterName, lastName, ...) //
    // muuda createAccount (accountNr, clientId) // ühel kliendil saab olla mitu kontot
    // getBalanceHistory(accountNr) // kõik tehingud selle kontoga

    /*
    konto info hoidke mapis mitte listis
    sellisel juhul saab teha
    @
     */
/*    @PutMapping("account")
    public void depositMoney(@RequestParam("accountNr") String accountNR,
                             @RequestParam("money")BigDecimal money){
        Map<String, Object> accounts = new HashMap<>();
        accounts.put("EE123",null);
        accounts.put("EE124",null);
        accounts.put("EE125",null);
        accounts.get("EE126");
    }*/

    // sisuliselt 2 veeruga tabel??
    // mis on identifikaator, mille järgi tahame kätte saada -- paneme vasakule poole
    // paremale poole paneme kogu objekti
    // accountide puhul: key on konto nr ja value on objekt
    // vasakul pool konto nr, parema pool konto jääk
    // put kirjutab olemasoleva väärtuse üle
    // saab olla ainult 1 unikaalne key, value'sid võib olla mitu korda


//    public static void main(String[] args) {
//        Map<String, BigDecimal> accounts = new HashMap<>();
//        accounts.put("EE123", BigDecimal.ZERO);
//        accounts.put("EE124",BigDecimal.TEN);
//        accounts.put("EE125", new BigDecimal("234782934"));
//        System.out.println(accounts.get("EE124")); // mapist on key järgi lihtne parempoolne väärtus kätte saada
//    }

}
