package multithreading.race_conditions_demo;

import multithreading.DownloadFileTask;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class FixingRaceConditions {
    public static void main(String[] args) {
//        List<Thread> threads= new ArrayList<>();
//        // 1. Confinement
//        List<DownloadFileTask> downloadFileTasks= new ArrayList<>();
//        for (int i = 0; i < 10; i++) {
//                DownloadFileTask downloadFileTask = new DownloadFileTask();
//                Thread thread= new Thread(downloadFileTask);
//                thread.start();
//                threads.add(thread);
//                downloadFileTasks.add(downloadFileTask);
//            }
//            for(Thread thread: threads){
//                try {
//                    thread.join();
//                } catch (InterruptedException e) {
//                    throw new RuntimeException(e);
//                }
//            }

//        Optional<Integer> optionalResult = downloadFileTasks.stream().map(downloadFileTask -> downloadFileTask.getStatus().getTotalBytes())
//                .reduce((a, b) -> a + b);
//        System.out.println(optionalResult.orElse(0));


        //2. Using locks
//        DownloadStatus status= new DownloadStatus();
//        List<Thread> threadList= new ArrayList<>();
//        // We create ten threads to update this single object (status) --> thereby creating a race condition if mechanisms are not put in place
//        for(int i=0; i< 10; i++){
//            Thread thread= new Thread(new DownloadFileTask(status));
//            thread.start();
//            threadList.add(thread);
//        }
//        // We are telling the main thread to wait until each thread is done before executing
//        threadList.stream().forEach(thread ->
//        {
//            try {
//                thread.join();
//            } catch (InterruptedException e) {
//                throw new RuntimeException(e);
//            }
//        });
//
//        System.out.println(status.getTotalBytes());

        //3 using the volatile keyword to fix visibility issue on a field updated by a single thread, while others would only read the field
        // . This doesn't fix race conditions
//        DownloadStatus status= new DownloadStatus();
//        Thread thread1= new Thread(new DownloadFileTask(status));
//        var thread2 = new Thread(()->{  // Another to create a thread and implement its run method
//            while (!status.isDone()) {}
//            System.out.println(status.getTotalBytes());
//        });
//
//        thread1.start();
//        thread2.start();

        // For more efficiency to ensure the while loop on line 60 runs once, we use the wait() and notify()
//        DownloadStatus status= new DownloadStatus();
//        Thread thread1= new Thread(new DownloadFileTask(status));
//        var thread2 = new Thread(()->{  // Another to create a thread and implement its run method
//            while (!status.isDone()) { // NB: We don't need the volatile keyword on isDone when using the wait()
//                                      // and notify() methods because once the field changes the first thread notifies the second thread
//                synchronized (status) {
//                    try {
//                        status.wait();  // This puts the while loop in a sleep after the first iteration to prevent it from running multiple times
//                        // once the wait method gets notified from the first thread that status has changed, the loop wakes, sees it and terminates.
//                        // N.B: We need to wrap the try-catch block in a synchronized block
//                    } catch (InterruptedException e) {
//                        throw new RuntimeException(e);
//                    }
//                }
//            }
//            System.out.println(status.getTotalBytes());
//        });
//
//        thread1.start();
//        thread2.start();

        // NB: There are better methods to handle communication between threads other than the wait() and notify()


//        // 4. Fixing race conditions using Atomic Objects e.g AtomicInteger
//        DownloadStatus status= new DownloadStatus();
//        List<Thread> threadList= new ArrayList<>();
//        // We create ten threads to update this single object (status) --> thereby creating a race condition if mechanisms are not put in place
//        for(int i=0; i< 10; i++){
//            Thread thread= new Thread(new DownloadFileTask(status));
//            thread.start();
//            threadList.add(thread);
//        }
//        // We are telling the main thread to wait until each thread is done before executing
//        threadList.stream().forEach(thread ->
//        {
//            try {
//                thread.join();
//            } catch (InterruptedException e) {
//                throw new RuntimeException(e);
//            }
//        });
//
//        System.out.println(status.getTotalBytes());

        // 4. Fixing race conditions using LongAdder, faster than AtomicObjects for solving race conditions on numbers
        DownloadStatus status= new DownloadStatus();
        List<Thread> threadList= new ArrayList<>();
        // We create ten threads to update this single object (status) --> thereby creating a race condition if mechanisms are not put in place
        for(int i=0; i< 10; i++){
            Thread thread= new Thread(new DownloadFileTask(status));
            thread.start();
            threadList.add(thread);
        }
        // We are telling the main thread to wait until each thread is done before executing
        threadList.stream().forEach(thread ->
        {
            try {
                thread.join();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        });

        System.out.println(status.getTotalBytes());



    }
}
