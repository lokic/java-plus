/**
 * 在函数式编程中，有时可能需要在lambda中临时传递一些数据，如果重新创建一个类来传递就显得有些繁琐。
 * 使用元组传递就显得比较方便，但是如果使用元组，在使用的时候需要getT1，getT2来获取元组的值，
 * 如果直接用getT1作为方法入参，则缺少可读性，不知道t1的业务含义；
 * 如果把getT1的值赋值给临时变量，再调用方法，则每次都要显性的赋值，比较麻烦。
 * <p>
 * 这个包下的函数，旨在减少使用元组传递的时候getT1，getT2的显性调用， 对lambda中入参变量名和元组中变量的默认绑定，减少显性赋值，提高代码可读性，使代码看起来更加简洁:
 * <p>
 * 如下 {@link com.github.lokic.javaplus.functional.tuple.TupleFunction2 } 的使用，可以很清楚得看出 t1是index，t2是desc：
 * <pre>{@code
 *     Optional.of(xx)
 *              .map(x -> {
 *                 Integer index = ...
 *                 String desc = ...
 *                 return Tuple.of(index, desc)
 *              })
 *             .map((TupleFunctional<Integer, String, String>) (index, desc) -> a + b))
 * }</pre>
 *
 * 或者
 *
 * <pre>{@code
 *     Optional.of(xx)
 *              .map(x -> {
 *                 Integer index = ...
 *                 String desc = ...
 *                 return Tuple.of(index, desc)
 *              })
 *             .map(TupleFunctional.cast((index, desc) -> a + b)))
 * }</pre>
 */
package com.github.lokic.javaplus.functional.tuple;