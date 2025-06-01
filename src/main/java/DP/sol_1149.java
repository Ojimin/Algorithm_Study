package DP;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// 1149 - RGB거리
// 1~N번 집, 빨강&초록&파랑 중 하나로 색칠할것
// 출력 : 모든집을 칠하는 비용의 최솟값
// 규칙 1. 1번 색 != 2번 색 - 첫번째
// 규칙 2. N번 색 != N-1번색 - 끝번째
// 규칙 3. i번 색 != i+1 != i-1 => 2~N-1
// 최솟값 : 그리디, dp,
//
public class sol_1149 {
    static int N;
    static int[][] arr;
    static int[][] dp;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        arr = new int[N][3];
        dp = new int[N][3];
        for (int i=0; i<N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j=0; j<3; j++) {
                arr[i][j] = Integer.parseInt(st.nextToken()); // 빨강 초록 파랑
            }
        }
        getMinimum();
        int min = Math.min(dp[N-1][0], dp[N-1][1]);
        min = Math.min(min, dp[N-1][2]);

        System.out.println(min);
    }

    public static void getMinimum() {
        dp[0][0] = arr[0][0];
        dp[0][1] = arr[0][1];
        dp[0][2] = arr[0][2];

        for (int i=1; i<N; i++) {
            dp[i][0] = Math.min(dp[i-1][1], dp[i-1][2]) + arr[i][0];
            dp[i][1] = Math.min(dp[i-1][0], dp[i-1][2]) + arr[i][1];
            dp[i][2] = Math.min(dp[i-1][0], dp[i-1][1]) + arr[i][2];
        }
    }
}
