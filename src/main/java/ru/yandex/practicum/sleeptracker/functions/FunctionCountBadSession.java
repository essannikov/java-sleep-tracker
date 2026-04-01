package ru.yandex.practicum.sleeptracker.functions;

import ru.yandex.practicum.sleeptracker.enums.SleepingQuality;
import ru.yandex.practicum.sleeptracker.SleepingSession;
import ru.yandex.practicum.sleeptracker.interfaces.FunctionInterface;

import java.util.List;

public class FunctionCountBadSession implements FunctionInterface {
    @Override
    public Long apply(List<SleepingSession> sleepingSessions) {
        if (sleepingSessions == null || sleepingSessions.isEmpty()) {
            return 0L;
        }

        return sleepingSessions.stream()
                .filter(sleepingSession -> sleepingSession.getQuality().equals(SleepingQuality.BAD)).count();
    }
}
