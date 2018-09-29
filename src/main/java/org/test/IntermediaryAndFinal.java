package org.test;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Stream;

public class IntermediaryAndFinal {
    public static void main(String[] args) {
        Stream<String> stream1 = Stream.of("one", "two", "three", "four", "five");

        Predicate<String> p2 = Predicate.isEqual("two");
        Predicate<String> p3 = Predicate.isEqual("three");

        List<String> list = new ArrayList<>();

        stream1
                .peek(System.out::println)
                .filter(p2.or(p3))
                .forEach(list::add);

        list.forEach(System.out::println);
    }
}
