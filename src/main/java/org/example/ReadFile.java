package org.example;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

public class ReadFile {

    private static final String FILEPATH_ = "/buses.txt";

    public static List<Bus> readAll() {
        List<Bus> result = new ArrayList<>();

        InputStream is = ReadFile.class.getResourceAsStream(FILEPATH_);
        if (is == null) {
            throw new IllegalStateException(
                    "Ресурс '" + FILEPATH_ + "' не найден в classpath");
        }

        try (BufferedReader br = new BufferedReader(
                new InputStreamReader(is, StandardCharsets.UTF_8))) {

            String line;
            int lineNumber = 0;

            while ((line = br.readLine()) != null) {
                lineNumber++;

            if (line.isBlank()) {
                    continue;
                }

            try {
                Bus bus = BusParser.parse(line, lineNumber);
                result.add(bus);
            } catch (InvalidBusDataException e) {
                    System.err.println(e.getMessage());
            }
            }
            } catch (IOException e) {
                System.err.println("Ошибка ввода‑вывода: " + e.getMessage());
            }

        return result;
    }
}
