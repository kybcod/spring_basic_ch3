package com.fastcampus.ch3.aop;

import org.springframework.transaction.annotation.Transactional;

import java.lang.reflect.Method;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class AopMain {
    public static void main(String[] args) throws Exception {
        MyAdvice myAdvice = new MyAdvice();

        Class myClass = Class.forName("com.fastcampus.ch3.aop.MyClass");
        Object obj = myClass.newInstance();

        //myClass에 정의된 멤서드를 배열로 얻어와 invoke 메서드에 정보를 넘겨줌
        for(Method m : myClass.getDeclaredMethods()) {
            myAdvice.invoke(m, obj, null);
        }
    }
}

class MyAdvice {
    Pattern p = Pattern.compile("a.*"); //m.invoke 에 메서드 이름이 a로 시작하는 함수만 실행

    boolean matches(Method m){
        Matcher matcher = p.matcher(m.getName());
        return matcher.matches();
    }

    void invoke(Method m, Object obj, Object... args) throws Exception {
        if(m.getAnnotation(Transactional.class)!=null) // transaction 어노테이션잉 붙어있는 것만
            System.out.println("[before]{");

        m.invoke(obj, args); // aaa() 호출가능

        if(m.getAnnotation(Transactional.class)!=null)
            System.out.println("}[after]");

        // 패턴에 맞는 것만 적용
        /*if(matches(m)){
            System.out.println("[before]{");
        }

        m.invoke(obj, args); // aaa(), aaa2() 호출

        if(matches(m)){
            System.out.println("}[after]");
        }*/

    }
}

class MyClass {
    @Transactional
    void aaa() {
        System.out.println("aaa() is called.");
    }
    void aaa2() {
        System.out.println("aaa2() is called.");
    }
    void bbb() {
        System.out.println("bbb() is called.");
    }
}
