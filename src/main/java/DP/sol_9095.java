package DP;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

// 9095 - 정수 n을 1,2,3의 합으로 나타내는 방법
// 규칙 : dp[n] = dp[n-1] + dp [n-2] + dp[n-3]
public class sol_9095 {
    static int T;
    static int[] dp = new int[11];
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        T = Integer.parseInt(br.readLine());
        StringBuilder sb = new StringBuilder();
        solve();
        for (int i=0; i<T; i++) {
            int n = Integer.parseInt(br.readLine());
            sb.append(dp[n]).append("\n");
        }
        System.out.println(sb);
    }

    public static void solve() {
        dp[1] = 1;
        dp[2] = 2;
        dp[3] = 4;

        for (int i=4; i<11; i++) {
            dp[i] = dp[i-1] + dp[i-2] + dp[i-3];
        }
    }

}
