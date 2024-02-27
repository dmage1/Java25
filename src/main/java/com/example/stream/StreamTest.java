package com.example.stream;

import com.example.model.Product;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.Test;

import java.util.*;
import java.util.regex.Pattern;
import java.util.stream.*;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

// https://www.baeldung.com/java-8-streams

public class StreamTest {

    private static final Logger logger = LogManager.getLogger(StreamTest.class);

    List<Product> productList = Arrays.asList(
            new Product(23, "potatoes", 95f),
            new Product(14, "orange", 35f),
            new Product(13, "lemon", 15f),
            new Product(23, "bread", 105f),
            new Product(13, "sugar", 50f));

    @Test
    void emptyStream_test() {
        // Use the empty() method upon creation to avoid returning null for streams with no element
        Stream<String> streamEmpty = Stream.empty();

        assertTrue(streamEmpty.findAny().isEmpty());
    }

    @Test
    void streamCollection_test() {
        // We can also create a stream of any type of Collection (Collection, List, Set)
        Collection<String> collection = Arrays.asList("a", "b", "c");
        Stream<String> streamOfCollection = collection.stream();

        long count = streamOfCollection.count();

        assertTrue(count == 3l);
    }

    @Test
    void streamOfArray_test() {
        // An array can also be the source of a stream:
        Stream<String> streamOfArray = Stream.of("a", "b", "c", "d", "e");

        // We can also create a stream out of an existing array or of part of an array:
        String[] arr = new String[]{"a", "b", "c", "d", "e"};

        Stream<String> streamOfArrayFull = Arrays.stream(arr);
        long count = streamOfArrayFull.count();
        assertEquals(5l, count);

        Stream<String> streamOfArrayPart = Arrays.stream(arr, 1, 3);
        count = streamOfArrayPart.count();
        assertEquals(2l, count);
    }

    @Test
    void streamBuilder_test() {
        // When builder is used, the desired type should be additionally specified in the right part of the statement,
        // otherwise the build() method will create an instance of the Stream<Object>:
        Stream<String> streamBuilder =
                Stream.<String>builder().add("a").add("b").add("c").build();

        long count = streamBuilder.count();
        assertEquals(3l, count);
    }

    @Test
    void streamGenerate_test() {
        // The generate() method accepts a Supplier<T> for element generation. As the resulting stream is infinite,
        // the developer should specify the desired size, or the generate() method will work until it reaches the
        // memory limit:
        Stream<String> streamGenerated =
                Stream.generate(() -> "element").limit(10);

        long count = streamGenerated.count();
        // The code above creates a sequence of ten strings with the value “element.”
        assertEquals(10l, count);
    }

    @Test
    void streamIterate_test() {
        // Another way of creating an infinite stream is by using the iterate() method:
        Stream<Integer> streamIterated = Stream.iterate(40, n -> n + 2).limit(20);

        // The first element of the resulting stream is the first parameter of the iterate() method. When creating every
        // following element, the specified function is applied to the previous element. In the example above the second
        // element will be 42.
        long count = streamIterated.count();
        assertEquals(20l, count);
    }

    @Test
    void streamPrimitives_test() {
        // The range(int startInclusive, int endExclusive) method creates an ordered stream from the first parameter to
        // the second parameter. It increments the value of subsequent elements with the step equal to 1. The result
        // doesn’t include the last parameter, it is just an upper bound of the sequence.
        IntStream intStream = IntStream.range(1, 3);
        intStream.forEach(l -> System.out.println(l)); //it works

        // The rangeClosed(int startInclusive, int endInclusive) method does the same thing with only one difference,
        // the second element is included. We can use these two methods to generate any of the three types of streams
        // of primitives.
        LongStream longStream = LongStream.rangeClosed(1, 3);
        longStream.forEach(System.out::println);

        // Since Java 8, the Random class provides a wide range of methods for generating streams of primitives. For
        // example, the following code creates a DoubleStream, which has three elements:
        Random random = new Random();
        DoubleStream doubleStream = random.doubles(3);
        doubleStream.forEach(System.out::println);
    }

