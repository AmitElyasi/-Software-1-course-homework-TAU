
public class Assignment02Q01Sec01 {
	public static void main(String[] args) {
		for (int i=0; i<args.length;i++) {
			int asciiRep= (int) args[i].charAt(0);
			if (asciiRep%3==0) {
				System.out.println(args[i].charAt(0));			}
		}
	}

}
