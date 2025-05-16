package BFS_DFS;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

//14940-쉬운 최단거리, 2가 목표지점
// 0 - 갈 수 없는 땅, 1 - 갈 수 있는 땅, 2- 목표
// 출력 : 각 지점에서 목표지점까지의 (최단)거리 출력, 이때 원래 갈 수 없는 땅 위치는 0 & 목표지점까지 도달할 수 없는 위치면 -1
// 도달할 수 없는 것을 어떻게 찾아낼 것?
// 예외 케이스 잘 생각 - 원래 길이 없었던 0인 곳들 처리 등
public class sol_14940 {
    static int N, M;
    static int[][] map;
    static boolean[][] visited;
    static int[] end = new int[2];
    static int[][] result;
    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        StringBuilder sb = new StringBuilder();
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        map = new int[N][M];
        result = new int[N][M];

        for (int i=0; i<N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j=0; j<M; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
                if (map[i][j] == 2) {
                    end[0] = i;
                    end[1] = j;
                }
            }
        }
        result[end[0]][end[1]] = 0;
        bfs(end[0], end[1]);
        // 도달 x 찾기
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (!visited[i][j] && map[i][j] != 0) result[i][j] = -1;
            }
        }

//        for (int i=0; i<N; i++) {
//            for (int j=0; j<M; j++) {
//             if (map[i][j] == 2) {
//                 result[i][j] = 0;
//                 continue;
//             }
//             if (map[i][j] == 0) {
//                 result[i][j] = 0;
//                 continue;
//             }
//             visited = new boolean[N][M];
//             if (!bfs(i, j)) result[i][j] =-1;
//            }
//        }
        for (int i=0; i<N; i++) {
            for (int j=0; j<M; j++) {
                sb.append(result[i][j]).append(" ");
            }
            sb.append("\n");
        }
        System.out.println(sb);
    }

    private static void bfs(int x, int y) {
        Queue<int[]> q = new LinkedList<>();
        visited = new boolean[N][M];
        q.offer(new int[]{x, y});
        visited[x][y] = true;
//        int cnt = 0; //거리 세기
//        boolean isReached = false;

        while (!q.isEmpty()) {
            int[] now = q.poll();

            for (int i=0; i<4; i++) {
                int nx = now[0] + dx[i];
                int ny = now[1] + dy[i];
//                cnt++;
                if (nx>=0 && nx<N && ny>=0 && ny<M && !visited[nx][ny]) {
                    if (map[nx][ny] == 1) {
                        result[nx][ny] = result[now[0]][now[1]]+1;
                        q.offer(new int[]{nx, ny});
                    }
                    visited[nx][ny] = true;
                }
            }
        }

    }
}
