package veritabani;

import cihaz.IObserver;
import cihaz.Message;

import java.sql.*;
import java.time.LocalDateTime;

public class PostgreSQL implements IVeritabani, IObserver {
    @Override
    public boolean IsPermitted(String username, String password) {

        try {
            Connection conn = DriverManager.getConnection("jdbc:postgresql://localhost:5432/Sogutucu",
                    "postgres","123456");

            String sql = "SELECT * FROM \"Kullanici\" WHERE kullaniciadi = '" + username + "'";

            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);

            conn.close();

            while (rs.next()) {
                if (rs.getString("sifre").equals(password)) {
                    rs.close();
                    stmt.close();
                    return true;
                }
            }

            rs.close();
            stmt.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }


        return false;
    }

    private  String msgToStr(Message message) {
        switch (message) {
            default -> {
                return "UNKNOWN";
            }
            case SuccessGiris -> {
                return "SUCCESSFUL LOGIN";
            }
            case FailGiris -> {
                return "FAILED LOGIN";
            }
            case SuccessSogutucuAc -> {
                return "COOLER STARTED";
            }
            case FailSogutucuAc -> {
                return "FAILED TO START COOLER";
            }
            case SuccessSogutucuKapat -> {
                return "COOLER STOPPED";
            }
            case FailSogutucuKapat -> {
                return "FAILED TO STOP COOLER";
            }
        }
    }

    @Override
    public void Update(Message message) {
        try {
            Connection conn = DriverManager.getConnection("jdbc:postgresql://localhost:5432/Sogutucu",
                    "postgres","123456");



            String sql = "INSERT INTO public.\"Log\" VALUES ('"
                    + LocalDateTime.now().toString() + "','"
                    + msgToStr(message) + "');";

            Statement stmt = conn.createStatement();
            stmt.executeUpdate(sql);

            conn.close();
            stmt.close();

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
}
