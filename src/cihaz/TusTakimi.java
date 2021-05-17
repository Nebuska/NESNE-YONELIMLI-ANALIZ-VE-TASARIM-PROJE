package cihaz;

import java.util.InputMismatchException;
import java.util.Scanner;

public class TusTakimi implements ITusTakimi {

    Scanner scanner;

    TusTakimi() {
        scanner = new Scanner(System.in);
    }

    @Override
    public String GetString() {
        return scanner.nextLine();
    }

    @Override
    public Secim GetSecim() {
        Secim secim;
        try {
            switch (scanner.nextInt()) {
                case 1 -> secim = Secim.sicaklikGor;
                case 2 -> secim = Secim.sogutucuAc;
                case 3 -> secim = Secim.sogutucuKapat;
                case 0 -> secim = Secim.programiKapat;
                default -> secim = Secim.none;
            }
        } catch (InputMismatchException e) {
            scanner.nextLine();
            secim = Secim.none;
        }
        return secim;


    }

}
