import java.io.BufferedWriter;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.nio.charset.StandardCharsets;
import java.util.List;

public class WriteToCSV {
    public void writeToCSV(List<Date> paymentDates, Integer year) throws Exception {
        try(BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(year + ".csv"), StandardCharsets.UTF_16LE))){
            writer.write("year, month, payday, reminderday" + System.lineSeparator());
            for(Date paymentDate : paymentDates) {
                writer.write(paymentDate.getYear() + ",");
                writer.write(paymentDate.getMonth() + ",");
                writer.write(paymentDate.getPayDay() + ",");
                writer.write(paymentDate.getReminderDay() + System.lineSeparator());
            }
        }
    }
}
