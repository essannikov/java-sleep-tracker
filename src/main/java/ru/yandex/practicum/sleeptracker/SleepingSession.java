package ru.yandex.practicum.sleeptracker;

import ru.yandex.practicum.sleeptracker.enums.SleepingQuality;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.Objects;

public class SleepingSession {
    private final LocalDateTime start;
    private final LocalDateTime end;
    private final SleepingQuality quality;
    private final Duration duration;

    public SleepingSession(LocalDateTime start, LocalDateTime end, SleepingQuality quality) {
        this.start = start;
        this.end = end;
        this.quality = quality;
        this.duration = Duration.between(start, end);
    }

    public LocalDateTime getStart() {
        return start;
    }

    public LocalDateTime getEnd() {
        return end;
    }

    public SleepingQuality getQuality() {
        return quality;
    }

    public Duration getDuration() {
        return duration;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        SleepingSession that = (SleepingSession) o;
        return Objects.equals(start, that.start) && Objects.equals(end, that.end) && quality == that.quality;
    }

    @Override
    public int hashCode() {
        return Objects.hash(start, end, quality);
    }
}
