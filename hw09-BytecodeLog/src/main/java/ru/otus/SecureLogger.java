package ru.otus;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class SecureLogger {

    private static final Set<Method> methodsForLogging = new HashSet<>();

    static <T> T createClassLogger(Class<T> classForLog) {
        InvocationHandler handler = new OperationsInvocationHandler<>(new MoneyTransferOperations());
        Method[] originalClassMethods = classForLog.getDeclaredMethods();
        Method[] proxyClassMethods = MoneyTransferInterface.class.getDeclaredMethods();
        checkProxyAndClassDeclaredMethods(originalClassMethods, proxyClassMethods);
        return (T) Proxy.newProxyInstance(SecureLogger.class.getClassLoader(),
                classForLog.getInterfaces(), handler);
    }

    private static void checkProxyAndClassDeclaredMethods(Method[] oMethods, Method[] pMethods){
        for (Method originalMethod : oMethods){
            if(checkLogAnnotation(originalMethod)){
                for (Method proxyMethod : pMethods){
                    if(checkMethodsForEquality(originalMethod, proxyMethod)){
                        methodsForLogging.add(proxyMethod);
                        break;
                    }
                }
            }
        }
    }

    private static boolean checkLogAnnotation(Method method){
        return method.getDeclaredAnnotation(Log.class) != null;
    }

    private static boolean checkMethodsForEquality(Method m1, Method m2){
        if (m1.getName().equals(m2.getName()) && m1.getParameterCount() == m2.getParameterCount()){
            var m1ParamsTypes = m1.getParameterTypes();
            var m2ParamsTypes = m2.getParameterTypes();
            for (int counter = 0; counter < m1ParamsTypes.length; counter++){
                if (!m1ParamsTypes[counter].equals(m2ParamsTypes[counter])){
                    return false;
                }
            }
        } else {
            return false;
        }
        return true;
    }

    static class OperationsInvocationHandler<T> implements InvocationHandler {

        private final T myClassInstance;

        OperationsInvocationHandler(T myClInst) {
            this.myClassInstance = myClInst;
        }

        @Override
        public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
            boolean isLogging = methodsForLogging.contains(method);
            if (isLogging) {
                DateFormat df = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
                Calendar myCalendar = Calendar.getInstance();
                String outputMessage = String.format("Logging executed method %s at %s ", method.getName(), df.format(myCalendar.getTime())) +
                        String.format("params: %s", Arrays.toString(args));
                System.out.println(outputMessage);
            }
            return method.invoke(myClassInstance, args);
        }
    }
}