/**
 * @author minha
 * 2021. 12. 6.
 * [1강 1번] 그래도 수명이 절반이 되어서는...
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class HalfLife {
	static int[] W;
	static int[] S;

	public static void main(String args[]) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();

		int T = Integer.parseInt(br.readLine());

		for(int test_case = 1; test_case <= T; test_case++) {
			StringTokenizer st = new StringTokenizer(br.readLine());

			int N = Integer.parseInt(st.nextToken());
			int K = Integer.parseInt(st.nextToken());

			W = new int[N];
			S = new int[K];

			int max = 0;
			int min = 200000;

			st = new StringTokenizer(br.readLine());
			for(int i = 0; i < N; i++) {
				W[i] = Integer.parseInt(st.nextToken());

				max = Math.max(max, W[i]);
				min = Math.min(min, W[i]);
			}

			st = new StringTokenizer(br.readLine());
			for(int i = 0; i < K; i++) {
				S[i] = Integer.parseInt(st.nextToken());
			}

			sb.append("#" + test_case + " " + parametricSearch(min, max) + "\n");
		}

		System.out.println(sb.toString());
	}

	public static int parametricSearch(int low, int high) {
		if(low == high) 
			return low;

		while(low < high) {
			int mid = (low + high) / 2; 

			if(isSave(mid)) { 
				high = mid;
			} else {
				low = mid+1; 
			}
		}

		return low; 
	}

	public static boolean isSave(int mid) {
		int index = 0;
		
		for(int i = 0; i < W.length; i++) {
			if(index == S.length) return true;

			if(W[i] <= mid) {
				int count = S[index];
				boolean flag = true;
				int j = i;
				int size = i + count;
				
				while(j < size && j < W.length) {
					if(W[j] > mid) {
						flag = false;
						break;
					}

					count--;
					j++;
				}

				if(flag && count == 0) index++;

				i = j-1;
			}
		}
		
		return index == S.length ? true : false;
	}

}