/**
 * @author minha
 * 2021. 10. 22.
 * 1232. [S/W 문제해결 기본] 9일차 - 사칙연산
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;

class Operation {
	static int[][] tree;
	static String[] value;
	static StringBuilder sb;
	static Stack<Double> stack;

	public static void main(String args[]) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		sb = new StringBuilder();

		int T = 10;

		for(int test_case = 1; test_case <= T; test_case++) {
			int N = Integer.parseInt(br.readLine());

			tree = new int[N+1][2];
			value = new String[N+1];

			for(int i = 1; i <= N; i++) {
				String[] arr = br.readLine().split(" ");
			
				value[i] = arr[1];
				stack = new Stack<>();
				
				if(arr.length == 4) {
					int a = Integer.parseInt(arr[0]);
					int b = Integer.parseInt(arr[2]);
					int c = Integer.parseInt(arr[3]);
					
					tree[a][0] = b;
					tree[a][1] = c;
				}
			}
			
			postOrder(1);
			
			int result = stack.pop().intValue();
			sb.append("#" + test_case + " " + result + "\n");
		}

		System.out.println(sb.toString());
	}
	
	static void postOrder(int x) {
		if(tree[x][0] == 0 && tree[x][1] == 0) {
			 operation(value[x]);
		} else {
			if(tree[x][0] != 0) {
				postOrder(tree[x][0]);
			} 
			
			if(tree[x][1] != 0) {
				postOrder(tree[x][1]);
			}

			operation(value[x]);
		}
	}
	
	static void operation(String v) {
		if(!v.equals("+") && !v.equals("-") && !v.equals("*") && !v.equals("/")) {
			stack.push(Double.parseDouble(v));
			return;
		}
		
		Double b = stack.pop();
		Double a = stack.pop();
		
		if(v.equals("+")) {
			stack.push(a + b);
		} else if(v.equals("-")) {
			stack.push(a - b);
		} else if(v.equals("*")) {
			stack.push(a * b);
		} else if(v.equals("/")) {
			stack.push(a / b);
		}
	}
	
}