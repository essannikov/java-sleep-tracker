package ru.yandex.practicum.sleeptracker.interfaces;

import ru.yandex.practicum.sleeptracker.SleepingSession;

import java.util.List;
import java.util.function.Function;

public interface FunctionInterface extends Function<List<SleepingSession>, Long> {
}