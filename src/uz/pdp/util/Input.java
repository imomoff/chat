package uz.pdp.util;

import java.util.Scanner;

public interface Input {
    static Integer inputInt (String msg) {
        System.out.print(msg);
        return new Scanner(System.in).nextInt();
    }
    static String inputStr (String msg) {
        System.out.print(msg);
        return new Scanner(System.in).nextLine();
    }
}
