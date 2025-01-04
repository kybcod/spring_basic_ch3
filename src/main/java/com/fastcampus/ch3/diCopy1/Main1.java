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

    // 주어진 키(key)에 해당하는 클래스 이름을 config.txt에서 읽어와 해당 클래스를 동적으로 생성하여 반환하는 함수
    static Object getObject(String key) throws Exception {
        Properties prop = new Properties();
        prop.load(new FileReader("cofig.txt"));

        Class clazz = Class.forName(prop.getProperty(key));
        return clazz.newInstance();
    }
}