    @Test
    void streamOfString_test() {
        // We can also use String as a source for creating a stream with the help of the chars() method of the String
        // class. Since there is no interface for CharStream in JDK, we use the IntStream to represent a stream of chars
        // instead.
        IntStream streamOfChars = "abc".chars();
        streamOfChars.forEach(System.out::println);

        // The following example breaks a String into sub-strings according to specified RegEx:
        Stream<String> streamOfString =
                Pattern.compile(", ").splitAsStream("a, b, c");
        streamOfString.forEach(System.out::println);
    }

    @Test
    void streamReferencing_test() {
        // We can instantiate a stream, and have an accessible reference to it, as long as only intermediate operations are called.
        Stream<String> stream =
                Stream.of("a", "b", "c").filter(element -> element.contains("b"));
        Optional<String> anyElement = stream.findAny();
        Optional<String> firstElement = Stream.of("a", "b", "c").findFirst();

        assertEquals("a", firstElement);
        assertEquals("b", anyElement); // we have filtered to "b"
    }

    @Test
    void streamPipeline_test() {
        // To perform a sequence of operations over the elements of the data source and aggregate their results, we need
        // three parts: the source, intermediate operation(s) and a terminal operation.
        //
        // Intermediate operations return a new modified stream. For example, to create a new stream of the existing one
        // without few elements, the skip() method should be used:
        Stream<String> onceModifiedStream =
                Stream.of("abcd", "bbcd", "cbcd").skip(1);
        long count = onceModifiedStream.count();
        assertEquals(2l, count);

        // If we need more than one modification, we can chain intermediate operations. Let’s assume that we also need
        // to substitute every element of the current Stream<String> with a sub-string of the first few chars. We can do
        // this by chaining the skip() and map() methods:
        Stream<String> twiceModifiedStream =
                Stream.of("abcd", "bbcd", "cbcd").skip(1).map(element -> element.substring(0, 3));
        assertEquals(Optional.of("bbc"), twiceModifiedStream.findFirst());

        // As we can see, the map() method takes a lambda expression as a parameter. If we want to learn more about
        // lambdas, we can take a look at our tutorial Lambda Expressions and Functional Interfaces: Tips and Best
        // Practices.
        //
        //A stream by itself is worthless; the user is interested in the result of the terminal operation, which can be
        // a value of some type or an action applied to every element of the stream. We can only use one terminal
        // operation per stream.
        //
        //The correct and most convenient way to use streams is by a stream pipeline, which is a chain of the stream
        // source, intermediate operations, and a terminal operation:

        List<String> list = Arrays.asList("abc1", "abc2", "abc3");
        long size = list.stream().skip(1)
                .map(element -> element.substring(0, 3)).count();
        list.stream().skip(1)
                .map(element -> element.substring(0, 3))
                .forEach(System.out::println);
    }

    private long counter;

    private void wasCalled() {
        logger.info("here");
        counter++;
    }

    @Test
    void streamLazyInvocation_test() {
        // Intermediate operations are lazy. This means that they will be invoked only if it is necessary for the
        // terminal operation execution.

        // Now let’s call the method wasCalled() from operation filter():
        List<String> list = Arrays.asList("abc1", "abc2", "abc3");
        counter = 0;
        Stream<String> stream1 = list.stream().filter(element -> {
            wasCalled();
            return element.contains("2");
        });
        //System.out.println("stream1 " + stream1.findFirst());
        assertEquals(0l, counter);

        Optional<String> stream2 = list.stream().filter(element -> {
            logger.info("filter() was called");
            return element.contains("2");
        }).map(element -> {
            logger.info("map() was called");
            return element.toUpperCase();
        }).findFirst();

        assertEquals(0l, counter);
    }

    @Test
    void reduce_test() {
        Stream<String> streamOfArray = Stream.of("a", "b", "c", "d", "e");
        //streamOfArray.forEach(System.out::println);

        OptionalInt reduced = IntStream.range(1, 4).reduce((a, b) -> a + b);
        assertEquals(6, reduced.getAsInt());

        int reducedParams = Stream.of(1, 2, 3)
                .reduce(10, (a, b) -> a + b, (a, b) -> {
                    logger.info("combiner was called");
                    return a + b;
                });
        assertEquals(10, reducedParams);

        int reducedParallel = Arrays.asList(1, 2, 3).parallelStream()
                .reduce(10, (a, b) -> a + b, (a, b) -> {
                    logger.info("combiner was called");
                    return a + b;
                });
        assertEquals(16, reducedParallel);
    }

