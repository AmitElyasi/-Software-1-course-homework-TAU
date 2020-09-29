package il.ac.tau.cs.software1.date;

public interface Date {

	public static int getDaysInMonth(int month) {
		if (month == 1 || month == 3 ||month==5|| month == 7 || month == 8 || month == 10 || month == 12) {
			return 31;
		} else if(month==2){
			return 28;
		}else {
			return 30;
		}
	}
	
	/*
	 * given number of days that has past since 1/1/1, returns the date in an int array.
	 */
	public static int[] daysToDate(int days) {
		if(days<0) {
			return new int[] {1,1,1};
		}
		int year=Math.floorDiv(days, 365)+1;
		days-=(year-1)*365;
		int month=1;
		while (days>0 && getDaysInMonth(month)<=days) {
			days-=getDaysInMonth(month);
			month++;
		}
		return new int[] {days+1,month,year};
	}

	public String toString();

	public int getDay();

	public int getMonth();

	public int getYear();

	public void addDays(int days);

	public default int differenceInDays(Date other) {
		return other.daysSinceStart() - this.daysSinceStart();
	}

	public default boolean isBetweenDates(Date date1, Date date2) {
		int thisCount = this.daysSinceStart();
		int date1Count = date1.daysSinceStart();
		int date2Count = date2.daysSinceStart();

		if ((thisCount > date1Count && thisCount < date2Count) || (thisCount > date2Count && thisCount < date1Count)
				|| thisCount == date1Count || thisCount == date2Count) {
			return true;
		}
		return false;
	}

	/*
	 * Returns the number of days that has past since 1/1/1
	 */
	public default int daysSinceStart() {
		int days = (this.getYear() - 1) * 365;
		for (int i = 1; i < this.getMonth(); i++) {
			days += getDaysInMonth(i);
		}
		days += this.getDay()-1;
		return days;
	}

}
