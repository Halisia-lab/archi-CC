package use_cases.member.application.payment;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public class RecurringPaymentUtils {
    static LocalDate today = LocalDate.now().plusMonths(0);
    static LocalDate onMonthLater = LocalDate.now().plusMonths(1);
    public static long DAYS_UNTIL_NEXT_MONTH = ChronoUnit.DAYS.between(today, onMonthLater);
    public static int DAYS_IN_CURRENT_MONTH = LocalDate.now().lengthOfMonth();
}
