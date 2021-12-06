/**
 * @author minha
 * 2021. 12. 7.
 * [2강 2번] 가장 짧은 길 전부 청소하기
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

class CleanRoad {

	public static void main(String args[]) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();

		int T = Integer.parseInt(br.readLine());

		for(int test_case = 1; test_case <= T; test_case++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int N = Integer.parseInt(st.nextToken());
			int M = Integer.parseInt(st.nextToken());

			ArrayList<Element>[] adjList = new ArrayList[N+1];
			for(int i = 1; i < N+1; i++) {
				adjList[i] = new ArrayList<>();
			}

			for(int i = 0; i < M; i++) {
				st = new StringTokenizer(br.readLine());

				int a = Integer.parseInt(st.nextToken());
				int b = Integer.parseInt(st.nextToken());
				int c = Integer.parseInt(st.nextToken());

				adjList[a].add(new Element(b, c));
				adjList[b].add(new Element(a, c));
			}

			boolean[] check = new boolean[N+1];
			long[] distance = new long[N+1];
			long[] cost = new long[N+1];
			long INF = Long.MAX_VALUE;

			Arrays.fill(distance, INF);
			Arrays.fill(cost, INF);

			distance[1] = 0;
			cost[1] = 0;

			PriorityQueue<Element> pq = new PriorityQueue<>();
			pq.offer(new Element(1, 0));

			while(!pq.isEmpty()) {
				int current = pq.poll().index;

				if(check[current]) continue;

				check[current] = true;

				for(Element next : adjList[current]) {
					if(distance[next.index] > distance[current] + next.distance) {
						distance[next.index] = distance[current] + next.distance;
						pq.offer(new Element(next.index, distance[next.index]));
						cost[next.index] = next.distance;
					} else if(distance[next.index] == distance[current] + next.distance) {
						cost[next.index] = Math.min(cost[next.index], next.distance);
					}
				}
			}

			long sum = 0;
			for(int i = 1; i < N+1; i++) {
				sum += cost[i];
			}
			sb.append("#" + test_case + " " + sum + "\n");
		}

		System.out.println(sb.toString());
	}

}

class Element implements Comparable<Element> {
	int index;
	long distance;

	Element(int index, long distance) {
		this.index = index;
		this.distance = distance;
	}

	@Override
	public int compareTo(Element o) {
		return this.distance <= o.distance ? -1 : 1;
	}
}