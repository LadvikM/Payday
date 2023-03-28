import java.time.LocalDate;
import java.time.Month;
import java.time.format.TextStyle;
import java.time.temporal.ChronoField;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;


public class DatesService {
    public List<Date> getDates(Integer year, LocalDate goodFriday) {
        List<Date> paymentDates = new ArrayList<>();
        Locale estonianLocale = new Locale("et", "EE");
        Date paymentDate;
        for (int i = 1; i <= 12; i++) {
            paymentDate = new Date();
            paymentDate.setYear(year);
            paymentDate.setMonth(Month.of(i).getDisplayName(TextStyle.FULL_STANDALONE, estonianLocale));
            LocalDate payDay = LocalDate.of(year, i, 10);
            findAndSetPayday(year, goodFriday, i, paymentDate, payDay);
            payDay = LocalDate.of(year, i, paymentDate.getPayDay());
            findAndSetReminderDay(goodFriday, paymentDate, payDay);
            paymentDates.add(paymentDate);
        }
        return paymentDates;
    }


    private static void findAndSetPayday(Integer year, LocalDate goodFriday, int i, Date paymentDate, LocalDate payDay) {
        boolean isSaturday = payDay.get(ChronoField.DAY_OF_WEEK) == 6;
        boolean isSunday = payDay.get(ChronoField.DAY_OF_WEEK) == 7;
        if (isSaturday) {
            payDay = LocalDate.of(year, i, payDay.getDayOfMonth() - 1);
            changePayDayIfOnGoodFriday(goodFriday, paymentDate, payDay);
        } else if (isSunday) {
            payDay = LocalDate.of(year, i, payDay.getDayOfMonth() - 2);
            changePayDayIfOnGoodFriday(goodFriday, paymentDate, payDay);
        } else changePayDayIfOnGoodFriday(goodFriday, paymentDate, payDay);
    }

    private static void changePayDayIfOnGoodFriday(LocalDate goodFriday, Date paymentDate, LocalDate payDay) {
        boolean isGoodFriday = payDay.isEqual(goodFriday);
        if (isGoodFriday) {
            paymentDate.setPayDay(payDay.getDayOfMonth() - 1);

        } else
            paymentDate.setPayDay(payDay.getDayOfMonth());
    }

    private static void findAndSetReminderDay(LocalDate goodFriday, Date paymentDate, LocalDate payDay) {
        LocalDate reminderDay;
        if (goodFriday.isEqual(payDay.minusDays(3))) {
            reminderDay = payDay.minusDays(6);
            paymentDate.setReminderDay(reminderDay.getDayOfMonth());
        } else if (payDay.get(ChronoField.DAY_OF_WEEK) == 1 || payDay.get(ChronoField.DAY_OF_WEEK) == 2
                || payDay.get(ChronoField.DAY_OF_WEEK) == 3) {
            reminderDay = payDay.minusDays(5);
            paymentDate.setReminderDay(reminderDay.getDayOfMonth());
        } else if (payDay.get(ChronoField.DAY_OF_WEEK) == 4 || payDay.get(ChronoField.DAY_OF_WEEK) == 5) {
            reminderDay = payDay.minusDays(3);
            paymentDate.setReminderDay(reminderDay.getDayOfMonth());
        }
    }





}






