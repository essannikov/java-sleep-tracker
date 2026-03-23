package ru.yandex.practicum.sleeptracker.functions;

import ru.yandex.practicum.sleeptracker.SleepingSession;
import ru.yandex.practicum.sleeptracker.enums.UserClassification;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;
import java.util.function.Function;

public class FunctionUserClassification implements Function<List<SleepingSession>, Long> {

    private final LocalTime OWL_START = LocalTime.of(23,0);
    private final LocalTime OWL_END = LocalTime.of(9,0);
    private final LocalTime LARK_START = LocalTime.of(22,0);
    private final LocalTime LARK_END = LocalTime.of(7,0);

    @Override
    public Long apply(List<SleepingSession> sleepingSessions) {
        if (sleepingSessions == null || sleepingSessions.isEmpty()) {
            return (long) UserClassification.DOVE.getNumber();
        }

        long countNightOwl = sleepingSessions.stream()
                .filter(sleepingSession -> { return
                        (sleepingSession.getStart().isAfter(
                                LocalDateTime.of(sleepingSession.getEnd().minusDays(1).toLocalDate(), OWL_START)) &&
                                sleepingSession.getEnd().isAfter(
                                        LocalDateTime.of(sleepingSession.getEnd().toLocalDate(), OWL_END)));})
                .count();

        long countNightLark = sleepingSessions.stream()
                .filter(sleepingSession -> { return
                        (sleepingSession.getStart().isBefore(
                                LocalDateTime.of(sleepingSession.getEnd().minusDays(1).toLocalDate(), LARK_START)) &&
                                sleepingSession.getEnd().isBefore(
                                        LocalDateTime.of(sleepingSession.getEnd().toLocalDate(), LARK_END)));})
                .count();

        if (countNightOwl > countNightLark) {
            return (long) UserClassification.OWL.getNumber();
        } else if (countNightOwl < countNightLark) {
            return (long) UserClassification.LARK.getNumber();
        }
        return (long) UserClassification.DOVE.getNumber();
    }
}
