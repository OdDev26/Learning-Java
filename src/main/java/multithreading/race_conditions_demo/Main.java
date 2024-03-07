package multithreading.race_conditions_demo;

import multithreading.DownloadFileTask;

import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
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

//        System.out.println(status.getTotalBytes());



    }
}