    @Test
    void collect_test() {
        // The reduction of a stream can also be executed by another terminal operation, the collect() method. It
        // accepts an argument of the type Collector, which specifies the mechanism of reduction. There are already
        // created, predefined collectors for most common operations. They can be accessed with the help of the
        // Collectors type.

        // Converting a stream to the Collection (Collection, List or Set):
        List<String> collectorCollection =
                productList.stream().map(Product::getName).collect(Collectors.toList());
        assertEquals(5, collectorCollection.size());

        // Reducing to String:
        String listToString = productList.stream().map(Product::getName)
                .collect(Collectors.joining(", ", "[", "]"));
        assertEquals("[potatoes, orange, lemon, bread, sugar]", listToString);

        // Processing the average value of all numeric elements of the stream:
        double averagePrice = productList.stream()
                .collect(Collectors.averagingDouble(Product::getPrice));
        assertEquals(60.0, averagePrice);

        // Processing the sum of all numeric elements of the stream:
        double summingPrice = productList.stream()
                .collect(Collectors.summingDouble(Product::getPrice));
        assertEquals(300.0, summingPrice);

        // Collecting statistical information about stream’s elements:
        DoubleSummaryStatistics statistics = productList.stream()
                .collect(Collectors.summarizingDouble(Product::getPrice));
        assertEquals("DoubleSummaryStatistics{count=5, sum=300.000000, min=15.000000, average=60.000000, max=105.000000}",
                statistics.toString());

        // Grouping of stream’s elements according to the specified function:
        Map<Double, List<Product>> collectorMapOfLists = productList.stream()
                .collect(Collectors.groupingBy(Product::getPrice));
        // map key is price; e.g. {35.0=[com.example.model.Product@d21a74c], ...
        assertEquals(5, collectorMapOfLists.size());

        // Dividing stream’s elements into groups according to some predicate:
        Map<Boolean, List<Product>> mapPartioned = productList.stream()
                .collect(Collectors.partitioningBy(element -> element.getPrice() > 30));
        // map key is boolean; {false=[com.example.model.Product@68267da0], true=[com.example.model.Product@6e509ffa
        assertEquals(2, mapPartioned.size());

        // Pushing the collector to perform additional transformation:
        Set<Product> unmodifiableSet = productList.stream()
                .collect(Collectors.collectingAndThen(Collectors.toSet(),
                        Collections::unmodifiableSet));
        assertEquals(5, unmodifiableSet.size());


        // If for some reason a custom collector should be created, the easiest and least verbose way of doing so is to
        // use the method of() of the type Collector.
        Collector<Product, ?, LinkedList<Product>> toLinkedList =
                Collector.of(LinkedList::new, LinkedList::add,
                        (first, second) -> {
                            first.addAll(second);
                            return first;
                        });

        LinkedList<Product> linkedListOfPersons =
                productList.stream().collect(toLinkedList);
        assertEquals(5, linkedListOfPersons.size());
    }


    @Test
    void parallel_test() {
        // The API allows us to create parallel streams, which perform operations in a parallel mode
        // When the source of a stream is a Collection or an array, it can be achieved with the help
        // of the parallelStream() method:
        Stream<Product> streamOfCollection = productList.parallelStream();
        boolean isParallel = streamOfCollection.isParallel();
        assertTrue(isParallel);
        boolean bigPrice = streamOfCollection
                .map(product -> product.getPrice() * 12)
                .anyMatch(price -> price > 200);
        assertTrue(bigPrice);

        // If the source of a stream is something other than a Collection or an array, the parallel()
        // method should be used:
        IntStream intStreamParallel = IntStream.range(1, 150).parallel();
        isParallel = intStreamParallel.isParallel();
        assertTrue(isParallel);

        long count = intStreamParallel.count();
        assertEquals(149l, count);
    }
}
