package cihaz;

public class Ekran implements IEkran, IObserver {

    private final ITusTakimi tusTakimi;

    public Ekran(TusTakimi tusTakimi) {
        this.tusTakimi = tusTakimi;
    }

    @Override
    public ITusTakimi.Secim AnaMenu() {
        System.out.println("1. Sıcaklığı Gör");
        System.out.println("2. Soğutucuyu Aç");
        System.out.println("3. Soğutucuyu Kapat");
        System.out.println("0. Programı Kapat");
        return tusTakimi.GetSecim();
    }

    @Override
    public Kullanici GirisEkrani() {
        System.out.print("Kullanıcı Adınızı Giriniz: ");
        String kullaniciAdi = tusTakimi.GetString();
        System.out.print("Şifrenizi Giriniz:");
        String sifre = tusTakimi.GetString();
        return new Kullanici(kullaniciAdi,sifre);
    }

    @Override
    public void SicaklikGoster(float sicaklik) {
        System.out.printf("Şuanki sıcaklık: %.1f°C\n",sicaklik);
    }

    private  String msgToStr(Message message) {
        switch (message) {
            default -> {
                return "UNKNOWN MESSAGE! CONTACT WITH ADMINISTER.";
            }
            case SuccessGiris -> {
                return "Hoşgeldiniz...";
            }
            case FailGiris -> {
                return "Girdiğiniz bilgiler geçersiz lütfen yeniden deneyiniz.";
            }
            case SuccessSogutucuAc -> {
                return "Soğutucu başarıyla çalıştırıldı.";
            }
            case FailSogutucuAc -> {
                return "Soğutucu zaten çalışıyor.";
            }
            case SuccessSogutucuKapat -> {
                return "Soğutucu başarıyla kapatıldı.";
            }
            case FailSogutucuKapat -> {
                return "Soğutucu zaten kapalı.";
            }
        }
    }

    @Override
    public void Yaz(Message message) {
        System.out.println(msgToStr(message));
    }

    @Override
    public void Update(Message message) {
        Yaz(message);
    }
}
