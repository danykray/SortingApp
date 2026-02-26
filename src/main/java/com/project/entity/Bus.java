package com.project.entity;

public class Bus {

    private final String number;
    private final String model;
    private final int mileage;

    private Bus(Builder builder) {
        this.number = BusValidator.validateNumber(builder.number);
        this.model = BusValidator.validateModel(builder.model);
        this.mileage = BusValidator.validateMileage(builder.mileage);
    }

    public String getNumber() { return number; }
    public String getModel() { return model; }
    public int getMileage() { return mileage; }
    public static Builder builder() { return new Builder(); }

    @Override
    public String toString() {
        return "Bus{" +
                "number='" + number + '\'' +
                ", model='" + model + '\'' +
                ", mileage=" + mileage +
                '}';
    }

    public static class Builder {
        private String number;
        private String model;
        private int mileage = 0;

        private Builder() {}

        public Builder setNumber(String number) {
            this.number = number;
            return this;
        }

        public Builder setModel(String model) {
            this.model = model;
            return this;
        }

        public Builder setMileage(int mileage) {
            this.mileage = mileage;
            return this;
        }

        public Bus build() {
            return new Bus(this);
        }
    }
}
