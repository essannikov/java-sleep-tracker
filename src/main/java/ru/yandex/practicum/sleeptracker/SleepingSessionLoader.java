package ru.yandex.practicum.sleeptracker;

import ru.yandex.practicum.sleeptracker.enums.SleepingQuality;
import ru.yandex.practicum.sleeptracker.exceptions.SleepingSessionConvertException;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class SleepingSessionLoader {
    public List<SleepingSession> getSessions(String filename) throws IOException {
        List<SleepingSession> resultList = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(filename, StandardCharsets.UTF_8))) {
            while (br.ready()) {
                try {
                    SleepingSession session = convert(br.readLine());
                    resultList.add(session);
                } catch (SleepingSessionConvertException ex) {
                    System.out.println(ex.getMessage());
                }
            }
        }

        resultList.sort(Comparator.comparing(SleepingSession::getStart));

        return resultList;
    }

    protected SleepingSession convert(String string) throws SleepingSessionConvertException {
        SleepingSession session;

        String[] values = string.split(";");
        if (values.length != 3) {
            throw new SleepingSessionConvertException("Ошибка в строке: " + string);
        }

        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yy HH:mm");
            LocalDateTime start = LocalDateTime.parse(values[0], formatter);
            LocalDateTime end = LocalDateTime.parse(values[1], formatter);
            SleepingQuality quality = SleepingQuality.valueOf(values[2]);

            session = new SleepingSession(start, end, quality);
        } catch (DateTimeParseException errParse) {
            throw new SleepingSessionConvertException("Ошибка формата даты, в строке: " + string);
        } catch (IllegalArgumentException errArg) {
            throw new SleepingSessionConvertException("Ошибка значения качества сна, в строке: " + string);
        }

        return session;
    }
}
