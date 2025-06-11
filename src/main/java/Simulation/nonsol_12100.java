package Simulation;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// 12100 - 2048(Easy)
// 규칙 1. 한번의 이동에서 이미 합쳐진 블록은 또 합쳐질 수 없음
// 규칙 2. 똑같은 값 세개가 있으면 이동하려고 하는 쪽의 칸이 먼저 합쳐짐 => 위쪽으로 이동시 위쪽에 있는 블록
// 출력 : 최대 5번 이동해서 만들 수 있는 가장 큰 블록의 값 => 무조건 5번까지는 이동해야함
// 주의 1. 어느 방향으로 이동을 해야 최대값이 나올까? => 여러 경우의 수를 돌리면서 최대한 값이 크게 만들어야함
// 주의 2. 각 블록은 한 이동에 한번만 합쳐질 수 있음
// 백트래킹 + 시뮬
// 반례 : 0처리 안함 & 제일 최소, 제일 최대일때 인덱스에만 저장하도록 변경, visited 변경, 같은 이차원배열을 쓰면 원본값도 바뀜 =>
// 다시
public class nonsol_12100 {
    static int N;
    static int[] dx = {-1, 0, 1, 0}; // 위 오른쪽 아래 왼쪽
    static int[] dy = {0, 1, 0, -1};
    static int max = 0;
    static boolean[][] visited;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        int[][] map = new int[N][N];

        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
                if (map[i][j] > max) max = map[i][j];
            }
        }
        dfs(0, map);
        System.out.println(max);
    }

    // 최대 5번 이동시키기
    // 제한 : depth & 더이상 이동해도 변한게 없으면?
    // move 할 때 중요한 것 : 어느 방향으로 이동하는 지에 따라 먼저 탐색하는 인덱스가 달라짐
    public static void dfs(int depth, int[][] map) {
        if (depth == 5) return;

        for (int i =0; i<4; i++) {
            int[][] newMap = moveBoard(map, i);
            dfs(depth+1, newMap);
        }
    }

