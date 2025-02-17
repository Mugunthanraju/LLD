package design.patterns.behavioral;

/*
Observer is a behavioral design pattern that lets you define a subscription
mechanism to notify multiple objects about any events that happen to the
object they’re observing.
*/

/*
> Allows you to change or take action on a set of objects when and if the
state of another object changes.
    > This can be done even if the modifiable set of objects is unknown
    beforehand or changes dynamically.
You can introduce new subscriber classes without having to change the
publisher's code, and vice versa if there's a publisher interface.
*/

import java.util.ArrayList;
import java.util.List;

// Subject (Observable)
interface Subject {
    void registerObserver(ObserverInterface observerInterface);
    void removeObserver(ObserverInterface observerInterface);
    void notifyObservers();
}

// Concrete Subject (ConcreteObservable)
class WeatherData implements Subject {
    private List<ObserverInterface> observerInterfaces;
    private float temperature;
    private float humidity;
    private float pressure;

    public WeatherData() {
        observerInterfaces = new ArrayList<>();
    }

    @Override
    public void registerObserver(ObserverInterface observerInterface) {
        observerInterfaces.add(observerInterface);
    }

    @Override
    public void removeObserver(ObserverInterface observerInterface) {
        observerInterfaces.remove(observerInterface);
    }

    @Override
    public void notifyObservers() {
        for (ObserverInterface observerInterface : observerInterfaces) {
            observerInterface.update(temperature, humidity, pressure);
        }
    }

    public void measurementsChanged() {
        notifyObservers();
    }

    public void setMeasurements(float temperature, float humidity, float pressure) {
        this.temperature = temperature;
        this.humidity = humidity;
        this.pressure = pressure;
        measurementsChanged();
    }
}

// Observer
interface ObserverInterface {
    void update(float temperature, float humidity, float pressure);
}

// Concrete Observer (ConcreteObserver)
class CurrentConditionsDisplay implements ObserverInterface {
    private float temperature;
    private float humidity;

    public void update(float temperature, float humidity, float pressure) {
        this.temperature = temperature;
        this.humidity = humidity;
        display();
    }

    private void display() {
        System.out.println("Current conditions: " + temperature + "°F and " + humidity + "% humidity");
    }
}

// Client
public class Observer {
    public static void main(String[] args) {
        WeatherData weatherData = new WeatherData();

        CurrentConditionsDisplay currentConditionsDisplay = new CurrentConditionsDisplay();
        weatherData.registerObserver(currentConditionsDisplay);

        weatherData.setMeasurements(80.0f, 65.0f, 30.4f);
        weatherData.setMeasurements(82.0f, 70.0f, 29.2f);
    }
}