package il.ac.tau.cs.software1.date;


public class DateFactory {
	
	public static Date createDate(String date) {
		return new DateString(date);
	} 
	
	public static Date createDate(int[] date) {
		return new DateArray(date);
	} 
	
	public static Date createDate(int date) {
		return new DateInt(date);
	}
}
