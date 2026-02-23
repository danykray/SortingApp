package com.project.entity;

import com.project.exception.InvalidBusDataException;

public class BusParser {

    private static final String DELIMITER_ = ";";

    private BusParser() {

    }

    public static Bus parse(String line, int lineNumber) throws InvalidBusDataException {

    String[] parts = line.split(DELIMITER_);

    if (parts.length != 3) {
        throw new InvalidBusDataException(lineNumber, line, "Ожидалось 3 поля, но получено " + parts.length);
    }
    
    String number = parts[0].trim();
    String model = parts[1].trim();
    String mileageStr = parts[2].trim();

    if (number.isEmpty()) {
        throw new InvalidBusDataException(lineNumber, line, "Поле 'номер' пустое");
    }

    if (!number.matches("[A-Za-z0-9]+")) {
        throw new InvalidBusDataException(lineNumber, line, "Номер должен состоять только из букв и цифр: '" + number + "'");
    }

    if (model.isEmpty()) {
        throw new InvalidBusDataException(lineNumber, line, "Поле 'модель' пустое");
    }

    Integer mileage;

        try {
            mileage = Integer.parseInt(mileageStr);
        } catch (NumberFormatException e) {
            throw new InvalidBusDataException(lineNumber, line, "Пробег не является целым числом: '" + mileageStr + "'");
        }

        if (mileage < 0) {
            throw new InvalidBusDataException(lineNumber, line, "Пробег не может быть отрицательным: " + mileage);
        }

    return new Bus(number, model, mileage);

    }
}
