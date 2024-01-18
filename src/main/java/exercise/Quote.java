package exercise;

import java.util.Arrays;
import java.util.List;

public class Quote {
    private String site;
    private double price;

    public  Quote(){

    }
    public  Quote(String site, double price){
       this.site= site;
       this.price= price;
    }

    public String getSite() {
        return site;
    }

    public void setSite(String site) {
        this.site = site;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public static void main(String[] args) {
        List<String> list= Arrays.asList("Bag","Keg","Can");
        System.out.println(String.join("-",list));
    }
}
