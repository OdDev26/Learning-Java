package multithreading.asynchronous_programming;

import java.util.Collection;
import java.util.Collections;
import java.util.concurrent.*;

public class AsynchronousProgrammingDemo {
    public static CompletableFuture<String> fetchEmailAsync(){
        return CompletableFuture.supplyAsync(()->"email");
    }
    public static CompletableFuture<String> fetchPlaylistAsync(String email){
        return CompletableFuture.supplyAsync(()->"playlist");
    }
    public static void main(String[] args) {

        // To run a task that doesn't return a value and to ensure the task runs in an asynchronous(non-blocking) way,
        // We use a CompletableFuture runAsync() method:
//         CompletableFuture.runAsync(()-> System.out.println("1")); // which means run the task in an asynchronous way,
        // if we don't pass an executor class ,a common pool is used (ForkJoinPool.commonPool())
        // which spins a default no of threads based on our cpu (4cores = 8 threads), if the no of threads
        // is smaller or greater than the desired no of threads, you can specify the no you want by creating a thread
        //  pool as a second parameter


        // If the task would return a value and we want it to run asynchronously in a thread, we do:
        CompletableFuture<Integer> completableFuture = CompletableFuture.supplyAsync(() -> 1);


        // NB: The CompletableFuture class implements an interface CompletionStage: This represents a stage in
        // an asynchronous operation and has methods for combining an asynchronous operation steps/stages in a
        // declarative way


//        completableFuture.thenRun(()-> System.out.println("Done")); // With the thenRun() method from the CompletableStage interface we can run
//        another code after the CompletableFuture is done
        // But using the thenRun() method the task is handled on the main method

//        completableFuture.thenRunAsync(()-> System.out.println("Done")); // With the thenRunAsync() method the task is handled on an underlying thread
        //  in the common thread pool (ForkJoinPool)

        // To consume the result of the CompletableFuture and do something with it
//        completableFuture.thenAccept(result-> System.out.println(result));      // This would execute the task on the main thread
//        completableFuture.thenAcceptAsync(result-> System.out.println(result)); // This would execute the task on a thread in the common thread pool (ForkJoinPool)


        // Handling exceptions
//        CompletableFuture<Integer> result = CompletableFuture.supplyAsync(()-> {
//            System.out.println("Getting current weather");
//            throw new IllegalArgumentException();  // to simulate an exception, we don't see it in the main thread unless we call the get() method of result
//        });

        // Note: The CompletableFuture provides a way of chaining multiple calls thereby creating an asynchronous flow
        // Exception is thrown when we call the get() method of result
//        try {
//            Integer output = result.exceptionally(throwable -> 1).get();// With this our application won't crash and we can return a default value if an exception is thrown, in this case 1
        // from the call before exception is thrown
        // NB: The exceptionally() method creates a new CompletebaleFuture object
//            System.out.println(output);
//        } catch (InterruptedException | ExecutionException e) {
//            throw new RuntimeException(e);
//        }

        // Transforming the result of a CompletableFuture to something else, using thenApply()
        try {
            Integer output = CompletableFuture.supplyAsync(() -> 2).thenApply(result -> (result * 2) + 1).get();
        } catch (InterruptedException | ExecutionException e) {
            throw new RuntimeException(e);
        }

        // Note: One advantage of CompletableFuture over Future is that we can combine asynchronous operations
        // in a declarative way
//        CompletableFuture.supplyAsync(()->2).thenApply(result->(result*2)+7).thenAccept(result-> System.out.println(result));

        // To execute a task only after another task has Completed, we use the thenCompose():
//        fetchEmailAsync().
//                thenCompose(AsynchronousProgrammingDemo::fetchPlaylistAsync)
//                .thenAccept(playlist-> System.out.println(playlist));

        // Combining the result of two asynchronous (running at the same time) operations, using thenCombine()
//        CompletableFuture<Integer> first= CompletableFuture.supplyAsync(()->"20USD")
//                .thenApply(str-> {
//                    str.replace("USD", "");
//                    return Integer.parseInt(str);
//                });
//
//        CompletableFuture<Double> second= CompletableFuture.supplyAsync(()->0.5);
//
//        first.thenCombine(second,(firstTaskOutput, secondTaskOutput)->firstTaskOutput * secondTaskOutput)
//                .thenAccept(result-> System.out.println(result));

        // To do something after several asynchronous tasks have execute we use the allOf() method of the CompletableFuture
        CompletableFuture<Integer> first = CompletableFuture.supplyAsync(() -> 2);
        CompletableFuture<Integer> second = CompletableFuture.supplyAsync(() -> 3);
        CompletableFuture<Integer> third = CompletableFuture.supplyAsync(() -> 4);

//        CompletableFuture.allOf(first,second,third).
//                thenRunAsync(()-> System.out.println("All tasks done")); // This runs once all the three tasks are done

        // To get one of the tasks result on line 95, we can do
//        CompletableFuture.allOf(first,second,third).
//                thenRunAsync(()-> {
//                    try {
//                        first.get();
//                    } catch (InterruptedException | ExecutionException e) {
//                        throw new RuntimeException(e);
//                    }
//                    System.out.println("All tasks done");
//                }); // This runs once all the three tasks are done


//        // To call two remote services (that return same data) asynchronously and return the result of the first service that responds, we use the anyOf() method of the CompletableFuture
        CompletableFuture<Integer> callToRemoteService1 = CompletableFuture.supplyAsync(() ->{
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            return 20;

        });
//        CompletableFuture<Integer> callToRemoteService2 = CompletableFuture.supplyAsync(() ->20);
//        CompletableFuture.anyOf(callToRemoteService1,callToRemoteService2).thenAccept(output-> System.out.println(output));


        // Handling Timeouts on CompletableFuture
        CompletableFuture<Integer> completableFuture1 = CompletableFuture.supplyAsync(() -> {
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            return 2;
        });

        try {
            // To specify the timeout duration and the default value that should be returned on timeout
            Integer result = completableFuture1.completeOnTimeout(1,2,TimeUnit.SECONDS).get(); // We are saying the call should timeout after 2 seconds and return a default value of 1
        } catch (InterruptedException | ExecutionException e) {
            throw new RuntimeException(e);
        }


    }


}

