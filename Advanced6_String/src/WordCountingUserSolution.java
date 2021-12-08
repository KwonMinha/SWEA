class WordCountingUserSolution {
	
	public static int FindString(int N, String A, int M, String B) {
		final int MOD = 100000007;
		final int D = 297;

        long aHash = 0;
        long bHash = 0;
        long power = 1;
        
        int count = 0;

        for(int i = 0; i <= N - M; i++) {
            if(i == 0) {
                for(int j = 0; j < M; j++) {
                    aHash = (aHash + (A.charAt(M - 1 - j)) * power) % MOD;
                    bHash = (bHash + (B.charAt(M - 1 - j)) * power) % MOD;

                    if(j < M - 1) {
                        power = (power * D) % MOD;
                    }
                }
            } else {
                aHash = (D * (aHash - A.charAt(i-1) * power) + A.charAt(i + M - 1)) % MOD;
                if(aHash < 0) {
                	aHash += MOD;
                }
            }
       
            if(aHash == bHash) {
                count++;
            }
        }
		
		return count;
	}
}