/**
 * @author minha
 * 2021. 11. 12.
 * [8강 1번] 프리랜서 
 * 이분탐색 - 풀이 완료 X 
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

class Freelancer3 {	
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
			
			Arrays.sort(work);
			
			dp[0] = work[0].cost;
			
			//binarySearch()
			
			for(int i = 1; i < N; i++) {
				//dp[i] = Math.max(dp[i-1],  + work[i].cost);
			}
			
			sb.append("#" + test_case + " " + dp[N-1] + "\n");
		}

		System.out.println(sb.toString());
	}
	
//	int binarySearch(int key, int low, int high) {
//		int mid;
//
//		while(low <= high) {
//			mid = (low + high) / 2;
//
//			if(key == arr[mid]) {
//				return mid;
//			} else if(key < arr[mid]) {
//				high = mid - 1;
//			} else {
//				low = mid + 1;
//			}
//		}
//
//		return -1; // 탐색 실패 
//	}
	
}