package com.example.model;

import com.example.model.comparator.ProductComparator;
import lombok.ToString;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

@ToString
public class Product {

    private int id;
    private String name;
    private double price;
    public Product(int id, String name, float price) {
        super();
        this.id = id;
        this.name = name;
        this.price = price;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public static List<Product> getProductList() {
        return Arrays.asList(
                new Product(1,"Samsung A5",17000f),
                new Product(3,"Iphone 6S",65000f),
                new Product(2,"Sony Xperia",25000f),
                new Product(4,"Nokia Lumia",15000f),
                new Product(5,"Redmi4 ",26000f),
                new Product(6,"Lenevo Vibe",19000f));
    }

    public static void main(String[] args) {
        List<Product> productList = Product.getProductList();

        System.out.println("Before Sorting : " + productList);
        Collections.sort(productList, new ProductComparator());
        System.out.println("After Sorting : " + productList);
    }
}
