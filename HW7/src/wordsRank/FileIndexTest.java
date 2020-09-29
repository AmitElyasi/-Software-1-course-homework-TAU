package il.ac.tau.cs.sw1.ex7.wordsRank;


import java.util.*;

import il.ac.tau.cs.sw1.ex7.histogram.IHistogram;

public class FileIndexTest {

	public static final String INPUT_FOLDER = "./src/il/ac/tau/cs/sw1/ex7/wordsRank/test_resources";
	static final String buffer = " _            _   \n" +
			"| |_ ___  ___| |_ \n" +
			"| __/ _ \\/ __| __|\n" +
			"| ||  __/\\__ \\ |_ \n" +
			" \\__\\___||___/\\__|\n";

	public static void enter(String name){
		System.out.println("=================================================");
		System.out.println("Starting test for "+name+"\n");
	}
	public static void exit(String name){
		System.out.println("\nFinished test for "+name);
		System.out.println("=================================================");
		System.out.println(buffer);

	}

	public static void main(String[] args) {
		enter("getCountInFile");
		getCountInFileTest();
		exit("getCountInFile");

		enter("getRankForWordInFile");
		getRankForWordInFileTest();
		exit("getRankForWordInFile");

		enter("getAverageRankForWord");
		getAverageRankForWordTest();
		exit("getAverageRankForWord");

		enter("getWordsWithAverageRankSmallerThankK");
		getWordsWithAverageRankSmallerThanKTest();
		exit("getWordsWithAverageRankSmallerThankK");

		enter("getWordsWithMaxRankSmallerThankK");
		getWordsWithMaxRankSmallerThanKTest();
		exit("getWordsWithMaxRankSmallerThankK");

		enter("getWordsWithMinRankSmallerThankK");
		getWordsWithMinRankSmallerThanKTest();
		exit("getWordsWithMinRankSmallerThankK");
	}

	public static void getCountInFileTest() {
		FileIndex fIndex = new FileIndex();
		fIndex.indexDirectory(INPUT_FOLDER);

		try {
			Assert.assertEquals(fIndex.getCountInFile("memory.txt", "MEMORY"), 3);
			Assert.assertEquals(fIndex.getCountInFile("memory.txt", "MemorY"), 3);
			Assert.assertEquals(fIndex.getCountInFile("memory.txt", "is"), 2);
			Assert.assertEquals(fIndex.getCountInFile("memory.txt", "computer"), 4);
			Assert.assertEquals(fIndex.getCountInFile("memory.txt", "cayley hamilton"), 0);
		} catch (FileIndexException e) {
			Assert.fail("could not find count in file that exists");
		}

	}

	public static void getRankForWordInFileTest() {

		FileIndex fIndex = new FileIndex();
		fIndex.indexDirectory(INPUT_FOLDER);

		try {
			Assert.assertEquals(fIndex.getRankForWordInFile("memory.txt", "COMPUTER"), 1);
			Assert.assertEquals(fIndex.getRankForWordInFile("memory.txt", "memory"), 2);
			Assert.assertEquals(fIndex.getRankForWordInFile("memory.txt", "is"), 3);
			Assert.assertEquals(fIndex.getRankForWordInFile("memory.txt", "640k"), 4);
			Assert.assertEquals(fIndex.getRankForWordInFile("memory.txt", "a"), 5);
			Assert.assertEquals(fIndex.getRankForWordInFile("memory.txt", "anyone"), 6);
			Assert.assertEquals(fIndex.getRankForWordInFile("memory.txt", "ever"), 7);
			Assert.assertEquals(fIndex.getRankForWordInFile("memory.txt", "more"), 8);
			Assert.assertEquals(fIndex.getRankForWordInFile("memory.txt", "need"), 9);
			Assert.assertEquals(fIndex.getRankForWordInFile("memory.txt", "on"), 10);
			Assert.assertEquals(fIndex.getRankForWordInFile("memory.txt", "than"), 11);
			Assert.assertEquals(fIndex.getRankForWordInFile("memory.txt", "will"), 12);
			Assert.assertEquals(fIndex.getRankForWordInFile("memory.txt", "cayley hamilton"), 42);
		} catch (FileIndexException e) {
			Assert.fail("could not find rank for file that exists");
		}

	}

