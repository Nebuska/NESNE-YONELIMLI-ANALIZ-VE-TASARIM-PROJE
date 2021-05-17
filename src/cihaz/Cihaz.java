package cihaz;

import veritabani.IVeritabani;
import veritabani.PostgreSQL;
import veritabani.Local;

import java.util.ArrayList;
import java.util.List;

public class Cihaz implements IObservable {
    private final IEkran ekran;
    private final IEyleyici eyleyici;
    private final ISicaklikAlgilayici sicaklikAlgilayici;
    private IVeritabani veritabani;
    private final List<IObserver> observers;

    @Override
    public void Attach(IObserver observer) {
        observers.add(observer);
    }

    @Override
    public void Detach(IObserver observer) {
        observers.remove(observer);
    }

    @Override
    public void Notify(Message message) {
        for (IObserver observer : observers) {
            observer.Update(message);
        }
    }

    public static class CihazBuilder {
        private final IEkran ekran;
        private final IEyleyici eyleyici;
        private final ISicaklikAlgilayici sicaklikAlgilayici;
        private IVeritabani veritabani;
        private final List<IObserver> observers;

        public enum Type {
            local,
            PostgreSQL,
            MySQL,
            MSSQL
        }

        public CihazBuilder() {
            ekran = new Ekran(new TusTakimi());
            eyleyici = new Eyleyici();
            sicaklikAlgilayici = new SicaklikAlgilayici();
            observers = new ArrayList<>();
            veritabani = null;
        }

        public CihazBuilder Veritabani(Type type) {
            switch (type) {
                case local -> veritabani = new Local();
                case PostgreSQL -> veritabani = new PostgreSQL();
                case MSSQL, MySQL -> {
                    System.out.println("Not available database");
                    System.exit(1);
                }
            }
            return this;
        }

        public Cihaz Build() {
            return new Cihaz(this);
        }
    }

    public Cihaz(CihazBuilder builder) {
        ekran = builder.ekran;
        eyleyici = builder.eyleyici;
        sicaklikAlgilayici = builder.sicaklikAlgilayici;
        veritabani = builder.veritabani;
        observers = builder.observers;
        Attach((IObserver) ekran);
        Attach((IObserver) veritabani);
        if (veritabani == null) {
            veritabani = new Local();
        }
    }

    public void Start() {
        Kullanici kullanici = null;
        do {
            if (kullanici != null) {
                Notify(Message.FailGiris);
            }
            kullanici = ekran.GirisEkrani();
        } while (!veritabani.IsPermitted(kullanici.kullaniciAdi,kullanici.sifre));
        Notify(Message.SuccessGiris);
        while (true) {
            switch (ekran.AnaMenu()) {
                case programiKapat -> {
                    return;
                }
                case sogutucuAc -> {
                    if (!eyleyici.IsWorking()) {
                        eyleyici.SogutucuAc();
                        Notify(Message.SuccessSogutucuAc);
                    } else {
                        Notify(Message.FailSogutucuAc);
                    }
                }
                case sicaklikGor -> ekran.SicaklikGoster(sicaklikAlgilayici.GetSicaklik());
                case sogutucuKapat -> {
                    if (eyleyici.IsWorking()) {
                        eyleyici.SogutucuKapat();
                        Notify(Message.SuccessSogutucuKapat);
                    } else {
                        Notify(Message.FailSogutucuKapat);
                    }

                }
            }
        }
    }


}