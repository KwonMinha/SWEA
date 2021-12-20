import java.util.Scanner;

class Solution {	
	private final static int MEMORYSIZE 		= 65536;
	
	private final static char[] mem = new char[MEMORYSIZE];
	
	private final static int CMD_PUT			= 100;
	private final static int CMD_DEL			= 200;
	private final static int CMD_GET			= 300;
	private final static int CMD_GETKEY			= 400;
	
	private final static int MAXLEN				= 12;
	
	private final static UserSolution usersolution = new UserSolution();
	
	public static boolean memread(char[] dest, int pos, int len) {
		if (pos < 0 || len <= 0 || pos + len > MEMORYSIZE)
			return false;
		
		for (int i = 0; i < len; ++i)
			dest[i] = mem[i + pos];

		return true;
	}


	public static boolean memwrite(char[] src, int pos, int len) {
		if (pos < 0 || len <= 0 || pos + len > MEMORYSIZE)
			return false;

		for (int i = 0; i < len; ++i)
			mem[i + pos] = (char)(src[i] & 0xff);

		return true;
	}
	
	private static boolean ztrcmp(char[] a, char[] b) {
		int p = 0;
		
		while(a[p] != '\0' && a[p] == b[p])
			++p;
		
		return a[p] == b[p];
	}
	
	private static void String2Char(String s, char[] b) {
		int n = s.length();
		for (int i = 0; i < n; ++i)
			b[i] = s.charAt(i);
		b[n] = '\0';
	}
	
	private static boolean run(Scanner sc) {
		int Q = sc.nextInt();
		int N = sc.nextInt();
		
		boolean okay = usersolution.init(N);
		
		char[] key = new char[MAXLEN + 1];
		char[] value = new char[MAXLEN + 1];
		char[] key_u = new char[MAXLEN + 1];
		char[] value_u = new char[MAXLEN + 1];
		
		for (int d = 0; d < Q; ++d) {
			int cmd = sc.nextInt();
			switch(cmd) {
			case CMD_PUT:
				String2Char(sc.next(), key);
				String2Char(sc.next(), value);
				if (okay) usersolution.put(key, value);
				break;
			case CMD_DEL:
				String2Char(sc.next(), key);
				if (okay) usersolution.del(key);
				break;
			case CMD_GET:
				String2Char(sc.next(), key);
				String2Char(sc.next(), value);
				if (okay) {
					usersolution.get(key, value_u);
					if (ztrcmp(value, value_u) == false)
						okay = false;
				}
				break;
			case CMD_GETKEY:
				String2Char(sc.next(), value);
				String2Char(sc.next(), key);
				if (okay) {
					usersolution.getkey(value,  key_u);
					if (ztrcmp(key, key_u) == false)
						okay = false;
				}
				break;
			default:
				break;
			}
		}
		
		return okay;
	}
	
	public static void main(String[] args) throws Exception {
		int TC;
	
		//System.setIn(new java.io.FileInputStream("res/eval_input.txt"));
		Scanner sc = new Scanner(System.in);
		TC = sc.nextInt();
        
        int totalscore = 0;
		for (int testcase = 1; testcase <= TC; ++testcase) {
			int score = run(sc) ? 1000 : 0;
            System.out.println("#" + testcase + " " + score);
            totalscore += score;
		}

        System.out.println("total score = " + totalscore / 10 / TC);
		sc.close();
	}
}

