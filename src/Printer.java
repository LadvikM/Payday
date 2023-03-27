import java.util.List;

public class Printer {
    public void printHeader() {
        System.out.printf("%30s %25s %10s %25s %10s%n", "Kuu", "|", "Palk maksta", "|", "Meeldetuletus");
        System.out.printf("%s%n", "----------------------------------------------------------------------------------------------------------------");
    }
    public void printDates(List<Date> paymentDates) {
        for (Date paymentDate : paymentDates) {
            System.out.printf("%30s %25s %10s %25s %10d%n", paymentDate.getMonth(), "|", paymentDate.getPayDay(), "|", paymentDate.getReminderDay());
        }

    }
}
