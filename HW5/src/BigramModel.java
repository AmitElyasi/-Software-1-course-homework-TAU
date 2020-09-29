package il.ac.tau.cs.sw1.ex5;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;

public class BigramModel {
	public static final int MAX_VOCABULARY_SIZE = 14000;
	public static final String VOC_FILE_SUFFIX = ".voc";
	public static final String COUNTS_FILE_SUFFIX = ".counts";
	public static final String SOME_NUM = "some_num";
	public static final int ELEMENT_NOT_FOUND = -1;

	String[] mVocabulary;
	int[][] mBigramCounts;

	// DO NOT CHANGE THIS !!!
	public void initModel(String fileName) throws IOException {
		mVocabulary = buildVocabularyIndex(fileName);
		mBigramCounts = buildCountsArray(fileName, mVocabulary);

	}

	/*
	 *
	 * Get string as an input
	 *
	 * Return true if the string contains English letter. returns false otherwise.
	 * 
	 */
	public static boolean containsEnglishLetter(String word) {
		for (int i = 0; i < word.length(); i++) {
			if (Character.isLetter(word.charAt(i))) {
				return true;
			}
		}
		return false;
	}

	/*
	 *
	 * Get string as an input
	 *
	 * Return true if the string contains only digits. returns false otherwise.
	 * 
	 */
	public static boolean containsOnlyDigits(String word) {
		if(word.compareTo("")==0) {
			return false;
		}
		for (int i = 0; i < word.length(); i++) {
			if (!Character.isDigit(word.charAt(i))) {
				return false;
			}
		}
		return true;
	}

	/*
	 *
	 * @input String[], String, int
	 *
	 * Return true if the string exists in the array in index lower then max.
	 * returns false otherwise.
	 * 
	 */
	public static int exists(String[] voc, String word, int max) {
		if (max > voc.length) {
			max = voc.length;
		}
		for (int i = 0; i < max; i++) {
			if (voc[i].compareTo(word) == 0) {
				return i;
			}
		}
		return -1;
	}

	/*
	 * @post: mVocabulary = prev(mVocabulary)
	 * 
	 * @post: mBigramCounts = prev(mBigramCounts)
	 */
	public String[] buildVocabularyIndex(String fileName) throws IOException { // Q 1

		File file = new File(fileName);
		BufferedReader bufferedReader = new BufferedReader(new FileReader(file));

		String[] vocabulary = new String[MAX_VOCABULARY_SIZE];
		String[] words;
		String line;
		int wordCount = 0, i;
		boolean some_num_was_added = false;

		while (wordCount <= MAX_VOCABULARY_SIZE && (line = bufferedReader.readLine()) != null) {
			i = 0;
			if(line.compareTo("")==0) {
				continue;
			}
			line = line.toLowerCase();
			words = line.split(" ");
			while (wordCount < MAX_VOCABULARY_SIZE && i < words.length) {
				if (containsEnglishLetter(words[i])) {
					if (exists(vocabulary, words[i], wordCount) == (-1)) {
						vocabulary[wordCount] = words[i];
						wordCount++;
					}
				} else if (containsOnlyDigits(words[i]) && !some_num_was_added) {
					vocabulary[wordCount] = SOME_NUM;
					some_num_was_added = true;
					wordCount++;
				}
				i++;
			}
		}
		bufferedReader.close();
		if (wordCount < MAX_VOCABULARY_SIZE) {
			vocabulary = Arrays.copyOfRange(vocabulary, 0, wordCount);
		}
		return vocabulary;
	}

