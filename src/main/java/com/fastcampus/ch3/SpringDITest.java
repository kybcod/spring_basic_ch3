package com.fastcampus.ch3;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;

import java.util.Arrays;

class Car{
    String color;
    int oil;
    Engine engine;
    Door[] doors;

    public void setColor(String color) {
        this.color = color;
    }

    public void setOil(int oil) {
        this.oil = oil;
    }

    public void setEngine(Engine engine) {
        this.engine = engine;
    }

    public void setDoors(Door[] doors) {
        this.doors = doors;
    }


    @Override
    public String toString() {
        return "Car{" +
                "color='" + color + '\'' +
                ", oil=" + oil +
                ", engine=" + engine +
                ", doors=" + Arrays.toString(doors) +
                '}';
    }
}
class Engine{}
class Door{}

public class SpringDITest {
    public static void main(String[] args) {
        ApplicationContext context = new GenericXmlApplicationContext("config.xml");
        Car car = (Car)context.getBean("car"); //byName
        Car car2 = context.getBean("car2", Car.class); //byType
        // car, car2 같은 문장

        Engine engine = (Engine)context.getBean("engine");
        Door door = (Door)context.getBean("door");

        car.setColor("red");
        car.setOil(100);
        car.setEngine(engine);
        car.setDoors(new Door[]{context.getBean("door", Door.class), (Door) context.getBean("door")});

        System.out.println("car = " + car);
        System.out.println("engine = " + engine);
        System.out.println("door = " + door);

    }
}
