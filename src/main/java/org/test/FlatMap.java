package org.test;

import java.util.Arrays;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Stream;

public class FlatMap {
    public static void main(String[] args) {

        List<Integer> list1 = Arrays.asList(1, 2, 3, 4, 5, 6, 7);
        List<Integer> list2 = Arrays.asList(2, 4, 6);
        List<Integer> list3 = Arrays.asList(3, 5, 7);

        List<List<Integer>> listOfLists = Arrays.asList(list1, list2, list3);

        //System.out.println(listOfLists);

//        listOfLists.stream()
//                .map(l -> l.size())
//                .forEach(System.out::println);

        Function<List<?>, Integer> size = List::size;

//        listOfLists.stream()
//                .map(size)
//                .forEach(System.out::println);

        Function<List<Integer>, Stream<Integer>> flatMapper =
                List::stream;


        listOfLists.stream()
                    .map(flatMapper)
                    .forEach(System.out::println);    // returns stream of stream

        listOfLists.stream()
                .flatMap(flatMapper)
                .forEach(System.out::println);        // returns flattened

    }
}
