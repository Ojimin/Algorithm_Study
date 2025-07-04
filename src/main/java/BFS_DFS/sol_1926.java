package BFS_DFS;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

// 1926 - 그림
// 출력 : 그림의 개수, 그 그림 중 가장 넓은 것의 넓이를 출력
// 그림 : 1로 연결된 것,
public class sol_1926 {
    static int[][] map;
    static boolean[][] visited;
    static int[] dx = {-1,1, 0, 0};
    static int[] dy = {0,0, -1, 1};
    static int n, m;
    static int cnt = 0;
    static int max = Integer.MIN_VALUE;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        map = new int[n][m];
        visited = new boolean[n][m];
        for (int i=0; i<n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j=0; j<m; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        for (int i=0; i<n; i++) {
            for (int j=0; j<m; j++) {
                if (!visited[i][j] && map[i][j] == 1) {
                    bfs(i, j);
                    cnt++;
                }
            }
        }
        System.out.println(cnt);
        if (cnt ==0) System.out.println(0);
        else System.out.println(max);
    }

    public static void bfs(int x, int y) {
        Queue<Integer[]> q = new LinkedList<>();
        q.offer(new Integer[]{x, y});
        visited[x][y] = true;
        int width = 1;

        while(!q.isEmpty()) {
            Integer[] cur = q.poll();
            for (int i=0; i<4; i++) {
                int nx = cur[0] + dx[i];
                int ny = cur[1] + dy[i];

                if (nx < 0 || nx >= n || ny <0 || ny>=m || visited[nx][ny]) continue;
                if (map[nx][ny] == 1) {
                    visited[nx][ny] = true;
                    q.offer(new Integer[]{nx, ny});
                    width++;
                }
            }
        }
        if (max < width) max = width;
    }
}
