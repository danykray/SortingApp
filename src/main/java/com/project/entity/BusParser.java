package com.project.entity;

import com.project.exception.InvalidBusDataException;

public class BusParser {

    private static final String DELIMITER_ = ";";

    private BusParser() {}

    public static Bus parse(String line, int lineNumber) throws InvalidBusDataException {

        if (line == null) {
            throw new InvalidBusDataException(lineNumber, null, "Строка равна null");
        }

        String[] parts = line.split(DELIMITER_);

        if (parts.length != 3) {
            throw new InvalidBusDataException(lineNumber, line, "Неверный формат строки. Ожидается: гос.номер; модель; пробег");
        }

        String number = parts[0].trim();
        String model = parts[1].trim();
        String mileageStr = parts[2].trim();

        Integer mileage;

        try {
            mileage = Integer.parseInt(mileageStr);
        } catch (NumberFormatException e) {
            throw new InvalidBusDataException(lineNumber, line, "Пробег не является целым числом: '" + mileageStr + "'");
        }

        try {
            return Bus.builder()
                    .setNumber(number)
                    .setModel(model)
                    .setMileage(mileage)
                    .build();
        } catch (IllegalArgumentException e) {
            throw new InvalidBusDataException(lineNumber, line, e.getMessage());
        }

    }
}
