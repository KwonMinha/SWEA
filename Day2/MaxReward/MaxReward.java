/**
 * @author minha
 * 2021. 10. 19.
 * [S/W 문제해결 응용] 2일차 - 최대 상금
 * 최적화 주석 버전 
 */

package MaxReward;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class MaxReward
{
	static int[] numArr;
	static int[] sumArr;
	static int[] numCount;
	static boolean hasSameNum;
	static int answer;
	static int maxSum;
	static int swapCount;
	static int totalCount;
	
	public static void main(String args[]) throws Exception
	{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		int T = Integer.parseInt(br.readLine());
		
		for(int test_case = 1; test_case <= T; test_case++)
		{
			StringTokenizer st = new StringTokenizer(br.readLine());
			
			String num = st.nextToken();
			int count = Integer.parseInt(st.nextToken());
			
			numArr = new int[num.length()];
			sumArr = new int[num.length()];
			numCount = new int[10];
			hasSameNum = false;
			
			answer = 0;
			maxSum = 0;
			swapCount = 0;
			
			totalCount = count;
			
			for(int i = 0; i < num.length(); i++) {
				numArr[i] = num.charAt(i) - '0';
				
				// 중복되는 숫자가 있는지 판별 
				numCount[numArr[i]]++;
				if(numCount[numArr[i]] >= 2) 
					hasSameNum = true;
			}
			
			dfs(0, 0);
			
			if(swapCount < totalCount) { // 주어진 횟수만큼 Swap하지 못한 경우 
				if(hasSameNum) { // 중복되는 숫자가 있다면 남은 교환 횟수가 얼마인지 상관없이 최대값 반환
					answer = maxSum;
				} else {
					// 남은 교환 횟수가 짝수인지 홀수인지 구별
					if((totalCount - swapCount) % 2 == 0) { // 짝수라면 최대값 반환 - 교환해봤자 그대로 
						answer = maxSum;
					} else { // 홀수라면 마지막 두 자리의 수를 교환 후 반환 - 가장 작은 자리 숫자 2개를 1번만 Swap
						swap(sumArr, sumArr.length-2, sumArr.length-1);
						
						answer = calculate(sumArr);
					}
				}
			}
			
			sb.append("#" + test_case + " " + answer).append("\n");
		}
		
		System.out.println(sb.toString());
	}
	
	public static void dfs(int start, int count) {
		// 현재 시점의 숫자 합 
		int currentSum = calculate(numArr);
		
		if(maxSum < currentSum) { // 지금까지 구한 최대값보다 큰 경우 값과 그때의 swapCount 갱신 
			maxSum = currentSum;
			swapCount = count;
			
			for(int i = 0; i < numArr.length; i++) // maxSum의 각 자리수별 값을 sumArr에 저장(swap 위해)
				sumArr[i] = numArr[i];
		}
		
		if(count == totalCount) { // 모든 횟수를 소진하며 swap한 경우 -> 정답인지 계산하고 return 
			answer = Math.max(answer, calculate(numArr));
			return;
		}
		
		// 현재 시점 start부터의 최대값 찾기 
		int max = 0;
		for(int i = start; i < numArr.length; i++) {
			if(numArr[i] > max) {
				max = numArr[i];
			}
		}
		
		// 최대값과 start값 swap -> 최대값을 만들기 위해서 교환이 몇번 이루어지는지 count를 통해 판별 
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
		String num = "";
		
		for(int i = 0; i < arr.length; i++) {
			num += String.valueOf(arr[i]);
		}
		
		return Integer.parseInt(num);
		
//		int sum = 0;
//	    for (int i = 0; i < arr.length; i++) {
//	    	sum += arr[i];
//	    	sum *= 10;
//	    }
//	    
//	    sum /= 10;
//	 
//	    return sum;
	}
	
}