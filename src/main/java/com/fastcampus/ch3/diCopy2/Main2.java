package com.fastcampus.ch3.diCopy2;

import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

class Car{}
class SprotCar extends Car {}
class Truck extends Car {}
class  Engine {}

class AppContext{
    Map map;

    AppContext(){

        try {
            Properties prop = new Properties();
            prop.load(new FileReader("config.txt"));

            // Properties 에 저장된 내용을 Map에 저장
            map = new HashMap(prop);

            // 반복문으로 클래스 이름을 얻어서 객체를 생성해서 다시 map에 저장
            for (Object key : map.keySet()) {
                Class clazz = Class.forName((String) map.get(key));
                map.put(key, clazz.newInstance());
            }

            Class clazz = Class.forName(prop.getProperty("car"));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }


    Object getBean(String key){
        return map.get(key);
    }
}

public class Main2 {
    public static void main(String[] args) throws Exception {
        AppContext context = new AppContext();
        Car car = (Car) getBean("car");
        Engine engine = (Engine) getBean("engine");
        System.out.println("car = " + car);
        System.out.println("engine = " + engine);
    }

    static Object getBean(String key) throws Exception {
        Properties prop = new Properties();
        prop.load(new FileReader("config.txt"));

        Class clazz = Class.forName(prop.getProperty(key));
        return clazz.newInstance();
    }
}
