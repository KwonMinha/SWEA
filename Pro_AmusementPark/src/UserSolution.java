import java.util.HashMap;
import java.util.PriorityQueue;

class UserSolution {
	int MAXN;
	int[][] ridesInfo; // mId, duration, capacity
	HashMap<Integer, Integer> ridesIndexMap; // mId, rideIndex
	PriorityQueue<Ride>[] waitQueue; 
	PriorityQueue<Ride> waitNumQueue; // mId, mNum
	int[] startTime; // 시작 가능 시간 = 끝나는 시간 

	public void init(int N, int mId[], int mDuration[], int mCapacity[]) {
		MAXN = N;
		ridesInfo = new int[100][3];
		ridesIndexMap = new HashMap<>();
		for(int i = 0; i < N; i++) {
			ridesInfo[i][0] = mId[i];
			ridesInfo[i][1] = mDuration[i];
			ridesInfo[i][2] = mCapacity[i];
			ridesIndexMap.put(mId[i], i);}

		waitQueue = new PriorityQueue[100];
		for(int i = 0; i < 100; i++) {
			waitQueue[i] = new PriorityQueue<>();
		}

		startTime = new int[100];

		return;
	}

	public int add(int tStamp, int mId, int mNum, int mPriority) {
		int rideIndex = ridesIndexMap.get(mId);		

		// tStamp 시간에 사람이 들어오기 바로 직전까지 놀이기구를 운행시켜 봄 (tStamp 시간이 바로바로 돌아오는 것이 아니라 중간에 텀이 있음) 
		// 그럼 남아 있는 대기큐를 바탕으로 놀이기구가 운행되거나 멈추거나 등 tStamp 전까지의 상태가 만들어짐 
		timing(tStamp-1, rideIndex); 
		
		// tStamp 이전까지 확인한 후에도 시작 가능 시간이 tStamp보다 작다는 것은 대기큐가 비어서 운행하지 않았다는 것
		// 이때는 시작 가능 시간이 아니라 tStamp시간 부터 운행을 재개해야하기 때문에 예외처리 
		if(startTime[rideIndex] < tStamp) {
			startTime[rideIndex] = tStamp;
		}
		
		PriorityQueue<Ride> pq = waitQueue[rideIndex];
		pq.add(new Ride(mNum, mPriority));
		
		// 전처리를 다 해준 뒤에 tStamp에 탑승하는 인원을 큐에 추가 후 놀이기구를 운행하고 시작 가능 시간을 업데이트 
		timing(tStamp, rideIndex);

		if(pq.isEmpty()) {
			return 0;
		} else {
			return pq.peek().priority;
		}
	}

	public void timing(int tStamp, int rideIndex) {
	PriorityQueue<Ride> pq = waitQueue[rideIndex];
		
		while(startTime[rideIndex] <= tStamp && !pq.isEmpty()) {	
			int count = ridesInfo[rideIndex][2];
			
			while(!pq.isEmpty() && count != 0) {
				Ride ride = pq.poll();

				if(ride.num <= count) {
					count -= ride.num;
				} else {
					pq.add(new Ride(ride.num - count, ride.priority));
					count = 0;
				}
			}
			
			startTime[rideIndex] += ridesInfo[rideIndex][1];
		}
	}

	public void search(int tStamp, int mCount, int mId[], int mWait[]) {
		PriorityQueue<Ride> searchPQ = new PriorityQueue<>();

		for(int i = 0; i < MAXN; i++) {
			// tStamp 시각에 탑승하는 인원은 대기 인원에서 제외하기 위해서 tStamp 시간까지 놀이기구를 돌려봄 
			timing(tStamp, i);

			// 제외 후 남은 대기 인원 파악 
			int sum = 0;
			for(Ride ride : waitQueue[i]) {
				sum += ride.num;
			}

			searchPQ.add(new Ride(ridesInfo[i][0], sum));
		}
		
		for(int i = 0; i < mCount; i++) {
			Ride ride = searchPQ.poll();
			mId[i] = ride.num;
			mWait[i] = ride.priority;
		}

		return;
	}
}

class Ride implements Comparable<Ride> {
	int num;
	int priority;

	Ride(int num, int priority) {
		this.num = num;
		this.priority = priority;
	}

	@Override
	public int compareTo(Ride o) {
		if(this.priority == o.priority) {
			return o.num - this.num;
		} else {
			return o.priority - this.priority;
		}
	}
}