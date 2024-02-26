Java Streams
------------

Stream is a new abstract layer introduced in Java 8. Using stream, you can process data in a declarative way similar to
SQL statements. For example, consider the following SQL statement.

SELECT max(salary), employee_id, employee_name FROM Employee
The above SQL expression automatically returns the maximum salaried employee's details, without doing any computation on
the developer's end. Using collections framework in Java, a developer has to use loops and make repeated checks. Another
concern is efficiency; as multi-core processors are available at ease, a Java developer has to write parallel code
processing that can be pretty error-prone.

To resolve such issues, Java 8 introduced the concept of stream that lets the developer to process data declaratively
and leverage multicore architecture without the need to write any specific code for it.

Stream represents a sequence of objects from a source, which supports aggregate operations.

Java 8 streams can’t be reused.

To perform a sequence of operations over the elements of the data source and aggregate their results, we need three parts:
o the source,
o intermediate operation(s) and a
o erminal operation.

We can only use one terminal operation per stream.

There are many ways to create a stream instance of different sources. Once created, the instance will not modify its
source, therefore allowing the creation of multiple instances from a single source.

From the performance point of view, the right order is one of the most important aspects of chaining operations in the stream pipeline.

This brings us to the following rule: intermediate operations which reduce the size of the stream should be placed
before operations which are applying to each element. So we need to keep methods such as skip(), filter(), and
distinct() at the top of our stream pipeline.