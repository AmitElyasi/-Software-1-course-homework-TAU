package il.ac.tau.cs.sw1.hw6;
import java.util.Arrays;

public class Polynomial_Tester {
	
	public static void main(String[] args) {
		
////////////////////p1///////////////////////
		Polynomial p1 = new Polynomial();
		System.out.println("");
		System.out.print("p1 = ");
		System.out.print(p1.coeff[0]);
		for (int i = 1 ; i < p1.coeff.length ; i++) {
			System.out.print(" + " + p1.coeff[i] + "*x ^" + i);}
		System.out.println("");
		System.out.println("if p1 != 0.0 There might be a"
				+ " problem with Polynomial()");
		
////////////////////p2///////////////////////
		System.out.println("");
		double[] coefficients = new double[]{1.0,2.0,3.0};
		Polynomial p2 = new Polynomial(coefficients);
		System.out.println("");
		System.out.print("p2 = ");
		System.out.print(p2.coeff[0]);
		for (int i = 1 ; i < p2.coeff.length ; i++) {
			System.out.print(" + (" + p2.coeff[i] + "*x^" + i +")");}
		System.out.println("");
		System.out.println("if p2 != [1.0 + (2.0*x^1) + (3.0*x^2)] \n There might be"
				+ " a problem with Polynomial(coefficients)");
		
////////////////////p3///////////////////////
		System.out.println("");
		Polynomial p3 = p2.multiplyByConstant(2.0);
		System.out.println("");
		System.out.print("p3 = ");
		System.out.print(p3.coeff[0]);
		for (int i = 1 ; i < p3.coeff.length ; i++) {
		System.out.print(" + (" + p3.coeff[i] + "*x^" + i +")");}
		System.out.println("");
		System.out.println("if p3 != 2.0 + (4.0*x^1) + (6.0*x^2) \n There might be"
			+ " a problem with multiplyByConstant(c) or with p2");

////////////////////p4///////////////////////
		System.out.println("");
		Polynomial p4 = p2.add(p3);
		System.out.println("");
		System.out.print("p4 = ");
		System.out.print(p4.coeff[0]);
		for (int i = 1 ; i < p4.coeff.length ; i++) {
		System.out.print(" + (" + p4.coeff[i] + "*x^" + i +")");}
		System.out.println("");
		System.out.println("if p4 != 3.0 + (6.0*x^1) + (9.0*x^2) \n There might be"
		+ " a problem with add(Polynomial) or with p2 or p3");

////////////////////p5///////////////////////
		System.out.println("");
		Polynomial p5 = p4.getFirstDerivative();
		System.out.println("");
		System.out.print("p5 = ");
		System.out.print(p5.coeff[0]);
		for (int i = 1 ; i < p5.coeff.length ; i++) {
		System.out.print(" + (" + p5.coeff[i] + "*x^" + i +")");}
		System.out.println("");
		System.out.println("if p5 != 6.0 + (18.0*x^1)  \n There might be"
		+ " a problem with getFirstDerivative() or with p4");
		
////////////////////p6///////////////////////
System.out.println("");
Polynomial p6 = new Polynomial(new double[] {3.0,1.0});
System.out.println("");
System.out.print("p6 = ");
System.out.print(p6.coeff[0]);
for (int i = 1 ; i < p6.coeff.length ; i++) {
System.out.print(" + (" + p6.coeff[i] + "*x^" + i +")");}
System.out.println("");
System.out.println("if p6 != 3.0 + (1.0*x^1)  \n There might be"
+ " a problem with Polynomial(new double[])");

////////////////////p7///////////////////////
System.out.println("");
Polynomial p7 = p5.multiply(p6);
System.out.println("");
System.out.print("p7 = ");
System.out.print(p7.coeff[0]);
for (int i = 1 ; i < p7.coeff.length ; i++) {
System.out.print(" + (" + p7.coeff[i] + "*x^" + i +")");}
System.out.println("");
System.out.println("if p7 != 18.0 + (60.0*x^1) + (18.0*x^2)  \n There might be"
+ " a problem with multiply(Polynomial) or with p6");
		
	}}


