# java-plus

## 项目简介

在 java8 的基础上提供一些实用的扩展，让java更加好用

## 使用

- 添加依赖

  ```xml
  <dependency>
      <groupId>com.github.lokic</groupId>
      <artifactId>java-plus</artifactId>
      <version>${lastest.version}</version>
  </dependency>
  ```
  > 最新版本[查询](https://search.maven.org/artifact/com.github.lokic/java-plus)

## 部分功能介绍

- 自定义的一些FunctionalInterface：

  - [ThrowXxxx](https://github.com/lokic/java-plus/tree/master/src/main/java/com/github/lokic/javaplus/functional/throwable)
    ：抛出受检异常的FunctionalInterface
  - [SneakythrowsXxxx](https://github.com/lokic/java-plus/blob/master/src/main/java/com/github/lokic/javaplus/functional/sneakythrows)
    ：偷偷抛出受检异常的FunctionalInterface
    - 在函数式编程中，如果调用一些方法是会抛出受检异常的，又希望lambda在碰到异常的情况下直接结束，就需要在lambda中强制try catch，然后转换成非受检异常抛出，这么做多了一层转换甚是繁琐。
    - 使用 SneakyThrowXxxx 的FunctionInterface，可以直接把受检异常变成非受检异常抛出，且不需要任何异常类的重新包装，
      这样既不需要定义一个统一非受检异常，也不需要定义很多特殊的非受检异常。需要捕获异常的时候也十分方便，直接try catch受检异常就可以了。在代码中也可以更加方便得使用方法引用，使代码看起来更加简洁和优雅。
  - [TupleXxxx](https://github.com/lokic/java-plus/blob/master/src/main/java/com/github/lokic/javaplus/functional/tuple)
    ：Tuple（元组）相关的FunctionalInterface
  - [EntryXxxx](https://github.com/lokic/java-plus/blob/master/src/main/java/com/github/lokic/javaplus/functional/entry)
    ：Entry相关的FunctionalInterface
  - [FunctionX](https://github.com/lokic/java-plus/tree/master/src/main/java/com/github/lokic/javaplus/functional/function)
    、[ConsumerX](https://github.com/lokic/java-plus/tree/master/src/main/java/com/github/lokic/javaplus/functional/consumer)
    、[PredicateX](https://github.com/lokic/java-plus/tree/master/src/main/java/com/github/lokic/javaplus/functional/predicate)
    ：定义了多个入参的Function、Consumer、Predicate

- FunctionalInterface之间的转换：主要查看XxxxFunctional相关的interface

  - [EntryFunctional](https://github.com/lokic/java-plus/blob/master/src/main/java/com/github/lokic/javaplus/functional/entry/EntryFunctional.java)
    ： 转换成Entry相关FunctionalInterface
  - [SneakyThrowsFunctional](https://github.com/lokic/java-plus/blob/master/src/main/java/com/github/lokic/javaplus/functional/sneakythrows/SneakyThrowsFunctional.java)
    ：转换成SneakyThrow相关的FunctionalInterface
  - [TupleFunctional](https://github.com/lokic/java-plus/blob/master/src/main/java/com/github/lokic/javaplus/functional/tuple/TupleFunctional.java)
    ：转换成Tuple（元组）相关的FunctionalInterface

- Lazy：带缓存功能的 Supplier

  - Supplier 每一次执行 Supplier.get() 都会执行一次 Supplier.get() 内的业务逻辑。 如果在一些Supplier.get() 比较耗时的场景，且多次执行 Supplier.get()
    ，结果相同的情况下， 我们没有必要多次执行，只需要把执行第一次的结果保存下来，之后多次调用直接返回即可，Lazy 就是这个目的设计出来的。

- [Property](https://github.com/lokic/java-plus/tree/master/src/main/java/com/github/lokic/javaplus/property)
  ：在定义枚举类之后，经常需要基于枚举类的某个字段作为key来查询枚举，抽象出这个模块来实现key到枚举的映射和转换逻辑，[示例](https://github.com/lokic/java-plus/tree/master/src/test/java/com/github/lokic/javaplus/property)
  - Property1、Property2、Property3 分别用于支持不同数量的值作为key来查询枚举的场景


- Builder：对POJO提供的通用builder，也可以作为对POJO提供fluent风格的setter

- Fors：提供类似scala中for-comprehensions的能力（for-loop和yield的语法）

  - Optionals.Fors：对Optional的支持
  - Streams.Fors：对Stream的支持
  - CompletableFutures.Fors：对CompletableFuture的支持

- Collectors：Collector相关扩展

  - Collectors.Reversed：倒序相关
  - Collectors.Distinct：去重相关

- [Join](https://github.com/lokic/java-plus/tree/master/src/main/java/com/github/lokic/javaplus/join/Join.java)
  ：一种类似SQL中join的操作，可以把内存中的Stream流像操作数据库中的表一样进行各种join

   ```java
   Stream<Integer> streamA = Stream.of(1, 2, 3, 4);
   Stream<String> streamB = Stream.of("1", "2", "3");
   
   Join.stream(streamA)
     .leftOuterJoin(streamB, Join.on(a -> String.valueOf(a), b -> b)
     .stream()   // 转成Stream之后是一个多元组的流，可以结合TupleFunctional进行很多便利的操作
     .map(...)
   ```

  

