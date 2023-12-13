package functional_interfaces;

import java.util.function.Predicate;

public class PredicateExample {
    public static void main(String[] args) {
        Predicate<String> isLongerThan3= str-> str.length() >3;

        Predicate<String> isLessThan9= str-> str.length() < 9;

        // Chaining predicates
//        boolean result =isLongerThan3.and(isLessThan9).test("abcd");
//        boolean result =isLongerThan3.or(isLessThan9).test("abc");
        boolean result =isLongerThan3.negate().test("abc");
        System.out.println(result);

    }
}
