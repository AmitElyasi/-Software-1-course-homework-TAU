package il.ac.tau.cs.sw1.ex7.wordsRank;

import java.util.Comparator;

import il.ac.tau.cs.sw1.ex7.wordsRank.RankedWord.rankType;


/**************************************
 *  Add your code to this class !!!   *
 **************************************/

class RankedWordComparator implements Comparator<RankedWord>{
	rankType type;

	public RankedWordComparator(rankType cType) {
		type=cType;
		
	}
	
	@Override
	public int compare(RankedWord o1, RankedWord o2) {
		return o1.getRankByType(type)-o2.getRankByType(type);
	}
}


