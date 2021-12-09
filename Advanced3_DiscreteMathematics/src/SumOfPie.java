/**
 * @author minha
 * 2021. 12. 9.
 * [3강 3번] 파이의 합 
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class SumOfPie {
	static int N = 1000000;
	static boolean[] primeNum;
	static int[] euler;
	static long[] sum;
	
	public static void main(String args[]) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		primeNum = new boolean[N + 1];
		euler = new int[N + 1];
		sum = new long[N + 1];
		
		getPrimeNum();
		
		getEuler();
		
		getSum();

		int T = Integer.parseInt(br.readLine());

		for(int test_case = 1; test_case <= T; test_case++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			
			long ans = sum[b] - sum[a-1];

			sb.append("#" + test_case + " " + ans + "\n");
		}

		System.out.println(sb.toString());
	}
	
	static void getPrimeNum() {
		primeNum[1] = true;
		
		for(int i = 2; i * i <= N; i++) { // 에라토스테네스의 체 
			if(!primeNum[i]) {
				for(int j = 2; i * j <= N; j++) {
					primeNum[i * j] = true;
				}
			}
		}
	}
	
	static void getEuler() {
		for(int i = 1; i <= N; i++) {
			euler[i] = i;
		}
		
		for(int i = 2; i <= N; i++) {
			if(!primeNum[i]) {
				for(int j = 1; i * j <= N; j++) {
					euler[i * j] = (i - 1) * (euler[i * j] / i);
				}
			}
		}
	}
	
	static void getSum() {
		sum[0] = 0;
		sum[1] = 1;
		
		for(int i = 2; i <= N; i++) {
			sum[i] = sum[i-1] + euler[i];
		}
	}
	
}