package Assignment;

class Vehicle {
    String brand;
    int wheels;

    void displayInfo() {
        System.out.println("Brand: " + brand);
        System.out.println("Wheels: " + wheels);
    }
}

class Car extends Vehicle {
    int seats = 4;
}

class Bus extends Vehicle {
    int seats = 40;
}

public class P14_VehicleInheritance {
    public static void main(String[] args) {
        Car car = new Car();
        car.brand = "Honda";
        car.wheels = 4;
        System.out.println("Car Info:");
        car.displayInfo();
        System.out.println("Seats: " + car.seats);

        Bus bus = new Bus();
        bus.brand = "Volvo";
        bus.wheels = 6;
        System.out.println("\nBus Info:");
        bus.displayInfo();
        System.out.println("Seats: " + bus.seats);
    }
}
