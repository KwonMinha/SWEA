import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map.Entry;

class UserSolution {

	static final int MAXL = 16;

	public static class RESULT {
		char mApp[][] = new char[5][MAXL];
	};

	HashMap<String, App> appMap;
	User[] userArray;
	Heap scoreHeap;
	Heap numHeap;

	void init(int N, char mApp[][])
	{
		appMap = new HashMap<>();
		userArray = new User[10001];
		scoreHeap = new Heap(0);
		numHeap = new Heap(1);

		String appName;

		for(int i = 0; i < N; i++) {
			appName = new String(mApp[i]);

			App app = new App(appName);
			
			appMap.put(appName, app);
			scoreHeap.push(app);
			numHeap.push(app);
		}

		for(int i = 1; i < 10001; i++) {
			userArray[i] = new User();
		}
	}

	void addRating(int mUser, char mApp[], int mScore)
	{
		User user = userArray[mUser];

		if(user.isBanned) {
			return;
		}

		String appName = new String(mApp);
		App app = appMap.get(appName);

		if(user.scoreMap.containsKey(appName)) {
			int score = user.scoreMap.get(appName);
			app.totalScore -= score;
			app.totalNum--;
		}  

		user.scoreMap.put(appName, mScore);
		app.totalScore += mScore;
		app.totalNum++;
		updateAvg(app);

		scoreHeap.update(app.scoreIdx);
		numHeap.update(app.numIdx);
	}

	void deleteRating(int mUser, char mApp[])
	{
		User user = userArray[mUser];

		String appName = new String(mApp);
		App app = appMap.get(appName);

		app.totalScore -= user.scoreMap.get(appName);
		app.totalNum--;
		updateAvg(app);

		user.scoreMap.remove(appName);

		scoreHeap.update(app.scoreIdx);
		numHeap.update(app.numIdx);
	}

	void banUser(int mUser)
	{
		User user = userArray[mUser];

		if(user.isBanned) {
			return;
		}

		user.isBanned = true;

		for(Entry<String, Integer> entry : user.scoreMap.entrySet()) {
			App app = appMap.get(entry.getKey());
			app.totalScore -= entry.getValue();
			app.totalNum--;
			updateAvg(app);

			scoreHeap.update(app.scoreIdx);
			numHeap.update(app.numIdx);
		}

		user.scoreMap.clear();
	}

	RESULT sortByScore()
	{
		RESULT ret = new RESULT();
		ret.mApp = addName(scoreHeap);
		return ret;
	}

	RESULT sortByNumber()
	{
		RESULT ret = new RESULT();
		ret.mApp = addName(numHeap);
		return ret;
	}

	char[][] addName(Heap heap) {
		char[][] mApp = new char[5][MAXL];
		ArrayList<App> list = new ArrayList<>();

		for(int i = 0; i < 5 && heap.size > 0; i++) {
			App app = heap.pop();
			String appName = app.name;

			for(int j = 0; j < appName.length(); j++) {
				mApp[i][j] = appName.charAt(j);
			}

			list.add(app);
		}

		for(App app : list) {
			heap.push(app);
		}

		return mApp;
	}

	void updateAvg(App app) {
		if(app.totalNum > 0) {
			double avg = (double) app.totalScore / (double) app.totalNum;
			avg = Math.floor(avg * 10) / 10.0;
			app.avg = avg;
		} else {
			app.avg = 0.0;
		}
		
		//app.avg = app.totalNum > 0 ? app.totalScore * 10 / app.totalNum : 0;
	}
}

class App {
	String name;
	int totalScore = 0;
	int totalNum = 0;
	double avg = 0.0;
	int scoreIdx = 0;
	int numIdx = 0;

	App(String name) {
		this.name = name;
	}
}

class User {
	HashMap<String, Integer> scoreMap = new HashMap<>();
	boolean isBanned = false;
}

class Heap {
	int mode = 0;
	App[] arr = new App[10001];
	//int[] index = new int[10001]; // 클래스에 인덱스 저장안할거면 배열로 따로 관리 
	int size = 0;

	Heap(int mode) {
		this.mode = mode;
	}

	//	// N값이 정해지지 않았다면
	//	Heap(int N) {
	//		arr = new App[N];
	//		index = new int[N];
	//	}

	void init() {
		size = 1;
	}

	boolean compare(App a, App b) {
		if(mode == 0) { // 평균 비교 
			if(a.avg == b.avg) {
				return a.name.compareTo(b.name) <= 0; // 같다면 이름 비교 
			} else {
				return a.avg > b.avg;
			}
		} else { // 총 인원수 비교 
			if(a.totalNum == b.totalNum) {
				return a.name.compareTo(b.name) <= 0;
			} else {
				return a.totalNum > b.totalNum;
			}
		}
	}

	void push(App app) {
		size++;
		arr[size] = app;
		updateIdx(app, size);
		up(size);
	}

	App pop() {
		App app = arr[1];
		updateIdx(app, 0);
		arr[1] = arr[size--];
		updateIdx(arr[1], 1);
		down(1);
		return app;
	}

	void up(int idx) {
		while(idx > 1) {
			if(compare(arr[idx>>1], arr[idx])) {
				break;
			}

			swap(idx>>1, idx);
			idx = idx>>1;
		}
	}

	void down(int idx) {
		while((idx<<1) <= size) {
			int mx;

			if((idx<<1) == size || compare(arr[idx<<1], arr[(idx<<1)+1])) {
				mx = idx<<1;
			} else {
				mx = (idx<<1) + 1;
			}

			if(compare(arr[idx], arr[mx])) {
				break;
			}

			swap(mx, idx);
			idx = mx;
		}
	}

	void swap(int idx1, int idx2) {
		App temp = arr[idx1];
		arr[idx1] = arr[idx2];
		arr[idx2] = temp;

		updateIdx(arr[idx1], idx1);
		updateIdx(arr[idx2], idx2);
	}

	void update(int idx) {
		up(idx);
		down(idx);
	}

	void updateIdx(App app, int idx) {
		if(mode == 0) {
			app.scoreIdx = idx;
		} else {
			app.numIdx = idx;
		}
	}

}