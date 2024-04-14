package com.isaac.demo.lifecycle;

import org.junit.jupiter.api.*;

public class LifeCycleTest {
    @BeforeAll
    static void beforeAll() {
        System.out.println("---beforeAll call---");
    }

    @AfterAll
    static void afterAll() {
        System.out.println("---afterAll call---");
    }

    @BeforeEach
    void beforeEach() {
        System.out.println("---beforeEach call---");
    }

    @AfterEach
    void afterEach() {
        System.out.println("---afterEach call---");
    }

    @Test
    void test1() {
        System.out.println("test1 method test");
    }

    @Test
    void test2() {
        System.out.println("test2 method test");
    }



}
