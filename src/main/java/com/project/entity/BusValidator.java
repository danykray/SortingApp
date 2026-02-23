package com.project.entity;

public class BusValidator {

    private static void testValidCreationBus() {
        System.out.println("Тест №1: Базовая проверка корректного создания автобуса");
        try {
            Bus bus = new Bus.Builder()
                    .setNumber("А196АА48")
                    .setModel("ПАЗ 01")
                    .setMileage(1000000)
                    .build();

            assert bus.getNumber().equals("А196АА48") : "Номер не соответствует";
            assert bus.getModel().equals("ПАЗ 01") : "Модель не соответствует";
            assert bus.getMileage() == 1000000 : "Пробег не соответствует";

            System.out.println("✓ Тест пройден успешно: Автобус создан корректно");
            System.out.println("  Создан: " + bus);
        } catch (Exception e) {
            System.out.println("✗ Ошибка! \n Исправьте:" + e.getMessage());
        }
    }

    private static void testPlateNumber() {
        System.out.println("\nТест №2: Проверка корректного создания гос. номера");

        String[] invalidPlateNumbers = {"123ABC48", "ABC12348", "А12ВС48", "А1234ВС48", "", null};

        for (String plateNumber : invalidPlateNumbers) {
            try {
                Bus bus = new Bus.Builder()
                        .setNumber(plateNumber)
                        .setModel("ПАЗ")
                        .build();
                System.out.println("✓ Тест пройден ");
            } catch (IllegalArgumentException e) {
                System.out.println("✗ Ошибка! \n Гос.номер: " +
                        (plateNumber == null ? "null" : plateNumber) + " не прошел проверку." + "\n Исправьте:" + e.getMessage());
            }
        }
    }

    private static void testInvalidModel() {
        System.out.println("\nТест №3: Проверка корректного создания модели");

        String[] invalidModels = {"PAZ 01", "ПАЗ-01", "", null};

        for (String model : invalidModels) {
            try {
                Bus bus = new Bus.Builder()
                        .setNumber("Н196НО48")
                        .setModel(model)
                        .build();
                System.out.println("✓ Тест пройден " + model);
            } catch (IllegalArgumentException e) {
                System.out.println("✗ Ошибка! \n Исправьте: " +
                        (model == null ? "null" : model) + " - " + e.getMessage());
            }
        }
    }

    private static void testNegativeMileage() {
        System.out.println("\nТест №4: Проверка отрицательного пробега");
        try {
            Bus bus = new Bus.Builder()
                    .setNumber("А123АА48")
                    .setModel("Камаз 02")
                    .setMileage(100)
                    .build();
            System.out.println("✓ Тест пройден ");
        } catch (IllegalArgumentException e) {
            System.out.println("✗ Ошибка! \n Исправьте:" + e.getMessage());
        }
    }

    private static void testEmptyFields() {
        System.out.println("\nТест 5: Проверка пустых полей");

        // Проверка пустого номера
        try {
            Bus bus = new Bus.Builder()
                    .setNumber("")
                    .setModel("МАЗ")
                    .build();
            System.out.println("✓ Тест пройден");
        } catch (IllegalArgumentException e) {
            System.out.println("✗ Ошибка! \n Исправьте: " + e.getMessage());
        }

        try {
            Bus bus = new Bus.Builder()
                    .setNumber("А123АА48")
                    .setModel("")
                    .build();
            System.err.println("✓ Тест пройден");
        } catch (IllegalArgumentException e) {
            System.out.println("✗ Ошибка! \n Исправьте: " + e.getMessage());
        }
    }
}
