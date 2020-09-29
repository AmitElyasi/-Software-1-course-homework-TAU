package il.ac.tau.cs.sw1.ex4;

import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;

public class WordPuzzle {
	public static final char HIDDEN_CHAR = '_';
	public static final int MAX_VOCABULARY_SIZE = 3000;

	/*
	 * get string as an input
	 *
	 * return true if the string contains only English letter return false otherwise
	 * 
	 */
	public static boolean isAlpha(String word) {

		for (int i=0;i<word.length();i++) {
			if (!Character.isLetter(word.charAt(i))) {
				return false;
			}
		}
		return true;
	}

	/*
	 * assume words is not full yet input: sorted words String[], i the number of
	 * words in array, newWord to insert array
	 * 
	 * return the index to insert newWord while keeping the array sorted return -1
	 * if newWord already exist in array.
	 */
	public static int decentPlaceInVocabulary(String[] words, int i, String newWord) {
		if (words[i - 1].compareTo(newWord) < 0) {
			return i;
		}

		if (words[0].compareTo(newWord) > 0) {
			return 0;
		}

		int low = 0, high = i - 1, mid = (int) (i / 2);
		while (low < high - 1) {// binary search
			if (words[mid] == null) {
				high = mid - 1;
				break;
			}
			if (words[mid].compareTo(newWord) > 0) {
				high = mid;
			} else {
				low = mid;
			}
			mid = (int) ((high + low) / 2);
		}

		if (words[low].compareTo(newWord) == 0 || words[high].compareTo(newWord) == 0) {
			return -1;
		}
		if (words[low].compareTo(newWord) > 0) {
			return low;
		}
		return high;
	}

	/*
	 * assume words is not full yet
	 * 
	 * move all words greater then newWord , one index "to the right" (word in index
	 * i goes to i+1)
	 * 
	 */
	public static void clearPlace(String[] words, int i, int properPlace) {
		int j = i - 1;
		while (j >= properPlace) {// clears place for newWord.
			words[j + 1] = words[j];
			j--;
		}
	}

	public static String[] scanVocabulary(Scanner scanner) { // Q - 1
		String[] words = new String[MAX_VOCABULARY_SIZE];
		int i = 0;
		String newWord;
		while (scanner.hasNext() && i < MAX_VOCABULARY_SIZE) {
			newWord = scanner.next().toLowerCase();
			if (!isAlpha(newWord) || newWord.length() < 2) {// check if newWord is legal
				continue;
			}

			if (i == 0) {
				words[i] = newWord;
				i++;
				continue;
			}
			int j = decentPlaceInVocabulary(words, i, newWord);

			if (j == (-1)) {
				continue;
			}

			if (j == i) {
				words[i] = newWord;
			} else {
				clearPlace(words, i, j);
				words[j] = newWord;
			}

			i++;
		}

		if (i < MAX_VOCABULARY_SIZE) {
			words = Arrays.copyOfRange(words, 0, i);
		}
		return words;
	}

	public static int countHiddenInPuzzle(char[] puzzle) { // Q - 2
		int count = 0;
		for (char c : puzzle) {
			if (c == HIDDEN_CHAR) {
				count++;
			}
		}
		return count;
	}

	public static String getRandomWord(String[] vocabulary, Random generator) {// Q - 3
		int i = generator.nextInt(vocabulary.length);
		return vocabulary[i];
	}

	public static boolean checkLegal(String word, char[] puzzle) { // Q - 4 //
		int[] hidden = new int[26]; // 1 for letters not hidden in puzzle, 2 for a hidden one.
		int countLetters = 0, countHidden = 0;

		for (int i = 0; i < puzzle.length; i++) {
			if (Character.isLetter(puzzle[i])) {
				if (hidden[word.charAt(i) - 97] == 2) {// suppose to be hidden but it is not.
					return false;
				}
				countLetters++;
				hidden[puzzle[i] - 97] = 1;

			} else {
				countHidden++;
				if (hidden[word.charAt(i) - 97] == 1) {// suppose to be not hidden but it is.
					return false;
				}
				if (hidden[word.charAt(i) - 97] == 0) {
					hidden[word.charAt(i) - 97] = 2;
				}
			}
		}
		if (countLetters < 1 || countHidden < 1) {
			return false;
		}
		return true;
	}

	public static char[] getRandomPuzzleCandidate(String word, double prob, Random generator) { // Q - 5
		char[] puzzle = word.toCharArray();
		float p;
		for (int i = 0; i < word.length(); i++) {
			p = generator.nextFloat();
			if (p <= prob) {
				puzzle[i] = HIDDEN_CHAR;
			}
		}
		return puzzle;
	}

