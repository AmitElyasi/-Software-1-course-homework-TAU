
public class Assignment02Q01Sec02 {
	public static void main(String[] args) {
		int sum;
		for (int i=0; i<args.length;i++) {
			sum=0;
			for (int j=0; j<args[i].length();j++){
				int asciiRep= (int) args[i].charAt(j);
				sum+=asciiRep;
				}
			System.out.println(sum);	
			}
		}
			
	}

