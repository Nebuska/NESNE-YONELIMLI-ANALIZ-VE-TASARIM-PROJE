package cihaz;

public interface IObservable {
    void Attach(IObserver observer);
    void Detach(IObserver observer);

    void Notify(Message message);
}
