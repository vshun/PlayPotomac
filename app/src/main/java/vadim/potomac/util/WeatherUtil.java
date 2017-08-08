package vadim.potomac.util;

import java.util.Date;
import java.util.Locale;
import java.text.ParseException;
import java.text.SimpleDateFormat;


public 	class WeatherUtil {

    public static float celsiusToFahrenheit(float tCelsius) {
         return (9.0f / 5.0f) * tCelsius + 32;
    }

    private static final SimpleDateFormat f = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault());
	private static final SimpleDateFormat df = new SimpleDateFormat("E", Locale.getDefault());

	public static String dayOfTheWeek (String date) throws ParseException {
    	Date myDate  = f.parse(date);
    	return df.format(myDate);
    }
}