package ru.otus;

import java.util.List;

public class Utils {
    public static <E extends List> void outputCollection(String message, E output){
        System.out.print(System.lineSeparator() + message + System.lineSeparator());
        for(int i = 0; i < output.size(); i++ ){
            System.out.print(output.get(i).toString() + "  ");
        }
        System.out.print(System.lineSeparator());
        System.out.println("List size: " + output.size());
    }
}
