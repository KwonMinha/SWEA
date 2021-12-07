/**
 * @author minha
 * 2021. 12. 7.
 * [2강 3번] 괄호 
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;

class Bracket {
	
	public static void main(String args[]) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();

		int T = Integer.parseInt(br.readLine());

		for(int test_case = 1; test_case <= T; test_case++) {
			int L = Integer.parseInt(br.readLine());
			String str = br.readLine();
			
			//System.out.println("\n" + test_case + " " + str);
			
			sb.append("#" + test_case + " ");
			
			if(L % 2 != 0) {
				sb.append("-1\n");
				continue;
			}
			
			Deque<Integer> left = new ArrayDeque<>();
			ArrayList<Integer> right = new ArrayList<>();
			
			for(int i = 0; i < L; i++) {
				if(str.charAt(i) == '(') {
					left.add(i);
				} else {
					if(left.isEmpty()) {
						right.add(i);
					} else {
						left.pollLast();
					}
				}
			}
			
			if(left.size() == 0 && right.size() == 0) {
				//System.out.println("size : 0");
				sb.append("0\n");
				continue;
			}
			
			if(left.size() == 0) {
				//System.out.println("size : 1");
				int mid = right.size() / 2;
				sb.append("1\n");
				sb.append(right.get(0) + " " + right.get(mid-1) + "\n");
				continue;
			}
			
			if(right.size() == 0) {
				//System.out.println("size : 1");
				int mid = left.size() / 2;
				sb.append("1\n");
				//sb.append(left.peekFirst() + " ");
				while(mid != 0) {
					left.pollFirst();
					mid--;
				}
				sb.append(left.peekFirst() + " " + left.peekLast() + "\n");
				continue;
			}
			
			sb.append("2\n");
			//System.out.println("size : 2");
			
			int flag = left.size() < right.size() ? 1 : 0; // 0 : left 사이즈가 더 작은 경우 / 1 : right
			
			//System.out.println("\nflag : " + flag);
			
			if(flag == 1) {
				//System.out.println(left.peekFirst() + " " + left.peekLast());
				sb.append(left.peekFirst() + " " + left.peekLast() + "\n");
			} else {
				//System.out.println(right.get(0) + " " + right.get(right.size()-1));
				sb.append(right.get(0) + " " + right.get(right.size()-1) + "\n");
			}
			
			int mid = (left.size() + right.size()) / 2;
			
			//System.out.println("\nmid : " + mid);
			
			if(flag == 1) {
				//System.out.println(right.get(0) + " " + right.get(mid-1));
				sb.append(right.get(0) + " " + right.get(mid-1) + "\n");
			} else {
				int cnt = mid - right.size();
				
				//System.out.println("cnt : " + cnt);
				while(cnt != 0) {
					left.pollFirst();
					cnt--;
				}
				
				//System.out.println(left.peekFirst() + " " + left.peekLast() + "\n");
				sb.append(left.peekFirst() + " " + left.peekLast() + "\n");
			}
			
		}

		//System.out.println();
		System.out.println(sb.toString());
	}
	
}