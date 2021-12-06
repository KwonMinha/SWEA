/**
 * @author minha
 * 2021. 12. 7.
 * [1강 4번] 최대 부분 배열
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

class MaxSubArray {
	
	public static void main(String args[]) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int T = Integer.parseInt(br.readLine());

		for(int test_case = 1; test_case <= T; test_case++) {
			int N = Integer.parseInt(br.readLine());
			
			int[] arr = new int[N];
			for(int i = 0; i < N; i++)
				arr[i] = Integer.parseInt(br.readLine());
			
			int max = Integer.MIN_VALUE; // 전체 마지막까지의 최대합 
			int maxSum = 0; // 각 단계에서의 부분 합 
			
			for(int i = -1; i < N-1; i++) {
				maxSum = Math.max(maxSum + arr[i+1], arr[i+1]);
				max = Math.max(max, maxSum);
			}
			
			System.out.println("#" + test_case + " " + max );
		}
	}
	
}