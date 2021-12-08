import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map.Entry;
import java.util.PriorityQueue;

class UserSolution2 {

	static final int MAXL = 16;
	
	public static class RESULT {
		char mApp[][] = new char[5][MAXL];
	};

	HashMap<String, Integer> appMap;
	String[] appName;
	int[] appTotal;
	int[] appScore;
	
	HashMap<Integer, Integer>[] userMap;
	ArrayList<Integer> banList;

	PriorityQueue<Element> scoreQueue;
	PriorityQueue<Element> numQueue;

	void init(int N, char mApp[][])
	{
		appMap = new HashMap<>();
		appName = new String[N];
		appTotal = new int[N];
		appScore = new int[N];
		
		userMap = new HashMap[10001];
		for(int i = 1; i < 10001; i++) {
			userMap[i] = new HashMap<>();
		}
		banList = new ArrayList<>();
		
		scoreQueue = new PriorityQueue<>();
		numQueue = new PriorityQueue<>();

		for(int i = 0; i < N; i++) {
			String name = new String(mApp[i]);

			appMap.put(name, i);
			appName[i] = name;
			
			scoreQueue.add(new Element(0.0, name));
			numQueue.add(new Element(0.0, name));
		}
	}

	void addRating(int mUser, char mApp[], int mScore)
	{
		if(banList.contains(mUser)) return;

		int aID = appMap.get(new String(mApp));
		HashMap<Integer, Integer> map = userMap[mUser];
		
		scoreQueue.remove(new Element(appName[aID]));
		numQueue.remove(new Element(appName[aID]));

		if(!map.containsKey(aID)) {
			appTotal[aID]++;
			appScore[aID] += mScore;
		} else {
			appScore[aID] -= map.get(aID);
			appScore[aID] += mScore;
		}

		double avg = getAvg(aID);
		scoreQueue.add(new Element(avg, appName[aID]));
		numQueue.add(new Element((double) appTotal[aID], appName[aID]));
		
		map.put(aID, mScore);
	}

	void deleteRating(int mUser, char mApp[])
	{
		int aID = appMap.get(new String(mApp));
		HashMap<Integer, Integer> map = userMap[mUser];
		
		scoreQueue.remove(new Element(appName[aID]));
		numQueue.remove(new Element(appName[aID]));

		appTotal[aID]--;
		appScore[aID] -= map.get(aID);
		map.remove(aID);
		
		double avg = getAvg(aID);
		scoreQueue.add(new Element(avg, appName[aID]));
		numQueue.add(new Element((double) appTotal[aID], appName[aID]));
	}

	void banUser(int mUser)
	{
		if(banList.contains(mUser)) return;

		for(Entry<Integer, Integer> entry : userMap[mUser].entrySet()) {
			int aID = entry.getKey();

			scoreQueue.remove(new Element(appName[aID]));
			numQueue.remove(new Element(appName[aID]));
			
			appScore[aID] -= entry.getValue();
			appTotal[aID]--;
			
			double avg = getAvg(aID);
			scoreQueue.add(new Element(avg, appName[aID]));
			numQueue.add(new Element((double) appTotal[aID], appName[aID]));
		}

		banList.add(mUser);
	}

	RESULT sortByScore()
	{
		//System.out.println("\n sort by score");

		RESULT ret = new RESULT();

		ret.mApp = addName(scoreQueue);

		return ret;
	}

	RESULT sortByNumber()
	{
		//System.out.println("\n sort by num");

		RESULT ret = new RESULT();

		ret.mApp = addName(numQueue);

		return ret;
	}

	char[][] addName(PriorityQueue<Element> pq) {
		int cnt = 0;
		char[][] mApp = new char[5][MAXL];

		PriorityQueue<Element> tempQueue = new PriorityQueue(pq);
		
		while(!tempQueue.isEmpty() && cnt < 5) {
			//System.out.println(tempQueue.peek().name + " " + tempQueue.peek().score);
			String name = tempQueue.poll().name;
			
			for(int i = 0; i < name.length(); i++) {
				mApp[cnt][i] = name.charAt(i);
			}

			cnt++;
		}

		return mApp;
	}
	
	double getAvg(int aID) {
		if(appScore[aID] == 0 || appTotal[aID] == 0) {
			return 0.0;
		} else {
			double avg = (double) appScore[aID] / (double) appTotal[aID];
			avg = Math.floor(avg * 10) / 10.0;
			return avg;
		}
	}

}

class Element implements Comparable<Element> {
	double score;
	String name;
	
	Element(double score, String name) {
		this.score = score;
		this.name = name;
	}
	
	Element(String name) {
		this.name = name;
	}
	
	@Override
	public int compareTo(Element o) {
		if(this.score == o.score) {
			return this.name.compareTo(o.name);
		} else {
			return Double.compare(o.score, this.score);
		}
	}

	@Override
	public boolean equals(Object obj) {
		if(obj instanceof Element) {
			Element e = (Element) obj;
			return this.name.equals(e.name);
		}
		return super.equals(obj);
	}
}