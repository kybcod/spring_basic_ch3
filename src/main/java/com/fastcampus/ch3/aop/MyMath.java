package com.fastcampus.ch3.aop;

import org.springframework.stereotype.Component;

@Component
public class MyMath {
    public int add(int a, int b) {
        int result = a + b;
        return result;
    }

    public int add(int a, int b, int c) {
        int result = a + b + c;
        return result;
    }

    public int sub(int a, int b) {
        int result = a - b;
        return result;
    }

    public int mul(int a, int b) {
        int result = a * b;
        return result;
    }

}
