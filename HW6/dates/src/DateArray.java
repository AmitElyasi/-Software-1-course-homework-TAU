package il.ac.tau.cs.software1.date;

public class DateArray implements Date {
	private int[] date;

	public DateArray(int[] date) {
		this.date = date;
	}

	@Override
	public String toString() {
		return this.date[2] + "/" + this.date[1] + "/" + this.date[0];
	}

	@Override
	public int getDay() {
		return this.date[2];
	}

	@Override
	public int getMonth() {
		return this.date[1];
	}

	@Override
	public int getYear() {
		return this.date[0];
	}

	@Override
	public void addDays(int days) {
		int[] d = Date.daysToDate(this.daysSinceStart() + days);
		this.date[0] = d[2];
		this.date[1] = d[1];
		this.date[2] = d[0];
	}

}
