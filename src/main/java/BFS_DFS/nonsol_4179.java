package BFS_DFS;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

// 4179 - 불!
// 지훈이가 불에 타기전에 탈출할 수 있는지의 여부, 얼마나 빨리 탈출할 수 있는지 결정
// 불은 각 지점에서 네 방향으로 확산됨, 지훈이는 미로의 가장자리에 접한 공간에서 탈출할 수 있음
// 지훈이와 불은 벽이 있는 공간은 통과 X
// #: 벽, . :지나갈수, J:지훈이의 초기위치, F:불이 난 공간
// 출력 : 불이 도달하기 전에 미로를 탈출할 수 없는 경우, IMPOSSIBLE 출력, 탈출가능한 경우 가장 빠른 탈출시간 출력
// 주의 1. 언제가 미로 탈출할 수 없을까? => 더이상 이동할 수 없을 때, 주의2. 불이있는 경우는 2군데 이상일수도 => 날짜가 정해져있기 때문에 바로바로 확산X, time을 기준으로 해야함.
// 탈출의 조건: 가장자리 => 위아래옆이 한번이라도 갈수가 없는 곳이면 가장자리임
public class nonsol_4179 {
    static int R, C;
    static char[][] map;
    static boolean[][] visited;
    static boolean[][] fireVisited;
    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1};
    static int[][] time, firetime;
    static int exitTime = 0;
    static int startX, startY;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        map = new char[R][C];
        time = new int[R][C];
        visited = new boolean[R][C];
        fireVisited = new boolean[R][C];

        for (int i=0; i<R; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j=0; j<C; j++) {
                map[i][j] = st.nextToken().charAt(0);
                if (map[i][j] == 'J') {
                    startX = i;
                    startY = j;
                    map[i][j] = ',';
                }
            }
        }

        while(true) {
            // 지훈이가 위치한 부분 가장자리 체크 &

            // 지훈 이동
//            for (int i =0; i<)
            // 불 이동 => 이미 체크한 불의 경우 앞으로 제외 => 새롭게 추가된 불은 추가
        }

    }

    public static void move(int x, int y) {
        Queue<int[]> q = new LinkedList<>();
        q.offer(new int[]{x, y});
        visited[x][y] = true;

        while(!q.isEmpty()) {
            int[] now = q.poll();
            for (int i=0; i<4; i++) {
                int nx = now[0] + dx[i];
                int ny = now[1] + dy[i];

                if (nx<0 || nx>=R || ny<0 || ny>=C || visited[nx][ny] || map[nx][ny] == 'F' || map[nx][ny] == '#') continue;
                if (map[nx][ny] == '.') {
                    visited[nx][ny] = true;
                    time[nx][ny] = time[now[0]][now[1]] + 1;
                    q.offer(new int[]{nx, ny});
                }
            }
        }
    }

    public static void moveFire(int x, int y) {
        Queue<int[]> q = new LinkedList<>();
        q.offer(new int[]{x, y});
        fireVisited[x][y] = true;

        while(!q.isEmpty()) {
            int[] now = q.poll();
            for (int i = 0; i < 4; i++) {
                int nx = now[0] + dx[i];
                int ny = now[1] + dy[i];

                if (nx < 0 || nx >= R || ny < 0 || ny >= C || fireVisited[nx][ny] || map[nx][ny] == '#') continue;
                if (map[nx][ny] == '.') {
                    fireVisited[nx][ny] = true;
                    q.offer(new int[]{nx, ny});
                }
            }
        }
    }
}
