package org.example.collections;

import java.util.Iterator;
import java.util.Spliterator;
import java.util.function.Consumer;

public class GenericList<T> implements Iterable<T>{
    private T[] items= (T[])new Object[20];
    private int count;

    public T get(int index){
       return items[index];
    }

    @Override
    public Iterator<T> iterator() { // we need this to iterate through the GenericList (Without caring about its contents), although we could also use a for each
        return null;
    }
}

