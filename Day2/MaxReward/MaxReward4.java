/**
 * @author minha
 * 2021. 10. 19.
 * [S/W 문제해결 응용] 2일차 - 최대 상금
 * 완전탐색 최적화 버전 
 */

package MaxReward;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class MaxReward4 {
	static char[] arr;
	static int answer;
	
	public static void main(String args[]) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		int T = Integer.parseInt(br.readLine());
		
		for(int test_case = 1; test_case <= T; test_case++)
		{
			StringTokenizer st = new StringTokenizer(br.readLine());
			
			int num = Integer.parseInt(st.nextToken());
			int count = Integer.parseInt(st.nextToken());
			
			arr = Integer.toString(num).toCharArray();
			answer = 0;
			
			if(count > arr.length) {
				count = arr.length;
			}
			
			dfs(count, 0);
			
			sb.append("#" + test_case + " " + answer + "\n");
		}
		
		System.out.println(sb.toString());
	}
	
	public static void dfs(int count, int start) {
		if(count == 0) {
			int sum = charArrToInt(arr);
			if(sum > answer) {
				answer = sum;
			}

			return;
		}
		
		for(int i = start; i < arr.length-1; i++) {
			for(int j = i+1; j < arr.length; j++) {
				char temp = arr[i];
				arr[i] = arr[j];
				arr[j] = temp;
				
				dfs(count-1, i);
				
				temp = arr[i];
				arr[i] = arr[j];
				arr[j] = temp;
				
			}
		}
	}
	
	public static int charArrToInt(char[] numbers) {
		return Integer.parseInt(new String(numbers));
	}
}