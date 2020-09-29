
public class Assignment02Q01Sec03 {
	public static void main(String[] args) {
		int count=0;
		for (int i=0; i<args.length;i++) {
			int num=Integer.parseInt(args[i]);
			if ((num%3)%2==1) {
				count++;
				}
			}
		System.out.println(count);
		}
	}

