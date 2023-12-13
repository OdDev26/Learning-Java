package multithreading;

public class ThreadDemo {
    public static void main(String[] args){
        // To get the no of active threads
        int noOfActiveThreads = Thread.activeCount();
//        System.out.println(noOfActiveThreads);
        // To get the total no of threads available in cpu
        int noOfAvailableThreads = Runtime.getRuntime().availableProcessors();

        // How to create a Thread
        Thread thread = new Thread(new DownloadFileTask());
        thread.start(); // to start the thread // N.B Thread dies after executing

        // To know the name of the thread executing a process,
//        System.out.println(Thread.currentThread().getName());

        // simulating downloading 5 files in seperate threads
//        for (var i=0; i< 5; i++){
//            Thread thread = new Thread(new DownloadFile());
//            thread.start();
//        }

       // If we have more threads than the available CPU threads, the java compiler determines which thread to run
        // per time and gives it a slice of the CPU time

        // Joining threads (We use a thread join() method  in another thread, when we want the other thread to execute only after the previous has completed), e.g
//        try {
//           thread.join(); // This would pause all thread execution in the main thread until thread downloading file is done
//        } catch (InterruptedException e) {
//            throw new RuntimeException(e);
//        }
//        System.out.println("ready to scan file");

        // How to interrupt a thread
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        thread.interrupt(); // We also have to check for this interruption in the thread class and stop the thread operation
        System.out.println("Thread interrupted");

    }
}
