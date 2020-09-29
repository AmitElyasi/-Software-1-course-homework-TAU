
public class Assignment02Q02 {
	public static void main(String[] args) {
		int num = Integer.parseInt(args[0]);
		int count=0, n=1;
		boolean sign=true;
		double approxPi=0;
		while (count<num) {
			
			if (sign) {
				approxPi+=((double) 1/n);
				}
			else {
				approxPi-=((double) 1/n);
				}
			sign=!sign;
			count++;
			n+=2;
			}
		approxPi*=4;
		System.out.println(approxPi+" "+Math.PI);
	}
}
