import java.util.Scanner;

public class WordCountingSolution {
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner sc = new Scanner(System.in);
		
		for (int TestCase = sc.nextInt(), tc = 1; tc <= TestCase; tc = tc+1) {
			
			String A = sc.next();
			String B = sc.next();
			
			int N = A.length();
			int M = B.length();
			
			int answer = new WordCountingUserSolution().FindString(N, A, M, B);
			
			System.out.println("#"+ tc + " " + answer);
		}
	}
	
}

