package ru.otus;

import com.google.gson.*;

public class MyHWSerializationJSONDemo {

    public static void main(String[] args){
        Gson googleGson = new Gson();
        MyJSON myJSON = new MyJSON();

        ClassForTest a = new ClassForTest();
        System.out.println("My really complex Object.");
        System.out.println("JSON by Google:");
        System.out.println(googleGson.toJson(a));
        System.out.println("JSON by MyJSON:");
        System.out.println(myJSON.toJson(a));

        System.out.println();
        System.out.println("Other tests:");

        System.out.println(googleGson.toJson(22));
        System.out.println(myJSON.toJson(22));

        System.out.println(googleGson.toJson("test"));
        System.out.println(myJSON.toJson("test"));
    }
}
