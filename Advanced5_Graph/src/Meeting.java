/**
 * @author minha
 * 2021. 11. 22.
 * [5강 3번] 간담회 참석 
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

class Meeting {
	static class Node implements Comparable<Node> {
		int index;
		int distance;
		
		Node(int index, int distance) {
			this.index = index;
			this.distance = distance;
		}

		@Override
		public int compareTo(Node o) {
			return Integer.compare(this.distance, o.distance);
		}
	}
	
	static final int INF = Integer.MAX_VALUE;
	static ArrayList<Node>[] adjList;
	static ArrayList<Node>[] backAdjList;
	static boolean[] check;
	static int[] distance;
	static int[] backDistance;
	
	public static void main(String args[]) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();

		int T = Integer.parseInt(br.readLine());

		for(int test_case = 1; test_case <= T; test_case++) {
			st = new StringTokenizer(br.readLine());
			int N = Integer.parseInt(st.nextToken());
			int M = Integer.parseInt(st.nextToken());
			int X = Integer.parseInt(st.nextToken());
			
			adjList = new ArrayList[N+1];
			backAdjList = new ArrayList[N+1];
			for(int i = 1; i < N+1; i++) {
				adjList[i] = new ArrayList<>();
				backAdjList[i] = new ArrayList<>();
			}
			check = new boolean[N+1];
			distance = new int[N+1];
			backDistance = new int[N+1];
			
			int answer = 0;
			
			for(int i = 0; i < M; i++) {
				st = new StringTokenizer(br.readLine());
				int s = Integer.parseInt(st.nextToken());
				int e = Integer.parseInt(st.nextToken());
				int t = Integer.parseInt(st.nextToken());
				
				adjList[s].add(new Node(e, t));
				backAdjList[e].add(new Node(s, t));
			}
			
			// N개의 강의실에서 X까지 가는 최단 거리 저장 
			dijkstra(X, backAdjList, backDistance);
			
			// X에서 N개의 다른 모든 강의실로 가는 최단 거리 저장 
			dijkstra(X, adjList, distance);
			
			for(int i = 1; i < N+1; i++) {
				if(i == X) continue;
				
				answer = Math.max(answer, backDistance[i] + distance[i]);
			}

			sb.append("#" + test_case + " " + answer + "\n");
		}

		System.out.println(sb.toString());
	}
	
	static void dijkstra(int start, ArrayList<Node>[] list, int[] dist) {
		Arrays.fill(dist, INF);
		Arrays.fill(check, false);
		dist[start] = 0;
		
		PriorityQueue<Node> pq = new PriorityQueue<>();
		pq.offer(new Node(start, 0));
		
		while(!pq.isEmpty()) {
			int current = pq.poll().index;
			
			if(check[current]) continue;
			
			check[current] = true;
			
			for(Node next : list[current]) {
				if(dist[next.index] > dist[current] + next.distance) {
					dist[next.index] = dist[current] + next.distance;
					pq.offer(new Node(next.index, dist[next.index]));
				}
			}
		}
	}
	
}