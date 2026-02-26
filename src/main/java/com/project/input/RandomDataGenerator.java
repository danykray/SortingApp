package com.project.input;

import com.project.entity.Bus;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class RandomDataGenerator {

    private static final int MILEAGE_MAX = 500_000;
    private static final String LETTERS = "АВЕКМНОРСТУХ";

    private final Random random = new Random();

    private final List<String> models = List.of(
            "ПАЗ",
            "ГАЗ",
            "МАЗ",
            "ЛИАЗ",
            "КАМАЗ"
    );

    public List<Bus> generate(int size) {

        List<Bus> buses = new ArrayList<>(size);

        for (int i = 0; i < size; i++) {
            buses.add(generateBus());
        }
        return buses;
    }

    private Bus generateBus() {
        return Bus.builder()
                .setNumber(generateNumber())
                .setModel(generateModel())
                .setMileage(generateMileage())
                .build();
    }

    private char randomLett(){
        return LETTERS.charAt(random.nextInt(LETTERS.length()));
    }

    private int generateMileage() {
        return random.nextInt(MILEAGE_MAX + 1);
    }

    private String generateModel() {
        String base = models.get(random.nextInt(models.size()));
        if (random.nextBoolean()) {
            int suffix = random.nextInt(9900) + 1;
            return base + " " + suffix;
        }
        return base;
    }

    private String generateNumber(){
        StringBuilder sb = new StringBuilder();

        sb.append(randomLett());
        sb.append(random.nextInt(10));
        sb.append(random.nextInt(10));
        sb.append(random.nextInt(10));
        sb.append(randomLett());
        sb.append(randomLett());

        int regionDigits = random.nextBoolean() ? 2 : 3;
        for (int i = 0; i < regionDigits; i++) {
            sb.append(random.nextInt(10));
        }

        return sb.toString();
    }
}