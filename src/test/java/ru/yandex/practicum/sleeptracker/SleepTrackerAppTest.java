package ru.yandex.practicum.sleeptracker;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import ru.yandex.practicum.sleeptracker.enums.SleepingQuality;
import ru.yandex.practicum.sleeptracker.enums.UserClassification;
import ru.yandex.practicum.sleeptracker.functions.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class SleepTrackerAppTest {
    private static List<SleepingSession> sleepingSessionList = new ArrayList<>();

    @BeforeAll
    public static void create() {
        sleepingSessionList.add(
                new SleepingSession(
                        LocalDateTime.of(2026, 1,1,23,15),
                        LocalDateTime.of(2026,1,2,7,15),
                        SleepingQuality.GOOD));
        sleepingSessionList.add(
                new SleepingSession(
                        LocalDateTime.of(2026, 1,3,1,15),
                        LocalDateTime.of(2026,1,3,5,15),
                        SleepingQuality.BAD));
        sleepingSessionList.add(
                new SleepingSession(
                        LocalDateTime.of(2026, 1,4,23,15),
                        LocalDateTime.of(2026,1,5,5,15),
                        SleepingQuality.NORMAL));

        sleepingSessionList.sort(Comparator.comparing(SleepingSession::getStart));
    }

    @Test
    public void shouldFunctionAvgDurSessionReturn0() {
        long result = new FunctionAvgDurSession().apply(null);
        assertEquals(0, result);
    }

    @Test
    public void shouldFunctionAvgDurSessionReturn360() {
        long result = new FunctionAvgDurSession().apply(sleepingSessionList);
        assertEquals(360, result);
    }

    @Test
    public void shouldFunctionCountBadSessionReturn0() {
        long result = new FunctionCountBadSession().apply(null);
        assertEquals(0, result);
    }

    @Test
    public void shouldFunctionCountBadSessionReturn1() {
        long result = new FunctionCountBadSession().apply(sleepingSessionList);
        assertEquals(1, result);
    }

    @Test
    public void shouldFunctionCountSessionReturn0() {
        long result = new FunctionCountSession().apply(null);
        assertEquals(0, result);
    }

    @Test
    public void shouldFunctionCountSessionReturn3() {
        long result = new FunctionCountSession().apply(sleepingSessionList);
        assertEquals(3, result);
    }

    @Test
    public void shouldFunctionMaxDurSessionReturn0() {
        long result = new FunctionMaxDurSession().apply(null);
        assertEquals(0, result);
    }

    @Test
    public void shouldFunctionMaxDurSessionReturn480() {
        long result = new FunctionMaxDurSession().apply(sleepingSessionList);
        assertEquals(480, result);
    }

    @Test
    public void shouldFunctionMinDurSessionReturn0() {
        long result = new FunctionMinDurSession().apply(null);
        assertEquals(0, result);
    }

    @Test
    public void shouldFunctionMinDurSessionReturn240() {
        long result = new FunctionMinDurSession().apply(sleepingSessionList);
        assertEquals(240, result);
    }

    @Test
    public void shouldFunctionSleeplessNightReturn0() {
        //когда файл (журнал) пустой
        long result = new FunctionSleeplessNight().apply(null);
        assertEquals(0, result);
    }

    @Test
    public void shouldFunctionSleeplessNightReturn1() {
        long result = new FunctionSleeplessNight().apply(sleepingSessionList);
        assertEquals(1, result);
    }

    @Test
    public void shouldFunctionSleeplessNightReturn2() {
        //когда первая сессия сна начинается после 0:00 и до 12:00
        List<SleepingSession> tmpList = new ArrayList<>(sleepingSessionList);
        tmpList.add(
                new SleepingSession(
                        LocalDateTime.of(2026, 1,1,10,15),
                        LocalDateTime.of(2026,1,1,11,15),
                        SleepingQuality.GOOD));
        tmpList.sort(Comparator.comparing(SleepingSession::getStart));

        long result = new FunctionSleeplessNight().apply(tmpList);
        assertEquals(2, result);
    }

    @Test
    public void shouldFunctionSleeplessNightReturn3() {
        //когда интервал логирования начинается в одном месяце, а заканчивается в другом
        List<SleepingSession> tmpList = new ArrayList<>(sleepingSessionList);
        tmpList.add(
                new SleepingSession(
                        LocalDateTime.of(2025, 12,31,10,15),
                        LocalDateTime.of(2025,12,31,11,15),
                        SleepingQuality.GOOD));
        tmpList.sort(Comparator.comparing(SleepingSession::getStart));

        long result = new FunctionSleeplessNight().apply(tmpList);
        assertEquals(3, result);
    }

    @Test
    public void shouldFunctionUserClassificationReturnDOVE() {
        List<SleepingSession> tmpList = new ArrayList<>();
        tmpList.add(
                new SleepingSession(
                        LocalDateTime.of(2026, 1,5,21,15),
                        LocalDateTime.of(2026,1,6,5,15),
                        SleepingQuality.NORMAL));
        tmpList.add(
                new SleepingSession(
                        LocalDateTime.of(2026, 1,6,23,15),
                        LocalDateTime.of(2026,1,7,9,15),
                        SleepingQuality.NORMAL));
        tmpList.sort(Comparator.comparing(SleepingSession::getStart));

        long result = new FunctionUserClassification().apply(tmpList);
        assertEquals(UserClassification.DOVE.getNumber(), result);

        //Check NULL
        result = new FunctionUserClassification().apply(null);
        assertEquals(UserClassification.DOVE.getNumber(), result);
    }

    @Test
    public void shouldFunctionUserClassificationReturnOWL() {
        List<SleepingSession> tmpList = new ArrayList<>(sleepingSessionList);
        tmpList.add(
                new SleepingSession(
                        LocalDateTime.of(2026, 1,5,23,15),
                        LocalDateTime.of(2026,1,6,9,15),
                        SleepingQuality.NORMAL));
        tmpList.sort(Comparator.comparing(SleepingSession::getStart));

        long result = new FunctionUserClassification().apply(tmpList);
        assertEquals(UserClassification.OWL.getNumber(), result);
    }

    @Test
    public void shouldFunctionUserClassificationReturnLARK() {
        List<SleepingSession> tmpList = new ArrayList<>(sleepingSessionList);
        tmpList.add(
                new SleepingSession(
                        LocalDateTime.of(2026, 1,5,21,15),
                        LocalDateTime.of(2026,1,6,5,15),
                        SleepingQuality.NORMAL));
        tmpList.add(
                new SleepingSession(
                        LocalDateTime.of(2026, 1,6,20,15),
                        LocalDateTime.of(2026,1,7,4,15),
                        SleepingQuality.NORMAL));
        tmpList.add(
                new SleepingSession(
                        LocalDateTime.of(2026, 1,7,21,15),
                        LocalDateTime.of(2026,1,8,3,15),
                        SleepingQuality.NORMAL));
        tmpList.add(
                new SleepingSession(
                        LocalDateTime.of(2026, 1,8,19,15),
                        LocalDateTime.of(2026,1,9,2,15),
                        SleepingQuality.NORMAL));
        tmpList.sort(Comparator.comparing(SleepingSession::getStart));

        long result = new FunctionUserClassification().apply(tmpList);
        assertEquals(UserClassification.LARK.getNumber(), result);
    }
}