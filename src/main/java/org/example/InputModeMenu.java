package org.example;

import java.util.Scanner;

import static org.example.InputMode.*;

import java.util.InputMismatchException;
import java.util.Scanner;

enum InputMode {
    MANUAL,
    RANDOM,
    FILE,
    EXIT
}


public class InputModeMenu{

    private final Scanner scanner;

    public InputModeMenu(Scanner scanner) {
        this.scanner = scanner;
    }

    public InputMode chooseMode() {
        while (true) {
            printMenu();

            try {
                int choice = scanner.nextInt();
                scanner.nextLine();

                switch (choice) {
                    case 0: return InputMode.EXIT;
                    case 1: return InputMode.MANUAL;
                    case 2: return InputMode.RANDOM;
                    case 3: return InputMode.FILE;
                    default:
                        System.out.println("Неверный выбор. Попробуйте снова.");
                }
            } catch (InputMismatchException e) {
                System.out.println("Нужно ввести число");
                scanner.nextLine();
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
}