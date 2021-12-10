/**
 * @author minha
 * 2021. 12. 10.
 * [7강 2번] Closet
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Comparator;
import java.util.StringTokenizer;
import java.util.TreeSet;

class Closet {
	static Point[] point;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;

		int T = Integer.parseInt(br.readLine());

		for(int test_case = 1; test_case <= T; test_case++) {

			int N = Integer.parseInt(br.readLine());

			point = new Point[N];

			for(int i = 0; i < N; i++) {
				st = new StringTokenizer(br.readLine(), " ");
				
				int x = Integer.parseInt(st.nextToken());
				int y = Integer.parseInt(st.nextToken());
				
				point[i] = new Point(x, y);
			}

			Arrays.sort(point, Xcomparator);

			TreeSet<Point> set = new TreeSet<Point>(Ycomparator);

			long minDist = dist(point[0], point[1]);

			set.add(point[0]);
			set.add(point[1]);

			int lowIdx = 0;

			for(int i = 2; i < N; i++) {
				Point current = point[i];

				while(lowIdx < i) {
					Point next = point[lowIdx];

					long xDist = current.x - next.x;

					if(xDist * xDist > minDist) {
						set.remove(next);
						lowIdx++;
					} else {
						break;
					}
				}

				int sqrtMinDist = (int)Math.sqrt(minDist) + 1;

				TreeSet<Point> ySubSet = (TreeSet<Point>) set.subSet(new Point(-1000000, current.y - sqrtMinDist), new Point(1000000, current.y + sqrtMinDist));

				for(Point p : ySubSet) {
					minDist = Math.min(minDist, dist(current, p));
				}

				set.add(current);
			}

			sb.append("#" + test_case + " " + minDist + "\n");
		}

		System.out.println(sb.toString());
	}

	static long dist(Point o1, Point o2) {
		return (o1.x - o2.x) * (o1.x - o2.x) + (o1.y - o2.y) * (o1.y - o2.y);
	}

	static Comparator<Point> Xcomparator = new Comparator<Point>() {
		@Override
		public int compare(Point o1, Point o2) {
			return Long.valueOf(o1.x).compareTo(Long.valueOf(o2.x));
		}
	};

	static Comparator<Point> Ycomparator = new Comparator<Point>() {
		@Override
		public int compare(Point o1, Point o2) {
			if(o1.y == o2.y) {
				return Long.valueOf(o1.x).compareTo(Long.valueOf(o2.x));
			} else {
				return Long.valueOf(o1.y).compareTo(Long.valueOf(o2.y));
			}
		}
	};

}

class Point {
	long x;
	long y;

	public Point(long x, long y) {
		this.x = x;
		this.y = y;
	}
}