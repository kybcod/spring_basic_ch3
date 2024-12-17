package com.fastcampus.ch3.diCopy1;

import java.io.FileReader;
import java.util.Properties;

class Car{}
class SprotCar extends Car {}
class Truck extends Car {}

public class Main1 {
    public static void main(String[] args) {
        Car car = new SprotCar();
        System.out.println("car = " + car);
    }

    static Car getCar() throws Exception {
        Properties prop = new Properties();
        prop.load(new FileReader("cofig.txt"));

        Class clazz = Class.forName(prop.getProperty("car"));
        return (Car) clazz.newInstance();
    }
}
