import java.util.regex.Pattern;

public class Bus {
    private final String plateNumber;
    private final String model;
    private final int mileage;

    private Bus(Builder builder) {
        this.plateNumber = validatePlateNumber(builder.plateNumber);
        this.model = validateModel(builder.model);
        this.mileage = validateMileage(builder.mileage);
    }

    private String validatePlateNumber(String plateNumber) {
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

    private String validateModel(String model) {
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

    private int validateMileage(int mileage) {
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
class BusTest {

    public static void main(String[] args) {
        System.out.println(" Тестирование класса Bus \n");

        testValidCreationBus();
        testPlateNumber();
        testInvalidModel();
        testNegativeMileage();
        testEmptyFields();

        System.out.println("\n Тесты завершены");
    }

    private static void testValidCreationBus() {
        System.out.println("Тест №1: Базовая проверка корректного создания автобуса");
        try {
            Bus bus = new Bus.Builder()
                    .setPlateNumber("А196АА48")
                    .setModel("ПАЗ 01")
                    .setMileage(1000000)
                    .build();

            assert bus.getPlateNumber().equals("А196АА48") : "Номер не соответствует";
            assert bus.getModel().equals("ПАЗ 01") : "Модель не соответствует";
            assert bus.getMileage() == 1000000 : "Пробег не соответствует";

            System.out.println("✓ Тест пройден успешно: Автобус создан корректно");
            System.out.println("  Создан: " + bus);
        } catch (Exception e) {
            System.out.println("✗ Ошибка! \n Исправьте:" + e.getMessage());
        }
    }

    private static void testPlateNumber() {
        System.out.println("\nТест №2: Проверка корректного создания гос. номера");

        String[] invalidPlateNumbers = {"123ABC48", "ABC12348", "А12ВС48", "А1234ВС48", "", null};

        for (String plateNumber : invalidPlateNumbers) {
            try {
                Bus bus = new Bus.Builder()
                        .setPlateNumber(plateNumber)
                        .setModel("ПАЗ")
                        .build();
                System.out.println("✓ Тест пройден ");
            } catch (IllegalArgumentException e) {
                System.out.println("✗ Ошибка! \n Гос.номер: " +
                        (plateNumber == null ? "null" : plateNumber) + " не прошел проверку." + "\n Исправьте:" + e.getMessage());
            }
        }
    }

    private static void testInvalidModel() {
        System.out.println("\nТест №3: Проверка корректного создания модели");

        String[] invalidModels = {"PAZ 01", "ПАЗ-01", "", null};

        for (String model : invalidModels) {
            try {
                Bus bus = new Bus.Builder()
                        .setPlateNumber("Н196НО48")
                        .setModel(model)
                        .build();
                System.out.println("✓ Тест пройден " + model);
            } catch (IllegalArgumentException e) {
                System.out.println("✗ Ошибка! \n Исправьте: " +
                        (model == null ? "null" : model) + " - " + e.getMessage());
            }
        }
    }

    private static void testNegativeMileage() {
        System.out.println("\nТест №4: Проверка отрицательного пробега");
        try {
            Bus bus = new Bus.Builder()
                    .setPlateNumber("А123АА48")
                    .setModel("Камаз 02")
                    .setMileage(100)
                    .build();
            System.out.println("✓ Тест пройден ");
        } catch (IllegalArgumentException e) {
            System.out.println("✗ Ошибка! \n Исправьте:" + e.getMessage());
        }
    }

    private static void testEmptyFields() {
        System.out.println("\nТест 5: Проверка пустых полей");

        // Проверка пустого номера
        try {
            Bus bus = new Bus.Builder()
                    .setPlateNumber("")
                    .setModel("МАЗ")
                    .build();
            System.out.println("✓ Тест пройден");
        } catch (IllegalArgumentException e) {
            System.out.println("✗ Ошибка! \n Исправьте: " + e.getMessage());
        }

        try {
            Bus bus = new Bus.Builder()
                    .setPlateNumber("А123АА48")
                    .setModel("")
                    .build();
            System.err.println("✓ Тест пройден");
        } catch (IllegalArgumentException e) {
            System.out.println("✗ Ошибка! \n Исправьте: " + e.getMessage());
        }
    }
}
