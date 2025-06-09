package DP;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

// 9462 - 파도반 수열
// P(N) = 나선에 있는 정삼각형 변의 길이
// 1<=N<=100
// dp => 점화식 존재
// 항상 변수 범위 체크
public class sol_9461 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());
        StringBuilder sb = new StringBuilder();
        long[] dp = new long[101];
        dp[1] = 1;
        dp[2] = 1;
        dp[3] = 1;
        dp[4] = 2;
        dp[5] = 2;
        for (int i=6; i<=100; i++) {
            dp[i] = dp[i-5] + dp[i-1];
        }
        for (int i=1;i<=T;i++){
            int N = Integer.parseInt(br.readLine());
            sb.append(dp[N]).append("\n");
        }
        System.out.println(sb);
    }
}