	public static char[] getRandomPuzzle(String word, double prob, Random generator) { // Q - 6
		char[] puzzle;
		for (int i = 0; i < 1000; i++) {
			puzzle = getRandomPuzzleCandidate(word, prob, generator);
			if (checkLegal(word, puzzle)) {
				return puzzle;
			}
		}
		throwPuzzleGenerationException();
		return null;
	}

	public static int applyGuess(char guess, String solution, char[] puzzle) { // Q - 7
		int count = 0;
		for (int i = 0; i < solution.length(); i++) {
			if (puzzle[i] == HIDDEN_CHAR && solution.charAt(i) == guess) {
				count++;
				puzzle[i] = guess;
			}
		}
		return count;
	}

	public static char[] getHelp(String solution, char[] puzzle) { // Q - 8
		int i = 0;
		while (Character.isLetter(puzzle[i])) {
			i++;
		}
		for (int j = 0; j < solution.length(); j++) {
			if (solution.charAt(j) == solution.charAt(i)) {
				puzzle[j] = solution.charAt(j);
			}
		}
		return puzzle;
	}

	public static void main(String[] args) throws Exception { // Q - 9
		Random generator = new MyRandom(new int[] { 0, 1, 2, 3, 4, 5 },
				new float[] { 0.0f, 0.1f, 0.2f, 0.3f, 0.4f, 0.5f, 0.6f, 0.7f, 0.8f, 0.9f, 1.0f });
		
		if (args[0] == null) { throw new RuntimeException("no argumants were given!"); }
		String path =args[0];
	 	java.io.File f = new java.io.File(path);
		Scanner vocabScan = new Scanner(f);
		String[] vocabulary = scanVocabulary(vocabScan);
		printReadVocabulary(path, vocabulary.length);
		Scanner inputScan = new Scanner(System.in);
		
		// A
		printSettingsMessage(); // a

		printEnterHidingProbability(); // b
		float prob = Float.valueOf(inputScan.next()); // i
		
		String word = getRandomWord(vocabulary, generator); // ii
		char[] puzzle = getRandomPuzzle(word, prob, generator);
		
		String ans = "yes";
		
		while (ans.compareTo("no") != 0) { // 2
			if (ans.compareTo("yes") == 0) { // 1
				word = getRandomWord(vocabulary, generator); // ii
				puzzle = getRandomPuzzle(word, prob, generator);
				printPuzzle(puzzle);
			}
			printReplacePuzzleMessage(); // iii 3
			ans = inputScan.next();
		}

		// B
		printGameStageMessage(); // a

		int attempts = countHiddenInPuzzle(puzzle) + 3; // b

		printPuzzle(puzzle); // c
		printEnterYourGuessMessage();
		char guess;

		for (int i = 0; i < attempts; i++) { // d

			guess = inputScan.next().charAt(0);
			if (applyGuess(guess, word, puzzle) > 0) { // i
				if (countHiddenInPuzzle(puzzle) == 0) { // 1
					printWinMessage();
					break;
				} else { // 2
					printCorrectGuess(attempts - i - 1);

				}
			} else { // ii
				if (guess == 'H') { // 2
					puzzle = getHelp(word, puzzle);
					if (countHiddenInPuzzle(puzzle) == 0) {
						printWinMessage();
						break;
					}
				} else { // 1
					printWrongGuess(attempts - i - 1);
				}
			}
		}
		printGameOver(); // e
		inputScan.close();
		vocabScan.close();
	}

	/*************************************************************/
	/********************* Don't change this ********************/
	/*************************************************************/

	public static void printReadVocabulary(String vocabularyFileName, int numOfWords) {
		System.out.println("Read " + numOfWords + " words from " + vocabularyFileName);
	}

	public static void throwPuzzleGenerationException() {
		throw new RuntimeException("Failed creating a legal puzzle after 1000 attempts!");
	}

	public static void printSettingsMessage() {
		System.out.println("--- Settings stage ---");
	}

	public static void printEnterHidingProbability() {
		System.out.println("Enter your hiding probability:");
	}

	public static void printPuzzle(char[] puzzle) {
		System.out.println(puzzle);
	}

	public static void printReplacePuzzleMessage() {
		System.out.println("Replace puzzle?");
	}

	public static void printGameStageMessage() {
		System.out.println("--- Game stage ---");
	}

	public static void printEnterYourGuessMessage() {
		System.out.println("Enter your guess:");
	}

	public static void printCorrectGuess(int attemptsNum) {
		System.out.println("Correct Guess, " + attemptsNum + " guesses left");
	}

	public static void printWrongGuess(int attemptsNum) {
		System.out.println("Wrong Guess, " + attemptsNum + " guesses left");
	}

	public static void printWinMessage() {
		System.out.println("Congratulations! You solved the puzzle");
	}

	public static void printGameOver() {
		System.out.println("Game over!");
	}

}
