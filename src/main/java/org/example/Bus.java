package org.example;

import java.util.Objects;

public class Bus {
    private String number;
    private String model;
    private Integer mileage;

    public Bus(String number, String model, Integer mileage){
        this.setNumber(number);
        this.setModel(model);
        this.setMileage(mileage);
    }

        public String getNumber() {
        return number;
    }

        public String getModel() {
        return model;
    }

    public Integer getMileage() {
        return mileage;
    }

    public void setNumber(String number) {
        String trimNumber = number != null? number.trim() : "";
        if(trimNumber.isEmpty()){
            throw new IllegalArgumentException("Номер автобуса отсутствует");
        }
        this.number = number;
    }

        public void setModel(String model) {
        String trimModel = model != null? model.trim() : "";
        if(trimModel.isEmpty()){
            throw new IllegalArgumentException("Название модели отсутствует");
        }
        this.model = model;
    }

    public void setMileage(Integer mileage) {
        if(mileage != null && mileage < 0){
            throw new IllegalArgumentException("Пробег не может быть отрицательным значением");
        }
        this.mileage = mileage;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Bus bus)) return false;
        return Objects.equals(number, bus.number) && Objects.equals(model, bus.model) && Objects.equals(mileage, bus.mileage);
    }

    @Override
    public int hashCode() {
        return Objects.hash(number, model, mileage);
    }

    @Override
    public String toString() {
        return String.format("Bus{number=%s, model='%s', mileage=%s km}", number, model, mileage);
    }
}
