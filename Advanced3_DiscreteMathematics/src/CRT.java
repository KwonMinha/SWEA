/**
 * @author minha
 * 2021. 12. 10.
 * [3강 1번] CRT
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class CRT {
	static int[] remainder, number;
	
	public static void main(String args[]) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;

		int T = Integer.parseInt(br.readLine());

		for(int test_case = 1; test_case <= T; test_case++) {
			int N = Integer.parseInt(br.readLine());
			
			remainder = new int[N];
			number = new int[N];
			
			for(int i = 0; i < N; i++) {
				st = new StringTokenizer(br.readLine());
				
				int a = Integer.parseInt(st.nextToken());
				int b = Integer.parseInt(st.nextToken());
				
				remainder[i] = a;
				number[i] = b;
			}
			
			int k = N;

			sb.append("#" + test_case + " " + crt(k) + "\n");
		}

		System.out.println(sb.toString());
	}
	
	static int crt(int k) {
		int x = 1; 
		
        while(true) {
        	int i;
            for(i = 0; i < k; i++ )
                if (x % number[i] != remainder[i])
                   break;
            
            if(i == k)
                return x;
      
            x++;
        }
	}
	
}