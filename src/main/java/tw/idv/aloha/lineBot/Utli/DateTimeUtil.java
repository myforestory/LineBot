package tw.idv.aloha.lineBot.Utli;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateTimeUtil {

	public boolean isBetweenTime(String greaterTime, String beforeTime) {
		Boolean isBetween = false;
		Date date = new Date();
		SimpleDateFormat dateFormat = new SimpleDateFormat("HH");
		dateFormat.format(date);
		try {
			Boolean greater = dateFormat.parse(dateFormat.format(date)).after(dateFormat.parse(greaterTime));
			Boolean before = dateFormat.parse(dateFormat.format(date)).before(dateFormat.parse(beforeTime));
			if (greater || before) {
				isBetween = false;
			} else {
				isBetween = true;
			}
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return isBetween;
	}
}
