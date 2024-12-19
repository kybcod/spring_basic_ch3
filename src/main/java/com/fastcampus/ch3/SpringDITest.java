package com.fastcampus.ch3;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.Arrays;

@Component("engine") class Engine{} // <bean id="engine" class"com.fastcampus.ch3.Engine"/>
@Component class SuperEngine extends Engine {}
@Component class TurboEngine extends Engine {}
@Component  class Door{}
@Component
class Car{
    @Value("red") String color;
    @Value("100") int oil;
    //@Autowired @Qualifier("superEngine") => byType
    // == @Resource(name = "superEngine") => byName
    @Resource(name = "superEngine") //
    Engine engine; // byType - 타입으로 먼저 검색, 여러개면 이름으로 검색 - engine, superEngine, turboEngine
    @Autowired Door[] doors;

    public Car() {} //기본 생성자 꼭 만들기

    public Car(String color, int oil, Engine engine, Door[] doors) {
        this.color = color;
        this.oil = oil;
        this.engine = engine;
        this.doors = doors;
    }

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

public class SpringDITest {
    public static void main(String[] args) {
        ApplicationContext context = new GenericXmlApplicationContext("config.xml");
        Car car = (Car)context.getBean("car"); //byName
        Car car2 = context.getBean("car2", Car.class); //byType
        // car, car2 같은 문장

        // Engine engine = (Engine)context.getBean("engine"); //byName
        // Engine engine = (Engine)context.getBean(Engine.class); //byType - 같은 타입이 여러 개일 경우 오류
        Engine engine = (Engine)context.getBean("superEngine"); //byType
        Door door = (Door)context.getBean("door");

        // set or autowired or resource 해주어야 함
        car.setColor("red");
        car.setOil(100);
        car.setEngine(engine);
        car.setDoors(new Door[]{context.getBean("door", Door.class), (Door) context.getBean("door")});

        System.out.println("car = " + car);
        System.out.println("engine = " + engine);
        System.out.println("door = " + door);

    }
}
