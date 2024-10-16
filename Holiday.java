import java.time.LocalDate;

public class Holiday {
    private LocalDate date;
    private boolean monthHoliday = false;

    public Holiday(LocalDate date) {
        this.date = date;
    }

    public Holiday(LocalDate date, boolean monthHoliday) {
        this.date = date;
        this.monthHoliday = monthHoliday;
    }

    public boolean isHoliday(LocalDate date) {
        if (monthHoliday) {
            return this.date.getMonth().equals(date.getMonth());
        }
        return this.date.equals(date);
    }
}
