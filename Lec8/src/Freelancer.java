/**
 * @author minha
 * 2021. 11. 12.
 * [8강 1번] 프리랜서 
 * Work 클래스가 static이라서 오류 
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Comparator;
import java.util.StringTokenizer;

class Freelancer {
	
	static class Work{
		int start;
		int end;
		int cost;
		
		Work(int start, int end, int cost) {
			this.start = start;
			this.end = end;
			this.cost = cost;
		}
	}
	
	static int N, M;
	static Work[] work;
	static int[] dp;
	
	public static void main(String args[]) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();

		int T = Integer.parseInt(br.readLine());

		for(int test_case = 1; test_case <= T; test_case++) {
			st = new StringTokenizer(br.readLine());
			
			N = Integer.parseInt(st.nextToken());
			M = Integer.parseInt(st.nextToken());
			
			work = new Work[N];
			dp = new int[N];
			
			for(int i = 0; i < N; i++) {
				st = new StringTokenizer(br.readLine());
				
				int s = Integer.parseInt(st.nextToken());
				int e = Integer.parseInt(st.nextToken());
				int c = Integer.parseInt(st.nextToken());
				
				work[i] = new Work(s, e, c);
			}
			
			Arrays.sort(work, new Comparator<Work>() {
				@Override
				public int compare(Freelancer.Work o1, Freelancer.Work o2) {
					return o1.end - o2.end;
				}
			});
			
			dp[0] = work[0].cost;
			
			for(int i = 1; i < N; i++) {
				dp[i] = Math.max(dp[i-1], findShortWork(i) + work[i].cost);
			}
			
			sb.append("#" + test_case + " " + dp[N-1] + "\n");
		}

		System.out.println(sb.toString());
	}
	
	static int findShortWork(int index) {
		int shortIndex = -1;
		
		for(int i = 0; i < index; i++) {
			if(work[i].end < work[index].start) {
				shortIndex = i;
			}
		}
		
		// shortIndex가 -1이라면 만족하는 일정이 없는 경우
		return shortIndex >= 0 ? dp[shortIndex] : 0;
	}
	
}