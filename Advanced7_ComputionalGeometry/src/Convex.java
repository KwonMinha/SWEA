/**
 * @author minha
 * 2021. 12. 12.
 * [7강 1번] Convex
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Stack;
import java.util.StringTokenizer;

class Convex {
	static Point minPoint;
	static ArrayList<Point> pointList;

	public static void main(String args[]) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;

		int T = Integer.parseInt(br.readLine());

		for(int test_case = 1; test_case <= T; test_case++) {
			int N = Integer.parseInt(br.readLine());

			minPoint = new Point(Integer.MAX_VALUE, Integer.MAX_VALUE);

			pointList = new ArrayList<>();

			for(int i = 0; i < N; i++) {
				st = new StringTokenizer(br.readLine());

				int x = Integer.parseInt(st.nextToken());
				int y = Integer.parseInt(st.nextToken());

				pointList.add(new Point(x, y));
			}

			Stack<Point> stack = convexHull();

			sb.append("#" + test_case + " " + stack.size() + "\n");
		}

		System.out.println(sb.toString());
	}

	static Stack<Point> convexHull() {
		for(int i = 0; i < pointList.size(); i++) {
			if(pointList.get(i).y < minPoint.y) { // y좌표가 가장 낮은 점 선택
				minPoint = pointList.get(i);
			} else if(pointList.get(i).y == minPoint.y) {
				if(pointList.get(i).x < minPoint.x) { // 여러개 있다면 x좌표가 가장 작은 것 선택 
					minPoint = pointList.get(i);
				}
			}
		}

		// 선택된 점을 기준으로 각도순 정렬
		Collections.sort(pointList, new Comparator<Point>() {
			@Override
			public int compare(Point o1, Point o2) {
				int result = ccw(minPoint, o1, o2);

				if(result > 0) {
					return -1;
				} else if(result < 0) {
					return 1;
				} else {
					if(dist(minPoint, o1) > dist(minPoint, o2)) { // 각도가 같다면 거리순으로 정렬
						return 1;
					}
				}
				return -1;
			}
		});

		// Graham's Scan
		Stack<Point> stack = new Stack<>();
		stack.push(minPoint);

		for(int i = 1; i < pointList.size(); i++) {
			while(stack.size() > 1 && ccw(stack.get(stack.size() - 2), stack.get(stack.size()-1), pointList.get(i)) <= 0) {
				stack.pop();
			}
			stack.add(pointList.get(i));
		}

		return stack;
	}

	static int ccw(Point A, Point B, Point C) {
		// 벡터의 외적 값 구하기 - 신발끈 공식 
		long result = (B.x - A.x) * (C.y - A.y) - (C.x - A.x) * (B.y - A.y);

		if (result > 0)
			return 1; // 반시계 
		else if (result < 0) 
			return -1; // 시계 
		else 
			return 0; // 일직선 
	}

	// 각도가 같은 경우 거리를 비교해 정렬해야 함 
	static long dist(Point A, Point B) {
		return (B.x - A.x) * (B.x - A.x) + (B.y - A.y) * (B.y - A.y);
	}

}

class Point {
	long x;
	long y;

	Point(long x, long y) {
		this.x = x;
		this.y = y;
	}
}