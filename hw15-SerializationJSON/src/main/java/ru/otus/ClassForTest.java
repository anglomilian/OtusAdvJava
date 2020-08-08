package ru.otus;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

class ClassForTest {

    private boolean boolValue = false;

    public Byte byteValue = 64;

    public Short shortValue = 128;

    public Integer intValue = 256;

    public Long longValue = 512L;

    public Float floatValue = 1024F;

    public Double doubleValue = 2.7D;

    private char charValue = 'V';

    public String stringValue = "Rabbit Roger";

    public InnerObject[] objectsArray = new InnerObject[] { new InnerObject(13, "Thirteenth"), new InnerObject(14, "Eric Foreman") };

    public Collection<InnerObject> objectsCollection = new ArrayList();

    public int[] intArray = new int[] { 7, 7, 7 };

    public List<Long> longCollection = new ArrayList();

    public List<Double> doubleCollection = new ArrayList();

    InnerObject w = new InnerObject(5, "Element");

    public ClassForTest() {
        objectsCollection.add(new InnerObject(128, "2 pow 7"));
        objectsCollection.add(new InnerObject(256, "2 pow 8"));
        objectsCollection.add(new InnerObject(512, "2 pow 9"));
        objectsCollection.add(new InnerObject(1024, "2 pow 10"));

        longCollection.add(45L);
        longCollection.add(60L);
        longCollection.add(75L);
        longCollection.add(90L);

        doubleCollection.add(12345.54321);
        doubleCollection.add(67890.09876);
        doubleCollection.add(54321.12345);
        doubleCollection.add(109876.67890);
    }
}

class InnerObject {
    int id;
    String value;

    public InnerObject(int key, String value) {
        this.id = key;
        this.value = value;
    }
}