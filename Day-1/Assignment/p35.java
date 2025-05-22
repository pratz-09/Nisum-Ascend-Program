package Assignment;

class Demo {
    int value;

    Demo(int value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return "Demo[value=" + value + "]";
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (!(obj instanceof Demo))
            return false;
        Demo other = (Demo) obj;
        return this.value == other.value;
    }

    @Override
    public int hashCode() {
        return Integer.hashCode(value);
    }
}

public class p35 {
    public static void main(String[] args) {
        Demo d1 = new Demo(10);
        Demo d2 = new Demo(10);

        System.out.println("toString(): " + d1.toString());
        System.out.println("equals(): " + d1.equals(d2));
        System.out.println("hashCode(): " + d1.hashCode());
    }
}
