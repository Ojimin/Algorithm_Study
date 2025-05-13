package DP;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

//10844-쉬운 계단 수
//출력 : 길이가 N인 계단 수가 총 몇개인지, 0으로 시작하는 수는 계단수가 아님
//dp인가- dp[N][i] : N개짜리 계단(자릿수), i는 맨 앞 자릿수가 i일때 의미,
// 계산 방법 = dp[2][3]일 때 만들 수 있는 계단 : dp[1][2] + dp[1][4] => 3(N+1)과 3(N-1)임
// dp[n][0] = 0이 계단수로 쓰일 수 있는 경우의 수!!!!!!!
// 인접한 모든 자리의 차이가 1 = 계단 수
//다시
public class sol_10844 {
    static long result=0;
    static long mod = 1000000000;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        long[][] dp = new long[n+1][10];
        // N=1 경우의 수
        for (int i=1;i<10; i++) {
            dp[1][i]=1;
        }

        // N>1
        for (int i=2; i<=n; i++) {
            for (int j=0; j<10; j++) {
                if (j == 0) dp[i][j] = dp[i-1][j+1] % mod;
                else if (j==9) dp[i][j] = dp[i-1][j-1] % mod;
                else dp[i][j] = (dp[i-1][j-1] + dp[i-1][j+1]) % mod;
            }
        }
        for (int i=0; i<10; i++) {
            result += dp[n][i];
        }
        System.out.println(result % mod);
    }
}
