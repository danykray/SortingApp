package com.project.entity;

import com.project.entity.Bus;
import com.project.entity.BusParser;
import com.project.entity.BusValidator;
import com.project.exception.InvalidBusDataException;

public class BusTest {

    private static int testCount = 0;
    private static int passedCount = 0;

    public static void main(String[] args) {
        System.out.println("Запуск тестов");
        System.out.println("_".repeat(60));

        runAllTests();

        System.out.println("\n" + "_".repeat(60));
        System.out.printf("Вывод: %d из %d тестов пройдено успешно%n", passedCount, testCount);
        if (passedCount == testCount) {
            System.out.println("✅ Все тесты пройдены успешно!");
        } else {
            System.out.println("❌ Не все тесты пройдены!");
        }
    }

    private static void runAllTests() {
        testBusBuilderValidCreation();
        testBusBuilderEmptyNumber();
        testBusBuilderNullNumber();
        testBusBuilderInvalidNumberFormat();
        testBusBuilderEmptyModel();
        testBusBuilderNullModel();
        testBusBuilderNegativeMileage();
        testBusBuilderZeroMileage();
        testBusValidatorValidNumber();
        testBusValidatorEmptyNumber();
        testBusValidatorNullNumber();
        testBusValidatorInvalidNumberFormat();
        testBusValidatorValidModel();
        testBusValidatorNullModel();
        testBusValidatorInvalidModelCharacters();
        testBusValidatorPositiveMileage();
        testBusValidatorZeroMileage();
        testBusValidatorNegativeMileage();
        testBusParserValidLine();
        testBusParserNullLine();
        testBusParserWrongFieldCount();
        testBusParserEmptyNumberField();
        testBusParserInvalidMileageFormat();
        testBusParserNegativeMileageInParse();
    }

    private static void testBusParserNegativeMileageInParse() {

    }

    private static void testBusParserInvalidMileageFormat() {

    }

    private static void testBusParserEmptyNumberField() {

    }

    private static void testBusParserWrongFieldCount() {

    }

    private static void testBusParserNullLine() {

    }

    private static void testBusParserValidLine() {

    }

    private static void testBusValidatorNegativeMileage() {

    }

    private static void testBusValidatorZeroMileage() {

    }

    private static void testBusValidatorPositiveMileage() {

    }

    private static void testBusValidatorInvalidModelCharacters() {

    }

    private static void testBusValidatorNullModel() {

    }

    private static void assertTrue(boolean condition, String message) {
        testCount++;
        if (condition) {
            passedCount++;
            System.out.printf("✅ Тест №%d пройден : %s%n", testCount, message);
        } else {
            System.out.printf("❌ Тест №%d Ошибка! %s%n", testCount, message);

        }
    }

    private static void assertFalse(boolean condition, String message) {
        assertTrue(!condition, message);
    }

    private static <T> void assertEquals(T expected, T actual, String message) {

        if ((expected == null && actual == null) || (expected != null && expected.equals(actual))) {
            passedCount++;
            System.out.printf("✅ Тест №%d пройден успешно : %s%n", testCount, message);
        } else {
            System.out.printf("❌ Тест №%d Ошибка! %s. Исправьте: (ожидалось - %s, получено - %s)%n",
                    testCount, message, expected, actual);
        }
    }

    private static void assertThrows(Class<? extends Exception> expectedException,
                                     Runnable testCode, String message) {
        testCount++;
        try {
            testCode.run();
            System.out.printf("❌ Тест №%d Ошибка! Исправьте: %s — используется исключение %n", testCount, message);
        } catch (Exception e) {
            if (expectedException.isInstance(e)) {
                passedCount++;
                System.out.printf("✅ Тест №%d пройден. %s (ожидалось %s, получено %s)%n",
                        testCount, message, expectedException.getSimpleName(), e.getClass().getSimpleName());
            } else {
                System.out.printf("❌ Тест №%d Ошибка! Исправьте: %s — ожидалось %s, но получено %s: %s%n",
                        testCount, message, expectedException.getSimpleName(),
                        e.getClass().getSimpleName(), e.getMessage());
            }
        }
    }

    private static void testBusBuilderValidCreation() {
        Runnable test = () -> {
            Bus bus = Bus.builder()
                    .setNumber("Р196НО48")
                    .setModel("ПАЗ 3205")
                    .setMileage(150000)
                    .build();

            assertEquals("Р195НО48", bus.getNumber(), "Проверка номера автобуса");
            assertEquals("ПАЗ 3205", bus.getModel(), "Проверка модели автобуса");
            assertEquals(150000, bus.getMileage(), "Проверка пробега автобуса");
        };
        assertThrows(IllegalArgumentException.class, test, "Базовая проверка на корректное создание автобуса");
    }

    private static void testBusBuilderEmptyNumber() {
        Runnable test = () -> Bus.builder().setNumber("").build();
        assertThrows(IllegalArgumentException.class, test, "Пустой номер вызывает исключение");
    }

    private static void testBusBuilderNullNumber() {
        Runnable test = () -> Bus.builder().setNumber(null).build();
        assertThrows(IllegalArgumentException.class, test, "Null вызывает исключение");
    }

    private static void testBusBuilderInvalidNumberFormat() {
        Runnable test = () -> Bus.builder().setNumber("A123BC45").build();
        assertThrows(IllegalArgumentException.class, test, "Неверный формат номера вызывает исключение");
    }

    private static void testBusBuilderEmptyModel() {
        Runnable test = () -> Bus.builder().setModel("").build();
        assertThrows(IllegalArgumentException.class, test, "Пустая модель вызывает исключение");
    }

    private static void testBusBuilderNullModel() {
        Runnable test = () -> Bus.builder().setModel(null).build();
        assertThrows(IllegalArgumentException.class, test, "Null модель вызывает исключение");
    }

    private static void testBusBuilderNegativeMileage() {
        Runnable test = () -> Bus.builder().setMileage(-5000).build();
        assertThrows(IllegalArgumentException.class, test, "Отрицательный пробег вызывает исключение");
    }

    private static void testBusBuilderZeroMileage() {
        Runnable test = () -> Bus.builder().setMileage(0).build();
        assertThrows(IllegalArgumentException.class, test, "Нулевой пробег вызывает исключение");
    }

    private static void testBusValidatorValidNumber() {
        try {
            String result = BusValidator.validateNumber("Р196НО48");
            assertTrue(result.equals("Р196НО48"), "Корректный номер проходит проверку");
        } catch (Exception e) {
            assertFalse(true, "Корректный номер не вызывает исключение: " + e.getMessage());
        }
    }

    private static void testBusValidatorEmptyNumber() {
        Runnable test = () -> BusValidator.validateNumber("");
        assertThrows(IllegalArgumentException.class, test, "Пустой номер вызывает исключение");
    }

    private static void testBusValidatorNullNumber() {
        Runnable test = () -> BusValidator.validateNumber(null);
        assertThrows(IllegalArgumentException.class, test, "Null номер вызывает исключение");
    }

    private static void testBusValidatorInvalidNumberFormat() {
        Runnable test = () -> BusValidator.validateNumber("A123BC45");
        assertThrows(IllegalArgumentException.class, test, "Неверный формат номера вызывает исключение");
    }

    private static void testBusValidatorValidModel() {
        try {
            String result = BusValidator.validateModel("ПАЗ 3205");
            assertTrue(result.equals("ПАЗ 3205"), "Корректная модель проходит проверку");
        } catch (Exception e) {
            assertFalse(true, "Корректная модель не вызывает исключение: " + e.getMessage());
        }
    }
}