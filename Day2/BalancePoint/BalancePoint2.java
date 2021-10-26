/**
 * @author minha
 * 2021. 10. 27.
 * 1245. [S/W 문제해결 응용] 2일차 - 균형점
 * 오차 전까지 while문 
 */

package BalancePoint;

import java.util.Scanner;

class BalancePoint2 {
	static int N;
	static int[] xArray;
	static int[] mArray;
	static double[] answer;
	
	public static void main(String args[]) throws Exception {
		Scanner sc = new Scanner(System.in);
		
		int T = sc.nextInt();

		for(int test_case = 1; test_case <= T; test_case++) {
			N = sc.nextInt();
			
			xArray = new int[N];
			mArray = new int[N];
			answer = new double[N];
			
			for(int i = 0; i < N; i++) {
				xArray[i] = sc.nextInt();
			}
			
			for(int i = 0; i < N; i++) {
				mArray[i] = sc.nextInt();
			}

			binarySearch();
			
			System.out.print("#" + test_case + " ");
			
			for(int i = 0; i < N-1; i++) {
				System.out.print(String.format("%.10f", answer[i]) + " ");
			}
			
			System.out.println();
		}
	}
	
	static double getPower(int start, int end, double mid) {
		double power = 0.0;
		
		for(int i = start; i <= end; i++) {
			double d = xArray[i] - mid;
			power += mArray[i] / ((d * d));
		}
		
		return power;
	}
	
	static void binarySearch() {
		double left, right, powerLeft, powerRight;
		double mid = 0;
		
		for(int i = 0; i < N-1; i++) {
			left = xArray[i];
			right = xArray[i+1];
			
			while(true) {
				mid = (left + right) / 2;
				
				powerLeft = getPower(0, i, mid);
				powerRight = getPower(i+1, N-1, mid);
				
				if(powerLeft > powerRight) {
					left = mid;
				} else {
					right = mid;
				}
				
				if(right - left < 1e-12)
					break;
			}
			
			answer[i] = mid;
		}		
	}
}