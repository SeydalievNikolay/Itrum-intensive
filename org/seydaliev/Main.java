package org.seydaliev;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class Main {
    public static <T> Map<T, Integer> countOfElements(T[] array) {
        Map<T, Integer> occurrences = new HashMap<>();
        for (T item : array) {
            occurrences.put(item, occurrences.getOrDefault(item,  0) +  1);
        }
        return occurrences;
    }
    public interface Filter {
        Object apply(Object o);
    }

    public static class FilterUtil {
        public static Object[] filter(Object[] array, Filter filter) {
            Object[] filtered = new Object[array.length];
            int j =   0;

            for (Object o : array) {
                Object applied = filter.apply(o);
                if (applied != null) {
                    filtered[j++] = applied;
                }
            }
            return Arrays.copyOf(filtered, j);
        }
    }

    public static class NonNullFilter implements Filter {
        @Override
        public Object apply(Object o) {
            return o;
        }
    }
    public static void main(String[] args) {
        System.out.println("Task №1 ");
        System.out.println();
        Object[] array = {"Hello", null, "World", null, "!"};
        Filter filter = new NonNullFilter();
        Object[] filteredArray = FilterUtil.filter(array, filter);

        for (Object obj : filteredArray) {
            System.out.println(obj);
        }

        System.out.println();
        System.out.println("Task №2");
        System.out.println();

        String[] words = {"привет", "пока", "chao", "привет", "яблоко", "месяц", "завтра"};
        Map<String, Integer> wordCount = countOfElements(words);
        System.out.println(wordCount);
    }
}