/**
 * @author minha
 * 2021. 10. 19.
 * [S/W 문제해결 기본] 5일차 - Magnetic
 * for문 버전 flag 버전 
 */

package Magnetic;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Magnetic4 {
	public static void main(String args[]) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();

		for(int test_case = 1; test_case <= 10; test_case++) {
			int N = Integer.parseInt(br.readLine());
			int[][] arr = new int[N][N];
			
			for(int i = 0; i < N; i++) {
				st = new StringTokenizer(br.readLine());
				for(int j = 0; j < N; j++) {
					arr[i][j] = Integer.parseInt(st.nextToken());
				}
			}

			int answer = 0;
			
			for(int i = 0; i < N; i++) {
				boolean flag = false;
				
				for(int j = 0; j < N; j++) {
					if(arr[j][i] == 1) {
						flag = true;
					} else if(arr[j][i] == 2 && flag) {
						answer++;
						flag = false;
					}
				}
			}

			sb.append("#" + test_case + " " + answer + "\n");
		}
		
		System.out.println(sb.toString());
	}
}