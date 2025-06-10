package Simulation;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

// 16234 - 인구 이동
// 옛날에 bfs, dfs에서 풀었삼
// N*N 크기, A[r][c] = 나라당 살고있는 사람수, 인구이동이 없을때까지 지속함
// 규칙 1. 두나라 인구차이가 L명 이상, R명 이하면 오늘 하루 열음
// 규칙 2. 국경선을 모두 열렸 -> 인구 이동 시작
// 규칙 3. 인접한 칸 이동 가능 -> 연합
// 규칙 4. 연합 각 칸 인구수 =(연합인구수) / (연합 칸 개수) => 소수점은 버림
// 규칙 5. 연합해체하고 국경선 닫음
// 출력 : 인구이동이 며칠동안 발생하는지 출력
// 주의 : day의 기준 & 같은 날에 결성한 연합군들 => 그 날 끝나고 계산 어떻게 할지
public class sol_16234 {
    static int N, L, R;
    static int[][] map;
    static int[][][] visited;
    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        L = Integer.parseInt(st.nextToken());
        R = Integer.parseInt(st.nextToken());
        map = new int[N][N];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        boolean isChanged = true;
        int day= 0;
        while(isChanged) {
            visited = new int[N][N][1];
            isChanged = false;
            for (int i=0; i<N; i++) {
                for (int j=0; j<N; j++) {
                    if (visited[i][j][0] == 0 && isPossible(i, j)) {
                        boolean result = bfs(i, j);
                        if (result) {
                            isChanged = true;
                        }
                    }
                }
            }
            if (isChanged) {
                day++;
                changePeople();
            }
            else break;
        }

        System.out.println(day);

    }

    public static boolean isPossible(int x, int y) {
        for (int i=0; i<4; i++) {
            int nx = x + dx[i];
            int ny = y + dy[i];

            if ( nx < 0 || nx >= N || ny < 0 || ny >= N || visited[nx][ny][0] != 0) continue;
            if (Math.abs(map[x][y] - map[nx][ny]) >= L && Math.abs(map[x][y] - map[nx][ny]) <= R) return true;

        }
        return false;
    }

    public static boolean bfs(int x, int y) {
        Queue<Integer[]> q = new LinkedList<>();
        q.add(new Integer[]{x, y});
        boolean isChanged = false;
        visited[x][y][0] = 1;
        int cnt = 1;
        int result = map[x][y];
        while(!q.isEmpty()) {
            Integer[] now = q.poll();
            int nowX = now[0];
            int nowY = now[1];

            for (int i=0; i<4; i++) {
                int nx = nowX + dx[i];
                int ny = nowY + dy[i];

                if (nx < 0 || ny < 0 || nx >= N || ny >= N || visited[nx][ny][0] != 0) continue;
                if (Math.abs(map[nowX][nowY] - map[nx][ny]) >= L && Math.abs(map[nowX][nowY] - map[nx][ny]) <= R) {
                    visited[nx][ny][0] = 1;
                    isChanged = true;
                    cnt++;
                    result += map[nx][ny];
                    q.add(new Integer[]{nx, ny});
                }

            }
        }
        if (isChanged) {
            int peopleCnt = result/cnt;
            for (int i=0; i<N; i++) {
                for (int j=0; j<N; j++) {
                    if (visited[i][j][0] == 1) {
                        visited[i][j][0] = peopleCnt;
                    }
                }
            }
        }
        return isChanged;
    }

    public static void changePeople() {
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (visited[i][j][0] != 0) {
                    map[i][j] = visited[i][j][0];
                }
            }
        }
    }
}
