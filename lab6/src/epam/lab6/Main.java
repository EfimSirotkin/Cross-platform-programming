package epam.lab6;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import classes.Employee;

public class Main {

    public static void main(String[] args) {

        List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);
        System.out.println("****Using skip() and limit() to print elements in a range: ");
        numbers.stream().skip(2).limit(5).forEach(System.out::println);
        System.out.println("****");

        Stream<List<Integer>> integerListStream = Stream.of(
                Arrays.asList(1, 2),
                Arrays.asList(3, 4),
                Arrays.asList(5)
        );

        Stream<Integer> integerStream = integerListStream.flatMap(Collection::stream);
        System.out.println("****Using flatMap to retrieve multiple elements of a collection");
        integerStream.forEach(System.out::println);
        System.out.println("****");

        List<Employee> list = new LinkedList<>();
        list.add(new Employee("100"));
        list.add(new Employee("205"));
        list.add(new Employee("140"));
        System.out.println("****Using reduce to calculate the sum of salaries(Immutable)");
        list.stream().map(Employee::getSalary).forEach(System.out::println);

        Integer sum = list
                .stream()
                .map(Employee::getSalary)// преобразуем stream Employee в stream Integer
                .reduce(0, (Integer a, Integer b) -> Integer.sum(a, b));//сворачиваем stream в одно значение
        System.out.println("=  " + sum);
        System.out.println("****");

        List<String> personList = Arrays.asList("Bruce Lee", "Chuck Norris", "Bruce Willis", "Bruce Lee");

        Map<String, Long> result = personList.stream().collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));
        System.out.println("****Use of groupingBy(): ");
        System.out.println(result);
        System.out.println("****");

        personList = personList.stream().distinct().collect(Collectors.toList());
        System.out.println("****Using distinct() to erase the duplicates: ");
        System.out.println(personList);
        System.out.println("****");

        System.out.println("****Using peek() to print elemetnts without interfering");
        List<String> newList = Stream.of("EURO/INR", "USD/AUD", "USD/GBP", "USD/EURO")
                .filter(e -> e.length() > 7)
                .peek(e -> System.out.println("Filtered value: " + e))
                .map(String::toLowerCase)
                .peek(e -> System.out.println("Mapped value: " + e))
                .collect(Collectors.toList());
        System.out.println("****");

    }
}
