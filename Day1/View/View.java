/**
 * @author minha
 * 2021. 10. 19.
 * [S/W 문제해결 기본] 1일차 - View
 * 양쪽 모두 거리 2 이상의 공간이 확보될 때 조망권이 확보
 */

package View;

import java.util.Scanner;

class View {
	static int[] arr;
	
	public static void main(String args[]) throws Exception {
		Scanner sc = new Scanner(System.in);
		StringBuilder sb = new StringBuilder();

		for(int test_case = 1; test_case <= 10; test_case++) {
			int N = sc.nextInt();
			
			arr = new int[N];
			for(int i = 0; i < N; i++) {
				arr[i] = sc.nextInt();
			}
			
			int answer = 0;
			
			for(int i = 2; i < N-2; i++) {
				int max = getMax(i);
				
				if(arr[i] > max) {
					answer += arr[i] - max;
				}
			}
			
			sb.append("#" + test_case + " " + answer + "\n");
		}
		
		System.out.println(sb.toString());
	}
	
	static int getMax(int index) {
		int max = arr[index - 2];
		
		if(max < arr[index - 1])
			max = arr[index - 1];
		
		if(max < arr[index + 1])
			max = arr[index + 1];
		
		if(max < arr[index + 2])
			max = arr[index + 2];
		
		return max;
	}
}