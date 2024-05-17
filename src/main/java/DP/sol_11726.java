package DP;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

//11726-2*n 타일링,11727과 유사
//dp
public class sol_11726 {
    static int n;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        long[] dp = new long[n+1];
        dp[1]=1;
        if (n>1) {
            dp[2]=2; //n이 1이면 에러 발생할 수 있음
        }

        for(int i=3; i<=n; i++) {
            dp[i] = (dp[i-1]+dp[i-2]) % 10007;
        }
        System.out.println(dp[n]);
    }
}
