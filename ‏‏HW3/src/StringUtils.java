package il.ac.tau.cs.sw1.hw3;

import java.util.Arrays;

public class StringUtils {

	public static String findSortedSequence(String str) {
		String space = " ";
		String[] words = str.split(space);
		int size = words.length;
		if (size == 0) {
			return "";
		}

		int maxSeq = 1, maxSeqTemp = 1, maxInd = size - 1, maxIndTemp = 0;
		for (int i = 0; i < size - 1; i++) {
			if (words[i].compareTo(words[i + 1]) <= 0) {
				maxSeqTemp++;
				if (maxSeqTemp >= maxSeq) {
					maxInd = maxIndTemp;
					maxSeq = maxSeqTemp;
				}
			} else {
				maxIndTemp = i + 1;
				maxSeqTemp = 1;
			}
		}

		return String.join(space, Arrays.copyOfRange(words, maxInd, maxInd + maxSeq));
	}

	public static String parityXorStrings(String a, String b) {
		String output = "";
		int[] lettersA = new int[26];
		int[] lettersB = new int[26];
		for (int i = 0; i < a.length(); i++) {
			lettersA[((int) a.charAt(i)) - 97]++;
		}
		for (int i = 0; i < b.length(); i++) {
			lettersB[((int) b.charAt(i)) - 97]++;
		}
		for (int i = 0; i < a.length(); i++) {
			if (lettersA[((int) a.charAt(i)) - 97] % 2 == 1 && lettersB[((int) a.charAt(i)) - 97] % 2 == 0) {
				output += a.charAt(i);
			}
		}
		return output;

	}

	public static boolean isAnagram(String a, String b) {
		String noSpaceA = a.replaceAll("\\s", ""), noSpaceB = b.replaceAll("\\s", "");
		char[] charsA = noSpaceA.toCharArray(), charsB = noSpaceB.toCharArray();
		Arrays.sort(charsA);
		Arrays.sort(charsB);
		return Arrays.equals(charsA, charsB);

	}

}
