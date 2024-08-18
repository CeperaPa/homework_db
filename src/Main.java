import animals.AbsAnimal;
import data.AnimalTypeData;
import data.ColorData;
import data.CommandsData;
import factory.AnimalFactory;
import tables.AbsTable;
import tables.AnimalTable;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {

    private static AbsTable animalTable;

    static {
        try {
            animalTable = new AnimalTable();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static void main(String[] args) throws SQLException {

        List<AbsAnimal> animals = new ArrayList<>();

        Scanner scanner = new Scanner(System.in);

        CommandsData[] commandsData = CommandsData.values();
        String[] commandsString = new String[CommandsData.values().length];
        for (int i = 0; i < commandsString.length; i++) {
            commandsString[i] = commandsData[i].name().toLowerCase();
        }

        while (true) {

            System.out.printf("Введите команду: %s\n", String.join(" или ", commandsString));
            String commandStr = scanner.next().trim().toUpperCase();

            boolean isCommandIncorrect = false;
            for (CommandsData command : commandsData) {
                if (command.name().equals(commandStr)) {
                    isCommandIncorrect = true;
                    break;
                }
            }

            if (!isCommandIncorrect) {
                System.out.println("Ошибка! Вы ввели неверную команду");
                continue;
            }

            CommandsData userCommandData = CommandsData.valueOf(commandStr);
            switch (userCommandData) {
                case ADD:
                    AnimalFactory animalFactory = new AnimalFactory();
                    AnimalTypeData[] animalTypesData = AnimalTypeData.values();
                    String[] animalTypeStr = new String[animalTypesData.length];
                    for (int i = 0; i < animalTypesData.length; i++) {
                        animalTypeStr[i] = animalTypesData[i].name().toLowerCase();
                    }
                    AbsAnimal userAnimal;
                    String userAnimalStr = "";
                    while (true) {
                        System.out.printf("Введите животное: %s\n", String.join(" или ", animalTypeStr));
                        userAnimalStr = scanner.next().trim().toUpperCase();

                        boolean isAnimalIncorrect = false;
                        for (AnimalTypeData command : animalTypesData) {
                            if (command.name().equals(userAnimalStr)) {
                                isAnimalIncorrect = true;
                                break;
                            }
                        }
                        if (isAnimalIncorrect) {
                            break;
                        }
                        System.out.println("Ошибка! Вы ввели неверное животное. Попробуйте ещё раз.");
                    }
                    AnimalTypeData animalTypeData = AnimalTypeData.valueOf(userAnimalStr);
                    userAnimal = animalFactory.create(animalTypeData);

                    System.out.println("Введите имя животного");
                    String name = scanner.next().trim();
                    userAnimal.setName(name.substring(0, 1).toUpperCase() + name.substring(1));

                    int age;
                    boolean correctAge = false;

                    while (!correctAge) {
                        System.out.println("Введите возраст животного:");
                        String ageAnimal = scanner.next().trim();

                        try {
                            age = Integer.parseInt(ageAnimal);
                            if (age > 0) {
                                userAnimal.setAge(age);
                                correctAge = true;
                            } else
                                System.out.println("Ошибка! Пожалуйста, введите положительное число.");
                        } catch (NumberFormatException e) {
                            System.out.println("Ошибка! Пожалуйста, введите число.");
                        }

                    }

                    float weight;
                    boolean correctWeight = false;

                    while (!correctWeight) {
                        System.out.println("Введите вес животного:");
                        String weightAnimal = scanner.next().trim();

                        try {
                            weight = Float.parseFloat(weightAnimal);
                            if (weight > 0) {
                                userAnimal.setWeight(weight);
                                correctWeight = true;
                            } else
                                System.out.println("Ошибка! Пожалуйста, введите положительное число.");
                        } catch (NumberFormatException e) {
                            System.out.println("Ошибка! Пожалуйста, введите число.");
                        }
                    }

                    ColorData[] colorData = ColorData.values();
                    String[] animalColor = new String[colorData.length];
                    for (int i = 0; i < colorData.length; i++) {
                        animalColor[i] = colorData[i].name().toLowerCase();
                    }

                    while (true) {
                        System.out.printf("Введите цвет животного: %s\n", String.join(" или ", animalColor));
                        String animalColorStr = scanner.next().trim().toUpperCase();

                        boolean isColorIncorrect = false;
                        for (ColorData color : colorData) {
                            if (color.name().equals(animalColorStr)) {
                                isColorIncorrect = true;
                                userAnimal.setColor(animalColorStr);
                                break;
                            }
                        }
                        if (isColorIncorrect) {
                            break;
                        }
                        System.out.println("Ошибка! Вы ввели неверный цвет. Пожалуйста повторите.");
                    }
                    animals.add(userAnimal);
                    break;

                List<String> columns = new ArrayList<>();
                columns.add("id");
                columns.add("type");
                columns.add("name");
                columns.add("age");
                columns.add("weight");
                columns.add("color");
                animalTable.create(columns);
                animalTable.insert(userAnimal);


                case LIST:
                    for (AbsAnimal animal : animals) {
                        System.out.println(animal.toString());
                    }
                    break;

                case EXIT:
                    System.out.println("Вы ввели команду exit. До новых встреч!");
                    System.exit(0);
            }
        }
    }
}
