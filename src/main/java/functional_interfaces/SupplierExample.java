package functional_interfaces;

import java.util.function.Supplier;

public class SupplierExample {
    public static void main(String[] args) {
        Supplier<Double> supplier= ()-> Math.random(); // This returns a function. To call the function and get its value, we do:
        Double result = supplier.get();
        System.out.println(result);

        // NB: When working with primitives like int, long. Prefer to use the Variations of the supplier interface like IntSupplier, etc

    }
}
