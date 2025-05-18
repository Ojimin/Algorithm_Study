package BFS_DFS;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

// 1743 - 음식물 피하기
// 음식물은 근처에 있는 것끼리 뭉치게 돼서 큰 쓰레기가 됨
// 음식물 중에 제일 큰 음식물만 피하기 =>출력 : 제일 큰 음식물 크기 구하기
public class sol_1743 {
    static int N, M, K;
    static int[][] map;
    static boolean[][] visited;
    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        map = new int[N+1][M+1];
        visited = new boolean[N+1][M+1];
        int max = 0;
        for (int i = 0; i < K; i++) {
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            map[x][y] = 1;
        }

        for (int i=1; i<=N; i++) {
            for (int j=1; j<=M; j++) {
                if (map[i][j] == 1 && !visited[i][j]) {
                    int cnt = bfs(i, j);
                    if (max < cnt) max = cnt;
                }
            }
        }
        System.out.println(max);
    }

    public static int bfs(int x, int y) {
        Queue<int[]> q = new LinkedList<>();
        q.add(new int[]{x, y});
        visited[x][y] = true;
        int cnt = 1;
        while(!q.isEmpty()) {
            int[] now = q.poll();

            for (int i = 0; i < 4; i++) {
                int nx = now[0] + dx[i];
                int ny = now[1] + dy[i];

                if (nx >=1 && nx<=N && ny>=1 && ny<=M && map[nx][ny] == 1 && !visited[nx][ny]) {
                    visited[nx][ny] = true;
                    q.add(new int[]{nx, ny});
                    cnt++;
                }
            }
        }
        return cnt;
    }
}
