package org.example;

import java.util.List;
import java.util.Scanner;

public class InputModeMenu {

    private final Scanner scanner;
    private boolean isRunning = true;

    public InputModeMenu(Scanner scanner) {
        this.scanner = scanner;
    }

    public void start() {
        RandomDataGenerator generator = new RandomDataGenerator();

        while (isRunning) {
            printMenu();
            String input = scanner.nextLine().trim();

            try {
                int choice = Integer.parseInt(input);
                switch (choice) {
                    case 1:
                        manual();
                        break;
                    case 2:
                        random(generator);
                        break;
                    case 3:
                        file();
                        break;
                    case 0:
                        isRunning = false;
                        break;
                    default:
                        System.out.println("Неверный выбор. Введите число от 0 до 3.");
                }

            } catch (NumberFormatException e) {
                System.out.println("Нужно ввести число.");
            }
        }
    }

    private void printMenu() {
        System.out.println("""
        Выберите вариант:
        1 — Ручной ввод
        2 — Случайная генерация
        3 — Чтение из файла
        0 — Выход
        """);
        System.out.print("");
    }

    private void manual() {
        System.out.println("Пока нет");
    }

    private void random(RandomDataGenerator generator) {
        int size = requestArraySize();
        List<Bus> buses = generator.generate(size);

        System.out.println("Результат :");
        buses.forEach(System.out::println);
    }

    private void file() {
        System.out.println("Пока нет");
    }

    private int requestArraySize() {
        while (true) {
            System.out.print("Введите количество автобусов:");
            String input = scanner.nextLine().trim();
            try {
                int size = Integer.parseInt(input);
                if (size > 0) return size;
                else System.out.println("Число должно быть больше 0");
            } catch (NumberFormatException e) {
                System.out.println("Нужно ввести число");
            }
        }
    }
}
