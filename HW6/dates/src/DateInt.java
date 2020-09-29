package il.ac.tau.cs.software1.date;

public class DateInt implements Date {
	private int date;

	public DateInt(int date) {
		this.date = date;
	}

	@Override
	public String toString() {
		int[] d = Date.daysToDate(this.date);
		return Math.max(d[0],1) + "/" + Math.max(d[1],1) + "/" + Math.max(d[2],1);
	}

	@Override
	public int getDay() {
		int[] d = Date.daysToDate(this.date);
		return d[0];
	}

	@Override
	public int getMonth() {
		int[] d = Date.daysToDate(this.date);
		return d[1];
	}

	@Override
	public int getYear() {
		int[] d = Date.daysToDate(this.date);
		return d[2];
	}

	@Override
	public void addDays(int days) {
		if (this.date+days<=0) {
			this.date = 0;
		} else {
			this.date += days;
		}
	}

}
