package il.ac.tau.cs.sw1.hw3;


import java.util.Arrays;

public class TestArray {
    static final String LINE_BREAK = "=====================================\n" +
            "||         LINE BREAK              ||\n" +
            "=====================================";

    public static void main(String[] args) {
        Test1();
        System.out.println(LINE_BREAK);
        Test2();
        System.out.println(LINE_BREAK);
        Test3();
        System.out.println(LINE_BREAK);
        Test4();
        System.out.println(LINE_BREAK);
        Test5();
        System.out.println(LINE_BREAK);
        Test6();
        System.out.println(LINE_BREAK);
        Test7();
    }

    private static void printTransposeTest(int[][] input, int[][] output) {
        System.out.println("\ninput : ");
        printMat(input);
        System.out.println("output : ");
        printMat(ArrayUtils.transposeSecondaryMatrix(input));
        System.out.println("expected output : ");
        printMat(output);
    }

    private static void printMat(int[][] mat) {
        System.out.println("\n[ ");
        for (int[] row : mat) {
            System.out.println(Arrays.toString(row));
        }
        System.out.println(" ]\n");
    }

    public static boolean matEqual(final int[][] arr1, final int[][] arr2) {
        if (arr1 == null) {
            return (arr2 == null);
        }
        if (arr2 == null) {
            return false;
        }
        if (arr1.length != arr2.length) {
            return false;
        }
        for (int i = 0; i < arr1.length; i++) {
            if (!Arrays.equals(arr1[i], arr2[i])) {
                return false;
            }
        }
        return true;
    }



    private static void Test1() {
        System.out.println("Commencing Test 1...\n");
        /*printTransposeTest(new int[][]{{1, 2, 3}, {4, 5, 6}, {7, 8, 9}});
        printTransposeTest(new int[][]{{-1, 8}, {7, -3}});
        printTransposeTest(new int[][]{{5, 3, 2}});
        printTransposeTest(new int[][]{{1, 2, 3}, {4, 5, 6}});
        printTransposeTest(new int[][]{{}, {}});*/
        int[][] output,input;
        input = new int[][]{
                {1, 2, 3},
                {4, 5, 6},
                {7, 8, 9}
        };
        output = new int[][]{
                {9,6,3},
                {8,5,2},
                {7,4,1}
        };
        if (!matEqual(ArrayUtils.transposeSecondaryMatrix(input),output)){
            System.out.println("test 1.1 failed");
            printTransposeTest(input,output);
        }
        input = new int[][]{
                {-1,8},
                {7,-3}
        };
        output = new int[][]{
                {-3,8},
                {7,-1}
        };
        if (!matEqual(ArrayUtils.transposeSecondaryMatrix(input),output)){
            System.out.println("test 1.2 failed");
            printTransposeTest(input,output);
        }
        input = new int[][]{
                {5,3,2}
        };
        output = new int[][]{
                {2},
                {3},
                {5}
        };
        if (!matEqual(ArrayUtils.transposeSecondaryMatrix(input),output)){
            System.out.println("test 1.3 failed");
            printTransposeTest(input,output);
        }
        input = new int[][]{
                {1, 2, 3},
                {4, 5, 6}
        };
        output = new int[][]{
                {6,3},
                {5,2},
                {4,1}
        };
        if (!matEqual(ArrayUtils.transposeSecondaryMatrix(input),output)){
            System.out.println("test 1.4 failed");
            printTransposeTest(input,output);
        }
        input = new int[][]{
                {},
                {}
        };
        output = new int[][]{
                {},
                {}
        };
        if (!matEqual(ArrayUtils.transposeSecondaryMatrix(input),output)){
            System.out.println("test 1.5 failed");
            printTransposeTest(input,output);
        }
        System.out.println("\nEnd of test 1.\n");

    }

