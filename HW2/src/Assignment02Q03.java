public class Assignment02Q03 {
	public static void main(String[] args) {
		int num=Integer.parseInt(args[0]);
		int prev1=1, prev2=1, newFibb, sum=2;
		String outStr="1 1";
		for (int i=2;i<num;i++) {
			newFibb=prev1+prev2;
			prev1=prev2;
			prev2=newFibb;
			sum+=newFibb;
			outStr+=" "+newFibb;
		}
		System.out.println("The first "+num+" Fibonacci numbers are:"+"\n"+outStr+"\n"+ "The sum is: "+sum);
	}
}