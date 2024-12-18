package com.fastcampus.ch3;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;

class Car{ }
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

        System.out.println("car = " + car);
        System.out.println("engine = " + engine);
        System.out.println("door = " + door);

    }
}
