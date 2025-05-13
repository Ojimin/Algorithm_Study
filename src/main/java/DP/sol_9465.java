package DP;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

//9465-스티커,스티커를 떼면 왼쪽, 오른쪽, 위, 아래에 있는 스티커 사용 X
// 스티커의 점수의 최댓값을 구해야함=> 2n개의 스티커 중에서 점수의 합 최대 & 서로 변 공유 X
// 변을 맞대지 않는다 -> 왼쪽 : 0 -1 오른쪽 : 0 1 아래: 1 0 위 : -1 0
// n-1개 뽑으면 최대임 1. 남아있느 것들 중(-1이 아닌) 가장 최대값 선택 2. 선택한 값 주변은 다 0으로 만들것
// 위 방식으로 하면 X => 하나의 스티커를 뗀다고 했을때, 그 스티커의 왼쪽 대각선과 그 왼쪽의 값 중 더 큰 값을 더해가면서 최댓값을 구하는 과정
// 어떻게 이 아이디어를 고안? 왼 -> 오로 계산을 하니까 스티커 선택의 경우, 바로 옆, 아래는 선택 x => 누적합 계산 고려시 대각선만 생각한다
// 그리고 그 대각선 값은 둘중에 하나로 비교해야 최댓값 구하는 것이 가능
// 다시
public class sol_9465 {
    static int T;
    static int N;
    static int[][] map;
    static int[][] dp;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();

        T = Integer.parseInt(br.readLine());

        for (int i = 0; i < T; i++) {
            N = Integer.parseInt(br.readLine());
            map = new int[2][N+1];
            dp = new int[2][N+1];
            for (int j=0;j<2; j++) {
                st = new StringTokenizer(br.readLine());
                for (int k=1; k<=N; k++) {
                    map[j][k] = Integer.parseInt(st.nextToken());
                }
            }
            dp[0][1] = map[0][1];
            dp[1][1] = map[1][1];
            for(int j=2; j<=N; j++) {
                dp[0][j] = Math.max(dp[1][j-1], dp[1][j-2]) + map[0][j];
                dp[1][j] = Math.max(dp[0][j-1], dp[0][j-2]) + map[1][j];
            }
//            solve();
            sb.append(Math.max(dp[0][N], dp[1][N])).append("\n");
        }
        System.out.println(sb);
    }

//    public static void solve() {
//        int max = -2;
//        for (int i = 1; i<=N-1; i++) {
//            for (int j = 0; j<2; j++) {
//                for (int k = 0; k<N; k++) {
//                    if (map[j][k] == -1) continue;
//                    if (map[j][k] > max) max = map[j][k];
//                }
//            }
//        }
//    }
}
