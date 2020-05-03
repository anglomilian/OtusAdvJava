package ru.otus;

public class RandomString {
    private static final String ALPHABET_AND_NUMBERS_STRING = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";

    public static String getRandomString(int length){
        StringBuilder randomString = new StringBuilder();
        for (int i = 0; i < length; i++){
            int position = (int) (Math.random() * ALPHABET_AND_NUMBERS_STRING.length());
            randomString.append(ALPHABET_AND_NUMBERS_STRING.charAt(position));
        }
        return randomString.toString();
    }
}
