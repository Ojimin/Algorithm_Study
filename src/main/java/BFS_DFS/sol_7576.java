package BFS_DFS;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

// 7576 - 토마토
// 보관 후 하루가 지나면, 익은 토마트들의 인접한 곳에 있는 익지 않은 토마토가 영향을 받아 익음
// 며칠이 지나면 토마토가 다 익는지 최소 일수 구하기 & 처음부터 모든 토마토가 익어있으면 0 & 모두 익지 못하는 상황이면 -1
// 1: 익은 0: 익지 않은 -1: 토마토가 들어있찌 않은칸
// 다시 - day 자체를 두어서 계사한기 보다는 +1 하는 방식으로 진행하는 것이 나을듯!!
public class sol_7576 {
    static int M, N;
    static int[][] map;
    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1};
    static boolean[][] visited;
    static int day = 0;
    static boolean isChanged; // 익는지 체크
    static Queue<Position> q;

    public static class Position {
        int x;
        int y;
        public Position(int x, int y) { this.x = x; this.y = y; }
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        M = Integer.parseInt(st.nextToken());
        N = Integer.parseInt(st.nextToken());
        map = new int[N][M];
        boolean isRiped = true;
        q = new LinkedList<>();
        visited = new boolean[N][M];

        for (int i=0; i<N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j=0; j<M; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
                if (map[i][j] == 1) q.add(new Position(i, j));
            }
        }

        // 모든 토마토가 익었는지 사전 체크
        for (int i=0; i<N; i++) {
            for (int j=0; j<M; j++) {
                if (map[i][j] == 0) {
                    isRiped = false;
                    break;
                }
            }
            if (!isRiped) break;
        }

        if (isRiped) {
            System.out.println(day); //0 출력
            return;
        }
        bfs();

//        while (true) {
//            isChanged = false;
//            visited = new boolean[N][M];
//            for (int i=0; i<N; i++) {
//                for (int j=0; j<M; j++) {
//                    if (day == 0) {
//                        if (map[i][j] == 1 && !visited[i][j]) {
//                            bfs(i, j);
//                        }
//                    }
//                    else if (map[i][j] == day && !visited[i][j]) {
//                        bfs(i, j);
//                    }
//                }
//            }
//            if (isChanged) day++;
//            else {
//                break;
//            }
//        }
        // 다시 체크
        isRiped = true;
        for (int i=0; i<N; i++) {
            for (int j=0; j<M; j++) {
                if (map[i][j] == 0) {
                    isRiped = false;
                    break;
                }
            }
            if (!isRiped) break;
        }

        // 결과 출력
        if (!isRiped) System.out.println(-1);
        else {
            if (day == 0) System.out.println(day);
            else System.out.println(day-1);
        }

    }

    public static void bfs() {;
        while (!q.isEmpty()) {
            Position now = q.poll();
            visited[now.x][now.y] = true;
            for (int i=0; i<4; i++) {
                int nx = now.x + dx[i];
                int ny = now.y + dy[i];
                if (nx >=0 && nx<N && ny>=0 && ny<M && !visited[nx][ny] && map[nx][ny] == 0 ) {
                    visited[nx][ny] = true;
//                    isChanged = true;
                    map[nx][ny] = map[now.x][now.y] + 1;
                    q.add(new Position(nx, ny));
                    if (day < map[nx][ny]) {
                        day = map[nx][ny];
//                        System.out.println("day 변경 : " + day);
                    }
                }
            }
        }
    }
}
