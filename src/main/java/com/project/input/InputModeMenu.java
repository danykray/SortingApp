package com.project.input;

import com.project.entity.Bus;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class InputModeMenu {

    private final Scanner scanner;
    //private boolean isRunning = true;

    private final ConsoleInputReader consoleReader;
    private final RandomDataGenerator randomGenerator;

    public InputModeMenu(Scanner scanner) {
        this.scanner = scanner;
        this.consoleReader = new ConsoleInputReader(scanner);
        this.randomGenerator = new RandomDataGenerator();
    }

    public List<Bus> start() {
        //RandomDataGenerator generator = new RandomDataGenerator();

        while (true) {
            printMenu();
            String input = scanner.nextLine().trim();

            try {
                int choice = Integer.parseInt(input);
                switch (choice) {
                    case 1:
                        return manual();
                    case 2:
                        return random();
                    case 3:
                        return file();
                    case 0:
                        return null;
                    default:
                        System.out.println("Неверный выбор. Введите число от 0 до 3.");
                }

            } catch (NumberFormatException e) {
                System.out.println("Нужно ввести число.");
                continue;
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

    private List<Bus> manual() {
        int size = requestArraySize();
        return consoleReader.read(size);
    }

    private List<Bus> random() {
        int size = requestArraySize();
        List<Bus> buses = randomGenerator.generate(size);
        return buses;
    }

    private List<Bus> file() {

        System.out.println("Чтение данных из файла:");

        List<Bus> all = FileDataReader.readAll();

        if (all.isEmpty()) {
            System.out.println("Файл прочитан, но валидных записей не найдено.");
            return all;
        }

        boolean takeAll = requestTakeAllFromFile();
        if (takeAll) {
            return all;
        }

        int n = requestMaxFromFile(all.size());
        return new ArrayList<>(all.subList(0, n));
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

    private int requestMaxFromFile(int maxAvailable) {
        while (true) {
            System.out.printf("Сколько верных записей взять из файла? (1..%d): ", maxAvailable);
            String input = scanner.nextLine().trim();
            try {
                int n = Integer.parseInt(input);
                if (n >= 1 && n <= maxAvailable) return n;
                System.out.println("Число вне диапазона.");
            } catch (NumberFormatException e) {
                System.out.println("Нужно ввести число.");
            }
        }
    }

    public boolean requestTakeAllFromFile() {
        while (true) {
            System.out.print("Прочитать все верные записи из файла? (y/n): ");
            String input = scanner.nextLine().trim().toLowerCase();

            if (input.equals("y") || input.equals("yes") || input.equals("д") || input.equals("да")) return true;
            if (input.equals("n") || input.equals("no") || input.equals("н") || input.equals("нет")) return false;

            System.out.println("Введите y/n.");
        }
    }
}
