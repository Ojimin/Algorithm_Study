package DP;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// 1010 - 서쪽의 사이트 수 N만큼 다리를 지으려고 함 & 겹치면 안됨
// return : 다리를 지을 수 있는 경우의 수
// 수학 활용 => n-1Cr-1 + n-1Cr
// 미리 경우의 수를 계산해서 배열에 저장해두고 주어진 조건이 입력되면 해당 조건에 해당되는 배열의 값을 꺼내서 출력
public class sol_1010 {
    private final static int MAX = 30;
    static int T;
    static int N, M;
    static int[][] dp = new int[MAX+1][MAX+1];
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();
        T = Integer.parseInt(br.readLine());

        combination();
        for (int i=0; i<T; i++) {
            st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());
            M = Integer.parseInt(st.nextToken());
            sb.append(dp[M][N] + "\n");
        }
        System.out.println(sb);
        br.close();
    }

    // 조합 배열을 만드는 메서드 - bottom up 방식인듯
    public static void combination() {
        // nC1 = n
        for (int i=1; i<=MAX; i++) {
            dp[i][1] = i;
        }

        // nCr = n-1Cr-1 + n-1Cr => i, j 가 2이상이어야 성립
        for (int i=2; i<=MAX; i++) {
            for (int j=2; j<=MAX; j++) {
                dp[i][j] = dp[i-1][j-1] +dp[i-1][j];
            }
        }
    }
}
