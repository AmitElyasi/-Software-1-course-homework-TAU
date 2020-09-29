package il.ac.tau.cs.software1.date;

public class DateString implements Date {
	private String date;

	public DateString(String date) {
		this.date = date;
	}

	@Override
	public String toString() {
		return this.date;
	}

	@Override
	public int getDay() {
		String[] str = this.date.split("/");
		return Integer.valueOf(str[0]);
	}

	@Override
	public int getMonth() {
		String[] str = this.date.split("/");
		return Integer.valueOf(str[1]);
	}

	@Override
	public int getYear() {
		String[] str = this.date.split("/");
		return Integer.valueOf(str[2]);
	}

	@Override
	public void addDays(int days) {
		int[] d = Date.daysToDate(this.daysSinceStart() + days);
		this.date = Math.max(d[0], 1) + "/" + Math.max(d[1], 1) + "/" + Math.max(d[2], 1);
	}

}
