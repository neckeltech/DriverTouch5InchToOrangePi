package com.neckel.main;

import com.neckel.inch5.Touch5Inch;

public class Application {
    public static void main(String[] args) {
        System.out.println("inicialize...");

        Touch5Inch touch = new Touch5Inch();
        touch.inicialize();
    }
}