	public static void getAverageRankForWordTest() {

		FileIndex fIndex = new FileIndex();
		fIndex.indexDirectory(INPUT_FOLDER);

		Assert.assertEquals(fIndex.getAverageRankForWord("anyone"), 4);
		Assert.assertEquals(fIndex.getAverageRankForWord("COMPuter"), 18);
		Assert.assertEquals(fIndex.getAverageRankForWord("memory"), 19);
		Assert.assertEquals(fIndex.getAverageRankForWord("is"), 19);
		Assert.assertEquals(fIndex.getAverageRankForWord("640k"), 20);
		Assert.assertEquals(fIndex.getAverageRankForWord("a"), 20);
		Assert.assertEquals(fIndex.getAverageRankForWord("ever"), 21);
		Assert.assertEquals(fIndex.getAverageRankForWord("need"), 22);
		Assert.assertEquals(fIndex.getAverageRankForWord("more"), 22);
		Assert.assertEquals(fIndex.getAverageRankForWord("cayley-hamilton"), 22);
		Assert.assertEquals(fIndex.getAverageRankForWord("https://www.youtube.com/watch?v=rnqaxulzlae"), 23);
		Assert.assertEquals(fIndex.getAverageRankForWord("than"), 23);
		Assert.assertEquals(fIndex.getAverageRankForWord("forever"), 23);
		Assert.assertEquals(fIndex.getAverageRankForWord("on"), 23);
		Assert.assertEquals(fIndex.getAverageRankForWord("will"), 24);
		Assert.assertEquals(fIndex.getAverageRankForWord("java"), 24);
		Assert.assertEquals(fIndex.getAverageRankForWord("C++"), 39);

	}

	public static void getWordsWithAverageRankSmallerThanKTest() {

		FileIndex fIndex = new FileIndex();
		fIndex.indexDirectory(INPUT_FOLDER);

		List<String> res = fIndex.getWordsWithAverageRankSmallerThanK(5);
		List<String> expected = Collections.singletonList("anyone");
		Assert.assertListEquals(res, expected);

		res = fIndex.getWordsWithAverageRankSmallerThanK(20);
		expected = Arrays.asList("anyone", "computer", "memory", "is");
		Collections.sort(res);
		Collections.sort(expected);
		Assert.assertListEquals(res, expected);

		res = fIndex.getWordsWithAverageRankSmallerThanK(20);
		expected = Arrays.asList("anyone", "computer", "memory", "is");
		Collections.sort(res);
		Collections.sort(expected);
		Assert.assertListEquals(res, expected);

	}

	public static void getWordsWithMinRankSmallerThanKTest() {

		FileIndex fIndex = new FileIndex();
		fIndex.indexDirectory(INPUT_FOLDER);

		List<String> res = fIndex.getWordsWithMinRankSmallerThanK(1);
		List<String> expected = new ArrayList<>();
		Assert.assertListEquals(res, expected);

		res = fIndex.getWordsWithMinRankSmallerThanK(2);
		expected = Arrays.asList("anyone", "computer");
		Collections.sort(res);
		Collections.sort(expected);
		Assert.assertListEquals(res, expected);

		res = fIndex.getWordsWithMinRankSmallerThanK(3);
		expected = Arrays.asList("computer", "memory", "anyone", "cayley-hamilton" + "");
		Collections.sort(res);
		Collections.sort(expected);
		Assert.assertListEquals(res, expected);

	}

	public static void getWordsWithMaxRankSmallerThanKTest() {

		FileIndex fIndex = new FileIndex();
		fIndex.indexDirectory(INPUT_FOLDER);

		List<String> res = fIndex.getWordsWithMaxRankSmallerThanK(6);
		List<String> expected = new ArrayList<>();
		Assert.assertListEquals(res, expected);

		res = fIndex.getWordsWithMaxRankSmallerThanK(7);
		expected = Collections.singletonList("anyone");
		Assert.assertListEquals(res, expected);

		res = fIndex.getWordsWithMaxRankSmallerThanK(36);
		//System.out.println(res);
		expected = Arrays.asList("anyone", "640k", "a", "memory", "will", "need", "more", "is", "ever",
								 "computer", "than", "on");
		Collections.sort(res);
		Collections.sort(expected);
		Assert.assertListEquals(res, expected);

	}


