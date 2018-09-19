package tw.idv.aloha.lineBot.Utli;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
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
	
	public String dayOpeningTime(String allWeek){
		//"weekday_text" : ["星期一: 08:00 – 01:00","星期二: 08:00 – 01:00", "星期三: 08:00 – 01:00"]
		String todayOpeningTime = "";
		Date date = new Date();
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		int dayOfWeek = c.get(Calendar.DAY_OF_WEEK);
		
		
		
		
		
		return todayOpeningTime;
	}
}
