import java.util.ArrayList;
import java.time.LocalDate;

public class Holidays {
    private static final ArrayList<Holiday> holidays = new ArrayList<>();
    static {
        holidays.add(new Holiday(LocalDate.of(2013, 1, 1)));
        holidays.add(new Holiday(LocalDate.of(2013, 3, 28)));
        holidays.add(new Holiday(LocalDate.of(2013, 3, 29)));
            holidays.add(new Holiday(LocalDate.of(2013, 4, 1)));
            holidays.add(new Holiday(LocalDate.of(2013, 4, 30)));
            holidays.add(new Holiday(LocalDate.of(2013, 5, 1)));
            holidays.add(new Holiday(LocalDate.of(2013, 5, 8)));
            holidays.add(new Holiday(LocalDate.of(2013, 5, 9)));
            holidays.add(new Holiday(LocalDate.of(2013, 6, 5)));
            holidays.add(new Holiday(LocalDate.of(2013, 6, 6)));
            holidays.add(new Holiday(LocalDate.of(2013, 6, 21)));
            holidays.add(new Holiday(LocalDate.of(2013, 7, 1), true));
            holidays.add(new Holiday(LocalDate.of(2013, 11, 1)));
            holidays.add(new Holiday(LocalDate.of(2013, 12, 24)));
            holidays.add(new Holiday(LocalDate.of(2013, 12, 25)));
            holidays.add(new Holiday(LocalDate.of(2013, 12, 26)));
            holidays.add(new Holiday(LocalDate.of(2013, 12, 31))); 
    }

    public static boolean isHoliday(LocalDate date) {
        boolean result = holidays.stream().anyMatch(holiday -> holiday.isHoliday(date));
        return result;
    }
}
