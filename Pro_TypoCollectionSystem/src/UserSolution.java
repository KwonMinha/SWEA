import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Map.Entry;

class UserSolution {

	Map<String, HashMap<String, HashSet<Integer>>> typoMap;
	int[] lastTimestamp;
	String[] lastWord;
	
	void init(int n) {
		typoMap = new HashMap<String, HashMap<String, HashSet<Integer>>>();
		lastTimestamp = new int[n + 1];
		lastWord = new String[n + 1];
	}
	 
	int search(int mId, int searchTimestamp, char[] searchWord, char[][] correctWord) {
		StringBuilder sb = new StringBuilder();
		
		for(int i = 0; i < searchWord.length; i++) {
			if(searchWord[i] == '\0') {
				break;
			}
			
			sb.append(searchWord[i]);
		}
		
		String searchWordStr = sb.toString();
		
		int correctWordCnt = 0;
		
		if(!typoMap.containsKey(searchWordStr)) {
			typoMap.put(searchWordStr, new HashMap<String, HashSet<Integer>>());
		}
		
		if(lastTimestamp[mId] != 0 && searchTimestamp - lastTimestamp[mId] <= 10) {
			String lastWordStr = lastWord[mId];
			
			if(!typoMap.get(lastWordStr).containsKey(searchWordStr)) {
				if(typoCheck(lastWordStr, searchWordStr)) {
					typoMap.get(lastWordStr).put(searchWordStr, new HashSet<Integer>());
				}
			}
			
			if(typoMap.get(lastWordStr).containsKey(searchWordStr)) {
				if(typoMap.get(lastWordStr).get(searchWordStr).size() < 3) {
					typoMap.get(lastWordStr).get(searchWordStr).add(mId);
				}
			}
		}
		
		lastTimestamp[mId] = searchTimestamp;
		lastWord[mId] = searchWordStr;
		
		for(Entry<String, HashSet<Integer>> entry : typoMap.get(searchWordStr).entrySet()) {
			if(entry.getValue().size() == 3) {
				for(int i = 0; i < entry.getKey().length(); i++) {
					correctWord[correctWordCnt][i] = entry.getKey().charAt(i);
				}
				
				correctWord[correctWordCnt][entry.getKey().length()] = '\0';
				correctWordCnt++;
			}
		}
		
		return correctWordCnt;
	}
	
	boolean typoCheck(String typoWord, String correctWord) {
		int diff = 0;
		
		int typoIdx = 0;
		int correctIdx = 0;
		
		int typoLen = typoWord.length();
		int correctLen = correctWord.length();
		
		if(Math.abs(typoLen - correctLen) > 1) {
			return false;
		}
		
		while(typoIdx < typoLen && correctIdx < correctLen && diff < 2) {
			if(typoWord.charAt(typoIdx) != correctWord.charAt(correctIdx)) {
				diff++;
				
				if(typoLen >= correctLen) {
					typoIdx++;
				}
				
				if(typoLen <= correctLen) {
					correctIdx++;
				}
			} else{
				typoIdx++;
				correctIdx++;
			}
		}
		
		diff += (typoLen - typoIdx) + (correctLen - correctIdx);
		
		return diff == 1;
	}
}