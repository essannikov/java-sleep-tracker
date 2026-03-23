package ru.yandex.practicum.sleeptracker;

import ru.yandex.practicum.sleeptracker.functions.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

public class SleepTrackerApp {

    public static void main(String[] args) {
        if (args.length != 1) {
            System.out.println("Не задано имя файла");
            return;
        }

        String filename = args[0];
        List<SleepingSession> sleepingSessionList;
        try {
            sleepingSessionList = new SleepingSessionLoader().getSessions(filename);
        } catch (IOException exIO) {
            System.out.println(exIO.getMessage());
            return;
        }

        List<Function<List<SleepingSession>, Long>> functionsList = getFunctionList();
        List<String> resultFunc = functionsList.stream().
                map(listLongFunction ->
                        new SleepAnalysisResult(listLongFunction).apply(sleepingSessionList)).
                peek(System.out::println).
                toList();
    }

    protected static List<Function<List<SleepingSession>, Long>> getFunctionList() {
        List<Function<List<SleepingSession>, Long>> functionsList = new ArrayList<>();

        functionsList.add(new FunctionCountSession());
        functionsList.add(new FunctionMinDurSession());
        functionsList.add(new FunctionMaxDurSession());
        functionsList.add(new FunctionAvgDurSession());
        functionsList.add(new FunctionCountBadSession());
        functionsList.add(new FunctionSleeplessNight());
        functionsList.add(new FunctionUserClassification());

        return functionsList;
    }
}