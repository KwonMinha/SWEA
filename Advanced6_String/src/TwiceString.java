/**
 * @author minha
 * 2021. 12. 9.
 * [6강 2번] 두 번 이상 등장하는 문자열
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;

class TwiceString {
	static int N;
	static String str;

	public static void main(String args[]) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();

		int T = Integer.parseInt(br.readLine());

		for(int test_case = 1; test_case <= T; test_case++) {
			N = Integer.parseInt(br.readLine());
			str = br.readLine();

			int result = parametricSearch(0, N);
			if(result <= 0) result = 0;

			sb.append("#" + test_case + " " +  result + "\n");
		}

		System.out.println(sb.toString());
	}

	static int parametricSearch(int low, int high) {
		if(low == high) 
			return low;

		while(low < high) {
			int mid = (low + high + 1) / 2; 

			if(isExist(mid)) { 
				low = mid;
			} else {
				high = mid - 1;
			}
		}

		return low; 
	}

	static boolean isExist(int len) {	
		ArrayList<Long> list = new ArrayList<>();
		final long MOD = (long) Math.pow(2, 64);
		final int D = 257; // or 259
		long power = 1;

		long hash = 0;

		for(int i = 0; i <= N - len; i++) {
			if(i == 0) {
				for(int j = 0; j < len; j++) {
					hash = (hash + (str.charAt(len - 1 - j)) * power) % MOD;

					if(j < len - 1) {
						power = (power * D) % MOD;
					}
				}
			} else {
				hash = ((hash - str.charAt(i-1) * power) * D + str.charAt(i + len - 1)) % MOD;
			}

			list.add(hash);
		}

		Collections.sort(list);

		for(int i = 1; i < list.size(); i++) {
			if(list.get(i-1).equals(list.get(i))) {
				return true;
			}
		}

		return false;
	}
}