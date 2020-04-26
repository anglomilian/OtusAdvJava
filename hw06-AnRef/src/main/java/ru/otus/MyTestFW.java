package ru.otus;

import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.ArrayList;

public class MyTestFW{
    private final  String className;
    private final Class[] MY_ANNOTATIONS = new Class[]{BeforeEach.class, Test.class, AfterEach.class};
    private final int ANNOTATIONS_NUMBER = 3;
    private ArrayList<Method>[] triplets = new ArrayList[ANNOTATIONS_NUMBER];
    private final int BEFOREALL = 0;
    private final int TEST = 1;
    private final int AFTERALL = 2;

    MyTestFW(String clNm) {
        className = clNm;
    }

    public void testRunner() throws ClassNotFoundException{
        Class classString = Class.forName(className);
        Method[] meths = getClassMethods(classString);
        int testMethodsNumber = testMethodsCount(meths);
        int failedMethodsNumber = 0;
        if (testMethodsNumber == 0){
            System.out.println("No @Test methods. Nothing to test.");
        }else{
            initTriplets(triplets);
            fillMethodsList(meths);
            failedMethodsNumber = executioner(classString);
            System.out.println("--- All tests completed ---");
            System.out.println("Successful tests: " + (testMethodsNumber - failedMethodsNumber));
            System.out.println("Failed tests: " + failedMethodsNumber);

        }
    }

    private Method[] getClassMethods(Class<?> classString){
        Method[] classMethods = classString.getDeclaredMethods();
        return classMethods;
    }

    private int testMethodsCount(Method[] meths){
        int testMethodsCounter = 0;
        for (Method m : meths) {
            if(m.getAnnotation(MY_ANNOTATIONS[TEST]) != null){
                testMethodsCounter++;
            }
        }
        return testMethodsCounter;
    }

    private void fillMethodsList(Method[] methods){
        for(Method method : methods) {
            addToTriplets(method, triplets[BEFOREALL], BEFOREALL);
            addToTriplets(method, triplets[TEST], TEST);
            addToTriplets(method, triplets[AFTERALL], AFTERALL);
        }
    }

    private int executioner(Class classString) {
        int failedTestsNumber = 0;
        for (Method method : triplets[TEST]){
            try {
                //creating test class instance for each test
                System.out.println(" --- Start running test section --- ");
                Object testClassInstance = classString.getConstructor().newInstance();
                if(!invokeMethods(testClassInstance, method)){
                    failedTestsNumber++;
                }
            }
            catch (Exception e) {
                System.out.println(e.toString() + " Can not create instance of " + classString.getName());
            }

        }
        return failedTestsNumber;
    }

    private boolean invokeMethods(Object tCI, Method method) {
        runSupportMethods(tCI, triplets[BEFOREALL], BEFOREALL);
        try{
            unlockIfPrivate(method);
            method.invoke(tCI);
        }catch (Exception e){
            System.out.println("Test failed for method: " + method);
            return false;
        }
        runSupportMethods(tCI, triplets[AFTERALL], AFTERALL);
        return true;
    }

    private void runSupportMethods(Object tCI, ArrayList<Method> methods, int type){
        for(Method method : methods){
            try{
                unlockIfPrivate(method);
                method.invoke(tCI);
            }
            catch (Exception e){
                String methodType = type == 0 ? "BEFOREALL" : "AFTERALL";
                System.out.println("Exception in " + methodType + " method: " + method.getName());
            }
        }
    }

    private void unlockIfPrivate(Method method){
        int modifiers = method.getModifiers();
        if (Modifier.isPrivate(modifiers)){
            method.setAccessible(true);
        }
    }
    

    private void addToTriplets(Method meth, ArrayList<Method> tr, int index){
        if (meth.getAnnotation(MY_ANNOTATIONS[index]) != null){
            tr.add(meth);
        }
    }

    private void initTriplets(ArrayList<Method>[] triplets) {
        for (int i = 0; i < ANNOTATIONS_NUMBER; i++) {
            triplets[i] = new ArrayList<>();
        }
    }
}
