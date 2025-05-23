package DP;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// 12865 - 평번한 배낭
// N개의 물건, 각 물건은 무게 W와 가치 V를 지님. 최대 K만큼의 무게만큼의 배낭 가능
// 출력 : 최대 가치값
// 0-1 배낭 문제의 대표적 문제
// 다시 복습!
public class sol_12865 {
    static int[][] arr;
    static int[][] dp;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken()); // 물품 수
        int K = Integer.parseInt(st.nextToken()); // 최대 무게
        arr = new int[N+1][2];
        dp = new int[N+1][K+1];
        for (int i=1; i<=N; i++) {
            st = new StringTokenizer(br.readLine());
            arr[i][0] = Integer.parseInt(st.nextToken()); // 무게
            arr[i][1] = Integer.parseInt(st.nextToken()); // 가치
        }
        solve(N,K);
        System.out.println(dp[N][K]);
    }

    public static void solve(int N, int K) {
        for (int i=0; i<=N; i++) {
            for (int j=0; j<=K; j++) {
                if (i == 0 || j==0) {
                    dp[i][j] = 0;
                    continue;
                }
                if (arr[i][0] > j) dp[i][j] = dp[i-1][j];
                else dp[i][j] = Math.max(dp[i-1][j], dp[i-1][j-arr[i][0]]+arr[i][1]);//현재 가치를 넣기 전을 비교해야함
            }
        }
    }
}
