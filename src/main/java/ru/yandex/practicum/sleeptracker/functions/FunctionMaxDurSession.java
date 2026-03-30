package ru.yandex.practicum.sleeptracker.functions;

import ru.yandex.practicum.sleeptracker.SleepingSession;
import ru.yandex.practicum.sleeptracker.interfaces.FunctionInterface;

import java.util.Comparator;
import java.util.List;

public class FunctionMaxDurSession implements FunctionInterface {
    @Override
    public Long apply(List<SleepingSession> sleepingSessions) {
        if (sleepingSessions == null || sleepingSessions.isEmpty()) {
            return 0L;
        }

        return sleepingSessions.stream()
                .max(Comparator.comparing(SleepingSession::getDuration))
                .get().getDuration().toMinutes();
    }
}
