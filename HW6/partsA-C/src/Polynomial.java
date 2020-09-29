package il.ac.tau.cs.sw1.hw6;

import java.util.Arrays;

public class Polynomial {
	private double[] coeff;

	/*
	 * Creates the zero-polynomial with p(x) = 0 for all x.
	 */
	public Polynomial() {
		this.coeff = new double[] { 0.0 };
	}

	/*
	 * Creates a new polynomial with the given coefficients.
	 */
	public Polynomial(double[] coefficients) {
		this.coeff = coefficients;
		this.deleteZeros();
	}

	/*
	 * Adds this polynomial to the given polynomial and returns the sum as a new
	 * polynomial.
	 */
	public Polynomial add(Polynomial polynomial) {
		double[] bigger, smaller;
		if (this.coeff.length >= polynomial.coeff.length) {
			bigger = this.coeff;
			smaller = polynomial.coeff;
		} else {
			smaller = this.coeff;
			bigger = polynomial.coeff;
		}
		double[] sum = bigger.clone();
		for (int i = 0; i < smaller.length; i++) {
			sum[i] += smaller[i];
		}
		Polynomial pol = new Polynomial(sum);
		pol.deleteZeros();
		return pol;
	}

	/*
	 * Multiplies this polynomial by c and returns the result as a new polynomial.
	 */
	public Polynomial multiplyByConstant(double c) {
		if (c == 0) {
			return new Polynomial();
		}
		double[] product = this.coeff.clone();
		for (int i = 0; i < product.length; i++) {
			product[i] = product[i] * c;
		}
		return new Polynomial(product);
	}

	/*
	 * Multiplies this polynomial by the given polynomial and returns the result as
	 * a new polynomial.
	 */
	public Polynomial multiply(Polynomial polynomial) {
		double[] product = new double[this.coeff.length + polynomial.coeff.length - 1];
		double[] bigger, smaller;
		if (this.coeff.length >= polynomial.coeff.length) {
			bigger = this.coeff;
			smaller = polynomial.coeff;
		} else {
			smaller = this.coeff;
			bigger = polynomial.coeff;
		}
		for (int i = 0; i < product.length; i++) {
			double sum = 0;
			for (int j = 0; j <= i && j < smaller.length && i - j >= 0; j++) {
				if (i - j >= bigger.length) {
					continue;
				}
				sum += smaller[j] * bigger[i - j];
			}
			product[i] = sum;
		}
		return new Polynomial(product);

	}

	/*
	 * Returns the degree (the largest exponent) of this polynomial.
	 */
	public int getDegree() {
		return this.coeff.length - 1;
	}

	/*
	 * Returns the coefficient of the variable x with degree n in this polynomial.
	 */
	public double getCoefficientL() {
		return this.coeff.length;
	}

	/*
	 * Returns the coefficient of the variable x with degree n in this polynomial.
	 */
	public double getCoefficient(int n) {
		if (n >= this.coeff.length) {
			return 0;
		}
		return this.coeff[n];
	}

	/*
	 * set the coefficient of the variable x with degree n to c in this polynomial.
	 * If the degree of this polynomial < n, it means that that the coefficient of
	 * the variable x with degree n was 0, and now it will change to c.
	 */
	public void setCoefficient(int n, double c) {
		if (n >= this.coeff.length) {
			double[] res = new double[n + 1];
			for (int i = 0; i < this.coeff.length; i++) {
				res[i] = this.coeff[i];
			}
			res[n] = c;
			this.coeff = res;
		} else {
			this.coeff[n] = c;
		}
		this.deleteZeros();
	}

	/*
	 * Returns the derivative of this polynomial. The first derivative of a
	 * polynomial a0x0 + ... + anxn is defined as 1 * a1x0 + ... + n anxn-1.
	 * 
	 */
	public Polynomial getFirstDerivative() {
		if (this.coeff.length == 1) {
			return new Polynomial();
		}
		double[] dev = new double[this.coeff.length - 1];
		for (int i = 0; i < dev.length; i++) {
			dev[i] = this.coeff[i + 1] * (i + 1);
		}
		return new Polynomial(dev);
	}

	/*
	 * given an assignment for the variable x, compute the value of the polynomial
	 */
	public double computePolynomial(double x) {
		double res = 0.0;
		for (int i = 0; i < this.coeff.length; i++) {
			res += this.coeff[i] * Math.pow(x, i);
		}
		return res;
	}

	/*
	 * given an assignment for the variable x, return true iff x is a root of this
	 * polynomial
	 */
	public boolean isARoot(double x) {
		double res = computePolynomial(x);
		if (res == 0) {
			return true;
		}
		return false;

	}

	/*
	 * deletes zeros at the end of the polynomial if necessary
	 * 
	 */
	private void deleteZeros() {

		int i = this.coeff.length - 1;
		while (this.coeff[i] == 0.0) {
			if (i == 0) {
				break;
			}
			i--;
		}
		this.coeff = Arrays.copyOfRange(this.coeff, 0, i + 1);
	}
}
