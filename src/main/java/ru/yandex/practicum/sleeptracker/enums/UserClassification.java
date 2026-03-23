package ru.yandex.practicum.sleeptracker.enums;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public enum UserClassification {
    OWL(1, "Сова"),
    LARK(2, "Жаворонок"),
    DOVE(3, "Голубь");

    private final int number;
    private final String name;
    private static final Map<Integer, String> BY_CODE = new HashMap<>();

    UserClassification(int number, String name) {
        this.number = number;
        this.name = name;
    }

    public int getNumber() {
        return number;
    }

    public String getName() {
        return name;
    }

    public static Optional<UserClassification> getByCode(int number) {
        return Arrays.stream(UserClassification.values()).
                filter(userClassification -> userClassification.getNumber() == number).
                findFirst();
    }
}
