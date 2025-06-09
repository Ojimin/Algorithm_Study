package DP;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// 11048 - 이동하기
// 1,1 -> N, M으로 이동할 때, 아래, 오른쪽, 대각선 이동가능
// 출력 : 가져올 수 있는 사탕 개수 최댓값
// bfs, dfs 같음 문제 형태는
// 그러나 dp => 칸에 들어갈 값들을 누적하여 기록한 다음 현재값을 더했을 때, 가장 최댓값인것 출력
public class sol_11048 {
    static int N,M;
    static int[][] map;
    static int[][] dp;
    static int[] dx = {1,0,1};
    static int[] dy = {0,1,1};
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        map = new int[N][M];

        for (int i=0; i<N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j=0; j<M; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        dp = new int[N][M]; // 사탕 누적 개수 기록
        dp[0][0] = map[0][0];
        for (int i=0; i<N; i++) {
            for (int j=0; j<M; j++) {
                for (int k=0; k<3; k++) {
                    int nx = i + dx[k];
                    int ny = j + dy[k];
                    if (nx < 0 || ny < 0 || nx >= N || ny >= M) continue;
                    if (map[nx][ny] + dp[i][j] > dp[nx][ny]) {
                        dp[nx][ny] = dp[i][j] + map[nx][ny];
                    }
                }
            }
        }
        System.out.println(dp[N-1][M-1]);
    }
}
