import java.util.Scanner;

public class RollerCoaster {
	
	public static int CalcFinalSpeed(int N, int[] a, int[] b, int[] p) {
		int v = 1;
		
		for (int i = 0; i < N; i++) {
			
			int CurRail = p[i];
			
			v = (int)(((long)(a[CurRail])*v + b[CurRail]) % 1000000007);
			
		}
		
		return v;
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner sc = new Scanner(System.in);
		
		for (int TestCase = sc.nextInt(), tc = 1; tc <= TestCase; tc = tc+1) {
			int N = sc.nextInt();
			int[] a = new int[N];
			int[] b = new int[N];
			
			for (int i = 0; i < N; i++) {
				a[i] = sc.nextInt();
				b[i] = sc.nextInt();
			}
			
			int answer = new RollerCoasterUserSolution().MinRailSpeed(N, a, b);
			
			System.out.println("#"+ tc + " " + answer);
		}
	}
	
}

