package org.example.generics;

public class GenericList <T extends Comparable & Cloneable> // This means T must be Comparable and Cloneable(This are called type constraints)
     // The java compiler replaces all Ts with the Comparable interface, if there are no constraints all Ts are
    // replaced with the Object class at compile time (Type erasure)

         {
    private T[] items = (T[]) new Object[10]; // T represents the type of objects stored in this GenericList
    private int count;

   public void add(T item){
       items[count++]= item;
   }
}
