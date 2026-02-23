package com.project.app;

import com.project.input.FileDataReader;
import com.project.entity.Bus;

import java.util.List;

public class Main {
    static void main(String[] args) {
        System.out.println("Чтение данных об автобусах из ../buses.txt\n");

        List<Bus> buses = FileDataReader.readAll();

        System.out.println("\nНайдено корректных записей: " + buses.size());

        for (Bus bus : buses) {
            System.out.println(bus);
        }
    }
}