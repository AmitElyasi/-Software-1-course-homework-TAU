package il.ac.tau.cs.sw1.hw3;


public class ArrayUtils {

	public static int[][] transposeSecondaryMatrix(int[][] m) {
		int rows = m.length;
		if (rows == 0) {
			return m;
		}
		int cols = m[0].length;
		if (cols == 0) {
			return m;
		}

		int[][] newM = new int[cols][rows];
		for (int i = 0; i < rows; i++) {
			for (int j = 0; j < cols; j++) {
				newM[cols - j - 1][rows - i - 1] = m[i][j];
			}
		}

		return newM;
	}

	public static int[] shiftArrayCyclic(int[] array, int move, char direction) {

		if (move <= 0 || (direction != 'R' && direction != 'L' || array.length <= 1)) {
			return array;
		}
		int size = array.length;
		if (move >= size) {
			move = move % size;
		}

		if (direction == 'L') {
			move = size - move;
		}
		int[] tempArr = new int[size];
		for (int i = 0; i < size; i++) {
			tempArr[(i + move) % size] = array[i];
		}
		for (int i = 0; i < size; i++) {
			array[i] = tempArr[i];
		}

		return array;
	}
	public static int alternateSum(int[] array) {
		boolean sign=true;
		int sum = 0, size = array.length, start=0, max=0;
		for (int i = 0; i < size; i++) {
			if (sign) {
				sum += array[i];
			} else {
				sum -= array[i];
			}
			if (sum<0) {
				sum-=array[start];
				sum*=(-1);
				start++;
				sign=!sign;
			}
			sign=!sign;
			if(sum>max) {
				max=sum;
			}
		}
		return max;
	}

	public static int findPath(int[][] m, int i, int j) {
		boolean[][] path=new boolean[m.length][m.length];
		return findPathRec(m,i,j,path);
	}
	private static int findPathRec(int[][] m, int i, int j,boolean[][] path) {
		if(m[i][j]==1) {
			path[i][j]=true;
			return 1;
		}
		for (int k=0;k<m.length;k++) {
			if (k!=j)
			if(m[k][j]==1 && !path[k][j]) {
				path[k][j]=true;
				if (findPathRec(m,i,k,path)==1) {
					return 1;
				}
			}
		}
		return 0;

	}
}