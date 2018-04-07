package utils;

import java.util.Calendar;

public final class CalendarUtils {

    private CalendarUtils() {

    }

    public static int getTimeDifferenceInYears(Calendar startTime, Calendar endTime) {
        if (startTime.after(endTime)) {
            Calendar tempCalendar = startTime;
            startTime = endTime;
            endTime = tempCalendar;
        }

        int years = endTime.get(Calendar.YEAR) - startTime.get(Calendar.YEAR);
        if (endTime.get(Calendar.DAY_OF_YEAR) < startTime.get(Calendar.DAY_OF_YEAR)){
            years--;
        }

        return years;
    }

    public static int getTimeDifferenceInMonths(Calendar startTime, Calendar endTime) {
        if (startTime.after(endTime)) {
            Calendar tempCalendar = startTime;
            startTime = endTime;
            endTime = tempCalendar;
        }

        int years = endTime.get(Calendar.YEAR) - startTime.get(Calendar.YEAR);
        int months = endTime.get(Calendar.MONTH) - startTime.get(Calendar.MONTH);
        if (endTime.get(Calendar.DAY_OF_MONTH) < startTime.get(Calendar.DAY_OF_MONTH)) {
            months--;
        }
        if (months < 0){
            years--;
            months += 12;
        }


        return years * 12 + months;
    }
}
