import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.TreeSet;

class UserSolution
{
	class Robot {
		int id;
		int iq;
		int wID;
		int time;
		int status; // 0 : 센터 / 1 : 작업 / 2 : 수리 
		
		Robot(int id) {
			this.id = id;
			this.status = 0;
		}
	}
	
	Robot[] robots;
	HashMap<Integer, ArrayList<Robot>> workMap;
	TreeSet<Robot> maxSet;
	TreeSet<Robot> minSet;
	
	public void init(int N)
	{
		robots = new Robot[N+1];
		workMap = new HashMap<>();
		
		maxSet = new TreeSet<>(new Comparator<Robot>() {
			@Override
			public int compare(UserSolution.Robot o1, UserSolution.Robot o2) {
				if(o1.iq == o2.iq) {
					return o1.id - o2.id;
				}
				return o2.iq - o1.iq;
			}
		});
		
		minSet = new TreeSet<>(new Comparator<Robot>() {
			@Override
			public int compare(UserSolution.Robot o1, UserSolution.Robot o2) {
				if(o1.iq == o2.iq) {
					return o1.id - o2.id;
				}
				return o1.iq - o2.iq;
			}
		});
		
		for(int i = 1; i < N+1; i++) {
			Robot robot = new Robot(i);
			
			robots[i] = robot;
			maxSet.add(robot);
			minSet.add(robot);
		}
	}

	public int callJob(int cTime, int wID, int mNum, int mOpt)
	{
		int sum = 0;
		
		Robot robot;
		ArrayList<Robot> list = new ArrayList<>();
		
		for(int i = 0; i < mNum; i++) {
			if(mOpt == 0) { // 높은 지능 우선 방식 
				robot = maxSet.pollFirst();
				minSet.remove(robot);
			} else {  // 낮은 지능 우선 방식 
				robot = minSet.pollFirst();
				maxSet.remove(robot);
			}
			
			robot.status = 1;
			robot.wID = wID;
			robot.iq += cTime;
			
			list.add(robot);
			
			sum += robot.id;
		}
		
		workMap.put(wID, list);
		
		return sum;
	}

	public void returnJob(int cTime, int wID)
	{
		ArrayList<Robot> list = workMap.get(wID);
		
		if(list.isEmpty()) {
			return;
		}
		
		for(Robot robot : list) {
			robot.iq -= cTime;
			robot.status = 0;
			
			maxSet.add(robot);
			minSet.add(robot);
		}
		
		workMap.get(wID).clear();
	}

	public void broken(int cTime, int rID)
	{
		Robot robot = robots[rID];
		
		if(robot.status != 1) {
			return;
		}
		
		ArrayList<Robot> list = workMap.get(robot.wID);
		list.remove(robot);
		
		robot.status = 2;
		robot.wID = 0;
		robot.iq = 0;
	}

	public void repair(int cTime, int rID)
	{
		Robot robot = robots[rID];
		
		if(robot.status == 2) {
			robot.status = 0;
			robot.iq -= cTime;
			robot.wID = 0;
			
			maxSet.add(robot);
			minSet.add(robot);
		}
	}

	public int check(int cTime, int rID)
	{
		Robot robot = robots[rID];
		
		if(robot.status == 2) {
			return 0;
		} else if(robot.status == 1) {
			return robot.wID * (-1);
		} else {
			return robot.iq + cTime;
		}
	}
}