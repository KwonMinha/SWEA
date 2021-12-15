/**
 * @author minha
 * 2021. 12. 12.
 * [7강 3번] 선 맞춤 
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;

class LineAlignment {
	
	static class Point {
		long x;
		long y;

		Point(long x, long y) {
			this.x = x;
			this.y = y;
		}
	}
	
	static final long MAX = Long.MAX_VALUE;
	static int N;
	static long E;
	static ArrayList<Point> pointList;
	static long[] DP;

	public static void main(String args[]) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;

		int T = Integer.parseInt(br.readLine());

		for(int test_case = 1; test_case <= T; test_case++) {
			st = new StringTokenizer(br.readLine());

			N = Integer.parseInt(st.nextToken());
			E = Integer.parseInt(st.nextToken());

			pointList = new ArrayList<>();

			for(int i = 0; i < N; i++) {
				st = new StringTokenizer(br.readLine());

				long x = Integer.parseInt(st.nextToken());
				long y = Integer.parseInt(st.nextToken());

				pointList.add(new Point(x, y));
			}

			DP = new long[N];
			Arrays.fill(DP, MAX);
			DP[0] = 0;

			for(int i = 0; i < N-1; i++) {
				Point point = pointList.get(i);

				Point upper = new Point(pointList.get(i+1).x,  pointList.get(i+1).y + E); // 가능한 가장 큰 기울기 상의 점 
				Point lower = new Point( pointList.get(i+1).x,  pointList.get(i+1).y - E); // 가능한 가장 작은 기울기 상의 점 

				if(DP[i+1] > DP[i]) {
					DP[i+1] = DP[i] + 1;
				}

				for(int j = i + 2; j < N; j++) {
					Point upper2 = new Point(pointList.get(j).x, pointList.get(j).y + E);
					
					// U가 Right turn 
					if(ccw(point, upper, upper2) < 0) upper = upper2;

					Point lower2 = new Point(pointList.get(j).x, pointList.get(j).y - E);
					
					// L이 Left turn
					if(ccw(point, lower, lower2) > 0) lower = lower2;

					if(ccw(point, upper, lower) > 0) {
						break;
					}

					// 해당 점(j)가 가능한 기울기의 범위 내에 있는 경우  
					if(ccw(point, lower , pointList.get(j)) >= 0 && ccw(point, upper , pointList.get(j)) <= 0) {
						if(DP[j] > DP[i] + 1) {
							DP[j] = DP[i] + 1;
						}
					}
				}
			}

			sb.append("#" + test_case + " " + DP[N-1] + "\n");
		}

		System.out.println(sb.toString());
	}
	
	static int ccw(Point A, Point B, Point C) {
		// 벡터의 외적 값 구하기 - 신발끈 공식 
		long result = (B.x - A.x) * (C.y - A.y) - (C.x - A.x) * (B.y - A.y);

		if(result > 0) 
			return 1; // 반시계 
		else if(result < 0) 
			return -1; // 시계 
		else 
			return 0; // 일직선 
	}

}