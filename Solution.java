import java.text.SimpleDateFormat;
import java.util.*;
import java.time.*;

public class Solution {
    private static Map<String, Integer> tollFreeVehicles = new HashMap<>();
    private static final SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm");
    private static final SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
    private static int MAX_FEE = 60;

    static {
        tollFreeVehicles.put("MOTORCYCLE", 0);
        tollFreeVehicles.put("TRACTOR", 1);
        tollFreeVehicles.put("EMERGENCY", 2);
        tollFreeVehicles.put("DIPLOMAT", 3);
        tollFreeVehicles.put("FOREIGN", 4);
        tollFreeVehicles.put("MILITARY", 5);
    }

    public int solution(String vehicle, String[] stringDates)
    {
        // This function given a string vehicle and an array stringDates consisting of one string for each DateTime the vehicle passed the tax station
        // should return an integer that is the sum of the tax for this car, 
        ArrayList<Date> dates = new ArrayList<Date>();
        try {
        
        for (String stringDate : stringDates) {
            Date date = formatter.parse(stringDate);
            dates.add(date);
        }
        } catch (Exception e) {
            // do nothing today
        }
        
        return getTax(vehicle, dates);
    }

    public Map<String, List<Date>> splitDatesByDay(Date[] dates) {
        Map<String, List<Date>> datesByDay = new HashMap<>();

        for (Date date : dates) {
            String key = dateFormat.format(date);
            if (!datesByDay.containsKey(key)) {
                datesByDay.put(key, new ArrayList<>());
            }
            datesByDay.get(key).add(date);
        }

        return datesByDay;
    }

    public int getTax(String vehicle, ArrayList<Date> dates) {
        Date[] datesArray = dates.toArray(new Date[0]);
        Map<String, List<Date>> datesByDay = splitDatesByDay(datesArray);
        
        int totalFee = 0;
        for (Map.Entry<String, List<Date>> entry : datesByDay.entrySet()) {
            totalFee += getDayTax(vehicle, entry.getValue().toArray(new Date[0]));
        }
        return totalFee;
    }

    public int getDayTax(String vehicle, Date[] datesArray) {
        int totalFee = 0;
        int previousFee = 0;
        Arrays.sort(datesArray);
        Date startSingleChargeDate = datesArray[0];
        for (int i = 0; i < datesArray.length; i++) {
            Date date = datesArray[i];
            //System.out.println(date.toString());
            int tollFee = getTollFee(date, vehicle);
            if (i == 0 || singleChargeTriggered(startSingleChargeDate, date)) {
                totalFee -= previousFee;
                totalFee += Math.max(tollFee, previousFee);
            } else {
                startSingleChargeDate = date;
                totalFee += tollFee;
            }
            //System.out.println(totalFee);
            //System.out.println(tollFee);
            // It seems I might not be able to handle the case with passages
            // within 60 minutes in time
            previousFee = tollFee;
        }
        //System.out.println(totalFee);
        if (totalFee > MAX_FEE) return MAX_FEE;
        return totalFee;
    }

    public boolean singleChargeTriggered(Date first, Date second) {
        // Dates should be ordered
        long diffMilliseconds = second.getTime() - first.getTime();
        long minutes = diffMilliseconds/1000/60;
        return minutes < 60;
    }

    private boolean isTollFreeVehicle(String vehicle) {
        if (vehicle == null) return false;
        return tollFreeVehicles.containsKey(vehicle);
    }

    public int getTollFee(Date date, String vehicle)
    {
        if (isTollFreeDate(date) || isTollFreeVehicle(vehicle)) return 0;
        LocalDateTime localDateTime = date.toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime();
        LocalTime time = localDateTime.toLocalTime();

        return TimeAwareTaxes.getTax(time);
    }

    private Boolean isTollFreeDate(Date date)
    {
        int dateDay = date.getDay();
        if (dateDay == Calendar.SATURDAY || dateDay == Calendar.SUNDAY) return true;

        LocalDate localDate = date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        
        return Holidays.isHoliday(localDate);
    }
}