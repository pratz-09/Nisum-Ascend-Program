package patterns;

interface Service {
    void request();
}

class RealService implements Service {
    public void request() {
        System.out.println("Real Service");
    }
}

class ProxyService implements Service {
    private RealService real = new RealService();

    public void request() {
        System.out.println("Proxy check");
        real.request();
    }
}

public class ProxyPattern {
    public static void main(String[] args) {
        Service s = new ProxyService();
        s.request();
    }
}