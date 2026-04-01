package ru.yandex.practicum.sleeptracker;

import ru.yandex.practicum.sleeptracker.enums.UserClassification;
import ru.yandex.practicum.sleeptracker.functions.*;
import ru.yandex.practicum.sleeptracker.interfaces.FunctionInterface;

import java.util.List;

public class SleepAnalysisResult {
    private final FunctionInterface function;

    public SleepAnalysisResult(FunctionInterface function) {
        this.function = function;
    }

    public String apply(List<SleepingSession> sleepingSessionList) {
        String result;

        Long value = function.apply(sleepingSessionList);

        String funcName = function.getClass().getName();
        if (funcName.equals(FunctionCountSession.class.getName())) {
            result = "Количество сессий сна " + value;
        } else if (funcName.equals(FunctionMinDurSession.class.getName())) {
            result = "Минимальная продолжительность сессии (в минутах) " + value;
        } else if (funcName.equals(FunctionMaxDurSession.class.getName())) {
            result = "Максимальная продолжительность сессии (в минутах) " + value;
        } else if (funcName.equals(FunctionAvgDurSession.class.getName())) {
            result = "Средняя продолжительность сессии (в минутах) " + value;
        } else if (funcName.equals(FunctionCountBadSession.class.getName())) {
            result = "Количество сессий с плохим качеством сна " + value;
        } else if (funcName.equals(FunctionSleeplessNight.class.getName())) {
            result = "Бессонные ночи " + value;
        } else if (funcName.equals(FunctionUserClassification.class.getName())) {
            result = "Классификация пользователя " +
                    UserClassification.getByCode(Math.toIntExact(value)).orElse(UserClassification.DOVE).getName();
        } else {
            result = funcName;
        }

        return result;
    }
}
