package multithreading.race_conditions_demo;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.LongAdder;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class DownloadStatus {
    // Fixing visibility issue using the volatile keyword
//    private volatile boolean isDone; // adding volatile  tells the java compiler to get the actual value of isDone
    // from the main memory instead of thread cache (which isn't accurate), when the thread asks for this data
    private boolean isDone;

    // Solving race condition using AtomicInteger. Should be used to fix race conditions while working with numbers
    private AtomicInteger totalBytes = new AtomicInteger();
//    public int getTotalBytes() {
//        return totalBytes.get();
//  }

    // Solving race condition using LongAdder. Should be used to fix race conditions while working with numbers
//    private LongAdder totalBytes = new LongAdder();
    //    public int getTotalBytes() {
//        return totalBytes.intValue();;
//  }

    // Solving race condition using the synchronized keyword
//     private Object totalBytesLock = new Object();

    // Solving race condition using Synchronization (Locks)
//    private Lock lock = new ReentrantLock();

//    public int getTotalBytes() {
//        return totalBytes;
//    }

    public void incrementTotalBytes() {
        // Solving race condition using Locks
//        lock.lock();
//        try {
//            totalBytes++;   // This is a nonatomic operation (operation with multiple steps: threads read, increment and write to memory at the same time),
//                            // that's why in application without thread safety, its final value
//                            // when updated by multiple threads won't be accurate
//        }
//        finally {
//            lock.unlock();
//        }

        //  Solving race condition using the synchronized keyword
//        synchronized (totalBytesLock) { // this does the same thing as the lock implementation. Note
//            totalBytes++;                // we should use a custom lock e.g (totalBytesLock) instead of 'this' so as to allow
                                         // multiple threads to be able to modify different methods of the current class (this) at the same
                                         // time.
                                        // It's best practise not use synchronization because it defies the purpose of concurrency and can cause
                                        // unexpected bugs
//        }

        // Solving race conditions using atomic integers
       totalBytes.incrementAndGet(); // This increments the totalBytes by 1 (for Atomic integers) => same as totalBytes++

        // Solving race conditions using long adder
//        totalBytes.increment();  // This increments the totalBytes by 1 (for LongAdder)
    }

    public boolean isDone() {
        return isDone;
    }

    public void done() {
        isDone = true;
    }
}
