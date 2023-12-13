package functional_interfaces;

import java.util.Arrays;
import java.util.List;
import java.util.function.Consumer;

public class ConsumerExample {
    public static void main(String[] args) {
        List<Integer> integers= Arrays.asList(1,2,3);

        // Using consumer to print each item (Declarative style of programming)
       // integers.forEach((integer)-> System.out.println(integer));// (integer)-> System.out.println(integer) is a Consumer interface

        // Chaining Consumers
        List<String> items= Arrays.asList("a","b","c");
        Consumer<String> print= item-> System.out.println(item);
        Consumer<String> printUpperCase= item-> System.out.println(item.toUpperCase());
        items.forEach(print.andThen(printUpperCase));



    }
}
