package MiniProject.bussiness.util;

import java.math.BigDecimal;
import java.text.NumberFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class Format {
    public static final SimpleDateFormat spf = new SimpleDateFormat("dd/MM/yyyy");

    public static String formatCurrency(BigDecimal amount) {
        double amountDouble = amount.doubleValue();

        Locale vietnameseLocale = new Locale("vi", "VN");
        NumberFormat currencyFormat = NumberFormat.getCurrencyInstance(vietnameseLocale);

        return currencyFormat.format(amountDouble);
    }
}
