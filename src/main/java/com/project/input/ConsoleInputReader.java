package com.project.input;

import com.project.entity.Bus;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ConsoleInputReader {

    private final Scanner scanner;

    public ConsoleInputReader(Scanner scanner) {
        this.scanner = scanner;
    }

    public List<Bus> read(int size) {
        List<Bus> buses = new ArrayList<>(size);

        for (int i = 0; i < size; i++) {
            System.out.printf("%nАвтобус #%d%n", i + 1);
            buses.add(readOneBus());
        }

        return buses;
    }

    private Bus readOneBus() {
        while (true) {
            String number = readNonEmptyString("Введите гос. номер (пример: А196АА48): ");
            String model = readNonEmptyString("Введите модель (пример: ПАЗ 3205): ");
            int mileage = readInt("Введите пробег (целое число > 0): ");

            try {
                return Bus.builder()
                        .setNumber(number)
                        .setModel(model)
                        .setMileage(mileage)
                        .build();
            } catch (IllegalArgumentException e) {
                System.out.println("Ошибка данных: " + e.getMessage());
                System.out.println("Повторите ввод автобуса.\n");
            }
        }
    }

    private String readNonEmptyString(String userText) {
        while (true) {
            System.out.print(userText);
            String input = scanner.nextLine().trim();
            if (!input.isEmpty()) {
                return input;
            }
            System.out.println("Поле не может быть пустым.");
        }
    }

    private int readInt(String userText) {
        while (true) {
            System.out.print(userText);
            String input = scanner.nextLine().trim();
            try {
                return Integer.parseInt(input);
            } catch (NumberFormatException e) {
                System.out.println("Нужно ввести целое число.");
            }
        }
    }
}
