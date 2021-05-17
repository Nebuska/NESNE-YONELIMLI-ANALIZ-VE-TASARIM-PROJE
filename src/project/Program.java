package project;

import cihaz.Cihaz;

public class Program {
    public static void main(String[] args) {
        Cihaz cihaz = new Cihaz.
                CihazBuilder().
                Veritabani(Cihaz.CihazBuilder.Type.PostgreSQL).
                Build();
        cihaz.Start();
    }
}
