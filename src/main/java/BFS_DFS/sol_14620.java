package BFS_DFS;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// 14620 - 꽃길
// 꽃이 핀 뒤, 다른 꽃잎과 닿게 되면 두 꽃 모두 죽어버림 => 가장 싼 가격에 화단을 대여하고자 함
// 꽃 하나 당 5평 대여
// 출력 : 꽃 3개 심기 위한 최소 비용
// dfs(백트래킹)로 가능한 모든 개수 세고 최소 비용 구하기
public class sol_14620 {
    static int N;
    static int[][] map;
    static boolean[][] visited;
    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1};
    static int count;
    static int min = Integer.MAX_VALUE;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        map = new int[N][N];
        visited = new boolean[N][N];

        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        dfs(0);
        System.out.println(min);
    }

    public static void dfs(int cnt) {
        if (cnt == 3) {
            if (min > count) min = count;
            return;
        }

        for (int i=0; i<N; i++) {
            for (int j=0; j<N; j++) {
                if (cnt == 0) count = 0;
                if (!visited[i][j] && check(i, j)) {
                    visited[i][j] = true;
                    int tmp = getExpenseByOne(i, j);
                    count += tmp;
                    dfs(cnt+1);
                    deleteVisit(i, j);
                    count-= tmp;
                }
            }
        }
    }

    // 꽃을 심을 수 있는지 체크
    public static boolean check(int x, int y) {
        boolean isPossible = true;
        for (int i=0; i<4; i++) {
            int nx = x + dx[i];
            int ny = y + dy[i];
            if (nx <0 || nx >= N || ny <0 || ny >= N || visited[nx][ny]) {
                isPossible = false;
                break;
            }
        }
        return isPossible;
    }

    // 꽃을 심기 비용
    public static int getExpenseByOne(int x, int y) {
        int amount = map[x][y];
        for (int i=0; i<4; i++) {
            int nx = x + dx[i];
            int ny = y + dy[i];
            visited[nx][ny] = true;
            amount += map[nx][ny];
        }
        return amount;
    }

    // 되돌아갈 때 방문 해제
    public static void deleteVisit (int x, int y) {
        visited[x][y] = false;
        for (int i=0; i<4; i++) {
            int nx = x + dx[i];
            int ny = y + dy[i];
            visited[nx][ny] = false;
        }
    }
}
