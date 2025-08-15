package DP;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// 2098 - 외판원 순회 문제
// 다시
public class sol_2098 {
    static int n;
    static int[][] graph;
    static int[][] dp;
    static int INF = 1_000_000_000;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        graph = new int[n][n];
        dp = new int[1<<n][n];
        for (int i=0;i<n; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j=0; j<n; j++) {
                graph[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        int result = TSP(1, 0); // 방문, 위치
        System.out.println(result);
    }
    public static int TSP(int visited, int loc) {
        // 시작을 모두 0으로 초기화, 간선이 없는 경우만 INF
        // 모든 도시 방문했는지
        if (visited == (1<<n) - 1) {
            if(graph[loc][0] > 0) return graph[loc][0]; // 값 유효
            return INF;
        }
        // 이미 계산된 값이면 그대로 반환
        if (dp[visited][loc] > 0) return dp[visited][loc]; // 현재 방문한 상태가 최신상태로 업데이트 된 경우
        dp[visited][loc] = INF; // 아직 방문 X & INF로 초기화
        // 현재 값 vs 해당 dest까지 방문 아직 x & 간선존재 안했으면 비용 게산해서 더 최소인것으로 택함
        for (int dest = 0; dest<n; dest++) {
            if (isValid(visited, loc, dest)) {
                dp[visited][loc] = Math.min(dp[visited][loc], TSP(visited | (1<<dest), dest) + graph[loc][dest]);
            }
        }
        return dp[visited][loc];
    }
    // 아직 방문하지 않고 && 방문하려는 곳에 간선 존재
    public static boolean isValid(int visited, int loc, int dest) {
        return (visited & (1<<dest)) == 0 && graph[loc][dest] > 0;
    }
}
