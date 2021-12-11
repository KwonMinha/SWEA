import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Solution {
    private static UserSolution usersolution = new UserSolution();
    private static BufferedReader br;
    private static StringTokenizer line;
    private final static int NEW_CLASS				= 1;
    private final static int NEW_RECORD				= 2;
    private final static int CHANGE_RECORD 			= 3;
    private final static int CHECK_ATTENDANCE		= 4;
    private final static int CHECK_CHEATING 		= 5;

    private static void stringToArray(char dst[], String s){
        for(int i=0;i<s.length();i++) dst[i] = s.charAt(i);	
        dst[s.length()] = 0;
    }

    private static int run (int Result) throws Exception 
    {

        StringTokenizer line = new StringTokenizer(br.readLine(), " ");
        int N;
        N = Integer.parseInt(line.nextToken());
        usersolution.init();

        for (int i = 0; i < N; ++i) {

            line = new StringTokenizer(br.readLine(), " ");
            int mClassId, mRatio, mRecordId;
            char mClassBegin[] = new char[64], mClassEnd[] = new char[64], mRecordBegin[] = new char[64], mRecordEnd[] = new char[64];
            char mNewBegin[] = new char[64], mNewEnd[] = new char[64], mDate[] = new char[64];
            int res, ans;

            int cmd = Integer.parseInt(line.nextToken());

            switch (cmd) {
                case NEW_CLASS:
                    mClassId = Integer.parseInt(line.nextToken());
                    stringToArray(mClassBegin, line.nextToken());
                    stringToArray(mClassEnd, line.nextToken());
                    mRatio = Integer.parseInt(line.nextToken());
                    usersolution.newClass(mClassId, mClassBegin, mClassEnd, mRatio);
                    break;

                case NEW_RECORD:
                    mRecordId = Integer.parseInt(line.nextToken());
                    mClassId = Integer.parseInt(line.nextToken());
                    stringToArray(mRecordBegin, line.nextToken());
                    stringToArray(mRecordEnd, line.nextToken());
                    usersolution.newRecord(mRecordId, mClassId, mRecordBegin, mRecordEnd);
                    break;

                case CHANGE_RECORD:
                    mRecordId = Integer.parseInt(line.nextToken());
                    stringToArray(mNewBegin, line.nextToken());
                    stringToArray(mNewEnd, line.nextToken());
                    usersolution.changeRecord(mRecordId, mNewBegin, mNewEnd);
                    break;

                case CHECK_ATTENDANCE:
                    mClassId = Integer.parseInt(line.nextToken());
                    stringToArray(mDate, line.nextToken());
                    res = usersolution.checkAttendance(mClassId, mDate);
                    ans = Integer.parseInt(line.nextToken());
                    if ( res != ans ) {
                        Result = 0;
                    }
                    break;

                case CHECK_CHEATING:
                    res = usersolution.checkCheating();
                    ans = Integer.parseInt(line.nextToken());
                    if ( res != ans ) {
                        Result = 0;
                    }
                    break;
            }
        }

        usersolution.destroy();

        return Result;
    }
    
    public static void main(String[] args) throws Exception {

        //System.setIn(new java.io.FileInputStream("res/sample_input.txt"));

        br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer line = new StringTokenizer(br.readLine(), " ");

        int TC = Integer.parseInt(line.nextToken());
        int Ans = Integer.parseInt(line.nextToken());
        for (int testcase = 1; testcase <= TC; ++testcase) {
            System.out.println("#" + testcase + " " + run(Ans));
        }
        
    }
}