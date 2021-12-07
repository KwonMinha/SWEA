import java.util.HashMap;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;

class Customer {
	boolean isRent;
	int validTime;
	int bicycleDurableTime;
	int bicycleStartTime;
	
	Customer(boolean isRent, int validTime, int bicycleDurableTime, int bicycleStartTime) {
		this.isRent = isRent;
		this.validTime = validTime;
		this.bicycleDurableTime = bicycleDurableTime;
		this.bicycleStartTime = bicycleStartTime;
	}
}

class UserSolution
{		
	// 자전거 대여소 
	static int maxTime; // 최대 운행 시간 
	static int[] shopList; // 자전거 대여소 배달 소요 시간 저장 
	static PriorityQueue<Integer>[] bicycleQueue; // 자전거 대여소에 있는 자전거 운행시간 저장
	static Queue<Integer>[] deliveryQueue; // 폐기된 후 배송될 자전거의 배송 시각 저장 

	// 고객 
	static int nameIndex;
	static HashMap<String, Integer> customerMap; // 고객 이름 - 고객 ID 
	static Customer[] customerList;// 고객 정보 저장 (자전거 대여 여부 / 이용권 남은 시간 / 자전거 운행 시간 / 자전거 탑승 시간)

	public void init(int N, int durableTime, int deliveryTimes[])
	{
		maxTime = durableTime;

		shopList = new int[N];
		bicycleQueue = new PriorityQueue[N];
		deliveryQueue = new LinkedList[N];
		
		for(int i = 0; i < N; i++) {
			shopList[i] = deliveryTimes[i];
			bicycleQueue[i] = new PriorityQueue<>();
			deliveryQueue[i] = new LinkedList<>();
		}
		
		nameIndex = 0;
		customerMap = new HashMap<>();
		customerList = new Customer[10000];
	}

	public void addBicycle(int cTimestamp, int pID, int bicycleNum)
	{
		for(int i = 0; i < bicycleNum; i++) {
			bicycleQueue[pID].add(0);
		}
	}

	public void buyTicket(int cTimestamp, char uName[], int validTime)
	{
		int cID = getNameID(uName);
		
		if(customerList[cID].validTime == 0) { // 이용권을 소지하지 않은 경우 		
			customerList[cID].validTime = cTimestamp + validTime - 1;
		} else {
			if(customerList[cID].validTime < cTimestamp) { // 유효기간 지난 이용권 -> 재발급 
				customerList[cID].validTime = cTimestamp + validTime - 1;
			} else { // 유효기간이 지나지 않은 이용권 -> 갱신 
				customerList[cID].validTime += validTime;
			}
		}
	}

	public int rentBicycle(int cTimestamp, char uName[], int pID)
	{
		int cID = getNameID(uName);
		
		// 빌리기 전 배송 온 자전거 추가 
		while(!deliveryQueue[pID].isEmpty() && deliveryQueue[pID].peek() <= cTimestamp) {
			deliveryQueue[pID].poll();
			bicycleQueue[pID].add(0);
		}

		// 대여 불가 
		if(customerList[cID].isRent || bicycleQueue[pID].isEmpty() || customerList[cID].validTime == 0 || customerList[cID].validTime < cTimestamp) {
			return -1;
		} else {
			// 대여 성공 
			customerList[cID].isRent = true;
			customerList[cID].bicycleDurableTime = bicycleQueue[pID].poll();
			customerList[cID].bicycleStartTime = cTimestamp;
			
			return customerList[cID].bicycleDurableTime;
		}
	}

	public int returnBicycle(int cTimestamp, char uName[], int pID)
	{
		int cID = getNameID(uName);
		
		if(!customerList[cID].isRent) { // 빌린 자전거가 없을 경우 
			return -1;
		} 
		
		removeBicycle(cID, pID, cTimestamp); // 자전거 폐기 
		customerList[cID].isRent = false; // 자전거 반납 
		
		if(customerList[cID].validTime > cTimestamp) { // 유효 기간이 안 지난 경우 
			return 0;
		} else { // 유효 기간이 지난 경우 
			int validTime = cTimestamp - customerList[cID].validTime;
			return validTime;
		}
	}
	
	public void removeBicycle(int cID, int pID, int cTimestamp) {
		int runtime = cTimestamp - customerList[cID].bicycleStartTime;
		
		if(customerList[cID].bicycleDurableTime + runtime > maxTime) {
			deliveryQueue[pID].add(cTimestamp + shopList[pID]);
		} else {
			bicycleQueue[pID].add(customerList[cID].bicycleDurableTime + runtime);
		}
	}
	
	public int getNameID(char uName[]) {
		StringBuilder sb = new StringBuilder();
		
		for(int i = 0; i < uName.length; i++) {
			if(uName[i] == '\0') break;
			sb.append(String.valueOf(uName[i]));
		}
		
		if(customerMap.get(sb.toString()) == null) {
			customerMap.put(sb.toString(), nameIndex);
			customerList[nameIndex] = new Customer(false, 0, 0, 0);
			nameIndex++;
		}
		
		return customerMap.get(sb.toString());
	}
}