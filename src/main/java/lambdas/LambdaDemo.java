package lambdas;

public class LambdaDemo {

    public LambdaDemo(String message){}
    public void demo(String message){
        System.out.println(message);
    }
    public static void print1(String message){}
   public static void print(){
       greet((message)-> System.out.println(message));// We use () -> to represent functional interfaces e.g Printer, the body of ()-> can be anything
       Printer printer= (message)-> System.out.println(message); // We could also save the lambda as a variable

       // When we are only passing a parameter to an object's method in a lambda expression, we can replace the
       // lambda expression with a method reference by following this syntax:
       // Class/Object::method

       greet(System.out::println); //line 12 is the same as line 5
       greet((message -> LambdaDemo.print1(message))); // can be rewritten as:  greet(LambdaDemo::print1);

       greet((message -> new LambdaDemo(message)));  // can be rewritten as:      greet(LambdaDemo::new);





   }

   public static void show(){
        greet(LambdaDemo::print1); // is sames as : (message)-> LambdaDemo.print1(message)
   }

    public static void greet(Printer printer) {
        printer.print("Hello");
    }

    public static void main(String[] args) {

    }
}
