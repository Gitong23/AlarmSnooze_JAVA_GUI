package EV5;

import java.util.Calendar;
import java.util.Date;

public class Alarm extends Clock{
	public boolean isSetted = false;
	public boolean isEveryDay = false;
	public boolean isSnooze = false;
	public int snoozeMin = 1; //Defualt Value
	
	
	@Override
	public void setTime() { // get tomorrow time
		// TODO Auto-generated method stub
		Date date = new Date();
		Calendar c = Calendar.getInstance(); 
		c.setTime(date); 
		c.add(Calendar.DATE, 1);
		date = c.getTime();
		
		String s = date.toString();
		System.out.println(s);
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
	
	public void setTimeSnooze() { // Next minute
		Date date = new Date();
		Calendar c = Calendar.getInstance(); 
		c.setTime(date); 
		c.add(Calendar.MINUTE, snoozeMin);
		date = c.getTime();
		
		String s = date.toString();
		System.out.println(s);
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