    private static void printCyclic(int[] arr, int move, char dir,int[] output) {
        System.out.println("input : " + String.join(", ",

                Arrays.toString(arr),
                String.valueOf(move),
                String.valueOf(dir)) + "\noutput : " +
                Arrays.toString(ArrayUtils.shiftArrayCyclic(arr, move, dir)) +
                "\n"+
                "expected output : "+
                Arrays.toString(output)+
                "\n");
    }

    private static void Test2() {
        System.out.println("\nCommencing Test 2...\n");
        int[] input,output;
        input = new int[]{1,2,3,4,5};
        output = input.clone();
        if (!Arrays.equals(ArrayUtils.shiftArrayCyclic(input, -1, 'R'), output)){
            System.out.println("test 2.1 failed");
            printCyclic(input, -1, 'R', output);
        }

        input = new int[]{1,2,3,4,5};
        output = new int[]{5,1,2,3,4};
        if (!Arrays.equals(ArrayUtils.shiftArrayCyclic(input, 1, 'R'), output)){
            System.out.println("test 2.2 failed");
            printCyclic(input, 1, 'R', output);
        }

        input = new int[]{1,2,3,4,5};
        output = input.clone();
        if (!Arrays.equals(ArrayUtils.shiftArrayCyclic(input, 1, 'r'), output)){
            System.out.println("test 2.3 failed");
            printCyclic(input, 1, 'r', output);
        }

        input = new int[]{1,2,3,4,5};
        output = input.clone();
        if (!Arrays.equals(ArrayUtils.shiftArrayCyclic(input, 1, 'g'), output)){
            System.out.println("test 2.4 failed");
            printCyclic(input, 1, 'g', output);
        }

        input = new int[]{1,2,3,4,5};
        output = new int[]{4,5,1,2,3};
        if (!Arrays.equals(ArrayUtils.shiftArrayCyclic(input, 3, 'L'), output)){
            System.out.println("test 2.5 failed");
            printCyclic(input, 3, 'L', output);
        }

        input = new int[]{0,8,9,5,6};
        output = new int[]{8,9,5,6,0};
        if (!Arrays.equals(ArrayUtils.shiftArrayCyclic(input, 6, 'L'), output)){
            System.out.println("test 2.6 failed");
            printCyclic(input, 6, 'L', output);
        }

        input = new int[]{};
        output = input.clone();
        if (!Arrays.equals(ArrayUtils.shiftArrayCyclic(input, 3, 'R'), output)){
            System.out.println("test 2.7 failed");
            printCyclic(input, 3, 'R', output);
        }

        System.out.println("\nEnd of test 2.\n");
    }

    private static void printSum(int[] arr,int output) {
        System.out.println("input : " +
                Arrays.toString(arr) +
                "\noutput : " +
                ArrayUtils.alternateSum(arr) +
                "\nexpected output : "+
                output+
                "\n");
    }

    private static void Test3() {
        System.out.println("\nCommencing Test 3...\n");
        int[] input;
        int output;

        input = new int[]{1, -2, 3, 4, 5};
        output=7;
        if (ArrayUtils.alternateSum(input)!=output){
            System.out.println("test 3.1 failed");
            printSum(input, output);
        }

        input = new int[]{1, 2, -3, 4, 5};
        output=9;
        if (ArrayUtils.alternateSum(input)!=output){
            System.out.println("test 3.2 failed");
            printSum(input, output);
        }

        input = new int[]{};
        output = 0;
        if (ArrayUtils.alternateSum(input)!=output){
            System.out.println("test 3.3 failed");
            printSum(input, output);
        }

        System.out.println("\nEnd of test 3.\n");
    }

    private static void printPath(int[][] m, int i, int j,int output) {
        System.out.println("input : " +
                String.join(", ",
                        String.valueOf(i),
                        String.valueOf(j)));

        printMat(m);
        System.out.println("\noutput : " +
                ArrayUtils.findPath(m, i, j) +
                "\nexpected output : "+
                output+
                "\n");
    }

