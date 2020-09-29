package il.ac.tau.cs.sw1.hw6;

import java.util.Arrays;

public class PolyTest {


    Polynomial p1, p2, p3, p4, p5, p6, p7;
    double[] coef;
    final double DELTA = 0.0001;
    final double PHI = (1 + Math.sqrt(5)) / 2.;
    static final String buffer = " _            _   \n" +
            "| |_ ___  ___| |_ \n" +
            "| __/ _ \\/ __| __|\n" +
            "| ||  __/\\__ \\ |_ \n" +
            " \\__\\___||___/\\__|\n";

    public static void main(String[] args) {
        PolyTest pT = new PolyTest();
        pT.setUp("getDegree");
        pT.getDegree();
        pT.tearDown("getDegree");

        System.out.println(buffer);

        pT.setUp("getCoefficient");
        pT.getCoefficient();
        pT.tearDown("getCoefficient");

        System.out.println(buffer);

        pT.setUp("add");
        pT.add();
        pT.tearDown("add");

        System.out.println(buffer);

        pT.setUp("multiplyByConstant");
        pT.multiplyByConstant();
        pT.tearDown("multiplyByConstant");

        System.out.println(buffer);

        pT.setUp("multiply");
        pT.multiply();
        pT.tearDown("multiply");

        System.out.println(buffer);

        pT.setUp("setCoefficient");
        pT.setCoefficient();
        pT.tearDown("setCoefficient");

        System.out.println(buffer);

        pT.setUp("getFirstDerivative");
        pT.getFirstDerivative();
        pT.tearDown("getFirstDerivative");

        System.out.println(buffer);

        pT.setUp("computePolynomial");
        pT.computePolynomial();
        pT.tearDown("computePolynomial");

        System.out.println(buffer);

        pT.setUp("isARoot");
        pT.isARoot();
        pT.tearDown("isARoot");
    }
    public void setUp(String func) {
        System.out.println("=================================================");
        System.out.println("Starting test for "+func+"\n");
        p1 = new Polynomial();
        p2 = new Polynomial(new double[]{2, 1, 4, 3});
    }
    public void tearDown(String func){
        System.out.println("\nFinished test for "+func);
        System.out.println("=================================================");
    }

    public void getDegree() {
        Assert.assertEquals(0, p1.getDegree());
        Assert.assertEquals(3, p2.getDegree());
        p3 = new Polynomial(new double[]{2, 1, 3, 0});
        Assert.assertEquals(2, p3.getDegree());
    }

    public void getCoefficient() {
        coef = new double[]{2, 1, 4, 3};
        Assert.assertArrayEquals(coef, createCoef(p2), DELTA);

        coef = new double[]{0};
        Assert.assertArrayEquals(coef, createCoef(p1), DELTA);

        Assert.assertEquals(0, p2.getCoefficient(15), DELTA);
        Assert.assertEquals(0, p2.getCoefficient(4), DELTA);
    }

    public void add() {
        p3 = new Polynomial(new double[]{0, 0, 0, -3});
        p4 = p2.add(p3);
        Assert.assertEquals(2, p4.getDegree());
        p1 = new Polynomial(new double[]{0, -1, -4});
        p5 = p4.add(p1);
        Assert.assertEquals(0, p5.getDegree());
        p6 = new Polynomial(new double[]{0, 0.5});
        p7 = p5.add(p6);
        coef = new double[]{2, 0.5};
        Assert.assertArrayEquals(coef, createCoef(p7), DELTA);
    }

    public void multiplyByConstant() {
        p3 = p2.multiplyByConstant(2);
        coef = new double[]{4, 2, 8, 6};
        Assert.assertArrayEquals(coef, createCoef(p3), DELTA);
        p4 = p3.multiplyByConstant(2);
        coef = new double[]{8, 4, 16, 12};
        Assert.assertArrayEquals(coef, createCoef(p4), DELTA);
        coef = new double[]{1, 0.5, 2, 1.5};
        p5 = p4.multiplyByConstant(0.125);
        Assert.assertArrayEquals(coef, createCoef(p5), DELTA);
    }

    public void multiply() {
        p3 = p2.multiply(p1);
        coef = new double[]{0};
        Assert.assertArrayEquals(coef, createCoef(p3), DELTA);

        p1 = new Polynomial(new double[]{2});
        p4 = p2.multiply(p1);
        coef = new double[]{4, 2, 8, 6};
        Assert.assertArrayEquals(coef, createCoef(p4), DELTA);

        p5 = p4.multiply(p2);
        coef = new double[]{8, 8, 34, 40, 44, 48, 18};
        Assert.assertEquals(6, p5.getDegree());
        Assert.assertArrayEquals(coef, createCoef(p5), DELTA);

        p6 = new Polynomial(new double[]{1, 1. / 8.});
        p7 = p5.multiply(p6);
        coef = new double[]{8, 9, 35, 177. / 4., 49, 107. / 2., 24, 9. / 4.};
        Assert.assertEquals(7, p7.getDegree());
        Assert.assertArrayEquals(coef, createCoef(p7), DELTA);
    }

    public void setCoefficient() {
        coef = createCoef(p2);

        p2.setCoefficient(2, 0);
        coef[2] = 0;
        Assert.assertArrayEquals(coef, createCoef(p2), DELTA);
        Assert.assertEquals(3, p2.getDegree());

        p2.setCoefficient(3, 0);
        coef = new double[]{coef[0], coef[1]};
        Assert.assertArrayEquals(coef, createCoef(p2), DELTA);
        Assert.assertEquals(1, p2.getDegree());

        coef = new double[]{0.5, 0.3, 0, 0, 0, 0.8};
        p2.setCoefficient(0, 0.5);
        p2.setCoefficient(1, 0.3);
        p2.setCoefficient(5, 0.8);
        Assert.assertArrayEquals(coef, createCoef(p2), DELTA);
    }

