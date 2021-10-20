/**
 * @author minha
 * 2021. 10. 20.
 * [S/W 문제해결 기본] 3일차 - 회문1
 * break문 삭제 - 삭제하나 안하나 비슷한듯 
 */

package Palindrome;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

class Palindrome2 {
	public static void main(String args[]) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		int T = 10;
		
		for(int test_case = 1; test_case <= T; test_case++) {
			int len = Integer.parseInt(br.readLine());
			
			char[][] arr = new char[8][8];
			
			for(int i = 0; i < 8; i++) {
				String str = br.readLine();
				for(int j = 0; j < 8; j++) {
					arr[i][j] = str.charAt(j);
				}	
			}
			
			int answer = 0;
			
			for(int i = 0; i < arr.length; i++) {
				for(int j = 0; j < arr.length-len+1; j++) {
					// 가로
					boolean flag = true;
					for(int k = 0; k < len/2; k++)
						if(arr[i][j+k] != arr[i][j+len-k-1])
							flag = false;
					if(flag) answer++;
					
					// 세로 
					flag = true;
					for(int k = 0; k < len/2; k++)
						if(arr[j+k][i] != arr[j+len-k-1][i])
							flag = false;
					if(flag) answer++;
				}
			}
			
			sb.append("#" + test_case + " " + answer + "\n");
		}
		
		System.out.println(sb.toString());
	}
}