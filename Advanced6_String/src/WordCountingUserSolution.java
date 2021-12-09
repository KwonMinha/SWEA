class WordCountingUserSolution {
	
	public static int FindString(int N, String A, int M, String B) {
		final long MOD = (long) Math.pow(2, 64);
		final int D = 257; // or 259
	    long power = 1;

        long aHash = 0;
        long bHash = 0;
        
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
            	// mod를 100000007, D를 297로 잘 못 잡고 설정함 
//                aHash = (D * (aHash  - A.charAt(i-1) * power) + A.charAt(i + M - 1)) % MOD;
//                if(aHash < 0) {
//                	aHash += MOD;
//                }
                
//                aHash = D * aHash % MOD - D * A.charAt(i-1) * power % MOD + A.charAt(i + M - 1);
//                aHash %= MOD;
                
                aHash = ((D * (aHash - A.charAt(i-1) * power)) + A.charAt(i + M - 1)) % MOD;
            }
       
            if(aHash == bHash) {
                count++;
            }
        }
		
		return count;
	}
}