    public void getFirstDerivative() {
        p3 = p2.getFirstDerivative();
        coef = new double[]{1, 8, 9};
        Assert.assertArrayEquals(coef, createCoef(p3), DELTA);

        p4 = new Polynomial(new double[]{1, 1, 0.5, 1. / 6., 1. / 24.});
        p5 = p4.getFirstDerivative();
        coef = new double[]{1, 1, 0.5, 1. / 6.};
        Assert.assertArrayEquals(coef, createCoef(p5), DELTA);

        p6 = new Polynomial(new double[]{0.1, 0.2, 0.4, 0.8, 1.6, 3.2});
        p7 = p6.getFirstDerivative();
        coef = new double[]{0.2, 0.8, 2.4, 6.4, 16};
        Assert.assertArrayEquals(coef, createCoef(p7), DELTA);

        Polynomial p8 = p7.getFirstDerivative();
        coef = new double[]{0.8, 4.8, 19.2, 64};
        Assert.assertArrayEquals(coef, createCoef(p8), DELTA);
    }

    void computeSinglePolynomial(Polynomial p, double x, double y) {
        Assert.assertEquals(y, p.computePolynomial(x), DELTA);
    }

    public void computePolynomial() {
        computeSinglePolynomial(p2, 1, 10);
        computeSinglePolynomial(p2, 0.1, 2.143);
        computeSinglePolynomial(p1, PHI, 0);
        p3 = new Polynomial(new double[]{-1, -1, 1});
        computeSinglePolynomial(p3, PHI, 0);
        p4 = new Polynomial(new double[]{5, Math.sqrt(2), 0, Math.sqrt(2)});
        computeSinglePolynomial(p4, Math.sqrt(2), 11);
    }

    String checkRoot(Polynomial p, double x) {
        return p.isARoot(x) ? String.format("%.3f is a root", x) :
                String.format("%.3f is not a root", x);
    }

    public void isARoot() {
        p3 = new Polynomial(new double[]{-1, -1, 1});
        System.out.println("the calculation take care of floating-point errors\n");
        Assert.assertEquals("0.000 is a root", checkRoot(p1, 0));
        Assert.assertEquals("1.618 is a root", checkRoot(p3, PHI));
        Assert.assertEquals("-0.618 is a root", checkRoot(p1, 1 - PHI));
    }

    public boolean equals(Polynomial p1, Polynomial p2) {
        if (p1.getDegree() != p2.getDegree()) {
            return false;
        }
        for (int i = 0; i < p1.getDegree() + 1; i++) {
            if (p2.getCoefficient(i) != p1.getCoefficient(i)) {
                return false;
            }
        }
        return true;
    }

    public double[] createCoef(Polynomial p) {
        double[] coef = new double[p.getDegree() + 1];
        for (int i = 0; i < coef.length; i++) {
            coef[i] = p.getCoefficient(i);
        }
        return coef;
    }

    public static class Assert {

        public static void assertEquals(Object a, Object b) {
            if (a instanceof String) {
                if (!a.equals(b)) {
                    AssertionError error = new AssertionError(String.format("\nExpected  : %s\n" +
                            "Actual    : %s", a, b));
                    error.printStackTrace(System.out);
                }
            } else if (a instanceof Boolean) {
                if (!a.equals(b)) {
                    AssertionError error = new AssertionError(String.format("\nExpected  : %b\n" +
                            "Actual    : %b", a, b));
                    error.printStackTrace(System.out);
                }
            } else if (!a.equals(b)) {
                AssertionError error = new AssertionError(String.format("\nExpected  : %d\n" +
                        "Actual    : %d", a, b));
                error.printStackTrace(System.out);
            }
        }

        public static void assertEquals(double a, double b, double delta) {
            if (Math.abs(a - b) >= delta) {
                AssertionError error = new AssertionError(String.format("\nExpected  : %.3f\n" +
                        "Actual    : %.3f", a, b));
                error.printStackTrace(System.out);
            }
        }

        public static void assertArrayEquals(double[] a, double[] b,double delta) {
            int sizeA, sizeB;
            sizeA = a.length;
            sizeB = b.length;

            if (sizeA!=sizeB){
                AssertionError error = new AssertionError(String.format("\narrays sizes differ\nExpected  : %s\n" +
                        "Actual    : %s", Arrays.toString(a), Arrays.toString(b)));
                error.printStackTrace(System.out);
            }
            for (int i = 0; i < Math.min(sizeA, sizeB); i++) {
                if (Math.abs(a[i]-b[i])>delta) {
                    AssertionError error = new AssertionError(String.format("\nfirst incompatability at [%d]\nExpected  : %s\n" +
                            "Actual    : %s", i, addErrorToArray(a, i), addErrorToArray(b, i)));
                    error.printStackTrace(System.out);
                    break;
                }
            }
        }

        static String addErrorToArray(double[] arr1, int i){
            String res="[";
            for (int j = 0; j < arr1.length; j++) {
                if (j!=0){
                    res+=",";
                }
                if (j==i){
                    res+=String.format(" <%.3f>",arr1[j]);
                }
                else {
                    res+=String.format(" %.3f",arr1[j]);
                }
            }
            res+="]";
            return res;
        }
    }
}