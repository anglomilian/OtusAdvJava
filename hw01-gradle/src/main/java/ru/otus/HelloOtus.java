package ru.otus;

import com.google.common.collect.Lists;

import java.util.List;

public class HelloOtus {
    final private int EGGS_PRICE = 20;
    final private int GRECHKA_PRICE = 30;
    final private int TOILET_PAPER_PRICE = 40;
    private List<String> names = Lists.newArrayList();
    //"eggs", "toilet paper", "grechka"
    HelloOtus(String... goodsList ){
        if (goodsList.length > 0) {
            for (String good : goodsList) {
                names.add(good);
            }
        } else{
            names.add("Empty");
        }
    }

    int CheckOut(){
        int totalSum = 0;

        for (String item:names){
            switch (item){
                case "grechka": totalSum+=GRECHKA_PRICE;
                case "eggs" : totalSum+=EGGS_PRICE;
                case "toilet paper" : totalSum+=TOILET_PAPER_PRICE;
            }
        }
        return totalSum;
    }
}
