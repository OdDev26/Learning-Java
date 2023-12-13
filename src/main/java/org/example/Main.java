package org.example;

import org.example.collections.Customer;
import org.example.collections.EmailComparator;

import java.util.*;

public class Main {
    public static void main(String[] args) {
        // Boxing-> When Java compiler converts primitive type to wrapper class
        // Unboxing-> When Java compiler converts reference type (e.g Integer) to primitive type(e.g int)

    List<Customer> customerList= new ArrayList<>();
    customerList.add(new Customer("b", "e3"));
    customerList.add(new Customer("c", "e1"));
    customerList.add(new Customer("a", "e2"));

//    Collections.sort(customerList);
        Collections.sort(customerList,new EmailComparator()); // To sort by their email
    System.out.println(customerList);

    }
}