# **内置函数式接口**

JDK 1.8 API中包含了很多内置的函数式接口。有些是在以前版本的Java中大家耳 熟能详的，例如Comparator接口，或者Runnable接口。对这些现成的接口进行实 现，可以通过@FunctionalInterface 标注来启用Lambda功能支持。

此外，Java 8 API 还提供了很多新的函数式接口，来降低程序员的工作负担。有些 新的接口已经在Google Guava库中很有名了。如果你对这些库很熟的话，你甚至 闭上眼睛都能够想到，这些接口在类库的实现过程中起了多么大的作用。



**Predicates**

Predicate是一个布尔类型的函数，该函数只有一个输入参数。Predicate接口包含 了多种默认方法，用于处理复杂的逻辑动词(and, or，negate)

```java
Predicate<String> predicate = (s) -> s.length() > 0;
predicate.test("foo");  // true
predicate.negate().test("foo"); // false

Predicate<Boolean> nonNull = Objects::nonNull;
Predicate<Boolean> isNull = Objects::isNull;
Predicate<String> isEmpty = String::isEmpty;
Predicate<String> isNotEmpty = isEmpty.negate();
```

**Functions**

Function接口接收一个参数，并返回单一的结果。默认方法可以将多个函数串在一 起(compse, andThen)

```java
Function<String, Integer> toInteger = Integer::valueOf;
Function<String, String> backToString = toInteger.andThen(String::valueOf);
backToString.apply("123"); // "123"
```

**Suppliers**

Supplier接口产生一个给定类型的结果。与Function不同的是，Supplier没有输入参数。

```java
Supplier<Person> personSupplier = Person::new; 
personSupplier.get(); // new Person
```

**Consumers**

Consumer代表了在一个输入参数上需要进行的操作。

```java
Consumer<Person> greeter = (p) -> System.out.println("Hello, " + p.firstName);
greeter.accept(new Person("Luke", "Skywalker"));
```

**Comparators**

Comparator接口在早期的Java版本中非常著名。Java 8 为这个接口添加了不同的 默认方法。

```java
Comparator<Person> comparator = (p1, p2) -> p1.firstName.compareTo(p2.firstName);

Person p1 = new Person("John", "Doe");
Person p2 = new Person("Alice", "Wonderland");

comparator.compare(p1, p2); // > 0 comparator.reversed().compare(p1, p2); // < 0
```



