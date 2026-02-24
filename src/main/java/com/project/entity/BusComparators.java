package com.project.entity;

import java.util.Comparator;

public final class BusComparators {

    private BusComparators() {}

    public static Comparator<Bus> byNumber() {
        return Comparator.comparing(Bus::getNumber, String.CASE_INSENSITIVE_ORDER);
    }

    public static Comparator<Bus> byModel() {
        return Comparator.comparing(Bus::getModel, String.CASE_INSENSITIVE_ORDER);
    }

    public static Comparator<Bus> byMileage() {
        return Comparator.comparingInt(Bus::getMileage);
    }

    public static Comparator<Bus> byAllFields() {
        return byNumber()
                .thenComparing(byModel())
                .thenComparing(byMileage());
    }
}