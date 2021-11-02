/**
 * 在函数式编程中，如果调用一些方法是会抛出受检异常的，又希望lambda在碰到异常的情况下直接结束，
 * 就需要在lambda中强制try catch，然后转换成非受检异常抛出，这么做多了一层转换甚是繁琐。
 * <p>
 * 如果需要知道是什么异常的抛出：
 * 可以，用一个统一非受检异常包装，在外层重新把异常拆包出来就能知道原始异常是什么；
 * 或者，用特殊的非受检异常包装这个受检异常，这样或导致有很多非受检异常。
 * <p>
 * 使用 SneakyThrowXxxx 的FunctionInterface，可以直接把受检异常变成非受检异常抛出，且不需要任何异常类的重新包装，
 * 这样既不需要定义一个统一非受检异常，也不需要定义很多特殊的非受检异常。需要捕获异常的时候也十分方便，直接try catch受检异常就可以了;
 * 在代码中也可以更加方便得使用方法引用，使代码看起来更加简洁和优雅。
 * <p>
 * 如下 {@link com.github.lokic.javaplus.functional.sneakythrows.SneakyThrowFunction1 } 的使用：
 * <pre>{@code
 *      Optional.of(0)
 *              .map( (SneakyThrowFunction1<Integer, Integer>) this::throwableMethod );
 *
 *      int throwableMethod(int i) throws Exception {
 *
 *      }
 * }</pre>
 *
 * 或者
 *
 * <pre>{@code
 *      Optional.of(0)
 *              .map( SneakyThrowFunctional.function(this::throwableMethod ));
 *
 *      int throwableMethod(int i) throws Exception {
 *
 *      }
 * }</pre>
 */
package com.github.lokic.javaplus.functional.sneakythrows;
