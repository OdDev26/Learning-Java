package multithreading.synchronized_collections;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

public class SynchronizedCollectionDemo {
    public static void main(String[] args) {
//        Collection<Integer> a = new ArrayList<>();
//        Thread thread1 = new Thread(()->{
//            a.addAll(Arrays.asList(1,2,3));
//        });
//
//        Thread thread2 = new Thread(()->{
//            a.addAll(Arrays.asList(2,3,4));
//        });

        // The above cause a raise condition and an incorrect collection a output, to fix this we need to ensure
        // a is synchronized

        Collection<Integer> a = Collections.synchronizedCollection(new ArrayList<>()); // This ensures the threads modifying this collection
        Thread thread1 = new Thread(()->{                                              // are synchronized thus leading to a correct output a
            a.addAll(Arrays.asList(1,2,3));
        });

        Thread thread2 = new Thread(()->{
            a.addAll(Arrays.asList(2,3,4));
        });

//        thread1.start();
//        thread2.start();

        // we must always allow our threads execute before proceeding to other threads e.g the main thread by using the join() method

        try {
            thread1.join();
            thread2.join();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
//        System.out.println(a);

        // When a single collection is updated by multiple threads concurrently, it's better to use a Concurrent Collection, these are faster
        // (more scalable) than synchronized collecions
        // Note: In concurrent systems its preferred to use a ConcurrentHashMap instead of an HashMap, bcos HashMap are not thread-safe, e.g
//        Map<String, Integer> map = new ConcurrentHashMap<>();



    }
}
