package EV5;

import java.util.Date;

public class Clock {
	
	public int sec;
	public int minute;
	public int hour;
	public int day;
	public String dayOfWeek;
	public String month;
	public int year;
	
	public void setTime() {
		Date date = new Date();
		String s = date.toString();
		//System.out.println(s);
		String ss[] = s.split(" ");
		dayOfWeek=ss[0];
		month = ss[1];
		day = Integer.parseInt(ss[2]);
		year = Integer.parseInt(ss[ss.length-1]);
		String time[] = ss[3].split(":");
		sec = Integer.parseInt(time[2]);
		minute = Integer.parseInt(time[1]);
		hour = Integer.parseInt(time[0]);	
	}
	
}
