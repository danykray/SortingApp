import java.util.regex.Pattern;

public class Bus {
    private final String plateNumber;
    private final String model;
    private final int mileage;

    public Bus(Builder builder) {
        this.plateNumber = validatePlateNumber(builder.plateNumber);
        this.model = validateModel(builder.model);
        this.mileage = validateMileage(builder.mileage);
    }

    public String validatePlateNumber(String plateNumber) {
        if (plateNumber == null || plateNumber.trim().isEmpty()) {
            throw new IllegalArgumentException("Государственный номер не может быть пустым");
        }
        String regex = "^[А-ЯA-Z]\\d{3}[А-ЯA-Z]{2}\\d{2,3}$";
        if (!Pattern.matches(regex, plateNumber)) {
            throw new IllegalArgumentException("Неверный формат гос. номера." +
                    "\n"+
                    "В гос.номере должны использоваться только следующие буквы: АВЕКМНОРСТУХ" +
                    "\n"+
                    " Формат гос.номера: буква + 3 цифры + 2 буквы + 2–3 цифры региона. Пример: Р196НО48");
        }
        return plateNumber;
    }

    public String validateModel(String model) {
        if (model == null || model.trim().isEmpty()) {
            throw new IllegalArgumentException("Модель не может быть пустой");
        }
        String regex = "^[А-Яа-я0-9\\s]+$";
        if (!Pattern.matches(regex, model)) {
            throw new IllegalArgumentException(
                    "Модель должна содержать только русские буквы, цифры и пробелы"
            );
        }
        return model.trim();
    }

    public int validateMileage(int mileage) {
        if (mileage <= 0) {
            throw new IllegalArgumentException("Пробег должен быть положительным числом (больше 0)");
        }
        return mileage;
    }

    public String getPlateNumber() { return plateNumber; }
    public String getModel() { return model; }
    public int getMileage() { return mileage; }

    @Override
    public String toString() {
        return "Bus{" +
                "plateNumber='" + plateNumber + '\'' +
                ", model='" + model + '\'' +
                ", mileage=" + mileage +
                '}';
    }

    public static class Builder {
        private String plateNumber;
        private String model;
        private int mileage = 0;

        public Builder setPlateNumber(String plateNumber) {
            this.plateNumber = plateNumber;
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