    private static void Test4() {
        System.out.println("\nCommencing Test 4...\n");
        int[][] input;
        int output;

        input = new int[][]{{1, 0, 0}, {0, 1, 0}, {0, 0, 1}};
        output = 1;
        if (ArrayUtils.findPath(input, 1, 1)!=output){
            System.out.println("test 4.1 failed");
            printPath(input, 1, 1, output);
        }
        input = new int[][]{{1, 0, 0, 1}, {0, 1, 0, 1}, {0, 0, 1, 0}, {1, 1, 0, 1}};
        output = 1;
        if (ArrayUtils.findPath(input, 0, 1)!=output){
            System.out.println("test 4.2 failed");
            printPath(input, 0, 1, output);
        }

        input = new int[][]{{1, 1, 0}, {0, 1, 1}, {0, 1, 1}};
        output = 1;
        if (ArrayUtils.findPath(input, 0, 2)!=output){
            System.out.println("test 4.3 failed");
            printPath(input, 0, 2, output);
        }


        input = new int[][]{{1, 1, 0}, {0, 1, 1}, {0, 1, 1}};
        output = 0;
        if (ArrayUtils.findPath(input, 2, 0)!=output){
            System.out.println("test 4.4 failed");
            printPath(input, 2, 0, output);
        }

        input = new int[][]{{1, 0, 0, 1}, {1, 1, 0, 1}, {0, 1, 1, 0}, {1, 1, 0, 1}};
        output = 0;
        if (ArrayUtils.findPath(input, 0, 2)!=output){
            System.out.println("test 4.5 failed");
            printPath(input, 0, 2, output);
        }
        System.out.println("\nEnd of test 4.\n");
    }

    private static void printInOut5(String str,String output) {
        if (str.replace(" ", "").equals("")){
            str="\""+str+"\"";
        }

        System.out.println("\ninput : " +
                str +
                "\noutput : " +
                StringUtils.findSortedSequence(str) +
                "\nexpected output : "+
                output+
                "\n");
    }

    private static void Test5() {
        System.out.println("\nCommencing Test 5...\n");

        String input, output;

        input = "to be or not to be";
        output = "not to";
        if (!StringUtils.findSortedSequence(input).equals(output)){
            System.out.println("test 5.1 failed");
            printInOut5(input, output);
        }
        input = "my mind is an empty zoo";
        output = "an empty zoo";
        if (!StringUtils.findSortedSequence(input).equals(output)){
            System.out.println("test 5.2 failed");
            printInOut5(input, output);
        }

        input = "";
        output = "";
        if (!StringUtils.findSortedSequence(input).equals(output)){
            System.out.println("test 5.3 failed");
            printInOut5(input, output);
        }

        input = "andy bought candy";
        output = "andy bought candy";
        if (!StringUtils.findSortedSequence(input).equals(output)){
            System.out.println("test 5.4 failed");
            printInOut5(input, output);
        }

        input = "life is not not not fair";
        output = "is not not not";
        if (!StringUtils.findSortedSequence(input).equals(output)){
            System.out.println("test 5.5 failed");
            printInOut5(input, output);
        }

        input = "art act";
        output = "act";
        if (!StringUtils.findSortedSequence(input).equals(output)){
            System.out.println("test 5.6 failed");
            printInOut5(input, output);
        }
        System.out.println("\nEnd of test 5.\n");

    }

    private static void printInOut6(String a, String b,String output) {
        if (a.replace(" ", "").equals("")){
            a="\""+a+"\"";
        }
        if (b.replace(" ", "").equals("")){
            b="\""+b+"\"";
        }
        System.out.println("\ninput : " + String.join(", ",
                a, b) +
                "\noutput : " +
                StringUtils.parityXorStrings(a, b) +
                "\nexpected output : "+
                output+
                "\n");
    }

