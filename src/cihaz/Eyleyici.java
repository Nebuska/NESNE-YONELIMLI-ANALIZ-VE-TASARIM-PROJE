package cihaz;

public class Eyleyici implements IEyleyici {

    boolean isWorking = false;

    @Override
    public void SogutucuAc() {
        isWorking = true;
    }

    @Override
    public void SogutucuKapat() {
        isWorking = false;
    }

    @Override
    public boolean IsWorking() {
        return isWorking;
    }
}
