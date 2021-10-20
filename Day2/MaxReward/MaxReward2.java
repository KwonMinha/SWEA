/**
 * @author minha
 * 2021. 10. 19.
 * [S/W 문제해결 응용] 2일차 - 최대 상금
 * 최적화 주석없는 버전 
 */

package MaxReward;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
 
class MaxReward2 {
    static int[] numArr;
    static int[] sumArr;
    static int maxSum;
    static int swapCount;
    static int totalCount;
    static int[] numCount;
    static boolean hasSameNum;
    static int answer;
 
    public static void main(String args[]) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
 
        int T = Integer.parseInt(br.readLine());
 
        for(int test_case = 1; test_case <= T; test_case++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
 
            String num = st.nextToken();
            totalCount = Integer.parseInt(st.nextToken());
 
            numArr = new int[num.length()];
            sumArr = new int[num.length()];
            numCount = new int[10];
            hasSameNum = false;
            answer = 0;
            maxSum = 0;
            swapCount = 0;
 
            for(int i = 0; i < num.length(); i++) {
                numArr[i] = num.charAt(i) - '0';
 
                numCount[numArr[i]]++;
                if(numCount[numArr[i]] >= 2) 
                    hasSameNum = true;
            }
 
            dfs(0, 0);
 
            if(swapCount < totalCount) { 
                if(hasSameNum) { 
                    answer = maxSum;
                } else {
                    if((totalCount - swapCount) % 2 == 0) {
                        answer = maxSum;
                    } else {
                        swap(sumArr, sumArr.length-2, sumArr.length-1);
                        answer = calculate(sumArr);
                    }
                }
            }
 
            sb.append("#" + test_case + " " + answer + "\n");
        }
 
        System.out.println(sb.toString());
    }
 
    static void dfs(int start, int count) {
        int currentSum = calculate(numArr);
 
        if(maxSum < currentSum) {
            maxSum = currentSum;
            swapCount = count;
 
            for(int i = 0; i < numArr.length; i++)
                sumArr[i] = numArr[i];
        }
 
        if(count == totalCount) {
            answer = Math.max(answer, calculate(numArr));
            return;
        }
 
        int max = 0;
        for(int i = start; i < numArr.length; i++) {
            if(numArr[i] > max) {
                max = numArr[i];
            }
        }
 
        for(int i = start; i < numArr.length; i++) {
            if(numArr[i] == max) {
                if(i == start) {
                    dfs(start+1, count);
                } else {
                    swap(numArr, i, start);
                    dfs(start+1, count+1);
                    swap(numArr, i, start);
                }
            }
        }
    }
 
    static void swap(int[] arr, int a, int b) {
        int temp = arr[a];
        arr[a] = arr[b];
        arr[b] = temp;
    }
 
    static int calculate(int[] arr) {       
        int sum = 0;
        for (int i = 0; i < arr.length; i++) {
            sum += arr[i];
            sum *= 10;
        }
 
        sum /= 10;
 
        return sum;
    }
 
}