    private static void Test6() {
        System.out.println("\nCommencing Test 6...\n");

        String input1, input2, output;
        input1 = "dog";
        input2 = "god";
        output = "";
        if (!StringUtils.parityXorStrings(input1, input2).equals(output)) {
            System.out.println("test 6.1 failed");
            printInOut6(input1, input2, output);
        }

        input1 = "catcatcat";
        input2 = "tacotaco";
        output = "catcatcat";
        if (!StringUtils.parityXorStrings(input1, input2).equals(output)) {
            System.out.println("test 6.2 failed");
            printInOut6(input1, input2, output);
        }

        input1 = "cat";
        input2 = "jeffjeff";
        output = "cat";
        if (!StringUtils.parityXorStrings(input1, input2).equals(output)) {
            System.out.println("test 6.3 failed");
            printInOut6(input1, input2, output);
        }

        input1 = "catcat";
        input2 = "jeffjeff";
        output = "";
        if (!StringUtils.parityXorStrings(input1, input2).equals(output)) {
            System.out.println("test 6.4 failed");
            printInOut6(input1, input2, output);
        }

        input1 = "catcat";
        input2 = "jeff";
        output = "";
        if (!StringUtils.parityXorStrings(input1, input2).equals(output)) {
            System.out.println("test 6.5 failed");
            printInOut6(input1, input2, output);
        }

        input1 = "cat";
        input2 = "cajeffjefft";
        output = "";
        if (!StringUtils.parityXorStrings(input1, input2).equals(output)) {
            System.out.println("test 6.6 failed");
            printInOut6(input1, input2, output);
        }

        input1 = "jeff";
        input2 = "catff";
        output = "je";
        if (!StringUtils.parityXorStrings(input1, input2).equals(output)) {
            System.out.println("test 6.7 failed");
            printInOut6(input1, input2, output);
        }

        input1 = "izoi";
        input2 = "oziizo";
        output = "zo";
        if (!StringUtils.parityXorStrings(input1, input2).equals(output)) {
            System.out.println("test 6.8 failed");
            printInOut6(input1, input2, output);
        }

        input1 = "fireman";
        input2 = "maniac";
        output = "frea";
        if (!StringUtils.parityXorStrings(input1, input2).equals(output)) {
            System.out.println("test 6.9 failed");
            printInOut6(input1, input2, output);
        }
        System.out.println("\nEnd of test 6.\n");
    }

    private static void printInOut7(String a, String b, boolean output) {
        if (a.replace(" ", "").equals("")){
            a="\""+a+"\"";
        }
        if (b.replace(" ", "").equals("")){
            b="\""+b+"\"";
        }
        System.out.println("\ninput : " + String.join(", ",
                a, b) +
                "\noutput : " +
                StringUtils.isAnagram(a, b) +
                "\nexpected output : "+
                output+
                "\n");
    }

    private static void Test7() {
        System.out.println("\nCommencing Test 7...\n");
        String input1, input2;
        boolean output;

        input1="mothEr in law";
        input2="hitlEr woman";
        output=true;
        if (StringUtils.isAnagram(input1, input2)!=output){
            System.out.println("test 7.1 failed");
            printInOut7(input1, input2, output);
        }

        input1="ListeN";
        input2="SileNt";
        output=false;
        if (StringUtils.isAnagram(input1, input2)!=output){
            System.out.println("test 7.2 failed");
            printInOut7(input1, input2, output);
        }

        input1="software";
        input2="jeans";
        output=false;
        if (StringUtils.isAnagram(input1, input2)!=output){
            System.out.println("test 7.3 failed");
            printInOut7(input1, input2, output);
        }

        input1="Funeral";
        input2="real Fun";
        output=true;
        if (StringUtils.isAnagram(input1, input2)!=output){
            System.out.println("test 7.4 failed");
            printInOut7(input1, input2, output);
        }

        input1="Aa";
        input2="aA";
        output=true;
        if (StringUtils.isAnagram(input1, input2)!=output){
            System.out.println("test 7.5 failed");
            printInOut7(input1, input2, output);
        }

        input1="";
        input2=" ";
        output=true;
        if (StringUtils.isAnagram(input1, input2)!=output){
            System.out.println("test 7.6 failed");
            printInOut7(input1, input2, output);
        }
        System.out.println("\nEnd of test 7.\n");
    }
}
