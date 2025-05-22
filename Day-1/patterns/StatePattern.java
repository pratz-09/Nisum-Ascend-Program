package patterns;

import java.util.*;
interface Observer { void update(String msg); }
class Subject {
    private List<Observer> observers = new ArrayList<>();
    void addObserver(Observer o) { observers.add(o); }
    void notifyObservers(String msg) { for (Observer o : observers) o.update(msg); }
}
class ConcreteObserver implements Observer {
    public void update(String msg) { System.out.println("Received: " + msg); }
}
interface State { void handle(); }
class OnState implements State { public void handle() { System.out.println("State: ON"); } }
class OffState implements State { public void handle() { System.out.println("State: OFF"); } }
class Context {
    private State state;
    void setState(State s) { state = s; }
    void request() { state.handle(); }
}
public class StatePattern {
    public static void main(String[] args) {
        Context c = new Context();
        c.setState(new OnState());
        c.request();
        c.setState(new OffState());
        c.request();
        
        Subject s = new Subject();
        s.addObserver(new ConcreteObserver());
        s.notifyObservers("Hello");
    }
}