package org.example;

import java.util.List;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    static void main(String[] args) {
        System.out.println("Чтение данных об автобусах из ../buses.txt\n");

        List<Bus> buses = ReadFile.readAll();

        System.out.println("\nНайдено корректных записей: " + buses.size());

        for (Bus bus : buses) {
            System.out.println(bus);
        }
    }
}