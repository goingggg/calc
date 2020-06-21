package com.xiaoyin.calculator;

import java.nio.channels.Pipe;

//算术操作类
public class Operation {
    /**
     * @param num1
     * @param num2
     * @return
     */
    public static int add(int num1, int num2) {
        return num1 + num2;
    }

    public static int add(String num1, String num2) {
        Integer integer1 = Integer.valueOf(num1);
        Integer integer2 = Integer.valueOf(num2);
        return integer1 + integer2;
    }

    public static int sub(int num1, int num2) {
        return num1 - num2;
    }

    public static int sub(String num1, String num2) {
        Integer integer1 = Integer.valueOf(num1);
        Integer integer2 = Integer.valueOf(num2);
        return integer1 - integer2;
    }

    public static int mult(int num1, int num2) {
        return num1 * num2;
    }

    public static int mult(String num1, String num2) {
        Integer integer1 = Integer.valueOf(num1);
        Integer integer2 = Integer.valueOf(num2);
        return integer1 * integer2;
    }

    public static int divide(int num1, int num2) {
        return num1 / num2;
    }

    public static int divide(String num1, String num2) {
        Integer integer1 = Integer.valueOf(num1);
        Integer integer2 = Integer.valueOf(num2);
        return integer1 / integer2;
    }

    public static int mixOperat(String str) {
        char[] chars = str.toCharArray();
        if (str.indexOf("+")!=-1){
            String[] split = str.split("\\+");
        }
    return 0;
    }
}
