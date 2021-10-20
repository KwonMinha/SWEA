/**
 * @author minha
 * 2021. 10. 19.
 * [S/W 문제해결 기본] 1일차 - View
 * Math.max 이용 
 */

package View;

import java.util.Scanner;

class View2 {
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
				int max = Math.max(arr[i - 2], Math.max(arr[i - 1], Math.max(arr[i + 1], arr[i + 2])));
				
				if(arr[i] > max) {
					answer += arr[i] - max;
				}
			}
			sb.append("#" + test_case + " " + answer + "\n");
		}
		System.out.println(sb.toString());
	}
	
}