/**
 * @author minha
 * 2021. 12. 6.
 * [1강 2번] 평등주의
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Equalitarianism {
	static int N, K;
	static int[] A, B;
	
	public static void main(String args[]) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();

		int T = Integer.parseInt(br.readLine());

		for(int test_case = 1; test_case <= T; test_case++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			K = Integer.parseInt(st.nextToken());
			
			A = new int[N];
			B = new int[N];
			
			st = new StringTokenizer(br.readLine());
			for(int i = 0; i < N; i++) 
				A[i] = Integer.parseInt(st.nextToken());
			
			System.out.println("#" + test_case + " " + parametricSearch(0, 1000000000, 0));
		}
	}
	
	static int parametricSearch(int low, int high, int ans) {
		while(low <= high) {
				int mid = (low + high) / 2; 

				if(isDecrease(mid)) { 
					ans = mid;
					high = mid - 1;
				} else {
					low = mid + 1;
				}
			}

			return ans; 
		}
	
	static boolean isDecrease(int mid) {
		B[0] = A[0];
		
		for(int i = 1; i < N; i++)
			B[i] = Math.min(B[i-1] + mid, A[i]);
		
		for(int i = N-2; i >= 0; i--)
			B[i] = Math.min(B[i+1] + mid, B[i]);
	
//		long count = 0;
//		for(int i = 0; i < N; i++)
//			count += A[i] - B[i];
//		
//		return count <= K ? true : false;
		
		long count = K;
		for(int i = 0; i < N; i++)
			count -= A[i] - B[i];
		
		return count >= 0 ? true : false;

	}
}