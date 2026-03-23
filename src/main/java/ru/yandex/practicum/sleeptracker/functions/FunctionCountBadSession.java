package ru.yandex.practicum.sleeptracker.functions;

import ru.yandex.practicum.sleeptracker.enums.SleepingQuality;
import ru.yandex.practicum.sleeptracker.SleepingSession;

import java.util.List;
import java.util.function.Function;

public class FunctionCountBadSession implements Function<List<SleepingSession>, Long> {
    @Override
    public Long apply(List<SleepingSession> sleepingSessions) {
        if (sleepingSessions == null || sleepingSessions.isEmpty()){
            return (long) 0;
        }

        return sleepingSessions.stream().
                filter(sleepingSession -> sleepingSession.getQuality().equals(SleepingQuality.BAD)).count();
    }
}
