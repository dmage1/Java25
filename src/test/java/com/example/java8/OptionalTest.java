package com.example.java8;

import com.example.model.Person;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

// Optional is meant to be used as a return type - the intent of Java when releasing Optional was to use it as a return type,
// Additionally, using Optional in a serializable class will result in a NotSerializableException.
// Misuse of Optionals - Do do pass Optionals as a parameters to a method.

public class OptionalTest {

    // Creating Optional Objects - https://www.baeldung.com/java-optional#or-else-throw

    @Test
    public void whenCreatesEmptyOptional_thenCorrect() {
        // The isPresent() method to check if there is a value inside the Optional object.
        // A value is present only if we have created Optional with a non-null value.
        Optional<String> empty = Optional.empty();
        assertFalse(empty.isPresent());
    }

    @Test
    public void givenNonNull_whenCreatesNonNullable_thenCorrect() {
        // We can also create an Optional object with the static method of():
        // the argument passed to the of() method can’t be null. Otherwise, we’ll get a NullPointerException:
        String name = "Dave";
        Optional<String> opt = Optional.of(name);
        assertTrue(opt.isPresent());
    }

    @Test
    public void givenNonNull_whenCreatesNullable_thenCorrect() {
        // But in case we expect some null values, we can use the ofNullable() method:
        String name = "Dave";
        Optional<String> opt = Optional.ofNullable(name);
        assertTrue(opt.isPresent());

        // By doing this, if we pass in a null reference, it doesn’t throw an exception but rather returns an empty Optional object
        name = null;
        opt = Optional.ofNullable(name);
        assertFalse(opt.isPresent());
    }

    // Checking Value Presence: isPresent() and isEmpty()

    @Test
    public void givenOptional_whenIsPresentWorks_thenCorrect() {
        // When we have an Optional object returned from a method or created by us, we can check if there is a value in
        // it or not with the isPresent() method:

        // *** This method returns true if the wrapped value is not null. ***

        Optional<String> opt = Optional.of("Dave");
        assertTrue(opt.isPresent());

        // *** This method returns false if the wrapped value is null. ***

        opt = Optional.ofNullable(null);
        assertFalse(opt.isPresent());
    }

    @Test
    public void givenAnEmptyOptional_thenIsEmptyBehavesAsExpected() {
        // Also, as of Java 11, we can do the opposite with the isEmpty method:
        Optional<String> opt = Optional.of("Dave");
        assertFalse(opt.isEmpty());

        opt = Optional.ofNullable(null);
        assertTrue(opt.isEmpty());
    }

    // Conditional Action With ifPresent()

    @Test
    public void givenOptional_whenIfPresentWorks_thenCorrect() {
        // The ifPresent() method enables us to run some code on the wrapped value if it’s found to be non-null. Before Optional, we’d do:

        // if(name != null) {
        //    System.out.println(name.length());
        // }

        // Optional makes us deal with nullable values explicitly as a way of enforcing good programming practices.
        Optional<String> opt = Optional.of("Dave");
        opt.ifPresent(name -> System.out.println(name.length()));
    }

    // Default Value With orElse()

    @Test
    public void whenOrElseWorks_thenCorrect() {
        // The orElse() method is used to retrieve the value wrapped inside an Optional instance. It takes one
        // parameter, which acts as a default value. The orElse() method returns the wrapped value if it’s present,
        // and its argument otherwise:
        String nullName = null;
        String name = Optional.ofNullable(nullName).orElse("bob");
        assertEquals("bob", name);
    }

    // Default Value With orElseGet() - *** Use orElseGet ***

    @Test
    public void whenOrElseGetWorks_thenCorrect() {
        // The orElseGet() method is similar to orElse(). However, instead of taking a value to return if the Optional
        // value is not present, it takes a supplier functional interface, which is invoked and returns the value of
        // the invocation:
        String nullName = null;
        String name = Optional.ofNullable(nullName).orElseGet(() -> "john");
        assertEquals("john", name);
    }

    // Difference Between orElse and orElseGet()

    @Test
    public void whenOrElseGetAndOrElseOverlap_thenCorrect() {
        // The getMyDefault() method is called in each case. It so happens that when the wrapped value is not present,
        // then both orElse() and orElseGet() work exactly the same way.
        String text = null;

        System.out.println("Using orElseGet:"); // *** Use orElseGet ***
        String defaultText = Optional.ofNullable(text).orElseGet(this::getMyDefault);
        assertEquals("Default Value", defaultText);

        System.out.println("Using orElse:");
        defaultText = Optional.ofNullable(text).orElse(getMyDefault());
        assertEquals("Default Value", defaultText);
    }

