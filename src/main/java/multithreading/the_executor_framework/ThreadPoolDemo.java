package multithreading.the_executor_framework;

import java.util.List;
import java.util.concurrent.*;

public class ThreadPoolDemo {
    // To ensure threads are not destroyed when they complete a particular task (i.e. can be reused for newer tasks) we use a ThreadPool, it
    // queues a new task until a thread is available to handle
    // This also allows us to specify the no of threads to be created, so we don't encounter out of memory exception --> ThreadPool benefits

    // In java the concept of ThreadPool is represented using an ExecutorService interface and its implementations such as:
    // 1. ThreadPoolExecutor (The most common one we use)
    // 2. ScheduledThreadPoolExecutor ==> With this we can schedule tasks to run after a delay or periodically
    // 3. ForkJoinPool --> This is desingned to split a tasks into smaller tasks and combine the results to produce an output

    public static void main(String[] args) {
        // Creating a thread pool
//        Executors.newFixedThreadPool();  // This creates an instance of the ThreadPoolExecutor with a no of worker threads
//        Executors.newScheduledThreadPool();  // This creates an instance of the ScheduledThreadPoolExecutor with a no of worker threads

//        ExecutorService threadPool = Executors.newFixedThreadPool(2);// With this we create a ThreadPoolExecutor with 2 threads
        // To submit a task to a thread, we can submit 1000 tasks to these threads and once the threads become occupied, other tasks would be
        // placed on a queue until a thread becomes available
//        threadPool.submit(()->{
//            System.out.println(Thread.currentThread().getName());
//        });

        // We also need to exclusively shut down a thread pool, otherwise it would keep running
//        threadPool.shutdown(); // This would wait for all current tasks to complete before shutting down
//        threadPool.shutdownNow();  // This shuts down immediately and force existing tasks to stop

        // It is best practise to wrap the Thread pool code in a try-finally block so it always shut down if an exception occurs while performing
        // a task
//        ExecutorService threadPool = Executors.newFixedThreadPool(2);
//        try {
//            threadPool.submit(() -> {
//                System.out.println(Thread.currentThread().getName());
//            });
//        }
//        finally {
//            threadPool.shutdown();
//        }

        // The Callable interface: Used when we want to return a value when we submit a task to a thread
        // Useful if we want a thread to call an api to get some result
        ExecutorService threadPool = Executors.newFixedThreadPool(2);
        Future<Integer> future = threadPool.submit((() -> {  // We're implementing the Callable call() method which returns a future object immediately
            return 1;
        }));

        System.out.println("Do more task");  // Using the Callable interface to handle tasks like calling an api in a thread,
                                             // we can get this tasks done in the background(and result tracked in a Future<T> object )
                                            // while other processes in other threads (e.g main thread) can be executed

        try {
            System.out.println(future.get());  // Once we need the result of the call() method, we do this and this blocks the
                                               // current thread from executing until the result is returned

//            System.out.println(future.get(2,TimeUnit.SECONDS));  // We can pass this so the call to the external api using the
                                                                //    Callable call() method times out/cancels after 2 seconds



        } catch (InterruptedException | ExecutionException e) {
            throw new RuntimeException(e);
        }

    }



}
