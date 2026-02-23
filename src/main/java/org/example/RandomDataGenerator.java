package org.example;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class RandomDataGenerator {

    private static final int mileageMax = 500000;
    private static final String let = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";

    private final Random random = new Random();

    private List<String> models = List.of(
            "PAZ",
            "GAZ",
            "MAZ",
            "LiAZ",
            "KamAZ"
    );
    //Подшаманить потом
    List<Bus> generate(int size) {

        List<Bus> buses = new ArrayList<>(size);

        for (int i = 0; i < size; i++) {
            buses.add(generateBus());
        }
        return buses;
    }

    private Bus generateBus() { //подшаманить потом
        return Bus.builder()
                .number(generateNumber())
                .model(generateModel())
                .mileage(generateMileage())
                .build();
    }

    private char randomLett(){
        return let.charAt(random.nextInt(let.length()));
    }

    private int generateMileage() {
        return random.nextInt(mileageMax + 1);
    }

    private String generateModel() {
        return models.get(random.nextInt(models.size()));
    }

    private String generateNumber(){
        StringBuilder sb = new StringBuilder();
        sb.append(randomLett());
        sb.append(random.nextInt(10));
        sb.append(random.nextInt(10));
        sb.append(random.nextInt(10));
        sb.append(randomLett());
        sb.append(randomLett());

        return sb.toString();
    }
}