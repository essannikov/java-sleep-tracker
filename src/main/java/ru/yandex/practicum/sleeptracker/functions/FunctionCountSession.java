package ru.yandex.practicum.sleeptracker.functions;

import ru.yandex.practicum.sleeptracker.SleepingSession;
import ru.yandex.practicum.sleeptracker.interfaces.FunctionInterface;

import java.util.List;

public class FunctionCountSession implements FunctionInterface {
    @Override
    public Long apply(List<SleepingSession> sleepingSessions) {
        if (sleepingSessions == null || sleepingSessions.isEmpty()) {
            return 0L;
        }

        return (long) sleepingSessions.size();
    }
}
