package ru.yandex.practicum.sleeptracker.functions;

import ru.yandex.practicum.sleeptracker.SleepingSession;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.Period;
import java.util.List;
import java.util.function.Function;

public class FunctionSleeplessNight implements Function<List<SleepingSession>, Long> {

    private final LocalTime nightStart = LocalTime.of(0,0);
    private final LocalTime nightEnd = LocalTime.of(6,0);
    private final LocalTime noonTime = LocalTime.of(12,0);

    @Override
    public Long apply(List<SleepingSession> sleepingSessions) {
        if (sleepingSessions == null || sleepingSessions.isEmpty()) {
            return (long) 0;
        }

        // Также будем считать, что если первая сессия сна в файле началась после 12 дня,
        // потенциальной ночью для сна считается следующая ночь,
        // а если до 12 — то предыдущая.
        int nextNight = 0;
        if (sleepingSessions.getFirst().getStart().toLocalTime().isAfter(noonTime)) {
            nextNight++;
        }

        int countAllNight = Period.between(sleepingSessions.getFirst().getStart().toLocalDate().plusDays(nextNight),
                sleepingSessions.getLast().getEnd().toLocalDate().plusDays(1)).getDays();

        long countSleepNight = sleepingSessions.stream()
                .filter(sleepingSession -> {
                    return (sleepingSession.getStart().isBefore(
                                LocalDateTime.of(sleepingSession.getEnd().toLocalDate(), nightEnd)) &&
                                sleepingSession.getEnd().isAfter(
                                        LocalDateTime.of(sleepingSession.getEnd().toLocalDate(), nightStart))); })
                .count();

        return countAllNight - countSleepNight;
    }
}
