/**
 * @author minha
 * 2021. 10. 19.
 * [S/W 문제해결 기본] 5일차 - Magnetic
 * while문 버전 
 */

package Magnetic;

import java.util.Scanner;

class Magnetic
{
	public static void main(String args[]) throws Exception
	{
		Scanner sc = new Scanner(System.in);

		for(int test_case = 1; test_case <= 10; test_case++)
		{
			int N = sc.nextInt();
			int[][] arr = new int[N][N];

			// 테이블 입력 
			for(int i = 0; i < N; i++) {
				for(int j = 0; j < N; j++) {
					arr[i][j] = sc.nextInt();
				}
			}

			int answer = 0;

			// 한 열씩 검사 
			for(int i = 0; i < N; i++) {
				int index = 0;

				while(index < N) {

					if(arr[index][i] == 1) { // 아래로 떨어지는 N극만 판별 
						index++;

						while(index < N) { // index 칸 N극 다음 극 판별 
							if(arr[index][i] == 1) { // 또 N극일 경우 
								break;
							} else if(arr[index][i] == 2) { // S극일 경우 
								answer++; // 교착상태 -> 정답 추가 
								break;
							}
							index++;
						}
					} else { // 빈칸이나 S극일 경우 패스 
						index++;
					}
				}
			}

			System.out.println("#" + test_case + " " + answer);	
		}
	}
}