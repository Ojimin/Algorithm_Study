package BFS_DFS;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

// 14502 - 연구소
// 바이러스는 인접한 곳에 퍼지고 퍼지지 않기 위해 벽을 3개 세워야함
// 0- 빈칸, 1- 벽, 2- 바이러스
// 벽을 3개 세운 후 얻을 수 있는 안전 영역 크기 최댓값 구하기
// N 제한이 8(매우 작은 수)
// 벽을 세워야 하는 곳은 일단 바이러스 근처여야 함
// 1. 벽을 세우고 2. 바이러스를 그상태에서 퍼뜨린 다음 3. 안전영역의 최댓값(1번으로 다시 돌아가서 조정)
// 다시 - 벽을 어디다 세우고?
// 키포인트 : 벽 세우는 것(벽을 세울 수 있는 모든 경우의 수를 구해야함) - dfs / 백트래킹(중복없는 조합) & 깊은 복사
public class sol_14502 {
    static int N, M;
    static int[][] map;
//    static boolean[][] visited;
    static boolean[][] bfs_visited;
    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1};
    static int wall = 3;
    static int max = Integer.MIN_VALUE;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        map = new int[N][M];
//        visited = new boolean[N][M];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for(int j = 0; j < M; j++){
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        dfs(0);
        System.out.println(max);

    }
    public static void dfs(int wallCnt) {
        if (wallCnt == wall) {
            bfs();
            return;
        }

        for (int i=0; i<N; i++) {
            for (int j=0; j<M; j++) {
                if (map[i][j] == 0) {
                    map[i][j] = 1; // 이미 뽑은 map[i][j]는 1로 변경하기 때문에 굳이 방문 배열x
                    dfs(wallCnt+1);
                    map[i][j] = 0;
                }
            }
        }
    }

    public static void bfs() {
        Queue<Integer[]> q = new LinkedList<>();
        int[][] copyMap = new int[N][M]; // 깊은 복사
        bfs_visited = new boolean[N][M];

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                copyMap[i][j] = map[i][j];
                if(map[i][j] == 2) {
                    q.offer(new Integer[]{i, j});
                    bfs_visited[i][j] = true;
                }
            }
        }
        while (!q.isEmpty()) {
            Integer[] now = q.poll();

            for(int i=0; i<4; i++) {
                int nx = now[0] + dx[i];
                int ny = now[1] + dy[i];

                if (nx >=0 && nx<N && ny>=0 && ny<M && copyMap[nx][ny] == 0) {
                    bfs_visited[nx][ny] = true;
                    copyMap[nx][ny] = 2;
                    q.add(new Integer[]{nx, ny});
                }
            }
        }
        getSafetyZone(copyMap);
    }

    public static void getSafetyZone(int[][] copyMap) {
        int safetyZoneCnt = 0;
        for (int i=0; i<N; i++) {
            for (int j=0; j<M; j++) {
                if (copyMap[i][j] == 0) safetyZoneCnt++;
            }
        }
        if (safetyZoneCnt > max) max = safetyZoneCnt;
    }

}
