package com.example.lambda;

import com.example.model.Product;
import org.junit.jupiter.api.Test;

import javax.swing.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class LambdaExpressionTest {

    @FunctionalInterface  //It is optional
    interface Drawable {
        void draw();
    }

    interface Sayable{
        String say();
    }

    interface SayHello{
        String say(String name);
    }

    interface Addable{
        int add(int a,int b);
    }

    interface SaySomething{
        String say(String message);
    }

    int width = 10;

    @Test
    void lambdaExpression_test() {
        // Java Lambda Expression Example

        //with lambda
        Drawable d2 = () -> {
            System.out.println("Drawing " + width);
        };
        d2.draw();
    }

    @Test
    void lambdaExpressionNoParameter_test() {
        // Java Lambda Expression Example: No Parameter

        // A lambda expression can have zero or any number of arguments.
        Sayable s=()->{
            return "I have nothing to say.";
        };
        assertEquals("I have nothing to say.", s.say());
    }

    @Test
    void lambdaExpressionOneParameter_test() {
        // Java Lambda Expression Example: Single Parameter

        // Lambda expression with single parameter.
        SayHello s1=(name)->{
            return "Hello, "+name;
        };
        assertEquals("Hello, Sonoo", s1.say("Sonoo"));

        // You can omit function parentheses
        SayHello s2= name ->{
            return "Hello, "+name;
        };
        assertEquals("Hello, Sonoo", s2.say("Sonoo"));
    }

    @Test
    void lambdaExpressionMultipleParameters_test() {
        // Java Lambda Expression Example: Multiple Parameters

        // Multiple parameters in lambda expression
        Addable ad1=(a,b)->(a+b);
        assertEquals(30, ad1.add(10,20));

        // Multiple parameters with data type in lambda expression
        Addable ad2=(int a,int b)->(a+b);
        assertEquals(300, ad2.add(100,200));
    }

    @Test
    void lambdaExpressionWithOrWithoutReturnKeyword_test() {
        // Java Lambda Expression Example: with or without return keyword

        // In Java lambda expression, if there is only one statement, you may or may not use return keyword.
        // You must use return keyword when lambda expression contains multiple statements.

        // Lambda expression without return keyword.
        Addable ad1=(a,b)->(a+b);
        assertEquals(30, ad1.add(10,20));

        // Lambda expression with return keyword.
        Addable ad2=(int a,int b)->{
            return (a+b);
        };
        assertEquals(300, ad2.add(100,200));
    }

    @Test
    void lambdaExpressionForeachLoop_test() {
        // Java Lambda Expression Example: Foreach Loop

        List<String> list= Arrays.asList("Hello", "World!", "How", "Are", "You");

        list.forEach(
                (n)->System.out.println(n)
        );
    }

    @Test
    void lambdaExpressionMultipleStatements_test() {
        // Java Lambda Expression Example: Multiple Statements

        // You can pass multiple statements in lambda expression
        SaySomething person = (message)-> {
            String str1 = "I would like to say, ";
            String str2 = str1 + message;
            return str2;
        };
        System.out.println(person.say("time is precious."));
    }

    @Test
    void lambdaExpressionCreatingThread_test() {
        // Java Lambda Expression Example: Creating Thread

        // You can use lambda expression to run thread.

        //Thread Example without lambda
        Runnable r1=new Runnable(){
            public void run(){
                System.out.println("Thread1 is running...");
            }
        };
        Thread t1=new Thread(r1);
        t1.start();

        //Thread Example with lambda
        Runnable r2=()->{
            System.out.println("Thread2 is running...");
        };
        Thread t2=new Thread(r2);
        t2.start();
    }

    @Test
    void lambdaExpressionComparator_test() {
        // Java Lambda Expression Example: Comparator

        List<Product> list = new ArrayList<>();

        //Adding Products
        list.add(new Product(1, "HP Laptop", 25000f));
        list.add(new Product(3, "Keyboard", 300f));
        list.add(new Product(2, "Dell Mouse", 150f));

        System.out.println("Sorting on the basis of name...");

        // implementing lambda expression
        Collections.sort(list, (p1, p2) -> {
            return p1.getName().compareTo(p2.getName());
        });
        for (Product p : list) {
            System.out.println(p.getId() + " " + p.getName() + " " + p.getPrice());
        }
    }

    @Test
    void lambdaExpressionFilterCollectionData_test() {
        // Java Lambda Expression Example: Comparator

        List<Product> list=new ArrayList<>();

        //Adding Products
        list.add(new Product(1,"Samsung A5",17000f));
        list.add(new Product(3,"Iphone 6S",65000f));
        list.add(new Product(2,"Sony Xperia",25000f));
        list.add(new Product(4,"Nokia Lumia",15000f));
        list.add(new Product(5,"Redmi4 ",26000f));
        list.add(new Product(6,"Lenevo Vibe",19000f));

        // using lambda to filter data
        Stream<Product> filtered_data = list.stream().filter(p -> p.getPrice() > 20000);

        // using lambda to iterate through collection
        filtered_data.forEach(
                product -> System.out.println(product.getName()+": "+product.getPrice())
        );
    }

    @Test
    public static void main(String[] args) {
        // Java Lambda Expression Example: Event Listener

        JTextField tf=new JTextField();
        tf.setBounds(50, 50,150,20);
        JButton b=new JButton("click");
        b.setBounds(80,100,70,30);

        // lambda expression implementing here.
        b.addActionListener(e-> {tf.setText("hello swing");});

        JFrame f=new JFrame();
        f.add(tf);f.add(b);
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.setLayout(null);
        f.setSize(300, 200);
        f.setVisible(true);
    }
}