//    public static boolean checkMove(int[][] map, int dir) {
//
//    }

    // move 할 때 중요한 것 : 어느 방향으로 이동하는 지에 따라 먼저 탐색하는 인덱스가 달라짐
    public static int[][] moveBoard(int[][] oldMap, int dir) {
        int[][] map = new int[N][N];
        for (int i = 0; i < N; i++) {
            System.arraycopy(oldMap[i], 0, map[i], 0, N);
        }
        visited = new boolean[N][N];

        if (dir == 0){ // 위
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < N; j++) {
                    if (!visited[i][j] && map[i][j] != 0) {
                        int nx = i + dx[dir];
                        int ny = j + dy[dir];
                        if (nx < 0 || ny < 0 || nx >= N || ny >= N || visited[nx][ny]) continue;
                        else if (map[nx][ny] == map[i][j]) {
                            int moveX = Math.min(i, nx);
                            int moveY = j;
                            map[moveX][moveY] = map[nx][ny] * 2;
                            map[Math.max(i, nx)][j] = 0;
                            // 최댓값 갱신
                            if (map[moveX][moveY] > max) max = map[moveX][moveY];
                            // 비어있고 x값이 작은 곳을 찾아서 저장
                            int minX = moveX;
                            for (int k = moveX; k>=0; k--) {
                                if (minX > k && map[k][moveY] == 0) minX = k;
                            }
                            if(minX != moveX) {
                                map[minX][moveY] = map[moveX][moveY];
                                map[moveX][moveY] = 0;
                            }
                            visited[minX][moveY] = true;
                        } // map[nx][ny]가 0일때 => 합쳐진 것은 아니라 visited 처리는 안해도될듯
                        else if (map[nx][ny] == 0) {
                            map[nx][ny] = map[i][j];
                            map[i][j] = 0;
                            int minX = nx;
                            for (int k = nx; k>=0; k--) {
                                if (minX > k && map[k][ny]==0) minX = k;
                            }
                            if(minX != nx) {
                                map[minX][ny] = map[nx][ny];
                                map[nx][ny] = 0;
                            }
                        }
                    }

                }
            }

        } else if (dir == 1) { // 오른쪽
            for (int i = 0; i < N; i++) {
                for (int j=N-1; j>=0; j--) {
                    if (!visited[i][j]  && map[i][j] != 0) {
                        int nx = i + dx[dir];
                        int ny = j + dy[dir];
                        if (nx < 0 || ny < 0 || nx >= N || ny >= N || visited[nx][ny]) continue;
                        else if (map[nx][ny] == map[i][j]) {
                            int moveX = i;
                            int moveY = Math.max(j, ny);
                            map[moveX][moveY] = map[nx][ny] * 2;
                            map[moveX][Math.min(j, ny)] = 0;
                            // 최댓값 갱신
                            if (map[moveX][moveY] > max) max = map[moveX][moveY];
                            // 비어있고 y값이 큰 곳을 찾아서 저장
                            int maxY = moveY;
                            for (int k = moveY; k<N; k++) {
                                if (k > maxY && map[moveX][k] ==0) maxY = k;
                            }
                            if(maxY != moveY) {
                                map[moveX][maxY] = map[moveX][moveY];
                                map[moveX][moveY] = 0;
                            }
                            visited[moveX][maxY] = true;
                        } // map[nx][ny]가 0일때 => 합쳐진 것은 아니라 visited 처리는 안해도될듯
                        else if (map[nx][ny] == 0 && map[i][j] != 0) {
                            map[nx][ny] = map[i][j];
                            map[i][j] = 0;
                            int maxY = ny;
                            for (int k = ny; k<N; k++) {
                                if (k>maxY && map[nx][k] == 0) maxY = k;
                            }
                            if (maxY != ny) {
                                map[nx][maxY] = map[nx][ny];
                                map[nx][ny] = 0;
                            }
                        }
                    }
                }
            }

        } else if (dir == 2) { // 아래
            for (int i=N-1; i>=0; i--) {
                for (int j=0; j<N; j++){
                    if (!visited[i][j] && map[i][j] != 0) {
                        int nx = i + dx[dir];
                        int ny = j + dy[dir];
                        if (nx < 0 || ny < 0 || nx >= N || ny >= N || visited[nx][ny]) continue;
                        else if (map[nx][ny] == map[i][j]) {
                            int moveX = Math.max(i, nx);
                            int moveY = j;
                            map[moveX][moveY] = map[nx][ny] * 2;
                            map[Math.min(i, nx)][j] = 0;
                            // 최댓값 갱신
                            if (map[moveX][moveY] > max) max = map[moveX][moveY];
                            // 비어있고 x값이 큰 곳을 찾아서 저장
                            int maxX = moveX;
                            for (int k = moveX; k<N; k++) {
                                if (k > maxX && map[k][moveY] == 0) maxX = k;
                            }
                            if (maxX != moveX) {
                                map[maxX][moveY] = map[moveX][moveY];
                                map[moveX][moveY] = 0;
                            }
                            visited[maxX][moveY] = true;
                        } // map[nx][ny]가 0일때 => 합쳐진 것은 아니라 visited 처리는 안해도될듯
                        else if (map[nx][ny] == 0 && map[i][j] != 0) {
                            map[nx][ny] = map[i][j];
                            map[i][j] = 0;
                            int maxX = nx;
                            for (int k = nx; k<N; k++) {
                                if (k > maxX && map[k][ny] == 0) maxX = k;
                            }
                            if (maxX != nx) {
                                map[maxX][ny] = map[nx][ny];
                                map[nx][ny] = 0;
                            }
                        }
                    }
                }

            }


        } else if (dir == 3) { // 왼쪽
            for (int i=0; i<N; i++) {
                for (int j = 0; j<N; j++) {
                    if (!visited[i][j] && map[i][j] != 0) {
                        int nx = i + dx[dir];
                        int ny = j + dy[dir];
                        if (nx < 0 || ny < 0 || nx >= N || ny >= N || visited[nx][ny]) continue;
                        else if (map[nx][ny] == map[i][j]) {
                            int moveX = i;
                            int moveY = Math.min(j, ny);
                            map[moveX][moveY] = map[nx][ny] * 2;
                            map[i][Math.max(j, ny)] = 0;
                            // 최댓값 갱신
                            if (map[moveX][moveY] > max) max = map[moveX][moveY];
                            // 비어있고 y값이 작은 곳을 찾아서 저장
                            int minY = moveY;
                            for (int k = moveY; k>=0; k--) {
                                if (minY > k && map[moveX][k] ==0) minY = k;
                            }
                            if (minY != moveY) {
                                map[moveX][minY] = map[moveX][moveY];
                                map[moveX][moveY] = 0;
                            }
                            visited[moveX][minY] = true;
                        } // map[nx][ny]가 0일때 => 합쳐진 것은 아니라 visited 처리는 안해도될듯
                        else if (map[nx][ny] == 0) {
                            map[nx][ny] = map[i][j];
                            map[i][j] = 0;
                            int minY = ny;
                            for (int k = ny; k>=0; k--) {
                                if (minY > k && map[nx][k] == 0) minY = k;
                            }
                            if (minY != ny) {
                                map[nx][minY] = map[nx][ny];
                                map[nx][ny] = 0;
                            }
                        }
                    }

                }
            }

        }
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                System.out.print(map[i][j] + " ");
            }
            System.out.println();
        }
        System.out.println();

        return map;
    }
}
