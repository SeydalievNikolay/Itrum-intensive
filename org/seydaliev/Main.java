package org.seydaliev;

import org.seydaliev.aggregation.and.aggregation.of.results.Student;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) {
        List<Order> orders = List.of(
                new Order("Laptop",  1200.0),
                new Order("Smartphone",  800.0),
                new Order("Laptop",  1500.0),
                new Order("Tablet",  500.0),
                new Order("Smartphone",  900.0)
        );

        // Группировка заказов по продуктам и подсчет общей стоимости
        Map<String, Double> totalCostByProduct = orders.stream()
                .collect(Collectors.groupingBy(Order::product, Collectors.summingDouble(Order::cost)));

        // Сортировка продуктов по убыванию общей стоимости
        List<Map.Entry<String, Double>> sortedProducts = totalCostByProduct.entrySet().stream()
                .sorted(Map.Entry.<String, Double>comparingByValue().reversed())
                .limit(3)
                .collect(Collectors.toList());

        // Вывод результата
        System.out.println("Список трех самых дорогих продуктов и их общая стоимость:");
        sortedProducts.forEach(entry -> System.out.printf("%s: %.2f%n", entry.getKey(), entry.getValue()));

        System.out.println();
        System.out.println("Практическое задачние - Stream API - агрегация и объединение результатов");

        List<Student> students = Arrays.asList(
                new Student("Student1", Map.of("Math", 90, "Physics", 85)),
                new Student("Student2", Map.of("Math", 95, "Physics", 88)),
                new Student("Student3", Map.of("Math", 88, "Chemistry", 92)),
                new Student("Student4", Map.of("Physics", 78, "Chemistry", 85))
        );

        Map<String, Double> averageGrades = new HashMap<>();

        students.parallelStream()
                .flatMap(student -> student.getGrades().entrySet().stream())
                .collect(Collectors.groupingBy(Map.Entry::getKey, Collectors.averagingInt(Map.Entry::getValue)))
                .forEach((subject, averGrade) -> averageGrades.put(subject, averGrade));

        System.out.println("Общая Map с средними оценками по всем предметам: " + averageGrades);
    }
}