package EV5;

import java.time.DayOfWeek;
import java.time.LocalDate;


public class Test {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		LocalDate date = LocalDate.of(2023, 3, 14); // replace with your desired date
	    DayOfWeek dayOfWeek = date.getDayOfWeek();
	    String s = dayOfWeek.toString();
	    System.out.println(s.charAt(0)+s.substring(1,3).toLowerCase()); // output: MONDAY
	}

}
