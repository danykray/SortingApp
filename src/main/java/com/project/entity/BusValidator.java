package com.project.entity;

import java.util.regex.Pattern;

public class BusValidator {

    private static final String RUS_LETTERS = "АВЕКМНОРСТУХ";
    private static final Pattern NUMBER_PATTERN = Pattern.compile(
            "^[" + RUS_LETTERS + "]\\d{3}[" + RUS_LETTERS + "]{2}\\d{2,3}$"
    );
    private static final Pattern MODEL_PATTERN = Pattern.compile("^[А-Яа-яA-Za-z0-9\\s]+$");

    private BusValidator(){}

    public static String validateNumber(String number) {
        String n = number.trim();
        if (number == null || n.isEmpty()) {
            throw new IllegalArgumentException("Государственный номер не может быть пустым");
        }
        //String regex = "^[А-ЯA-Z]\\d{3}[А-ЯA-Z]{2}\\d{2,3}$";
        if (!NUMBER_PATTERN.matcher(n).matches()) {
            throw new IllegalArgumentException("Неверный формат гос. номера." +
                    "\n"+
                    "В гос.номере должны использоваться только следующие буквы: АВЕКМНОРСТУХ" +
                    "\n"+
                    " Формат гос.номера: буква + 3 цифры + 2 буквы + 2–3 цифры региона. Пример: Р196НО48");
        }
        return n;
    }

    public static String validateModel(String model) {
        String m = model.trim();
        if (model == null || m.isEmpty()) {
            throw new IllegalArgumentException("Модель не может быть пустой");
        }
        //String regex = "^[А-Яа-я0-9\\s]+$";
        if (!MODEL_PATTERN.matcher(m).matches()) {
            throw new IllegalArgumentException(
                    "Модель должна содержать только буквы, цифры и пробелы"
            );
        }
        return m;
    }

    public static int validateMileage(int mileage) {
        if (mileage <= 0) {
            throw new IllegalArgumentException("Пробег должен быть положительным числом (больше 0)");
        }
        return mileage;
    }
}

