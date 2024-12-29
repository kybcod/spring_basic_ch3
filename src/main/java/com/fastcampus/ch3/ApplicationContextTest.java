package com.fastcampus.ch3;

import org.springframework.beans.factory.annotation.*;
import org.springframework.context.*;
import org.springframework.context.annotation.*;
import org.springframework.context.annotation.Scope;
import org.springframework.context.support.*;
import org.springframework.stereotype.*;

import java.util.Map;
import java.util.Properties;

@Component
@Scope("prototype")
class Door {}
@Component class Engine {}
@Component class TurboEngine extends Engine {}
@Component class SuperEngine extends Engine {}

@Component
class Car {
    @Value("red") String color;
    @Value("100") int oil;

    //@Autowired
    Engine engine;
    //@Autowired
    Door[] doors;

    // 기본 생성자 생성 시 Autowired 생략하면 안됨(null 값 가짐)
    // public Car(){}

    // 생성자
    // @Autowired //생략가능
    // 기본 생성자 없을 때는 기본 타입(String, int)는 Spring에서 관리되는 Bean이 아니기 때문에, 직접 값을 설정하거나 외부 환경(application.yml 등)에서 주입
    public Car(@Value("red") String color, @Value("100") int oil, Engine engine, Door[] doors) {
        this.color = color;
        this.oil = oil;
        this.engine = engine;
        this.doors = doors;
    }
}

//#는 SpEL(Expression, 속성 값을 가져오는 것)을 평가, $는 application.yml 등 외부 설정 값을 참조.
@Component
@PropertySource("../../../../resources/setting.properties")
class SysInfo{
    @Value("#{systemProperties['user.timezone']}")
    String timeZone;
    @Value("#{systemEnvironment['APPDATA']}")
    String currDir;
    @Value("${autosaveDir")
    String autosaveDir;
    @Value("${autosaveInterval}")
    int autosaveInterval;
    @Value("${autosave}")
    boolean autosave;

    @Override
    public String toString() {
        return "SysInfo{" +
                "timeZone='" + timeZone + '\'' +
                ", currDir='" + currDir + '\'' +
                ", autosaveDir='" + autosaveDir + '\'' +
                ", autosaveInterval=" + autosaveInterval +
                ", autosave=" + autosave +
                '}';
    }
}

public class ApplicationContextTest {
    public static void main(String[] args) {
        ApplicationContext ac = new GenericXmlApplicationContext("config.xml");
//      Car car = ac.getBean("car", Car.class); // 타입을 지정하면 형변환 안해도됨. 아래의 문장과 동일
        Car car  = (Car) ac.getBean("car"); // 이름으로 빈 검색
        Car car2 = (Car) ac.getBean(Car.class);   // 타입으로 빈 검색
        System.out.println("car = " + car);
        System.out.println("car2 = " + car2);

        System.out.println("ac.getBean(SysInfo.class) = " + ac.getBean(SysInfo.class));
        Map<String, String> map = System.getenv(); //현재 운영 체제 환경 변수(environment variables)를 가져오는 메서드
        System.out.println("map = " + map);

        Properties p = new Properties();
        System.out.println("Properties = " + p);
    }
}