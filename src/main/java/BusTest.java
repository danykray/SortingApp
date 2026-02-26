public class BusTest {

    public static void main(String[] args) {
        System.out.println(" Тестирование класса Bus \n");

        testValidCreationBus();
        testPlateNumber();
        testInvalidModel();
        testNegativeMileage();
        testEmptyFields();

        System.out.println("\n Тесты завершены");
    }

    public static void testValidCreationBus() {
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

    public static void testPlateNumber() {
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

    public static void testInvalidModel() {
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

    public static void testNegativeMileage() {
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

    public static void testEmptyFields() {
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
