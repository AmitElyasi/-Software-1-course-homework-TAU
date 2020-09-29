package il.ac.tau.cs.sw1.hw6;

public class Test {
	
public static void main(String[] args) {
		
		
		Polynomial p1 = new Polynomial(); // p1 = 0
		
		printError(p1.getDegree() == 0,
				"the degree of p1 is 0");
		
		printError(p1.computePolynomial(3) == 0.0,
				"the value of p1 with x=3 is 0.0");
		
		double[] coefficients = new double[]{1.0,2.0,3.0};
		Polynomial p2 = new Polynomial(coefficients); // p2 = 1.0+2.0*x+3.0*x^2
		
		printError(p2.computePolynomial(3) == 34.0,
				"the value of p2 with x=3 is 34.0");
		
		printError(p2.getCoefficient(1) == 2.0,
				"the coefficient of x is 2.0");
		
		Polynomial p3 = p2.multiplyByConstant(2.0);
		/*
		 * p2 = 1.0+2.0*x+3.0*x^2
		 * p3 = 2.0+4.0*x+6.0*x^2
		 */
		
		printError(p2.getCoefficient(1) == 2.0,
				"the coefficient of x is 2.0");
		
		printError(p3.getCoefficient(1) == 4.0,
				"the coefficient of x is 4.0");
		
		
		Polynomial p4 = p2.add(p3);
		/*
		 * p2 = 1.0+2.0*x+3.0*x^2
		 * p3 = 2.0+4.0*x+6.0*x^2
		 * p4 = 3.0+6.0*x+9.0*x^2
		 */
		printError(p4.getDegree() == 2,
				"the degree of p4 is 2");
		
		Polynomial p5 = p4.getFirstDerivative();
		
		/*
		 * p4 = 3.0+6.0*x+9.0*x^2
		 * p5 = 6.0 + 18.0*x
		 */
		
		printError(p5.getDegree() == 1,
				"the degree of p5 is 1");
		
		Polynomial p6 = new Polynomial(new double[] {3.0,1.0});
		
		Polynomial p7 = p5.multiply(p6);
		
		/*
		 * p5 = 6.0 + 18.0*x
		 * p6 = 3.0 + 1.0*x
		 * p7 = 18.0 + 60.0x + 18.0x^2
		 */
		
		printError(p7.isARoot(-3),
				"-3 is a root of p7");
		
		
	}

	public static void printError(boolean condition, String message) {
		if (!condition) {
			throw new RuntimeException("[ERROR] " + message);
		}
	}
}
