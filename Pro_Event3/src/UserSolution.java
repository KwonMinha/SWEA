import java.util.ArrayList;
import java.util.HashMap;

class UserSolution {	
	static HashMap<String, Integer>[] userArray; // 각 유저가 등록한 일정 저장 <evnetName, eventIndex>
	static ArrayList<Integer>[] groupArray; // 각 그룹에 저장된 eventIndex 저장 
	static HashMap<String, Integer>[] groupCheckArray; // 각 그룹에 저장된 이벤트의 중복 개수 저장 <eventName, 중복되는 이벤트 개수>
	static ArrayList<String[]> eventList; // 모든 일정 정보 저장 (uid, gid, eventName, status(master / normal))

	void init()
	{
		userArray = new HashMap[1000];
		groupArray = new ArrayList[100];
		groupCheckArray = new HashMap[100];
		eventList = new ArrayList<>();
		
		for(int i = 0; i < 1000; i++) {
			if(i < 100) {
				groupArray[i] = new ArrayList<>();
				groupCheckArray[i] = new HashMap<>();
			}
			
			userArray[i] = new HashMap<>();
		}
	}

	void addEvent(int uid, char ename[], int groupid)
	{
		String eventName = getEventName(ename);
		
		String[] temp = new String[4];
		temp[0] = String.valueOf(uid);
		temp[1] = String.valueOf(groupid);
		temp[2] = eventName;
		
		if(groupCheckArray[groupid].get(eventName) == null) { // 중복 X -> master
			groupCheckArray[groupid].put(eventName, 1);
			temp[3] = "master";
		} else { // 중복 O -> normal
			groupCheckArray[groupid].put(eventName, groupCheckArray[groupid].get(eventName) + 1);
			temp[3] = "normal";
		}
		
		eventList.add(temp);
		int eventIndex = eventList.size()-1;
		userArray[uid].put(eventName, eventIndex);
		groupArray[groupid].add(eventIndex);
	}

	int deleteEvent(int uid, char ename[])
	{
		String eventName = getEventName(ename);
		
		if(userArray[uid].get(eventName) == null) {
			return 0;
		}
		
		int eventIndex = userArray[uid].get(eventName);		
		int groupId = Integer.parseInt(eventList.get(eventIndex)[1]);
		int changeCount = groupCheckArray[groupId].get(eventName);
		
		if(eventList.get(eventIndex)[3].equals("master")) { 
			int count = changeCount;
			groupCheckArray[groupId].remove(eventName); // 마스터 포함 중복 이벤트 모두 삭제 
			
			while(count != 0) { // 이름이 중복되어 함께 삭제되는 만큼만 루프 
				for(int i = 0; i < groupArray[groupId].size(); i++) {
					int idx = groupArray[groupId].get(i);
					if(eventList.get(idx)[2].equals(eventName)) {
						count--;
						userArray[Integer.parseInt(eventList.get(idx)[0])].remove(eventName);
						groupArray[groupId].remove(i);
						i--;
					}
				}
			}
			
			return changeCount;
		} else {
			userArray[uid].remove(eventName);
			
			int idx = groupArray[groupId].indexOf(eventIndex);
			groupArray[groupId].remove(idx);
			
			if(groupCheckArray[groupId].get(eventName) == 1) { // 중복되는 이름이 없다면 아예 없애기 
				groupCheckArray[groupId].remove(eventName);
			} else { // 중복되는 이름이 있다면 개수만 -1 
				int count = groupCheckArray[groupId].get(eventName);
				groupCheckArray[groupId].put(eventName, count-1);
			}
			
			return 1;
		}
	}

	int changeEvent(int uid, char ename[], char cname[])
	{
		String eventName = getEventName(ename);
		String changeEName = getEventName(cname);
		
		if(userArray[uid].get(eventName) == null) {
			return 0;
		}
	
		int eventIndex = userArray[uid].get(eventName);
		int groupId = Integer.parseInt(eventList.get(eventIndex)[1]);
		int changeCount = groupCheckArray[groupId].get(eventName);
		
		if(eventList.get(eventIndex)[3].equals("master")) { 
			int count = changeCount;
			groupCheckArray[groupId].remove(eventName);
			
			while(count != 0) {
				for(int i = 0; i < groupArray[groupId].size(); i++) {
					int idx = groupArray[groupId].get(i);
					if(eventList.get(idx)[2].equals(eventName)) {
						count--;
						eventList.get(idx)[2] = changeEName;
						userArray[Integer.parseInt(eventList.get(idx)[0])].remove(eventName);
						userArray[Integer.parseInt(eventList.get(idx)[0])].put(changeEName, idx);
					}
				}
			}
			
			// 변경할 이벤트 이름으로 변경된 개수만큼 groupCheckArray에 추가(changeEName 키가 존재하지 않는다면 기본 값 0 + changeCount, 존재한다면 키 값 + changeCount)
			groupCheckArray[groupId].put(changeEName, groupCheckArray[groupId].getOrDefault(changeEName, 0) + changeCount);
		
			return changeCount;
		} else {
			userArray[uid].remove(eventName);
			userArray[uid].put(changeEName, eventIndex);
			eventList.get(eventIndex)[2] = changeEName;
			eventList.get(eventIndex)[3] = "master";
			
			if(groupCheckArray[groupId].get(eventName) == 1) { // 중복되는 이름이 없다면 아예 없애기 
				groupCheckArray[groupId].remove(eventName);
			} else { // 중복되는 이름이 있다면 개수만 -1 
				int count = groupCheckArray[groupId].get(eventName);
				groupCheckArray[groupId].put(eventName, count-1);
			}
			
			// 변경할 이벤트 이름으로 1개 추가(changeEName 키가 존재하지 않는다면 기본 값 0 + 1, 존재한다면 키 값 + 1)
			groupCheckArray[groupId].put(changeEName, groupCheckArray[groupId].getOrDefault(changeEName, 0) + 1);
			
			return 1;
		}
	}

	int getCount(int uid)
	{
		return userArray[uid].size();
	}
	
	String getEventName(char ename[]) {
		StringBuilder sb = new StringBuilder();
		
		for(int i = 0; i < ename.length; i++) {
			if(ename[i] != '\0') {
				sb.append(ename[i]);
			} else {
				return sb.toString();
			}
		}
		
		return sb.toString();
	}
}