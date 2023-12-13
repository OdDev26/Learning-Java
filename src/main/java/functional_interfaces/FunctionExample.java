package functional_interfaces;

import java.util.function.BinaryOperator;
import java.util.function.Function;
import java.util.function.UnaryOperator;

public class FunctionExample {
    public static void main(String[] args) {
        Function<String,Integer> function= str-> str.length();
        // To call this function
        Integer result= function.apply("January");
//        System.out.println(result);

        // Composing functions
        Function<String, String> replaceEquals= str->str.replace(":","=");
        Function<String, String> addBraces= str->"{"+str+"}";
        // Declarative programming
        String output= replaceEquals.andThen(addBraces).apply("key:value");
        // To achieve line 16 using compose, we do:
        output= addBraces.compose(replaceEquals).apply("key:value");

        // Example of BinaryOperator Function, takes two inputs and returns an output
        BinaryOperator<Integer> binaryOperator= (a,b)-> a+b;
        Function<Integer, Integer> function1= a-> a*a;
        Integer newOutput = binaryOperator.andThen(function1).apply(2, 3);
//        System.out.println(newOutput);

        // Example of UnaryOperator Function, takes an input and returns an output
        UnaryOperator<Integer> square= a-> a*a;
        UnaryOperator<Integer> increment= a-> a+1;
        Integer result2 = square.andThen(increment).apply(2);
        System.out.println(result2);





    }
}
