package com.example.methodref;

// Method References in Java

// https://www.baeldung.com/java-method-references

import com.example.model.Bicycle;
import com.example.model.BicycleComparator;
import org.junit.jupiter.api.Test;
import org.springframework.util.StringUtils;

import java.util.Arrays;
import java.util.List;


public class methodRefTest {

    @Test
    void staticMethodReference_test() {
        // We’ll begin with a very simple example, capitalizing and printing a list of Strings:

        List<String> messages = Arrays.asList("hello", "baeldung", "readers!");

        // We can achieve this by leveraging a simple lambda expression calling the StringUtils.capitalize() method
        // directly:
        messages.forEach(word -> System.out.println(word));

        // Or, we can use a method reference to simply refer to the capitalize static method:
        messages.stream().map(StringUtils::capitalize).forEach(System.out::println);

        // Notice that method references always utilize the :: operator.
    }

    @Test
    void referenceInstanceMethodofParticularObject_test() {
        // Reference to an Instance Method of a Particular Object

        List<Bicycle> createBicyclesList = Arrays.asList(
                new Bicycle("Small Bike", 50),
                new Bicycle("Big Bike", 100),
                new Bicycle("Medium Bike", 75),
                new Bicycle("Tiny Bike", 10)
        );
        BicycleComparator bikeFrameSizeComparator = new BicycleComparator();

        // We could use a lambda expression to sort bicycles by frame size, but we’d need to specify two bikes for comparison:
        createBicyclesList.stream().sorted((a, b) -> bikeFrameSizeComparator.compare(a, b)).forEach(System.out::println);

        // Instead, we can use a method reference to have the compiler handle parameter passing for us:
        createBicyclesList.stream().sorted(bikeFrameSizeComparator::compare).forEach(System.out::println);

        // The method reference is much cleaner and more readable, as our intention is clearly shown by the code.
    }

    @Test
    void referenceInstanceMethodofArbitraryObject_test() {
        // Reference to an Instance Method of an Arbitrary Object of a Particular Type

        // This type of method reference is similar to the previous example, but without having to create a custom
        // object to perform the comparison.

        //Let’s create an Integer list that we want to sort:
        List<Integer> numbers = Arrays.asList(5, 3, 50, 24, 40, 2, 9, 18);

        // If we use a classic lambda expression, both parameters need to be explicitly passed, while using a method
        // reference is much more straightforward:
        numbers.stream()
                .sorted((a, b) -> a.compareTo(b)).forEach(System.out::println);;
        numbers.stream()
                .sorted(Integer::compareTo).forEach(System.out::println);

        // Even though it’s still a one-liner, the method reference is much easier to read and understand.
    }

    @Test
    void referenceConstructor_test() {
        // We can reference a constructor in the same way that we referenced a static method in our first example.
        // The only difference is that we’ll use the new keyword.

        // Let’s create a Bicycle array out of a String list with different brands:
        List<String> bikeBrands = Arrays.asList("Giant", "Scott", "Trek", "GT");

        // Next, we’ll use our new constructor from a method reference and make a Bicycle array from the original
        // String list:
        bikeBrands.stream()
                .map(Bicycle::new)
                .toArray(Bicycle[]::new);

        // Notice how we called both Bicycle and Array constructors using a method reference,
        // giving our code a much more concise and clear appearance.
    }
}
