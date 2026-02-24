package com.project.app;

import com.project.entity.Bus;
import com.project.entity.BusComparators;
import com.project.input.InputModeMenu;
import com.project.strategy.SelectionSort;
import com.project.strategy.SortingStrategy;

import java.util.Comparator;
import java.util.List;
import java.util.Locale;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        InputModeMenu menu = new InputModeMenu(scanner);
        SortingStrategy<Bus> sortingStrategy = new SelectionSort<>();

        while (true) {
            List<Bus> buses = menu.start();
            if (buses == null) break;

            System.out.println("\nПолучены данные (" + buses.size() + "):");
            buses.forEach(System.out::println);

            if(buses.isEmpty()) {
                System.out.println("Массив пуст - сортировка не требуется");
                continue;
            }

            while (true) {
                String choice = showSortMenu(scanner);

                if ("5".equals(choice)) {
                    System.out.println("Возврат в меню выбора ввода данных\n");
                    break;
                }
                if ("0".equals(choice)) {
                    System.out.println("Завершение программы");
                    scanner.close();
                    return;
                }

                Comparator<Bus> comparator = choiceToComparator(choice);
                if (comparator == null) {
                    System.out.println("Введено некорректное значение");
                    continue;
                }

                Bus[] array = buses.toArray(new Bus[0]);
                sortingStrategy.sort(array, comparator);

                System.out.println("\nПосле сортировки: ");
                for (Bus bus : array) {
                    System.out.println(bus);
                }
                System.out.println();
            }
        }
        System.out.println("Выход из программы.");
        scanner.close();
    }

    private static String showSortMenu(Scanner scanner) {
        System.out.println("""
                
                    Выберите вариант сортировки:
                    1 - По номеру
                    2 - По модели
                    3 - По пробегу
                    4 - По всем полям (номер -> модель -> пробег)
                    5 - Выйти в начало (выбор ввода данных)
                    0 - Завершить программу
                """);
        return scanner.nextLine().trim();
    }
    private static Comparator<Bus> choiceToComparator(String choice) {
        return switch (choice) {
            case "1" -> BusComparators.byNumber();
            case "2" -> BusComparators.byModel();
            case "3" -> BusComparators.byMileage();
            case "4" -> BusComparators.byAllFields();
            default -> null;
        };
    }
}
