package com.project.app;

import com.project.entity.Bus;
import com.project.input.InputModeMenu;

import java.util.List;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        InputModeMenu menu = new InputModeMenu(scanner);

        while (true) {

            List<Bus> buses = menu.start();
            if (buses == null) break;

            System.out.println("\nПолучены данные (" + buses.size() + "):");
            buses.forEach(System.out::println);
        }
        System.out.println("Выход из программы.");
        scanner.close();
    }
}
