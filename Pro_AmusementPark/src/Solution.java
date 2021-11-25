import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Solution {
	private final static int CMD_INIT = 1;
	private final static int CMD_ADD = 2;
	private final static int CMD_SEARCH = 3;
	private final static int MAX_N = 100;

	private final static UserSolution usersolution = new UserSolution();

	private static int[] rideId = new int[MAX_N];
	private static int[] rideDuration = new int[MAX_N];
	private static int[] rideCapa = new int[MAX_N];

	private static boolean run(BufferedReader br) throws Exception {
		int q = Integer.parseInt(br.readLine());

		int n, num, pri, time, idx, cnt;
		int cmd, ans, ret = 0;
		int[] mId = new int[10];
		int[] mWait = new int[10];
		boolean okay = false;

		for (int i = 0; i < q; ++i) {
			StringTokenizer st = new StringTokenizer(br.readLine(), " ");
			cmd = Integer.parseInt(st.nextToken());
			switch (cmd) {
				case CMD_INIT:
					n = Integer.parseInt(br.readLine());
					for (int j = 0; j < n; ++j) {
						StringTokenizer ridetoken = new StringTokenizer(br.readLine(), " ");
						rideId[j] = Integer.parseInt(ridetoken.nextToken());
						rideDuration[j] = Integer.parseInt(ridetoken.nextToken());
						rideCapa[j] = Integer.parseInt(ridetoken.nextToken());
					}
					usersolution.init(n, rideId, rideDuration, rideCapa);
					okay = true;
					break;
				case CMD_ADD:
					time = Integer.parseInt(st.nextToken());
					idx = Integer.parseInt(st.nextToken());
					num = Integer.parseInt(st.nextToken());
					pri = Integer.parseInt(st.nextToken());
					ans = Integer.parseInt(st.nextToken());
					if (okay) {
						ret = usersolution.add(time, rideId[idx], num, pri);
					}
					if (ret != ans)
						okay = false;
					break;
				case CMD_SEARCH:
					time = Integer.parseInt(st.nextToken());
					cnt = Integer.parseInt(st.nextToken());
					if (okay) {
						usersolution.search(time, cnt, mId, mWait);
					}
					for (int j = 0; j < cnt; ++j) {
						idx = Integer.parseInt(st.nextToken());
						num = Integer.parseInt(st.nextToken());
						if (num != mWait[j] || rideId[idx] != mId[j]) {
							okay = false;
						}
					}
					break;
				default:
					okay = false;
					break;
			}
		}
		return okay;
	}

	public static void main(String[] args) throws Exception {
		int TC, MARK;

		//System.setIn(new java.io.FileInputStream("res/sample_input.txt"));

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");

		TC = Integer.parseInt(st.nextToken());
		MARK = Integer.parseInt(st.nextToken());

		for (int testcase = 1; testcase <= TC; ++testcase) {
			int score = run(br) ? MARK : 0;
			System.out.println("#" + testcase + " " + score);
		}

		br.close();
	}
}