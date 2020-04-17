package ru.otus;
import java.util.*;

public class MyHWListDemo {
    public static void main(String[] args) {
        MyHWList<Integer> myTestList = new MyHWList<>();
        MyHWList<Integer> copyMyTestList = new MyHWList<>();
        //fill lists
        for(int i = 0; i < 30; i++ ) {
            copyMyTestList.add(i);
            myTestList.add(100 - i);
        }
        Utils.outputCollection("myTestList original: ", myTestList);
        Utils.outputCollection("copyMyTestList original: ", copyMyTestList);

        //set
        int e = myTestList.set(2, 22);
        System.out.println("Replaced element by set: " + e);
        //remove by index
        e = myTestList.remove(5);
        System.out.println("Removed by index: " + e);
        // remove object
        boolean r = myTestList.remove((Integer) 75);
        System.out.println("Removed Object: " + r);
        Utils.outputCollection("myTestList after set and removes: ", myTestList);

        //copy
        Collections.copy(copyMyTestList, myTestList);
        Utils.outputCollection("copyMyTestList after copy from myTestList", copyMyTestList);

        //add all
        Collections.addAll(copyMyTestList, 56, 99, 1002);
        Utils.outputCollection("copyMyTestList after addAll: ", copyMyTestList);

        //sort
        Collections.sort(copyMyTestList, Comparator.naturalOrder());
        Utils.outputCollection("copyMyTestList after sort: ", copyMyTestList);

        //books for custom comparator
        MyHWList<Book> books = new MyHWList<>();
        books.add(new Book("Java. The complete reference", "Herbert Schildt", 15.85));
        books.add(new Book("Effective Java.", "Joshua Bloch", 14.30));
        books.add(new Book("Clean code", "Robert C. Martin", 18.75));
        Utils.outputCollection("My new books original", books);

        Collections.sort(books, new PriceComparator());
        Utils.outputCollection("My new books sorted by price: ", books);

        Collections.sort(books, new NameComparator());
        Utils.outputCollection("My new books sorted by name: ", books);

        //Collections.addAll(Collection<? super T> c, T... elements)
        //Collections.static <T> void copy(List<? super T> dest, List<? extends T> src)
        //Collections.static <T> void sort(List<T> list, Comparator<? super T> c)

    }
}

class PriceComparator implements Comparator<Book>{
    @Override
    public int compare(Book a, Book b){
        return Double.compare(a.getPrice(), b.getPrice());
    }
}

class NameComparator implements Comparator<Book>{
    @Override
    public int compare(Book a, Book b){
        return a.getName().compareToIgnoreCase(b.getName());
    }
}

class Book{
    private String name;
    private String author;
    private double price;

    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }

    Book(String n, String a, double p){
        name = n;
        author = a;
        price = p;
    }

    @Override
    public String toString(){
        return String.format("\tBook name: %s, Author: %s, Price: %.02f" + System.lineSeparator(), name, author, price);
    }
}
