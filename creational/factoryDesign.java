import java.io.*;
import java.util.*;

// Example without factory design pattens

class Car {
    String engine;
    int seats;
    int price;
    String rc;
    String owner;
    String fuel;

    Car(String engine, int seats, int price, String fuel)
    {
        this.engine = engine;
        this.price  = price;
        this.seats = seats;
        this.fuel = fuel;
    }
    void buy()
    {
        System.out.println("congrats you have brought Car with "+engine+" "+price+" "+seats+" "+fuel);
    }
} 

class Bus {
    String seats;
    Boolean ac;
    Bus(String seats, Boolean ac){
        this.seats = seats;
        this.ac = ac;
    }
    void buy()
    {
        System.out.println("congrats you have brought bus with "+seats+" "+ac);
    }
}

class MotorCycle {
    String owner;
    int price;
    int cylinder;
    MotorCycle(String owner, int price, int cylinder){
        this.owner = owner;
        this.price = price;
        this.cylinder = cylinder;
    }

    void buy()
    {
        System.out.println("congrats you have brought MotorCycle with "+price+" "+cylinder+" "+owner);
    }
}

class WithoutFactoryDesign {
    static Car car;
    static MotorCycle bike;
    static Bus bus;
    
    public static void run(){
        String client = "BMW";
        if(client.equals("BMW"))
        {
            car = new Car("V8", 4, 800000, "Diesal");
            car.buy();
        }
        else if(client.equals("RE"))
        {
            bike = new MotorCycle("", 0, 1);
            bike.buy();
        }
        else if(client.equals("BUS")){
            bus = new Bus("TATA", true);
            bus.buy();
        }
    }
}
//  Example with Factory design pattern

interface Vehicle {
    void sell();
    void buy();
    void info();
}
class ConcreteCar implements Vehicle {
    private String engine;
    private int seats;
    private int price;
    private String rc;
    private String owner;
    private String fuel;

    public ConcreteCar(String engine, int seats, int price, String rc, String owner, String fuel) {
        this.engine = engine;
        this.seats = seats;
        this.price = price;
        this.rc = rc;
        this.owner = owner;
        this.fuel = fuel;
    }

    @Override
    public void buy() {
        System.out.println("Congrats! You bought a Car with engine: " + engine + ", seats: " + seats + ", price: " + price + ", fuel: " + fuel);
    }

    @Override
    public void sell() {
        System.out.println("Selling the car...");
    }

    @Override
    public void info() {
        System.out.println("Car Details - Engine: " + engine + ", Seats: " + seats + ", Price: " + price + ", RC: " + rc + ", Owner: " + owner + ", Fuel: " + fuel);
    }
}

class ConcreteBus implements Vehicle {
    private int seats;
    private String rc;
    private String owner;
    private boolean ac;

    public ConcreteBus(int seats, String rc, String owner, boolean ac) {
        this.seats = seats;
        this.rc = rc;
        this.owner = owner;
        this.ac = ac;
    }

    @Override
    public void buy() {
        System.out.println("Congrats! You bought a Bus with seats: " + seats + ", AC: " + ac);
    }

    @Override
    public void sell() {
        System.out.println("Selling the bus...");
    }

    @Override
    public void info() {
        System.out.println("Bus Details - Seats: " + seats + ", RC: " + rc + ", Owner: " + owner + ", AC: " + ac);
    }
}

class VehicleFactory {
    public static Vehicle createVehicle(String type, Object... params) {
        switch (type.toUpperCase()) {
            case "CAR":
                return new ConcreteCar(
                    (String) params[0], // engine
                    (int) params[1],    // seats
                    (int) params[2],    // price
                    (String) params[3], // rc
                    (String) params[4], // owner
                    (String) params[5]  // fuel
                );
            case "BUS":
                return new ConcreteBus(
                    (int) params[0],    // seats
                    (String) params[1], // rc
                    (String) params[2], // owner
                    (boolean) params[3] // ac
                );
            default:
                throw new IllegalArgumentException("Unknown vehicle type: " + type);
        }
    }
}


public class factoryDesign {
    
    public static void main(String[] args){
        // design pattern instance without factory design
        System.out.println("design pattern instance without factory design pattern\n");
        WithoutFactoryDesign designWithoutFactoryDesign = new WithoutFactoryDesign();
        designWithoutFactoryDesign.run();
        System.out.println();
        // design pattern instance with factory design
        
        System.out.println("design pattern instance with factory design pattern\n");

        Vehicle car = VehicleFactory.createVehicle("CAR", "V8", 4, 10000000, "SA 01 AB 0001", "Vivek Vishal", "Diesel");
        car.buy();
        car.info();

        System.out.println();
        // Example: Create a Bus
        Vehicle bus = VehicleFactory.createVehicle("BUS", 40, "KA 01 CD 1234", "John Doe", true);
        bus.buy();
        bus.info();

        System.out.println();
    }
}
