package org.example.collections;

public class Customer implements Comparable<Customer> // we to implement the Comparable interface so we can sort the Customer class
         {
    private String name;
    private String email;

    public Customer(String name, String email){
        this.name= name;
        this.email = email;
    }

    @Override
    public int compareTo(Customer o) {
        return name.compareTo(o.name);
    }

    @Override
    public String toString() {
        return name;
    }

             public String getName() {
                 return name;
             }

             public void setName(String name) {
                 this.name = name;
             }

             public String getEmail() {
                 return email;
             }

             public void setEmail(String email) {
                 this.email = email;
             }
         }
