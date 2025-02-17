package design.patterns.creational;

/*
Builder is a creational design pattern that lets you construct complex
objects step by step. The pattern allows you to produce different types
and representations of an object using the same construction code.
 */

/*
> Separate the construction of an object from its representation.
> Create a builder class containing the same fields of the object you
need created.
> Add several setter-methods for these fields and a "build" method
responsible for creating the object.
> Think about creating a director if the same creation code is used to
create several objects.
    > Client must create both the builder and the director.
 */

class CarBuilder {
    // Required
    String brand;
    int seats;

    // Optional
    boolean needInteriorUpgrade;
    boolean needAllLed;

    public CarBuilder() {
    }

//    public CarBuilder(String brand, String color, int seats) {
//        // This is useful if you don't use optional Director class.
//        this.brand = brand;
//        this.seats = seats;
//    }

    public void setSeats(int seats) {
        this.seats = seats;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public void setNeedInteriorUpgrade(boolean needInteriorUpgrade) {
        this.needInteriorUpgrade = needInteriorUpgrade;
    }

    public void setNeedAllLed(boolean needAllLed) {
        this.needAllLed = needAllLed;
    }

    public Car build() {
        Car car;
        car = new Car(this);
        return car;
    }
}

class Car {
    // Optional
    boolean needInteriorUpgrade;
    boolean needAllLed;
    // Required
    private final String brand;
    private final int seats;

    public Car(CarBuilder builder) {
        this.brand = builder.brand;
        this.seats = builder.seats;
        this.needInteriorUpgrade = builder.needInteriorUpgrade;
        this.needAllLed = builder.needAllLed;
    }

    public String getBrand() {
        return brand;
    }

    public int getSeats() {
        return seats;
    }

    public boolean isNeedInteriorUpgrade() {
        return needInteriorUpgrade;
    }

    public boolean isNeedAllLed() {
        return needAllLed;
    }

    public String carSpec() {
        return "Car{ " +
                "Brand = '" + brand + '\'' +
                ", Seat count = " + seats +
                ", Interior = " + (needInteriorUpgrade ? "Luxury" : "Basic") +
                ", Lights = " + (needAllLed ? "LEDs" : "Halogen") +
                " }";
    }
}

class CarDirector {
    // Optional Class - Director

    public void makeBasicCar(CarBuilder builder) {
        builder.setBrand("5Seater");
        builder.setSeats(5);
        builder.setNeedInteriorUpgrade(false);
        builder.setNeedAllLed(true);

    }

    public void makeBigCar(CarBuilder builder) {
        builder.setBrand("7Seater");
        builder.setSeats(7);
        builder.setNeedInteriorUpgrade(true);
        builder.setNeedAllLed(false);
    }
}

public class Builder {
    //    Client
    public static void main(String[] args) {
        CarDirector carDirector = new CarDirector();

        CarBuilder basicCarBuilder = new CarBuilder();
        carDirector.makeBasicCar(basicCarBuilder);
        Car basicCar = basicCarBuilder.build();
        System.out.println(basicCar.carSpec());

        CarBuilder bigCarBuilder = new CarBuilder();
        carDirector.makeBigCar(bigCarBuilder);
        Car bigCar = bigCarBuilder.build();
        System.out.println(bigCar.carSpec());
    }
}