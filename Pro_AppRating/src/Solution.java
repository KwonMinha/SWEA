import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Solution {	
	final private static int CMD_INIT = 100;
	final private static int CMD_ADDRATING = 200;
	final private static int CMD_DELETERATING = 300;
	final private static int CMD_BANUSER = 400;
	final private static int CMD_SORTBYSCORE = 500;
	final private static int CMD_SORTBYNUMBER = 600;
	
	final private static int MAXL = 16;
	private static char applist[][] = new char[10000][MAXL];
	private static char anslist[][] = new char[10000][MAXL];
	
	private static BufferedReader br;
	private static UserSolution usersolution = new UserSolution();
	
	private static int mSeed;

	private static int pseudoRand()
	{
		mSeed = (mSeed * 214013 + 2531011) & 0xFFFFFFFF;
		return (mSeed >> 16) & 0x7FFF;
	}
	
	private static void makeApp(int num)
	{
		for (int i = 0; i < num; i++) {
			int len = 5 + pseudoRand() % 11;
			for (int j = 0; j < len; j++) {
				applist[i][j] = (char) ('A' + pseudoRand() % 26);
			}
			applist[i][len] = 0;
		}
	}

    private static int mstrcmp(char str1[], char str2[])
    {
        int c = 0;
        while (str1[c] != 0 && str1[c] == str2[c]) ++c;
        return str1[c] - str2[c];
    }
	
	private static void mstrcpy(char dest[], char src[])
	{
		int i = 0;
		while (src[i] != '\0') { dest[i] = src[i]; i++; }
		dest[i] = src[i];
	}
	
	private static boolean run() throws IOException
	{
		boolean ret = false;
		int query_cnt, cmd;
		int appNum;
		int mUser, mApp, mScore;
		UserSolution.RESULT user;
		int ans;


		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		
		query_cnt = Integer.parseInt(st.nextToken());
		mSeed = Integer.parseInt(st.nextToken());
		appNum = Integer.parseInt(st.nextToken());
		makeApp(appNum);
		for (int i = 0; i < appNum; i++)
			mstrcpy(anslist[i], applist[i]);

		for (int q = 0; q < query_cnt; ++q)
		{
			st = new StringTokenizer(br.readLine(), " ");
			cmd = Integer.parseInt(st.nextToken());
			switch (cmd) {
			case CMD_INIT:
				usersolution.init(appNum, applist);
				ret = true;
				break;
			case CMD_ADDRATING:
				mUser = Integer.parseInt(st.nextToken());
				mApp = Integer.parseInt(st.nextToken());
				mScore = Integer.parseInt(st.nextToken());
				usersolution.addRating(mUser, applist[mApp], mScore);
				break;
			case CMD_DELETERATING:
				mUser = Integer.parseInt(st.nextToken());
				mApp = Integer.parseInt(st.nextToken());
				usersolution.deleteRating(mUser, applist[mApp]);
				break;
			case CMD_BANUSER:
				mUser = Integer.parseInt(st.nextToken());
				usersolution.banUser(mUser);
				break;
			case CMD_SORTBYSCORE:
				user = usersolution.sortByScore();
				for (int i = 0; i < 5; i++) {
					ans = Integer.parseInt(st.nextToken());
					if (mstrcmp(user.mApp[i], anslist[ans]) != 0)
						ret = false;
				}
				break;
			case CMD_SORTBYNUMBER:
				user = usersolution.sortByNumber();
				for (int i = 0; i < 5; i++) {
					ans = Integer.parseInt(st.nextToken());
					if (mstrcmp(user.mApp[i], anslist[ans]) != 0)
						ret = false;
				}
				break;
			default:
				ret = false;
				break;
			}
		}

		return ret;
	}
	
	public static void main(String[] args) throws Exception
	{
		int T, MARK;

		//System.setIn(new java.io.FileInputStream("res/sample_input.txt"));
		br = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer stinit = new StringTokenizer(br.readLine(), " ");
		T = Integer.parseInt(stinit.nextToken());
		MARK = Integer.parseInt(stinit.nextToken());
		
		for (int tc = 1; tc <= T; tc++)
		{
			int score = run() ? MARK : 0;
			System.out.println("#" + tc + " " + score);
		}
		
		br.close();
	}
}