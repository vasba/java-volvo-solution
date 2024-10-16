import java.time.*;
public class TimeAwareTax {
    private LocalTime startTime, endTime;
    private int tax;

    public TimeAwareTax(LocalTime startTime, LocalTime endTime, int tax) {
        this.startTime = startTime;
        this.endTime = endTime;
        this.tax = tax;
    }

    public TimeAwareTax(int startHour, int startMinute, int endHour, int endMinute, int tax) {
        this.startTime = LocalTime.of(startHour, startMinute);
        this.endTime = LocalTime.of(endHour, endMinute);
        this.tax = tax;
    }

    public boolean isWithin(LocalTime time) {
        return time.isAfter(startTime) && time.isBefore(endTime);
    }

    public int getTax() {
        return tax;
    }
}
