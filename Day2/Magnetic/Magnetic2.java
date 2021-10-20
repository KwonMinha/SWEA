/**
 * @author minha
 * 2021. 10. 19.
 * [S/W 문제해결 기본] 5일차 - Magnetic
 * for문 버전 
 */

package Magnetic;

import java.util.Scanner;

class Magnetic2
{
	public static void main(String args[]) throws Exception
	{
		Scanner sc = new Scanner(System.in);

		for(int test_case = 1; test_case <= 10; test_case++)
		{
			int N = sc.nextInt();
			
			int[][] arr = new int[N][N];

			for(int i = 0; i < N; i++) {
				for(int j = 0; j < N; j++) {
					arr[i][j] = sc.nextInt();
				}
			}

			int answer = 0;

			for(int i = 0; i < N; i++) {
				
				for(int j = 0; j < N; j++) {
					
					if(arr[j][i] == 1) {
						for(int k = j+1; k < N; k++) {
							if(arr[k][i] == 1) {
								j = k-1;
								break;
							} else if(arr[k][i] == 2) {
								answer++;
								j = k;
								break;
							}
						}
					}
				}
			}

			System.out.println("#" + test_case + " " + answer);	
		}
	}
}