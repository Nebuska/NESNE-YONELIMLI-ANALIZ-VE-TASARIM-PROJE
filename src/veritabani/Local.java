package veritabani;

import cihaz.IObserver;
import cihaz.Message;

public class Local implements IVeritabani, IObserver {
    @Override
    public boolean IsPermitted(String username, String password) {
        return username.equals("admin") && password.equals("admin");
    }

    @Override
    public void Update(Message message) {

    }
}
