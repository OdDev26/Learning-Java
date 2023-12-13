package exercise;

import java.util.concurrent.CompletableFuture;

public class BestPriceFinder {
    public static void main(String[] args) {
        CompletableFuture<Quote> task1= CompletableFuture.supplyAsync(()->{
            System.out.println("Getting quote from site 1");
            return new Quote("site1",120);
        });

        CompletableFuture<Quote> task2= CompletableFuture.supplyAsync(()->{
            System.out.println("Getting quote from site 3");
            return new Quote("site2",120);
        });

        CompletableFuture<Quote> task3= CompletableFuture.supplyAsync(()->{
            System.out.println("Getting quote from site 3");
            return new Quote("site3",120);
        });

        // Note varargs is the same as an array of items

    }
}
