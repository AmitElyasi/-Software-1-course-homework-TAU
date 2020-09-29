package il.ac.tau.cs.sw1.ex7.wordsRank;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import il.ac.tau.cs.sw1.ex7.histogram.HashMapHistogram;
import il.ac.tau.cs.sw1.ex7.histogram.IllegalKValueException;
import il.ac.tau.cs.sw1.ex7.wordsRank.RankedWord.rankType;

/**************************************
 * Add your code to this class !!! *
 **************************************/

public class FileIndex {
	
	public static final int UNRANKED_CONST = 30;
	private HashMap<String, HashMapHistogram<String>> fileIndex;
	private HashMap<String, HashMap<String,Integer>> wordIndex;
	private HashMap<String,RankedWord> rankedWordIndex;
	int numOfFiles=0, numOfWords=0;

	public FileIndex() {
		fileIndex = new HashMap<String, HashMapHistogram<String>>();
		wordIndex = new HashMap<String, HashMap<String,Integer>>();
		rankedWordIndex=new HashMap<String,RankedWord>();
		
	}

	/*
	 * @pre: the directory is no empty, and contains only readable text files
	 */
	public void indexDirectory(String folderPath) {
		// This code iterates over all the files in the folder. add your code wherever
		// is needed
		File folder = new File(folderPath);
		File[] listFiles = folder.listFiles();

		HashMapHistogram<String> hist;

		for (File file : listFiles) {
			// for every file in the folder
			if (file.isFile()) {
				numOfFiles++;
				hist = new HashMapHistogram<>();
				try {
					hist.addAll(FileUtils.readAllTokens(file));
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				fileIndex.put(file.getName(), hist);
				for (String word : hist.getItemsSet()) {
					numOfWords++;
					if (wordIndex.get(word) == null) {
						wordIndex.put(word, new HashMap<String,Integer>());
					}
					try {
						wordIndex.get(word).put(file.getName(), getRankForWordInFile(file.getName(),word));
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}
		}
		for(String word:wordIndex.keySet()) {
			for(String file:fileIndex.keySet()) {
				if(!wordIndex.get(word).containsKey(file)) {
					wordIndex.get(word).put(file, fileIndex.get(file).map.size()+UNRANKED_CONST);
				}
			}
			rankedWordIndex.put(word,new RankedWord(word, wordIndex.get(word)));
		}
	}

	/*
	 * @pre: the index is initialized
	 * 
	 * @pre filename is a name of a valid file
	 * 
	 * @pre word is not null
	 */
	public int getCountInFile(String filename, String word) throws FileIndexException {
		if(this.fileIndex.get(filename)==null) {
			throw (new FileIndexException("The file: "+filename+" does not exist in the index."));
		}
		return this.fileIndex.get(filename).getCountForItem(word.toLowerCase());

	}

	/*
	 * @pre: the index is initialized
	 * 
	 * @pre filename is a name of a valid file
	 * 
	 * @pre word is not null
	 */
	public int getRankForWordInFile(String filename, String word) throws FileIndexException {
		if(this.fileIndex.get(filename)==null) {
			throw (new FileIndexException("The file: "+filename+" does not exist in the index."));
		}
		word=word.toLowerCase();
		Iterator<String> iter = this.fileIndex.get(filename).iterator();

		int rank = 0;
		String curr;
		while (iter.hasNext()) {
			curr = iter.next();
			rank++;
			if (word.equals(curr)) {
				return rank;
			}
		}
		return rank + UNRANKED_CONST;
	}

	/*
	 * @pre: the index is initialized
	 * 
	 * @pre word is not null
	 */
	public int getAverageRankForWord(String word) {	
		word=word.toLowerCase();
		if(rankedWordIndex.get(word)==null) {
			return (int)Math.round(numOfWords/numOfFiles)+UNRANKED_CONST;
		}
		return rankedWordIndex.get(word).getRankByType(rankType.average);
	}

	public List<String> getWordsRankSmallerThanK(int k,rankType type){
		
		List<String> output=new ArrayList<String>();
		for(String word:rankedWordIndex.keySet()) {
			if(rankedWordIndex.get(word).getRankByType(type)<k) {
				output.add(word);
			}
		}
		return output;
	}
	
	public List<String> getWordsWithAverageRankSmallerThanK(int k) {
		return getWordsRankSmallerThanK(k,rankType.average);
	}

	public List<String> getWordsWithMinRankSmallerThanK(int k) {
		return getWordsRankSmallerThanK(k,rankType.min);
	}

	public List<String> getWordsWithMaxRankSmallerThanK(int k) {
		return getWordsRankSmallerThanK(k,rankType.max);
	}

}