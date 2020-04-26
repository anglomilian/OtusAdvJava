package ru.otus;

public class ClassWithTests {
    public int publicFieldForDemo;

    public ClassWithTests() {
        System.out.println("Constructor");
    }
    @AfterEach
    public void testAfterMethod0()
    {
        System.out.println("AfterEach0");
    }

    @AfterEach
    private void testAfterMethod1()
    {
        System.out.println("AfterEach1");
    }

    @Test
    public void testTestMethod1() {
        System.out.println("TestMethod1");
    }

    @BeforeEach
    public void testBeforeEach()
    {
        System.out.println("BeforeEach");
    }

    @Test
    public void testTestMethod0() {
        System.out.println("TestMethod0");
    }

    @Test
    public void testTestExeption() {
        System.out.println("TestMethodException");
        throw new IllegalArgumentException("Wrong argument type!!!");
    }

}
