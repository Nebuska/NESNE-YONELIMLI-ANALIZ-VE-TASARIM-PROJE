package cihaz;

public interface IEkran {
    ITusTakimi.Secim AnaMenu();
    Kullanici GirisEkrani();
    void SicaklikGoster(float sicaklik);
    void Yaz(Message message);
}
