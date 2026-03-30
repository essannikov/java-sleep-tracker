package ru.yandex.practicum.sleeptracker.functions;

import ru.yandex.practicum.sleeptracker.SleepingSession;
import ru.yandex.practicum.sleeptracker.interfaces.FunctionInterface;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.Period;
import java.util.List;

public class FunctionSleeplessNight implements FunctionInterface {

    private static final LocalTime NIGHT_START = LocalTime.of(0,0);
    private static final LocalTime NIGHT_END = LocalTime.of(6,0);
    private static final LocalTime NOON_TIME = LocalTime.of(12,0);

    @Override
    public Long apply(List<SleepingSession> sleepingSessions) {
        if (sleepingSessions == null || sleepingSessions.isEmpty()) {
            return 0L;
        }

        // Также будем считать, что если первая сессия сна в файле началась после 12 дня,
        // потенциальной ночью для сна считается следующая ночь,
        // а если до 12 — то предыдущая.
        int nextNight = 0;
        if (sleepingSessions.getFirst().getStart().toLocalTime().isAfter(NOON_TIME)) {
            nextNight++;
        }

        int countAllNight = Period.between(sleepingSessions.getFirst().getStart().toLocalDate().plusDays(nextNight),
                sleepingSessions.getLast().getEnd().toLocalDate().plusDays(1)).getDays();

        long countSleepNight = sleepingSessions.stream()
                .filter(sleepingSession -> {
                    return (sleepingSession.getStart().isBefore(
                                LocalDateTime.of(sleepingSession.getEnd().toLocalDate(), NIGHT_END)) &&
                                sleepingSession.getEnd().isAfter(
                                        LocalDateTime.of(sleepingSession.getEnd().toLocalDate(), NIGHT_START))); })
                .count();

        return countAllNight - countSleepNight;
    }
}
