/**
 * @author minha
 * 2021. 10. 27.
 * 1245. [S/W 문제해결 응용] 2일차 - 균형점
 * while문 + BufferedReader
 */

package BalancePoint;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BalancePoint3 {
	static int N;
	static int[] xArray;
	static int[] mArray;
	static double[] answer;
	
	public static void main(String args[]) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		int T = Integer.parseInt(br.readLine());

		for(int test_case = 1; test_case <= T; test_case++) {
			N = Integer.parseInt(br.readLine());
			
			xArray = new int[N];
			mArray = new int[N];
			answer = new double[N];
			
			st = new StringTokenizer(br.readLine());
			
			for(int i = 0; i < N; i++) {
				xArray[i] = Integer.parseInt(st.nextToken());
			}
			
			for(int i = 0; i < N; i++) {
				mArray[i] = Integer.parseInt(st.nextToken());
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