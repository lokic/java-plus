/**
 * {@link com.github.lokic.javaex.func.entry.EntryFunction} 、 {@link com.github.lokic.javaex.func.entry.EntryConsumer},
 * 旨在减少使用 {@link java.util.Map.Entry} 传递的时候， {@link java.util.Map.Entry#getKey()} 、
 * {@link java.util.Map.Entry#getValue()} ()}的显性调用。
 * 对lambda中入参变量名和元组中变量的默认绑定，减少显性赋值，提高代码可读性，使代码看起来更加简洁:
 * <p>
 * 如下 {@link com.github.lokic.javaex.func.entry.EntryFunction } 的使用，可以很清楚得看出 t1是index，t2是desc：
 * <pre>{@code
 *     Map<Integer, String> map = ...
 *     map.entrySet()
 *        .stream()
 *        .map((EntryFunction<Integer, String, String>) (index, desc) -> a + b))
 * }</pre>
 */
package com.github.lokic.javaex.func.entry;