	public static class Assert {
		private static final String VALUE_ERROR_MESSAGE = "";

		public static void assertEquals(Object a, Object b, String msg) {
			if (!a.equals(b)) {
				printError(msg, a, b);
			}
		}

		public static void assertEquals(Object a, Object b) {
			if (!a.equals(b)) {
				System.out.println("in assert: a:"+a+" b:"+b);
				printError(VALUE_ERROR_MESSAGE, b,a);
				Error e = new AssertionError();
				e.printStackTrace(System.out);
			}


		}

		public static <T> void assertHistEquals(IHistogram<T> a, Map<T, Integer> b) {
			for (T item : b.keySet()) {
				if (b.get(item) != a.getCountForItem(item)) {
					printError("histogram has wrong count for " + item, b.get(item), a.getCountForItem(item));
					Exception e = new Exception();
					e.printStackTrace(System.out);
					break;
				}
			}
		}

		public static void assertSetEquals(Set<String> a, Set<String> b) {
			List<String> l1 = new ArrayList<>(a);
			Collections.sort(l1);
			List<String> l2 = new ArrayList<>(b);
			Collections.sort(l2);
			if (l1.size() != l2.size()) {
				printError("sets size differ", l1, l2);
				return;
			}
			int index = -1;
			for (int i = 0; i < l1.size(); i++) {
				if (!l1.get(i).equals(l2.get(i))) {
					index = i;
					break;
				}
			}
			if (index != -1) {
				printError("first incompatabillity at " + index,
						   addErrorToArray((String[]) l1.toArray(), index),
						   addErrorToArray((String[]) l2.toArray(), index));
			}

		}

		public static void printError(String msg, Object o1, Object o2) {
			System.out.println(msg + "\n" +
									   "Expected  : " + o1 +
									   "\nActual    : " + o2);

		}

		public static void printKLegalError(int k) {
			System.out.println("operation with legal k failed.\nk = " + k);
		}

		public static void printKIllegalError(int k) {
			System.out.println("operation with illegal k succeeded.\nk = " + k);
		}

		static String addErrorToArray(Object[] arr1, int i) {
			StringBuilder res = new StringBuilder("[");
			for (int j = 0; j < arr1.length; j++) {
				if (j != 0) {
					res.append(",");
				}
				if (j == i) {
					res.append(String.format(" <<<%s>>>", arr1[j]));
				} else {
					res.append(String.format(" %s", arr1[j]));
				}
			}
			res.append("]");
			return res.toString();
		}

		public static <T> void printValueIllegalError(T item) {
			System.out.println("removed item that was not in the histogram. should throw error.\nitem = " + item);
			Exception e = new Exception();
			e.printStackTrace(System.out);
		}

		public static <T> void printValueLegalError(T item) {
			System.out.println("could not remove item that was in histogram. threw error and it shouldn't\nitem = " + item);
			Exception e = new Exception();
			e.printStackTrace(System.out);
		}

		public static <T> void printItemLegalError(T item) {
			System.out.println("operation with legal item failed.\nitem = " + item);
			Exception e = new Exception();
			e.printStackTrace(System.out);
		}

		public static void assertListEquals(List<String> expected, List<String> actual) {
			if (expected.size() != actual.size()) {
				printError("lists size differ", expected, actual);
				return;
			}
			int index = -1;
			for (int i = 0; i < expected.size(); i++) {
				if (!expected.get(i).equals(actual.get(i))) {
					index = i;
					break;
				}
			}
			if (index != -1) {
				printError("first incompatabillity at " + index,
						   addErrorToArray(actual.toArray(), index),
						   addErrorToArray(expected.toArray(), index));
			}
		}

		public static void fail(String should_not_reach_this_line) {
			System.out.println(should_not_reach_this_line);
			Error e = new AssertionError();
			e.printStackTrace();
		}
	}

}
