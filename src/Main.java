import java.time.LocalDate;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws Exception {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Sisesta aasta:");

        try {
            int year = scanner.nextInt();
            while (year != 0) {
                LocalDate goodFriday = calculateGoodFriday(year);
                DatesService datesService = new DatesService();
                List<Date> paymentDates = datesService.getDates(year, goodFriday);
                printTable(paymentDates);
                WriteToCSV writeToCSV = new WriteToCSV();
                writeToCSV.writeToCSV(paymentDates, year);
                System.out.println("Jätkamiseks sisesta uus aasta arv. Väljumiseks sisesta 0");
                year = scanner.nextInt();
            }


        } catch (Exception e) {
            System.out.println("Aasta sisestamisel tekkis viga. Proovi uuesti.");
            scanner.next();
            main(args);
        }

    }


    private static LocalDate calculateGoodFriday(int year) {
        EasterCalculation easterCalculation = new EasterCalculation();
        LocalDate easter = easterCalculation.calculateEaster(year);
        return easter.minusDays(2);
    }

    private static void printTable(List<Date> paymentDates) {
        Printer printer = new Printer();
        printer.printHeader();
        printer.printDates(paymentDates);
    }

}