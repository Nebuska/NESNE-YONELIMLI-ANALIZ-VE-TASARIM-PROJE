package cihaz;

public interface ITusTakimi {

    enum Secim {
        none,
        programiKapat,
        sicaklikGor,
        sogutucuAc,
        sogutucuKapat
    }

    String GetString();
    Secim GetSecim();
}
