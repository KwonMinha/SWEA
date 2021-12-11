import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Solution
{
	private final static int CMD_INIT = 100;
	private final static int CMD_ADD = 200;
	private final static int CMD_MERGE = 300;
	private final static int CMD_MOVE = 400;
	private final static int CMD_RECRUIT = 500;

	private static int Score = 0;
	private static BufferedReader br;
	private static StringTokenizer st;
	private static UserSolution usersolution = new UserSolution();

	private static void cmd_init() throws Exception
	{
		int mNum;
		mNum = Integer.parseInt(st.nextToken());
		usersolution.init(mNum);
	}

	private static void cmd_destroy()
	{
		usersolution.destroy();
	}

	private static void cmd_add() throws Exception
	{
		char[] mUpperDept = new char[10];
		char[] mNewDept = new char[10];
		int mNum, result, check;
		String inputStr = st.nextToken();
		for(int k = 0; k < inputStr.length(); ++k)
			mUpperDept[k] = inputStr.charAt(k);
		mUpperDept[inputStr.length()] = '\0';
		inputStr = st.nextToken();
		for(int k = 0; k < inputStr.length(); ++k)
			mNewDept[k] = inputStr.charAt(k);
		mNewDept[inputStr.length()] = '\0';
		mNum = Integer.parseInt(st.nextToken());
		result = usersolution.addDept(mUpperDept, mNewDept, mNum);
		check = Integer.parseInt(st.nextToken());
		if(result != check)
			Score = 0;
	}

	private static void cmd_merge() throws Exception
	{
		char[] mDept1 = new char[10];
		char[] mDept2 = new char[10];
		int result, check;
		String inputStr = st.nextToken();
		for(int k = 0; k < inputStr.length(); ++k)
			mDept1[k] = inputStr.charAt(k);
		mDept1[inputStr.length()] = '\0';
		inputStr = st.nextToken();
		for(int k = 0; k < inputStr.length(); ++k)
			mDept2[k] = inputStr.charAt(k);
		mDept2[inputStr.length()] = '\0';
		result = usersolution.mergeDept(mDept1, mDept2);
		check = Integer.parseInt(st.nextToken());
		if(result != check)
			Score = 0;
	}

	private static void cmd_move() throws Exception
	{
		char[] mDept = new char[10];
		int mDepth, mNum, result, check;
		String inputStr = st.nextToken();
		for(int k = 0; k < inputStr.length(); ++k)
			mDept[k] = inputStr.charAt(k);
		mDept[inputStr.length()] = '\0';
		mDepth = Integer.parseInt(st.nextToken());
		mNum = Integer.parseInt(st.nextToken());
		result = usersolution.moveEmployee(mDept, mDepth, mNum);
		check = Integer.parseInt(st.nextToken());
		if(result != check)
			Score = 0;
	}

	private static void cmd_recruit() throws Exception
	{
		int mDeptNum, mNum;
		mDeptNum = Integer.parseInt(st.nextToken());
		mNum = Integer.parseInt(st.nextToken());
		usersolution.recruit(mDeptNum, mNum);
	}

	private static void run() throws Exception
	{
		int N;
		st = new StringTokenizer(br.readLine(), " ");
		N = Integer.parseInt(st.nextToken());
		Score = 100;
		for (int i = 0; i < N; ++i)
		{
			int cmd;
			st = new StringTokenizer(br.readLine(), " ");
			cmd = Integer.parseInt(st.nextToken());
			switch (cmd)
			{
			case CMD_INIT: cmd_init(); break;
			case CMD_ADD: cmd_add(); break;
			case CMD_MOVE: cmd_move(); break;
			case CMD_MERGE: cmd_merge(); break;
			case CMD_RECRUIT: cmd_recruit(); break;
			default: Score = 0; break;
			}
		}
		cmd_destroy();
	}

	public static void main(String[] args) throws Exception {
		int T, MARK;
		//System.setIn(new java.io.FileInputStream("res/sample_input.txt"));
		br = new BufferedReader(new InputStreamReader(System.in));
		st = new StringTokenizer(br.readLine(), " ");
		T = Integer.parseInt(st.nextToken());
		MARK = Integer.parseInt(st.nextToken());
		for (int tc = 1; tc <= T; tc++)
		{
			run();
			if (Score == 100)
				System.out.println("#" + tc + " " + MARK);
			else
				System.out.println("#" + tc + " " + 0);
		}
		br.close();
	}
}