/**
 * @author minha
 * 2021. 10. 19.
 * [S/W 문제해결 응용] 2일차 - 최대 상금
 * 완전탐색1 
 */

package MaxReward;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class MaxReward3 {
	static int[] arr;
	static int answer;
	
	public static void main(String args[]) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int T = Integer.parseInt(br.readLine());
		
		for(int test_case = 1; test_case <= T; test_case++)
		{
			StringTokenizer st = new StringTokenizer(br.readLine());
			
			String num = st.nextToken();
			int count = Integer.parseInt(st.nextToken());
			
			arr = new int[num.length()];
			answer = 0;
			
			for(int i = 0; i < num.length(); i++) {
				arr[i] = num.charAt(i) - '0';
			}
			
			// 최대 교환 횟수는 총 숫자를 넘지 않도록 
			if(count > arr.length) {
				count = arr.length;
			}
			
			dfs(0, count);
			
			System.out.println("#" + test_case + " " + answer);	
		}
	}
	
	public static void dfs(int start, int depth) {
		if(depth == 0) {
			calculate();
			return;
		}
		
		for(int i = start; i < arr.length-1; i++) {
			for(int j = i+1; j < arr.length; j++) {
					int temp = arr[i];
					arr[i] = arr[j];
					arr[j] = temp;
					
					dfs(i, depth-1);
					
					temp = arr[i];
					arr[i] = arr[j];
					arr[j] = temp;
			}
		}
	}
	
	public static void calculate() {
		String num = "";
		
		for(int i = 0; i < arr.length; i++) {
			num += String.valueOf(arr[i]);
		}
		
		answer = Math.max(answer, Integer.parseInt(num));
	}
}