    public String getMyDefault() {
        System.out.println("Getting Default Value");
        return "Default Value";
    }

    // Notice that when using orElseGet() to retrieve the wrapped value, the getMyDefault() method is not even invoked
    // since the contained value is present.
    //
    // However, when using orElse(), whether the wrapped value is present or not, the default object is created. So in
    // this case, we have just created one redundant object that is never used.
    //
    //In this simple example, there is no significant cost to creating a default object, as the JVM knows how to deal
    // with such. However, when a method such as getMyDefault() has to make a web service call or even query a database,
    // the cost becomes very obvious.

    @Test
    public void whenOrElseGetAndOrElseDiffer_thenCorrect() {
        String text = "Text present";

        System.out.println("Using orElseGet:"); // *** Use orElseGet ***
        String defaultText
                = Optional.ofNullable(text).orElseGet(this::getMyDefault);
        assertEquals("Text present", defaultText);

        System.out.println("Using orElse:");
        defaultText = Optional.ofNullable(text).orElse(getMyDefault());
        assertEquals("Text present", defaultText);
    }

    // Exceptions With orElseThrow()

    @Test
    public void whenOrElseThrowWorks_thenCorrect() {
        // Instead of returning a default value when the wrapped value is not present, it throws an exception:
        assertThrows(IllegalArgumentException.class, () -> {
            String nullName = null;
            String name = Optional.ofNullable(nullName).orElseThrow(
                    IllegalArgumentException::new);
        });
    }

    // Returning Value With get()

    @Test
    public void givenOptional_whenGetsValue_thenCorrect() {
        // The final approach for retrieving the wrapped value is the get() method:
        Optional<String> opt = Optional.of("Dave");
        String name = opt.get();
        assertEquals("Dave", name);
    }

    @Test
    public void givenOptionalWithNull_whenGetThrowsException_thenCorrect() {
        // Instead of returning a default value when the wrapped value is not present, it throws an exception:
        assertThrows(NoSuchElementException.class, () -> {
            Optional<String> opt = Optional.ofNullable(null);
            String name = opt.get();
        });
    }

    // Conditional Return With filter()

    @Test
    public void whenOptionalFilterWorks_thenCorrect() {
        // takes a predicate as an argument and returns an Optional object. If the wrapped value passes testing by the
        // predicate, then the Optional is returned as-is.
        //However, if the predicate returns false, then it will return an empty Optional:
        Integer year = 2016;
        Optional<Integer> yearOptional = Optional.of(year);
        boolean is2016 = yearOptional.filter(y -> y == 2016).isPresent();
        assertTrue(is2016);
        boolean is2017 = yearOptional.filter(y -> y == 2017).isPresent();
        assertFalse(is2017);

        /*
        public boolean priceIsInRange2(Modem modem2) {
            return Optional.ofNullable(modem2)
                .map(Modem::getPrice)
                .filter(p -> p >= 10)
                .filter(p -> p <= 15)
                .isPresent();
        }
        */
    }

    // Transforming Value With map()

    @Test
    public void givenOptional_whenMapWorks_thenCorrect() {
        // We wrap a list of strings inside an Optional object and use its map method to perform an action on the
        // contained list. The action we perform is to retrieve the size of the list.
        List<String> companyNames = Arrays.asList(
                "paypal", "oracle", "", "microsoft", "", "apple");
        Optional<List<String>> listOptional = Optional.of(companyNames);

        int size = listOptional
                .map(List::size)
                .orElse(0);
        assertEquals(6, size);
    }

    @Test
    public void givenOptional_whenFlatMapWorks_thenCorrect2() {
        Person person = new Person("john", 26);
        Optional<Person> personOptional = Optional.of(person);
        // Just like the map() method, we also have the flatMap() method as an alternative for transforming values.

        // The difference is that map transforms values only when they are unwrapped
        Optional<Optional<String>> nameOptionalWrapper = personOptional.map(Person::getName);
        Optional<String> nameOptional = nameOptionalWrapper.orElseThrow(IllegalArgumentException::new);
        String name1 = nameOptional.orElse("");
        assertEquals("john", name1);

        // whereas flatMap takes a wrapped value and unwraps it before transforming it.
        String name = personOptional.flatMap(Person::getName).orElse("");
        assertEquals("john", name);
    }
}