	/*
	 * @post: mVocabulary = prev(mVocabulary)
	 * 
	 * @post: mBigramCounts = prev(mBigramCounts)
	 */
	public int[][] buildCountsArray(String fileName, String[] vocabulary) throws IOException { // Q - 2
		File file = new File(fileName);
		BufferedReader bufferedReader = new BufferedReader(new FileReader(file));

		int[][] bigramCount = new int[vocabulary.length][vocabulary.length];
		String[] words;
		String line, word1 = "", word2 = "";
		int i, index1, index2;

		while ((line = bufferedReader.readLine()) != null) {
			line = line.toLowerCase();
			i = 0;
			words = line.split(" ");

			if (containsEnglishLetter(words[i])) {
				word1 = words[i];
			} else if (containsOnlyDigits(words[i])) {
				word1 = SOME_NUM;
			}

			while (i < words.length - 1) {
				i++;

				if (containsEnglishLetter(words[i])) {
					word2 = words[i];
				} else if (containsOnlyDigits(words[i])) {
					word2 = SOME_NUM;
				}

				if (word2 == "") {
					word1 = "";
					continue;
				}

				index1 = exists(vocabulary, word1, vocabulary.length);
				index2 = exists(vocabulary, word2, vocabulary.length);

				if (index1 > -1 && index2 > -1) {
					bigramCount[index1][index2]++;
				}

				word1 = word2;
				word2 = "";
				index1 = index2;
				index2 = -1;
			}
		}
		bufferedReader.close();
		return bigramCount;
	}

	/*
	 * @pre: the method initModel was called (the language model is initialized)
	 * 
	 * @pre: fileName is a legal file path
	 */
	public void saveModel(String fileName) throws IOException { // Q-3
		BufferedWriter bufferedWriterVoc = new BufferedWriter(new FileWriter(fileName + VOC_FILE_SUFFIX));
		BufferedWriter bufferedWriterCount = new BufferedWriter(new FileWriter(fileName + COUNTS_FILE_SUFFIX));
		boolean firstCount = true;

		bufferedWriterVoc.write(this.mVocabulary.length + " words");

		for (int i = 0; i < this.mVocabulary.length; i++) {

			bufferedWriterVoc.write("\r\n" + i + "," + this.mVocabulary[i]);

			for (int j = 0; j < this.mVocabulary.length; j++) {
				if (this.mBigramCounts[i][j] != 0) {
					if (firstCount) {
						bufferedWriterCount.write(i + "," + j + ":" + this.mBigramCounts[i][j]);
						firstCount = false;
					} else {
						bufferedWriterCount.write("\r\n" + i + "," + j + ":" + this.mBigramCounts[i][j]);
					}
				}
			}
		}

		bufferedWriterVoc.close();
		bufferedWriterCount.close();
	}

	/*
	 * @pre: fileName is a legal file path
	 */
	public void loadModel(String fileName) throws IOException { // Q - 4
		BufferedReader bufferedReaderVoc = new BufferedReader(new FileReader(fileName + VOC_FILE_SUFFIX));
		BufferedReader bufferedReaderCount = new BufferedReader(new FileReader(fileName + COUNTS_FILE_SUFFIX));

		String line;
		String[] words, couples, indexes;

		line = bufferedReaderVoc.readLine();

		String[] headLine = line.split(" ");
		String[] voc = new String[Integer.parseInt(headLine[0])];

		int[][] counts = new int[Integer.parseInt(headLine[0])][Integer.parseInt(headLine[0])];

		while ((line = bufferedReaderVoc.readLine()) != null) {
			words = line.split(",",2);
			voc[Integer.parseInt(words[0])] = words[1];
		}
		this.mVocabulary = voc;

		while ((line = bufferedReaderCount.readLine()) != null) {
			couples = line.split(":");
			indexes = couples[0].split(",");
			counts[Integer.parseInt(indexes[0])][Integer.parseInt(indexes[1])] = Integer.parseInt(couples[1]);
		}
		this.mBigramCounts = counts;

		bufferedReaderVoc.close();
		bufferedReaderCount.close();
	}

