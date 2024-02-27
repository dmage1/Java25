Method References in Java
-------------------------

One of the most welcome changes in Java 8 was the introduction of lambda expressions, as these allow us to forego
anonymous classes, greatly reducing boilerplate code and improving readability.

Method references are a special type of lambda expressions. They’re often used to create simple lambda expressions by
referencing existing methods.

There are four kinds of method references:

o Static methods
o Instance methods of particular objects
o Instance methods of an arbitrary object of a particular type
o Constructor

In this tutorial, we’ll explore method references in Java.

Method references are a great way to make our code and intentions very clear and readable.
However, we can’t use them to replace all kinds of lambda expressions since they have some limitations.

o Their main limitation is a result of what’s also their biggest strength: the output from the previous expression
  needs to match the input parameters of the referenced method signature.

