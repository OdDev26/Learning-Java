package org.example.generics;

public class AnotherGenericList {
    public static <T extends Comparable<T>> T  max(T a, T b){
        // We need to do Comparable<T> so that we can compare T with another instance of T, e.g
        // So we can compare User with another User object, if we don't do this T would be compared with the Object class
        return a.compareTo(b) > 1?a:b;
    }

    public static <K,V> void print(K key, V value){
        System.out.println("Key="+key+", value="+value);
    }
}
