package com.demo.stream;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import com.demo.abs.Test;
import com.demo.abs.TestA;

public class ReduceTest {

    public static void main(String[] args) {
        Integer[] integers = new Integer[]{1, 3, 4, 6, 8};
        List<Integer> integerList = Arrays.asList(integers);
        Optional<Integer> integerOptional = integerList.stream().reduce(Integer::sum);
        integerOptional.ifPresent(System.out::println);
        int integerResult = integerList.stream().reduce(1, (a, b) -> a * b);    // 1是给定的初始值
        System.out.println(integerResult);
        int sum = integerList.stream().reduce(10, (a,b) -> a + b);
        System.out.println(sum);
        double ceil = integerList.stream().reduce(2000, (a, b) -> a / b);
        System.out.println(ceil);

        Test testA = new TestA();
        testA.create(new String[] {"12", "23"});
    }


}
