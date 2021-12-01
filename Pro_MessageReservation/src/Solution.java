import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Solution
{
	private final static int CMD_INIT				= 100;
	private final static int CMD_SEND_MESSAGE		= 200;
	private final static int CMD_RETRIEVE_SENTBOX	= 300;
	private final static int CMD_RETRIEVE_INBOX		= 400;
	private final static int CMD_READ_MESSAGE		= 500;
	private final static int CMD_DELETE_MESSAGE		= 600;
	
	private final static int MAXM					= 3;
	
	private final static UserSolution usersolution = new UserSolution();
		
	private static boolean run(BufferedReader br) throws Exception
	{
		StringTokenizer st;
		
		int Q, N;
		int cTimestamp, scheduleTimestamp;
		int uID, uID1, uID2, mID;
		int ret = -1, ans;
		int mIDList[] = new int[MAXM];
		int uIDList[] = new int[MAXM];
		int readList[] = new int[MAXM];
		int mIDList_a[] = new int[MAXM];
		int uIDList_a[] = new int[MAXM];
		int readList_a[] = new int[MAXM];
		
		boolean okay = false;
		
		Q = Integer.parseInt(br.readLine());
		
		for (int q = 0; q <= Q; ++q)
		{
			int op;			
			st = new StringTokenizer(br.readLine(), " ");
			op = Integer.parseInt(st.nextToken());

			switch(op)
			{
			case CMD_INIT:
				N = Integer.parseInt(st.nextToken());
				usersolution.init(N);
				okay = true;
				break;
			case CMD_SEND_MESSAGE:
				cTimestamp = Integer.parseInt(st.nextToken());
				uID1 = Integer.parseInt(st.nextToken());
				uID2 = Integer.parseInt(st.nextToken());
				mID = Integer.parseInt(st.nextToken());
				scheduleTimestamp = Integer.parseInt(st.nextToken());
				if (okay)
					usersolution.sendMessage(cTimestamp, uID1, uID2, mID, scheduleTimestamp);
				break;
			case CMD_RETRIEVE_SENTBOX:
				cTimestamp = Integer.parseInt(st.nextToken());
				uID = Integer.parseInt(st.nextToken());
				if (okay)
					ret = usersolution.retrieveSentbox(cTimestamp, uID, mIDList, uIDList, readList);
				ans = Integer.parseInt(st.nextToken());
				for (int i = 0; i < ans; ++i)
				{
					mIDList_a[i] = Integer.parseInt(st.nextToken());
					uIDList_a[i] = Integer.parseInt(st.nextToken());
					readList_a[i] = Integer.parseInt(st.nextToken());
				}
				if (ret != ans)
				{
					okay = false;
				}
				else
				{
					for (int i = 0; i < ans; ++i)
						if (mIDList[i] != mIDList_a[i]
								|| uIDList[i] != uIDList_a[i] || readList[i] != readList_a[i])
							okay = false;
				}
				break;
			case CMD_RETRIEVE_INBOX:
				cTimestamp = Integer.parseInt(st.nextToken());
				uID = Integer.parseInt(st.nextToken());
				if (okay)
					ret = usersolution.retrieveInbox(cTimestamp, uID, mIDList, uIDList, readList);
				ans = Integer.parseInt(st.nextToken());
				for (int i = 0; i < ans; ++i)
				{
					mIDList_a[i] = Integer.parseInt(st.nextToken());
					uIDList_a[i] = Integer.parseInt(st.nextToken());
					readList_a[i] = Integer.parseInt(st.nextToken());
				}
				if (ret != ans)
				{
					okay = false;
				}
				else
				{
					for (int i = 0; i < ans; ++i)
						if (mIDList[i] != mIDList_a[i]
								|| uIDList[i] != uIDList_a[i] || readList[i] != readList_a[i])
							okay = false;
				}
				break;
			case CMD_READ_MESSAGE:
				cTimestamp = Integer.parseInt(st.nextToken());
				uID = Integer.parseInt(st.nextToken());
				mID = Integer.parseInt(st.nextToken());
				if (okay)
					ret = usersolution.readMessage(cTimestamp, uID, mID);
				ans = Integer.parseInt(st.nextToken());
				if (ret != ans)
					okay = false;
				break;
			case CMD_DELETE_MESSAGE:
				cTimestamp = Integer.parseInt(st.nextToken());
				uID = Integer.parseInt(st.nextToken());
				mID = Integer.parseInt(st.nextToken());
				if (okay)
					ret = usersolution.deleteMessage(cTimestamp, uID, mID);
				ans = Integer.parseInt(st.nextToken());
				if (ret != ans)
					okay = false;
				break;
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

