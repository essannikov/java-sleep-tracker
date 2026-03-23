package ru.yandex.practicum.sleeptracker.functions;

import ru.yandex.practicum.sleeptracker.SleepingSession;

import java.util.Comparator;
import java.util.List;
import java.util.function.Function;

public class FunctionMinDurSession implements Function<List<SleepingSession>, Long> {
    @Override
    public Long apply(List<SleepingSession> sleepingSessions) {
        if (sleepingSessions == null || sleepingSessions.isEmpty()){
            return (long) 0;
        }

        return sleepingSessions.stream().
                min(Comparator.comparing(SleepingSession::getDuration)).
                get().getDuration().toMinutes();
    }
}
