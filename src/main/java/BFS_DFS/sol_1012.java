package BFS_DFS;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

//유기농 배추 : 지렁이는 인접한 다른 배추 상하좌우로 이동 가능, 심어놓은 배추에서 총 몇 마리의 지렁이가 필요한지 출력
//지렁이 개수 구하기, bfs로 하는게 낫지않을까?
//각 구역별로 탐색 끝나면 지렁이개수+1
//가로, 세로길이 유의!!
public class sol_1012 {
    static int M, N, K;
    static int[][] graph;
    static int[] dx = {-1, 0, 1, 0};
    static int[] dy = {0, 1, 0, -1};
    static boolean[][] visited;
    static int cnt; //흰지렁이 개수세기

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        int T = Integer.parseInt(br.readLine());
        for (int i = 0; i < T; i++) {
            cnt = 0;
            String[] input = br.readLine().split(" ");
            M = Integer.parseInt(input[0]);
            N = Integer.parseInt(input[1]);
            K = Integer.parseInt(input[2]);
            graph = new int[N][M];
            visited = new boolean[N][M];

            for (int j = 0; j < K; j++) {
                st = new StringTokenizer(br.readLine());
                int x = Integer.parseInt(st.nextToken());
                int y = Integer.parseInt(st.nextToken());
                graph[y][x] = 1;
            }

            //bfs 호출 및 결과 출력
            for (int j = 0; j < N; j++) {
                for (int k = 0; k < M; k++) {
                    if (graph[j][k] == 1 & !visited[j][k]) {
                        cnt += 1;
                        bfs(j, k);
                    }
                }
            }
            System.out.println(cnt);
        }
    }

    public static void bfs(int x, int y) {
        Queue<int[]> queue = new LinkedList<>();
        queue.offer(new int[]{x, y});
        visited[x][y] = true;
        while (!queue.isEmpty()) {
            int[] now = queue.poll();
            int now_x = now[0];
            int now_y = now[1];
            for (int i = 0; i < 4; i++) {
                int next_x = now_x + dx[i];
                int next_y = now_y + dy[i];
                if (next_x >= 0 && next_y >= 0 && next_x < N && next_y < M) {//범위체크
                    if (graph[next_x][next_y] == 1 && !visited[next_x][next_y]) {
                        visited[next_x][next_y] = true;
                        queue.offer(new int[]{next_x, next_y});
                    }
                }
            }
        }

    }
}
