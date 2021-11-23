class UserSolution2 {	
	
	void mstrcpy(char dst[], char src[])
	{
		int c = 0;
		while((dst[c] = src[c]) != 0) ++c;
	}
	
	int mstrcmp(char str1[], char str2[])
	{
		int c = 0;
		while(str1[c] != 0 && str1[c] == str2[c]) ++c;
		return str1[c] - str2[c];
	}
	
	class Event {
		char name[];
		int groupId;
	}

	char eventName[][][]; // [group][event_index]
	int eventNameN[]; 
	int eventUid[][][]; // [group][event_index][index]
	int eventUidN[][];
	Event userEvent[][]; // [uid][index]
	char userEventN[];

	void init()
	{
		eventName = new char[100][200][15];
		eventNameN = new int[100];
		eventUid = new int[100][200][5];
		eventUidN = new int[100][200];
		userEvent = new Event[1000][100];
		userEventN = new char[1000];

		for(int i = 0; i < 1000; i++) userEventN[i] = 0;
	}
	
	int find_eventInd(char ename[], int groupid) {
		for(int i = 0; i < eventNameN[groupid]; i++) 
			if(mstrcmp(eventName[groupid][i], ename) == 0)
				return i;
		
		return eventNameN[groupid];
	}
	
	int find_groupid(int uid, char ename[]) {
		for(int i = 0; i < userEventN[uid]; i++)
			if(mstrcmp(userEvent[uid][i].name, ename) == 0)
				return userEvent[uid][i].groupId;
		return -1;
	}

	void addEvent(int uid, char ename[], int groupid)
	{
		int eventInd = find_eventInd(ename, groupid);
		if(eventInd == eventNameN[groupid]) {
			mstrcpy(eventName[groupid][eventInd], ename);
			eventNameN[groupid]++;
		}
		eventUid[groupid][eventInd][eventUidN[groupid][eventInd]++] = uid;
		Event event = userEvent[uid][userEventN[uid]++];
		mstrcpy(event.name, ename);
		event.groupId = groupid;
	}

	void delete_user_event(int uid, char ename[]) {
		for(int i = 0; i < userEventN[uid]; i++)
			if(mstrcmp(userEvent[uid][i].name, ename) == 0) {
				int size = userEventN[uid];
				if(i != size -1) {
					mstrcpy(userEvent[uid][i].name, userEvent[uid][size-1].name);
					userEvent[uid][i].groupId = userEvent[uid][size-1].groupId;
				}
				userEventN[uid]--;
				return;
			}
	}
	
	int deleteEvent(int uid, char ename[])
	{
		int groupid = find_groupid(uid, ename);
		if(groupid == -1) return 0;
		int eventInd = find_eventInd(ename, groupid);
		if(eventInd == eventNameN[groupid]) return 0;
		int res = 1;
		
		if(eventUid[groupid][eventInd][0] == uid) {
			res = eventUidN[groupid][eventInd];
			for(int i = 0; i < eventUidN[groupid][eventInd]; i++) {
				delete_user_event(eventUid[groupid][eventInd][i], ename);
			}
			
			int eventN = eventNameN[groupid];
			if(eventInd != eventN -1) {
				mstrcpy(eventName[groupid][eventInd], eventName[groupid][eventN-1]);
				for(int i = 0; i < eventUidN[groupid][eventN-1]; i++)
					eventUid[groupid][eventInd][i] = eventUidN[groupid][eventN-1];
			}
			eventUidN[groupid][eventN-1] = 0;
			eventNameN[groupid]--;
		} else {
			delete_user_event(uid, ename);
			int userInd = 0;
			for(int i = 0; i < eventUidN[groupid][eventInd]; i++) {
				if(eventUid[groupid][eventInd][i] == uid)
					userInd = i;
			}
			int userN = eventUidN[groupid][eventInd];
			if(userInd != userN - 1)
				eventUid[groupid][eventInd][userInd] = eventUid[groupid][eventInd][userN-1];
			eventUidN[groupid][eventInd]--;
		}
		
		return res;
	}
	
	void change_user_event(int uid, char ename[], char cname[]) {
		for(int i = 0; i < userEventN[uid]; i++) {
			if(mstrcmp(userEvent[uid][i].name, ename) == 0) {
				mstrcpy(userEvent[uid][i].name, cname);
				return;
			}
		}
	}

	int changeEvent(int uid, char ename[], char cname[])
	{
		int groupid = find_groupid(uid, ename);
		if(groupid == -1) return 0;
		int eventInd = find_eventInd(ename, groupid);
		int res = 1;
		
		if(eventUid[groupid][eventInd][0] == uid) {
			mstrcpy(eventName[groupid][eventInd], cname);
			for(int i = 0; i < eventUidN[groupid][eventInd]; i++) 
				change_user_event(eventUid[groupid][eventInd][uid], ename, cname);
			res = eventUidN[groupid][eventInd];
		} else {
			delete_user_event(uid, ename);
			int userInd = 0;
			
			for(int i = 0; i < eventUidN[groupid][eventInd]; i++)
				if(eventUid[groupid][eventInd][i] == uid)
					userInd = i;
			int userN = eventUidN[groupid][eventInd];
			if(userInd != userN - 1)
				eventUid[groupid][eventInd][userInd] = eventUid[groupid][eventInd][userN-1];
			eventUidN[groupid][eventInd]--;
			addEvent(uid, cname, groupid);
		}
		return res;
	}

	int getCount(int uid)
	{
		return userEventN[uid];
	}

}