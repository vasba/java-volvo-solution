import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class TestSolution {
    public static void main(String[] args) throws ParseException {
        String filePath = "test/testdata/testData.txt";
        testTax(filePath);

        String filePath1 = "test/testdata/2013-02-06.txt";
        testTax(filePath1);

        String filePath2 = "test/testdata/2013-02-07.txt";
        testTax(filePath2);

        String filePath3 = "test/testdata/2013-02-08.txt";
        testTax(filePath3);

        String filePath4 = "test/testdata/2013-03-26.txt";
        testTax(filePath4);

        String filePath5 = "test/testdata/2013-03-28.txt";
        testTax(filePath5);
    }

    private static int testTax(String filePath) throws ParseException {
        int tax=0;
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            Date date;
            ArrayList<Date> dates = new ArrayList<>();
            while ((line = br.readLine()) != null) {
                //System.out.println(line);
                date = dateFormat.parse(line);
                dates.add(date);
            }
            
            Solution congestionTaxCalculator = new Solution();
            tax = congestionTaxCalculator.getTax("Car", dates);
            System.out.println("Car tax: " + tax);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return tax;
    }
}
