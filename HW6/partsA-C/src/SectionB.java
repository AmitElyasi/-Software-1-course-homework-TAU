package il.ac.tau.cs.sw1.hw6;

import java.util.Arrays;

public class SectionB {
	
	/*
	* @post $ret == true iff exists i such that array[i] == value
	*/
	public static boolean contains(int[] array, int value) { 
		for (int n:array) {
			if (n==value) {
				return true;
			}
		}
		return false;
	}
	
	
	
	// there is intentionally no @post condition here 
	/*
	* @pre array != null
	* @pre array.length > 2
	* @pre Arrays.equals(array, Arrays.sort(array))
	*/
	public static int something(int[] array) { 
		return 0;
	}
	
	/*
	* @pre Arrays.equals(array, Arrays.sort(array))
	* @pre array.length >= 1
	* @post for all i array[i] >= $ret
	*/
	public static int min(int[] array) { 
		
		return array[0];
	}
	
	/*
	* @pre array.length >=1
	* @post for all i array[i] >= $ret
	* @post Arrays.equals(array, prev(array))
	*/
	public static int min2(int[] array) { 
		int min=array[0];
		for(int n:array) {
			if (n<min) {
				min=n;
			}
		}
		return min;
	}
	
	/*
	* @pre word.length() >=1
	* @post for all i : $ret.charAt(i) == prev(word.charAt(word.length() - i - 1))
	*/
	public static String reverse(String word) 
	{
		String res="";
		for (int i=0;i<word.length(); i++) {
			res=word.charAt(i)+res;
		}
		return res;
	}

	
	/*
	* @pre array != null
	* @pre array.length > 2
	* @pre Arrays.equals(array, Arrays.sort(array))
	* @pre exist i,j such that: array[i] != array[j]
	* @post !Arrays.equals($ret, Arrays.sort($ret))
	* @post for any x: contains(prev(array),x) == true iff contains($ret, x) == true
	*/
	public static int[] guess(int[] array) { 
		int temp=array[0];
		array[0]=array[array.length-1];
		array[array.length-1]=temp;
		return array;
	}	
}
