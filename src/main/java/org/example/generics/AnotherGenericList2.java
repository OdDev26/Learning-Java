package org.example.generics;

import java.util.List;

public class AnotherGenericList2 {
    public static void print(List<?> users){ // ? can be anything
        System.out.println("");
    }
    public static void print1(List<? extends AnotherGenericList> users){ // ? must be a child of AnotherGenericList
        System.out.println("");
    }

    public static void print2(List<? super AnotherGenericList> users){ // means ? is an Object
        System.out.println("");
    }
}
