import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.PriorityQueue;

class UserSolution {
    class Class {
    	int id;
    	int start;
    	int end;
    	int ratio;
    	
    	Class(int id, int start, int end, int ratio) {
    		this.id = id;
    		this.start = start;
    		this.end = end;
    		this.ratio = ratio;
    	}
    }
    
    class Record implements Comparable<Record> {
    	int id;
    	int cId;
    	int start;
    	int end;
    	
    	Record(int id, int cId, int start, int end) {
    		this.id = id;
    		this.cId = cId;
    		this.start = start;
    		this.end = end;
    	}

		@Override
		public int compareTo(UserSolution.Record o) {
			if(this.start == o.start) {
				return this.end - o.end;
			}
			return this.start - o.start;
		}
    }
    
    HashMap<Integer, Class> classMap;
    HashMap<Integer, Record> recordMap;
    ArrayList<Record> recordList;
    
    public void init () {
    	classMap = new HashMap<>();
    	recordMap = new HashMap<>();
    	recordList = new ArrayList<>();
    }

    public void destroy () {

    }

    public void newClass (int mClassId, char mClassBegin[], char mClassEnd[], int mRatio) {
    	int startTime = convertTime(mClassBegin);
    	int endTime = convertTime(mClassEnd);
    	
    	classMap.put(mClassId, new Class(mClassId, startTime, endTime, mRatio));
    }

    public void newRecord (int mRecordId, int mClassId, char mRecordBegin[], char mRecordEnd[]) {
    	int startDate = convertDate(Arrays.copyOfRange(mRecordBegin, 0, 10));
    	int endDate = convertDate(Arrays.copyOfRange(mRecordEnd, 0, 10));
    	int startTime = convertTime(Arrays.copyOfRange(mRecordBegin, 11, 19));
    	int endTime = convertTime(Arrays.copyOfRange(mRecordEnd, 11, 19));
    
    	Record record = new Record(mRecordId, mClassId, (startDate * 86400 + startTime), (endDate * 86400 + endTime));
    	
    	recordMap.put(mRecordId, record);
    	recordList.add(record);
    }

    public void changeRecord (int mRecordId, char mNewBegin[], char mNewEnd[]) {
    	int startDate = convertDate(Arrays.copyOfRange(mNewBegin, 0, 10));
    	int endDate = convertDate(Arrays.copyOfRange(mNewEnd, 0, 10));
    	int startTime = convertTime(Arrays.copyOfRange(mNewBegin, 11, 19));
    	int endTime = convertTime(Arrays.copyOfRange(mNewEnd, 11, 19));
    	
    	Record record = recordMap.get(mRecordId);
    	
    	record.start = startDate * 86400 + startTime;
    	record.end = endDate * 86400 + endTime;
    }

    public int checkAttendance (int mClassId, char mDate[]) {
    	int date = convertDate(mDate);
    	
    	int startTime = date * 86400 + classMap.get(mClassId).start;
    	int endTime = date * 86400 + classMap.get(mClassId).end;
    	
    	int minTime = endTime - startTime + 1;
    	minTime *= classMap.get(mClassId).ratio;
    	minTime /= 100;
    	
    	int validTime = 0;
    	
    	PriorityQueue<Record> pq = new PriorityQueue<>();
    	for(int i = 0; i < recordList.size(); i++) {
    		if(recordList.get(i).cId == mClassId) {
    			pq.add(recordList.get(i));
    		}
    	}
    	
    	while(!pq.isEmpty()) {
    		Record record = pq.poll();
    		
    		if(record.start > endTime) { // 강의가 끝난 후의 수강 기록 -> 시간순으로 정렬되었기 때문에 더이상 볼 것 없음 
    			break;
    		}
    		
    		if(record.end < startTime) { // 강의 시작전 기록임 -> 다음 기록 봐야함 
    			continue;
    		}
    		
    		if(record.start <= startTime) { // 강의 시작보다 앞에서 기록 한 경우 
    			if(record.end >= endTime) { // 끝나는 시간이 강의 끝나는 시간 보다 더 늦게까지 기록되어 있기 때문에 전체 수강했음 -> 출석 인정 
    				return 1;
    			} else {
    				validTime += record.end - startTime + 1; // 강의가 끝나기 전에 기록이 끝나기 때문에 강의 시작 ~ 기록한 만큼만 유효 시간으로 인정 
    				startTime = record.end + 1;
    			}
    		} else { // 강의 시작 후 기록한 경우 
    			if(record.end >= endTime) { // 강의 끝날 때까지 들었으니 기록 시작 ~ 강의 끝나는 시간까지 인정 
    				validTime += endTime - record.start + 1;
    				break;
    			} else {
    				validTime += record.end - record.start + 1; // 강의가 끝나기 전에 기록을 끝냈으니 기록 시작 ~ 기록 종료만큼만 인정
    				startTime = record.end + 1;
    			}
    		}
    	}
    	
    	if(validTime > minTime) { // ratio 시간 이상으로 수업 들었으니 인정 
    		return 1;
    	} else {
    		return 0;
    	}
    }

    public int checkCheating () {
    	Collections.sort(recordList); // start 시간 순으로 정렬해 겹치는지 여부 판단 
    	
    	for(int i = 0; i < recordList.size()-1; i++) {
    		
    		for(int j = i + 1; j < recordList.size(); j++) {
    			if(recordList.get(i).cId == recordList.get(j).cId) {
    				continue;
    			}
    			
    			if(recordList.get(i).end >= recordList.get(j).start) { // 앞선 기록의 종료 시간이 뒷 강의의 시작을 넘어선 경우 
    				return 1;
    			}
    		}
    	}
    	
        return 0;
    }
    
    int convertDate(char time[]) {    
    	int[] sum_days_per_month = {0, 31, 59, 90, 120, 151, 181, 212, 243, 273, 304, 334};
    	
    	int year = (time[0] - '0') * 1000 + (time[1] - '0') * 100 + (time[2] - '0') * 10 + (time[3] - '0');
    	year -= 2019;
    	year *= 365;
    	
    	int month = (time[5] - '0') * 10 + (time[6] - '0');
    	month = sum_days_per_month[month-1];
    	
    	int day = (time[8] - '0') * 10 + (time[9] - '0'); 
    
    	return year + month + day;
    }

    int convertTime(char time[]) {
    	int hour = ((time[0] - '0') * 10 + (time[1] - '0')) * 3600;
    	int min = ((time[3] - '0') * 10 + (time[4] - '0')) * 60;
    	int sec = (time[6] - '0') * 10 + (time[7] - '0');
    	
    	return hour + min + sec;
    }

}