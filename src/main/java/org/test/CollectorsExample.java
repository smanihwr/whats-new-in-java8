package org.test;

import org.test.model.Person;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class CollectorsExample {
    public static void main(String[] args) {

        List<Person> personList = new ArrayList<>();

        try (
                BufferedReader reader =
                        new BufferedReader(
                                new InputStreamReader(
                                        CollectorsExample.class.getClassLoader().getResourceAsStream("people.txt")));

                Stream<String> stream = reader.lines();
        ) {
            stream.map(line -> {
                String[] s = line.split(" ");
                Person p = new Person(s[0].trim(), Integer.parseInt(s[1]));
                personList.add(p);
                return p;
            }).forEach(System.out::println);

        } catch (IOException ioe) {
            System.out.println(ioe);
        }

        Optional<Person> minAgePerson = personList.stream()
                .filter( person -> person.getAge() >= 20)
                .min(Comparator.comparing(Person::getAge));

        System.out.println(minAgePerson);

        Optional<Person> maxAgePerson = personList.stream()
                .filter( person -> person.getAge() >= 20)
                .max(Comparator.comparing(Person::getAge));

        System.out.println(maxAgePerson);

        Map<Integer, List<Person>> personsByAge = personList.stream()
                .collect(
                        Collectors.groupingBy(Person::getAge)
                );
        System.out.println(personsByAge);

        Map<Integer, Long> countByAge = personList.stream()
                .collect(
                        Collectors.groupingBy(Person::getAge, Collectors.counting())
                );
        System.out.println(countByAge);

        Map<Integer, List<String>> listOfNameByAge = personList.stream()
                .collect(
                        Collectors.groupingBy(
                                Person::getAge,
                                Collectors.mapping(Person::getName, Collectors.toList()))
                );
        System.out.println(listOfNameByAge);

        Map<Integer, Set<String>> listOfOrderedNameByAge = personList.stream()
                .collect(
                        Collectors.groupingBy(
                                Person::getAge,
                                Collectors.mapping(
                                        Person::getName,
                                        Collectors.toCollection(TreeSet::new)))
                );
        System.out.println(listOfOrderedNameByAge);

        Map<Integer, String> listOfNameStrByAge = personList.stream()
                .collect(
                        Collectors.groupingBy(
                                Person::getAge,
                                Collectors.mapping(
                                        Person::getName,
                                        Collectors.joining("; ")))
                );
        System.out.println(listOfNameStrByAge);

    }


}
