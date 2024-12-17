package com.fastcampus.ch3.diCopy1;

import java.io.FileReader;
import java.util.Properties;

class Car{}
class SprotCar extends Car {}
class Truck extends Car {}
class  Engine{}

public class Main1 {
    public static void main(String[] args) throws Exception {
        Car car = (Car) getObject("car");
        Engine engine = (Engine) getObject("engine");
        System.out.println("car = " + car);
    }

    static Object getObject(String key) throws Exception {
        Properties prop = new Properties();
        prop.load(new FileReader("cofig.txt"));

        Class clazz = Class.forName(prop.getProperty(key));
        return clazz.newInstance();
    }
}
