import java.time.LocalTime;
import java.util.ArrayList;

public class TimeAwareTaxes {
    private static final ArrayList<TimeAwareTax> taxes = new ArrayList<>();
    static {
        taxes.add(new TimeAwareTax(6, 0, 6, 29, 8));
        taxes.add(new TimeAwareTax(6, 30, 6, 59, 13));
        taxes.add(new TimeAwareTax(7, 0, 7, 59, 18));
        taxes.add(new TimeAwareTax(8, 0, 8, 29, 13));
        taxes.add(new TimeAwareTax(8, 30, 14, 59, 8));
        taxes.add(new TimeAwareTax(15, 0, 15, 29, 13));
        taxes.add(new TimeAwareTax(15, 30, 16, 59, 18));
        taxes.add(new TimeAwareTax(17, 0, 17, 59, 13));
        taxes.add(new TimeAwareTax(18, 0, 18, 29, 8));
        taxes.add(new TimeAwareTax(18, 30, 5, 59, 0));
   };

    public static int getTax(LocalTime time) {
        return taxes.stream()
                .filter(tax -> tax.isWithin(time))
                .map(TimeAwareTax::getTax).findFirst().orElse(0);
                
    }
}
