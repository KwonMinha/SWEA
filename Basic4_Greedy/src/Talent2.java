/**
 * @author minha
 * 2021. 10. 21.
 * 1265. [S/W 문제해결 응용] 9일차 - 달란트2
 * N개를 묶음P로 나눈 각 묶음의 숫자의 차이가 작아야 곱했을 때 값이 크다 
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

class Talent2 {
	public static void main(String args[]) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();

		int T = Integer.parseInt(br.readLine());

		for(int test_case = 1; test_case <= T; test_case++) {
			st = new StringTokenizer(br.readLine());

		int N = Integer.parseInt(st.nextToken());
			int P = Integer.parseInt(st.nextToken());
			
			int[] arr = new int[P];
			Arrays.fill(arr, N/P);
			
			int count = N % P;
			for(int i = 0; i < arr.length; i++) {
				if(count != 0) {
					arr[i] += 1;
					count--;
				} else {
					break;
				}
			}
			
			long answer = 1;
			for(long i : arr) {
				answer *= i;
			}

			sb.append("#" + test_case + " " + answer + "\n");
		}

		System.out.println(sb.toString());
	}
	
}