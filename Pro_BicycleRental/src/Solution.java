import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Solution
{	
	private final static int CMD_INIT		= 100;
	private final static int CMD_ADD		= 200;
	private final static int CMD_BUY		= 300;
	private final static int CMD_RENT		= 400;
	private final static int CMD_RETURN		= 500;
	
	private final static int MAXN			= 100;
	private final static int MAXL			= 8;
	
	private static void String2Char(char[] buf, String str)
	{
		for (int k = 0; k < str.length(); ++k)
			buf[k] = str.charAt(k);
		buf[str.length()] = '\0';
	}
	
	private static int mSeed;
	private static int pseudo_rand()
	{
		mSeed = mSeed * 246247151 + 9543169;
		return (mSeed >> 16) & 0x7FFFFFFF;
	}
	
	private final static UserSolution usersolution = new UserSolution();
	
	private static boolean run(BufferedReader br) throws Exception
	{
		int Q;
		int N, durableTime;
		int deliveryTimes[] = new int[MAXN];
		int cTimestamp, pID, bicycleNum, validTime;
		int res = -1, ans;
	
		char uName[] = new char[MAXL];
		
		int  dT;
		
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		
		Q     = Integer.parseInt(st.nextToken());
		mSeed = Integer.parseInt(st.nextToken());

		boolean okay = false;
		
		if (mSeed == 0)
		{
			for (int q = 0; q <= Q; ++q)
			{
				int cmd;		
				st = new StringTokenizer(br.readLine(), " ");
				cmd = Integer.parseInt(st.nextToken());
				switch(cmd)
				{
				case CMD_INIT:
					N = Integer.parseInt(st.nextToken());
					durableTime = Integer.parseInt(st.nextToken());
					for (int i = 0; i < N; ++i)
						deliveryTimes[i] = Integer.parseInt(st.nextToken());
					usersolution.init(N, durableTime, deliveryTimes);
					okay = true;
					break;
				case CMD_ADD:
					cTimestamp = Integer.parseInt(st.nextToken());
					pID = Integer.parseInt(st.nextToken());
					bicycleNum = Integer.parseInt(st.nextToken());
					if (okay)
						usersolution.addBicycle(cTimestamp, pID, bicycleNum);
					break;
				case CMD_BUY:
					cTimestamp = Integer.parseInt(st.nextToken());
					String2Char(uName, st.nextToken());
					validTime = Integer.parseInt(st.nextToken());
					if (okay)
						usersolution.buyTicket(cTimestamp, uName, validTime);
					break;
				case CMD_RENT:
					cTimestamp = Integer.parseInt(st.nextToken());
					String2Char(uName, st.nextToken());
					pID = Integer.parseInt(st.nextToken());
					if (okay)
						res = usersolution.rentBicycle(cTimestamp, uName, pID);
					ans = Integer.parseInt(st.nextToken());
					if (res != ans)
						okay = false;
					break;
				case CMD_RETURN:
					cTimestamp = Integer.parseInt(st.nextToken());;
					String2Char(uName, st.nextToken());
					pID = Integer.parseInt(st.nextToken());
					if (okay)
						res = usersolution.returnBicycle(cTimestamp, uName, pID);
					ans = Integer.parseInt(st.nextToken());
					if (res != ans)
						okay = false;
					break;
				}
			}
		}
		else
		{
			dT = Integer.parseInt(st.nextToken());		
			cTimestamp = 0;
	
			for (int q = 0; q <= Q; ++q)
			{
				int cmd;		
				st = new StringTokenizer(br.readLine(), " ");
				cmd = Integer.parseInt(st.nextToken());
				switch(cmd)
				{
				case CMD_INIT:
					N = Integer.parseInt(st.nextToken());
					durableTime = Integer.parseInt(st.nextToken());
					for (int i = 0; i < N; ++i)
						deliveryTimes[i] = Integer.parseInt(st.nextToken());
					usersolution.init(N, durableTime, deliveryTimes);
					okay = true;
					break;
				case CMD_ADD:
					cTimestamp += pseudo_rand() % dT + 1; 
					pID = Integer.parseInt(st.nextToken());
					bicycleNum = Integer.parseInt(st.nextToken());
					if (okay)
						usersolution.addBicycle(cTimestamp, pID, bicycleNum);
					break;
				case CMD_BUY:
					cTimestamp += pseudo_rand() % dT + 1; 
					String2Char(uName, st.nextToken());
					validTime = Integer.parseInt(st.nextToken());
					if (okay)
						usersolution.buyTicket(cTimestamp, uName, validTime);
					break;
				case CMD_RENT:
					cTimestamp += pseudo_rand() % dT + 1; 
					String2Char(uName, st.nextToken());
					pID = Integer.parseInt(st.nextToken());
					if (okay)
						res = usersolution.rentBicycle(cTimestamp, uName, pID);
					ans = Integer.parseInt(st.nextToken());
					if (res != ans)
						okay = false;
					break;
				case CMD_RETURN:
					cTimestamp += pseudo_rand() % dT + 1; 
					String2Char(uName, st.nextToken());
					pID = Integer.parseInt(st.nextToken());
					if (okay)
						res = usersolution.returnBicycle(cTimestamp, uName, pID);
					ans = Integer.parseInt(st.nextToken());
					if (res != ans)
						okay = false;
					break;
				}
			}
		}
		
		return okay;
	}

	public static void main(String[] args) throws Exception
	{
		int TC, MARK;

		//System.setIn(new java.io.FileInputStream("res/sample_input.txt"));
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		
		TC = Integer.parseInt(st.nextToken());
		MARK = Integer.parseInt(st.nextToken());

		for (int testcase = 1; testcase <= TC; ++testcase)
		{
            int score = run(br) ? MARK : 0;
            System.out.println("#" + testcase + " " + score);
		}

		br.close();
	}
}

