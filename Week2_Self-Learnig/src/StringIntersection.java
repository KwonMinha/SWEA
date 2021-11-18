/**
 * @author minha
 * 2021. 11. 05.
 * 2948. 문자열 교집합
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.StringTokenizer;

public class StringIntersection {
	
	public static void main(String args[]) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;

		int T = Integer.parseInt(br.readLine());

		for(int test_case = 1; test_case <= T; test_case++) {
			st = new StringTokenizer(br.readLine());
			
			int N = Integer.parseInt(st.nextToken()); 
			int M = Integer.parseInt(st.nextToken()); 
			
			HashSet<String> set = new HashSet<>();
			int result = 0;
			
			// 첫 번째 집합 
			st = new StringTokenizer(br.readLine());
			for(int i = 0; i < N; i++) {
				set.add(st.nextToken());
			}
			
			// 두 번째 집합 
			st = new StringTokenizer(br.readLine());
			for(int i = 0; i < M; i++) {
				if(set.contains(st.nextToken())) {
					result++;
				}
			}
			
			sb.append("#" + test_case + " " + result + "\n");
		}

		System.out.println(sb.toString());
	}
}