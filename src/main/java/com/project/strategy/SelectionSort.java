package com.project.strategy;

import java.util.Comparator;
import java.util.Objects;

public class SelectionSort<T> implements SortingStrategy<T> {

    @Override
    public void sort(T[] array, Comparator<T> comparator) {
        Objects.requireNonNull(array, "Список не должен быть пустым");
        Objects.requireNonNull(comparator, "Comparator не должен быть null");

        for (int i = 0; i < array.length - 1; i++) {
            int minIdx = i;

            for (int j = i + 1; j < array.length; j++) {
                if (comparator.compare(array[j], array[minIdx]) < 0) {
                    minIdx = j;
                }
            }

            if (minIdx != i) {
                T tmp = array[i];
                array[i] = array[minIdx];
                array[minIdx] = tmp;
            }
        }
    }
}