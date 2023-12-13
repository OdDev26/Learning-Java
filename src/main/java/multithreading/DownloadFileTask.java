package multithreading;

import multithreading.race_conditions_demo.DownloadStatus;

public class DownloadFileTask implements Runnable{

    private DownloadStatus status;

    public DownloadFileTask() {
        this.status= new DownloadStatus();
    }

    public DownloadFileTask(DownloadStatus status) {
        this.status = status;
    }

    public DownloadStatus getStatus() {
        return status;
    }

    @Override
    public void run() {
//        try {
//            Thread.sleep(3000); // With this we pause a thread
//        } catch (InterruptedException e) {
//            throw new RuntimeException(e);
//        }
        for (var i = 0; i < 10_000; i++) { // a file is 10_000 bytes
            if (Thread.currentThread().isInterrupted())
                return; // We must do this, otherwise the interrupt request from the other thread won't work
            status.incrementTotalBytes();
        }
        status.done();
        System.out.println("Download complete on thread" + Thread.currentThread().getName());
        synchronized (status) {
            status.notifyAll(); // To notify all threads waiting for a change in the status field, we need to wrap code in a synchronized block
        }
    }
}
