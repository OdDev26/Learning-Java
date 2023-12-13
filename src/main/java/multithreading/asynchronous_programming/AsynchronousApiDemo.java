package multithreading.asynchronous_programming;

import java.util.concurrent.CompletableFuture;

public class AsynchronousApiDemo {
    // Synchronous(blocking) method
    public static void sendMail(){
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        System.out.println("Mail was sent");
    }

    public static CompletableFuture<Void> sendAsync(){
        // It's best to implement a long running operation (e.g querying a database, making an api call,working with a file system, etc.) asynchronously
        return CompletableFuture.runAsync(()->sendMail()); // With this we've made the synchronous method asynchronous(non-blocking)
    }

    public static void main(String[] args) throws InterruptedException {
//        AsynchronousApiDemo.sendMail();
        // Another operation below would be blocked until mail is sent
//        System.out.println("Hello there");

        AsynchronousApiDemo.sendAsync();
        System.out.println("Hello there");
        Thread.sleep(3000);  // To cause a delay in the main thread (not needed in a real world application) so we see
                                  // the output of the sendAsync() method


    }
}
