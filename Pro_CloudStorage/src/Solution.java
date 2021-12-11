import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Solution {

    private final static int INIT = 100;
    private final static int ADD_DIRECTORY = 200;
    private final static int REMOVE_DIRECTORY = 300;
    private final static int MOVE_DIRECTORY = 400;
    private final static int RESTORE = 500;
    private final static int SYNC = 600;
    private final static int COUNT_DIRECTORY = 700;

    private static UserSolution usersolution = new UserSolution();

    private static boolean run(BufferedReader br) throws Exception {

        int query_num;
        int N = 0;
        boolean ok = false;
        int curTime, dev, pID, uID, prevTime, ans;

        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        query_num = Integer.parseInt(st.nextToken());

        for (int i = 0; i < query_num; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            int cmd = Integer.parseInt(st.nextToken());
            if (cmd == INIT) {
                N = Integer.parseInt(st.nextToken());
                usersolution.init(N);
                ok = true;
            } else if (cmd == ADD_DIRECTORY) {
                curTime = Integer.parseInt(st.nextToken());
                dev = Integer.parseInt(st.nextToken());
                pID = Integer.parseInt(st.nextToken());
                uID = Integer.parseInt(st.nextToken());
                usersolution.addDirectory(curTime, dev, pID, uID);
            } else if (cmd == REMOVE_DIRECTORY) {
                curTime = Integer.parseInt(st.nextToken());
                dev = Integer.parseInt(st.nextToken());
                uID = Integer.parseInt(st.nextToken());
                usersolution.removeDirectory(curTime, dev, uID);
            } else if (cmd == MOVE_DIRECTORY) {
                curTime = Integer.parseInt(st.nextToken());
                dev = Integer.parseInt(st.nextToken());
                pID = Integer.parseInt(st.nextToken());
                uID = Integer.parseInt(st.nextToken());
                usersolution.moveDirectory(curTime, dev, pID, uID);
            } else if (cmd == RESTORE) {
                curTime = Integer.parseInt(st.nextToken());
                dev = Integer.parseInt(st.nextToken());
                prevTime = Integer.parseInt(st.nextToken());
                usersolution.restore(curTime, dev, prevTime);
            } else if (cmd == SYNC) {
                curTime = Integer.parseInt(st.nextToken());
                dev = Integer.parseInt(st.nextToken());
                prevTime = Integer.parseInt(st.nextToken());
                usersolution.sync(curTime, dev, prevTime);
            } else if (cmd == COUNT_DIRECTORY) {
                dev = Integer.parseInt(st.nextToken());
                uID = Integer.parseInt(st.nextToken());
                int ret = 0;
                ret = usersolution.countDirectory(dev, uID);
                ans = Integer.parseInt(st.nextToken());
                if (ans != ret)
                    ok = false;
            }
        }
        return ok;
    }

    public static void main(String[] args) throws Exception {

        // System.setIn(new java.io.FileInputStream("res/sample_input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer line = new StringTokenizer(br.readLine(), " ");

        int TC = Integer.parseInt(line.nextToken());
        int MARK = Integer.parseInt(line.nextToken());

        for (int testcase = 1; testcase <= TC; ++testcase) {
            int score = run(br) ? MARK : 0;
            System.out.println("#" + testcase + " " + score);
        }
        br.close();
    }
}

