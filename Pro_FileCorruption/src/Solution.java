import java.util.Scanner;

class Solution {
	private static final int CMD_ADD = 1;
	private static final int CMD_MOVE = 2;
	private static final int CMD_INFECT = 3;
	private static final int CMD_RECOVER = 4;
	private static final int CMD_REMOVE = 5;

	private static Scanner sc;
	private static UserSolution userSolution = new UserSolution();

	private static int run(int score) {
		int N = Integer.parseInt(sc.next());

		for(int i=0; i<N; i++) {
			int cmd = Integer.parseInt(sc.next());
			int ret = 0;

			switch(cmd) {
				case CMD_ADD: {
					int id = Integer.parseInt(sc.next());
					int pid = Integer.parseInt(sc.next());
					int fileSize = Integer.parseInt(sc.next());
					ret = userSolution.cmdAdd(id, pid, fileSize);
					break;
				}
				case CMD_MOVE: {
					int id = Integer.parseInt(sc.next());
					int pid = Integer.parseInt(sc.next());
					ret = userSolution.cmdMove(id, pid);
					break;
				}
				case CMD_INFECT: {
					int id = Integer.parseInt(sc.next());
					ret = userSolution.cmdInfect(id);
					break;
				}
				case CMD_RECOVER: {
					int id = Integer.parseInt(sc.next());
					ret = userSolution.cmdRecover(id);
					break;
				}
				case CMD_REMOVE: {
					int id = Integer.parseInt(sc.next());
					ret = userSolution.cmdRemove(id);
					break;
				}
			}

			int checkSum = Integer.parseInt(sc.next());
			if(ret != checkSum) 
				score = 0;
		}
		return score;
	}

	public static void main(String arg[]) throws Exception {
//		System.setIn(new java.io.FileInputStream("res/sample_input.txt"));
		sc = new Scanner(System.in);
		
		int TC = sc.nextInt();
		int score = sc.nextInt();
		
		for(int t = 1; t <= TC; t++) {
			userSolution.init();
			int ret = run(score);
			System.out.println("#" + t + " " + ret);
		}
		sc.close();
	}
}

