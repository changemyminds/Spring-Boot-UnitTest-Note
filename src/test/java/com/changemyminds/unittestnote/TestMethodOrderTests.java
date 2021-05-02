package com.changemyminds.unittestnote;

import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

/**
 * Author: 張恩碩.
 * Date: 2021/5/2.
 * Description:
 * Reference:
 */
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class TestMethodOrderTests {
    @Test
    @Order(3)
    public void test3(){
        System.out.println("test3");
    }

    @Test
    @Order(2)
    public void test2(){
        System.out.println("test2");
    }

    @Test
    @Order(1)
    public void test1(){
        System.out.println("test1");
    }
}
