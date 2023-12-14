import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Scanner;

public class UserDataReader {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Введите данные (Фамилия Имя Отчество датарождения номертелефона пол):");
        String inputLine = scanner.nextLine();

        try {
            User user = parseUserInput(inputLine);
            saveUserData(user);
        } catch (UserDataException e) {
            System.err.println("Ошибка: " + e.getMessage());
        }
    }

    private static User parseUserInput(String input) throws UserDataException {
        String[] userData = input.split("\\s+");
        if (userData.length != 6) {
            throw new UserDataException("Введено неверное количество данных.");
        }

        String lastName = userData[0];
        String firstName = userData[1];
        String middleName = userData[2];
        String birthDate = userData[3]; // проверка формата
        long phone = parsePhone(userData[4]);
        char gender = parseGender(userData[5]);

        return new User(lastName, firstName, middleName, birthDate, phone, gender);
    }

    private static long parsePhone(String phoneStr) throws UserDataException {
        try {
            return Long.parseLong(phoneStr);
        } catch (NumberFormatException e) {
            throw new UserDataException("Неверный формат номера телефона.");
        }
    }

    private static char parseGender(String genderStr) throws UserDataException {
        if (!genderStr.equals("f") && !genderStr.equals("m")) {
            throw new UserDataException("Неверный формат пола (ожидается 'f' или 'm').");
        }
        return genderStr.charAt(0);
    }

    private static void saveUserData(User user) {
        String fileName = user.getLastName() + ".txt";
        try (PrintWriter out = new PrintWriter(Files.newBufferedWriter(Paths.get(fileName), java.nio.file.StandardOpenOption.CREATE, java.nio.file.StandardOpenOption.APPEND))) {
            out.println(user);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    static class User {
        private final String lastName, firstName, middleName, birthDate;
        private final long phoneNumber;
        private final char gender;

        public User(String lastName, String firstName, String middleName, String birthDate, long phoneNumber, char gender) {
            this.lastName = lastName;
            this.firstName = firstName;
            this.middleName = middleName;
            this.birthDate = birthDate;
            this.phoneNumber = phoneNumber;
            this.gender = gender;
        }

        @Override
        public String toString() {
            return String.format("%s %s %s %s %d %c", lastName, firstName, middleName, birthDate, phoneNumber, gender);
        }

        public String getLastName() {
            return lastName;
        }
    }

    static class UserDataException extends Exception {
        public UserDataException(String message) {
            super(message);
        }
    }
}