	/*
	 * @pre: word is in lowercase
	 * 
	 * @pre: the method initModel was called (the language model is initialized)
	 * 
	 * @pre: word is in lowercase
	 * 
	 * @post: $ret = -1 if word is not in vocabulary, otherwise $ret = the index of
	 * word in vocabulary
	 */
	public int getWordIndex(String word) { // Q - 5
		return exists(this.mVocabulary, word, this.mVocabulary.length);
	}

	/*
	 * @pre: word1, word2 are in lowercase
	 * 
	 * @pre: the method initModel was called (the language model is initialized)
	 * 
	 * @post: $ret = the count for the bigram <word1, word2>. if one of the words
	 * does not exist in the vocabulary, $ret = 0
	 */
	public int getBigramCount(String word1, String word2) { // Q - 6
		int index1 = getWordIndex(word1);
		int index2 = getWordIndex(word2);

		if (index1 < 0 || index2 < 0) {
			return 0;
		}

		return this.mBigramCounts[index1][index2];
	}

	/*
	 * @pre word in lowercase, and is in mVocabulary
	 * 
	 * @pre: the method initModel was called (the language model is initialized)
	 * 
	 * @post $ret = the word with the lowest vocabulary index that appears most
	 * fequently after word (if a bigram starting with word was never seen, $ret
	 * will be null
	 */
	public String getMostFrequentProceeding(String word) { // Q - 7
		int i = getWordIndex(word);
		int max = 0, maxI = -1;

		for (int j = 0; j < this.mVocabulary.length; j++) {
			if (this.mBigramCounts[i][j] > max) {
				max = this.mBigramCounts[i][j];
				maxI = j;
			}
		}
		if (maxI == -1) {
			return null;
		}
		return this.mVocabulary[maxI];
	}

	/*
	 * @pre: sentence is in lowercase
	 * 
	 * @pre: the method initModel was called (the language model is initialized)
	 * 
	 * @pre: each two words in the sentence are are separated with a single space
	 * 
	 * @post: if sentence is is probable, according to the model, $ret = true, else,
	 * $ret = false
	 */
	public boolean isLegalSentence(String sentence) { // Q - 8
		String[] words = sentence.split(" ");

		if (words.length == 0) {
			return true;
		}
		if (getWordIndex(words[0]) < 0) {
			return false;
		}
		for (int i = 1; i < words.length; i++) {
			if (getWordIndex(words[i]) < 0) {
				return false;
			}
			if (getBigramCount(words[i - 1], words[i]) == 0) {
				return false;
			}
		}
		return true;
	}

	/*
	 * @pre: arr1.length = arr2.legnth post if arr1 or arr2 are only filled with
	 * zeros, $ret = 0, otherwise
	 */
	public static double calcCosineSim(int[] arr1, int[] arr2) { // Q - 9
		double A = 0, B = 0, both = 0;

		for (int i = 0; i < arr1.length; i++) {
			both += arr1[i] * arr2[i];
			A += Math.pow(arr1[i], 2);
			B += Math.pow(arr2[i], 2);
		}
		if (A == 0 || B == 0) {
			return 0;
		}
		return (both) / (Math.sqrt(A) * Math.sqrt(B));

	}

	/*
	 * @pre: word is in vocabulary
	 * 
	 * @pre: the method initModel was called (the language model is initialized),
	 * 
	 * @post: $ret = w implies that w is the word with the largest
	 * cosineSimilarity(vector for word, vector for w) among all the other words in
	 * vocabulary
	 */
	public String getClosestWord(String word) { // Q - 10
		int i = getWordIndex(word);
		int[] thisVec = this.mBigramCounts[i];
		double max = 0, similarity;
		int maxI = 0;

		for (int j = 0; j < this.mVocabulary.length; j++) {
			if (j == i) {
				continue;
			}
			similarity = calcCosineSim(thisVec, this.mBigramCounts[j]);
			if (max < similarity) {
				max = similarity;
				maxI = j;
			}
		}
		return this.mVocabulary[maxI];
	}
}
