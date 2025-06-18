
package com.example.project.lifecycle;

import org.junit.jupiter.api.*;

public class LifecycleTest {

    @BeforeAll static void initAll() { System.out.println("Before All"); }
    @BeforeEach void init() { System.out.println("Before Each"); }
    @Test void test1() { System.out.println("Test 1"); }
    @AfterEach void tearDown() { System.out.println("After Each"); }
    @AfterAll static void tearDownAll() { System.out.println("After All"); }
}
