package com.vodafone;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;

public class DateConverter {
    public static LocalDate convertDate(String dateString) throws ParseException {
        // Parse the input date string to LocalDate object
        SimpleDateFormat inputFormatter = new SimpleDateFormat("yyyy-MM-dd");
        Date date = inputFormatter.parse(dateString);

        Instant instant = date.toInstant();

        return instant.atZone(ZoneId.systemDefault()).toLocalDate();
    }
}