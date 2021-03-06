/**
 * @author minha
 * 2021. 12. 1.
 * 쪽찌 예약 전송 
 * Sending의 우선순위큐에서 아예 보낸 사람의 보낸 쪽찌함에 메세지가 없는 경우(삭제한 경우) 예약 시간이 남은 대기 메세지는 패스
 */

import java.util.ArrayList;
import java.util.HashMap;
import java.util.PriorityQueue;

class UserSolution
{
	private final static int MAXM = 3;

	HashMap<Integer, Message> messageMap;
	ArrayList<Integer>[] sentBox;
	ArrayList<Integer>[] inBox;
	PriorityQueue<Message> pq;

	public void init(int N)
	{
		messageMap = new HashMap<>();
		sentBox = new ArrayList[1001];
		inBox = new ArrayList[1001];
		for(int i = 1; i < 1001; i++) {
			sentBox[i] = new ArrayList<>();
			inBox[i] = new ArrayList<>();
		}
		pq = new PriorityQueue<>();
	}

	public void sendMessage(int cTimestamp, int uID1, int uID2, int mID, int scheduleTimestamp)
	{
		sending(cTimestamp);
		
		sentBox[uID1].add(mID);
		messageMap.put(mID, new Message(mID, uID1, uID2, cTimestamp, scheduleTimestamp, 0));
		pq.add(new Message(mID, scheduleTimestamp));
	}

	void sending(int cTimestamp) {
		while(!pq.isEmpty() && pq.peek().sTime <= cTimestamp) {
			int mID = pq.poll().mID;
			Message msg = messageMap.get(mID);
			
			if(!sentBox[msg.sID].contains(mID)) continue; // 삭제된 메세지라면 패스 
			
			inBox[msg.rID].add(mID);
		}
	}

	public int retrieveSentbox(int cTimestamp, int uID, int mIDList[], int uIDList[], int readList[])
	{
		sending(cTimestamp);
		
		int cnt = 0;

		for(int i = sentBox[uID].size()-1; i >= 0; i--) {
			if(cnt == 3) break;

			int mID = sentBox[uID].get(i);
			mIDList[cnt] = mID;
			uIDList[cnt] = messageMap.get(mID).rID;
			readList[cnt] = messageMap.get(mID).read;
			cnt++;
		}

		return cnt;
	}

	public int retrieveInbox(int cTimestamp, int uID, int mIDList[], int uIDList[], int readList[])
	{
		sending(cTimestamp);

		int cnt = 0;

		for(int i = inBox[uID].size()-1; i >= 0; i--) {
			if(cnt == 3) break;

			int mID = inBox[uID].get(i);
			mIDList[cnt] = mID;
			uIDList[cnt] = messageMap.get(mID).sID;
			readList[cnt] = messageMap.get(mID).read;
			cnt++;
		}

		return cnt;
	}

	public int readMessage(int cTimestamp, int uID, int mID)
	{
		sending(cTimestamp);

		if(inBox[uID].contains(mID)) { // 받은 쪽찌함에서 읽음 
			messageMap.get(mID).read = 1;
			return 1;
		}

		if(sentBox[uID].contains(mID)) { // 보낸 쪽찌함에서 읽음 
			return 1;
		}

		return 0;
	}

	public int deleteMessage(int cTimestamp, int uID, int mID)
	{
		sending(cTimestamp);
		
		if(sentBox[uID].remove(Integer.valueOf(mID))) {
			return 1;
		} 

		if(inBox[uID].remove(Integer.valueOf(mID))) {
			return 1;
		} 

		return 0;
	}
}

class Message implements Comparable<Message>{
	int mID;
	int sID;
	int rID;
	int cTime;
	int sTime;
	int read;

	Message(int mID, int sID, int rID, int cTime, int sTime, int read) {
		this.mID = mID;
		this.sID = sID;
		this.rID = rID;
		this.cTime = cTime;
		this.sTime = sTime;
		this.read = read;
	}

	Message(int mID, int sTime) {
		this.mID = mID;
		this.sTime = sTime;
	}

	@Override
	public int compareTo(Message o) {
		if(this.sTime == o.sTime) {
			return this.mID - o.mID;
		} else {
			return this.sTime - o.sTime;
		}
	}
	
}