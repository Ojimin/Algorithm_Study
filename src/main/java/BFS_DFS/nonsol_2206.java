package BFS_DFS;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

// 2206 - 벽 부수고 이동하기
// N*M 행렬, 0 - 이동 가능 & 1 - 이동 불가
// 벽 한개까지는 부수고 이동 가능 + 한개 안부셔도 됨
// 출력 : 최단거리 출력(처음과 끝 포함) & 불가능하면 -1
// 그냥 N,M까지 가는 최단거리 구하고 해당 루트가 1이 1개이면 최단거리 설정, 아니면 X
// 9시 30분~ 10.39, 11.42~
// 앞에서 먼저 부수냐, 뒤에서 부수냐
public class nonsol_2206 {
    static int N, M;
    static int[][] map;
    static boolean[][] visited;
    static int[][] dist;
    //    static int cnt = 1; //벽 부수기
    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1};
    static boolean isPossible = false;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        map = new int[N][M];
        visited = new boolean[N][M];
        dist = new int[N][M];
        dist[N-1][M-1] = Integer.MAX_VALUE;
        for (int i=0; i<N; i++) {
            String line = br.readLine();
            for (int j=0; j<M; j++) {
                map[i][j] = Character.getNumericValue(line.charAt(j));
            }
        }
        bfs();
        if(isPossible) System.out.println(dist[N-1][M-1]);
        else System.out.println("-1");
    }

    public static void bfs() {
        Queue<Integer[]> q = new LinkedList<>();
        q.add(new Integer[]{0, 0});
        visited[0][0] = true;
        dist[0][0] = 1;
        int[][] copyMap = new int[N][M]; // 벽 개수 셀것
        for (int i=0; i<N; i++) {
            copyMap[i] = Arrays.copyOf(map[i], M);
        }
        if(N==1 && M==1) {
            isPossible = true;
            return;
        }
        while(!q.isEmpty()) {
            Integer[] now = q.poll();
            for (int i=0; i<4; i++) {
                int nx = now[0] + dx[i];
                int ny = now[1] + dy[i];
                // 방문은 했지만 최단거리일 경우에는 한번더 탐색을 하도록 허용을 해줌
                if (nx >= 0 && ny >=0 && nx<N && ny<M ) {
                    if (visited[nx][ny] && dist[nx][ny] != (dist[now[0]][now[1]] +1)) continue;
                    if (nx == N-1 && ny == M-1) {
//                    System.out.println("목표지점 도달. 장애물 값: " + copyMap[now[0]][now[1]]);
                        int tmp = dist[now[0]][now[1]] +1;
                        if (tmp < dist[nx][ny] && copyMap[now[0]][now[1]] <=1) {
                            dist[nx][ny] = tmp;
                            isPossible = true;
                        }
                    }
                    else {
                        int tmp = copyMap[now[0]][now[1]] + map[nx][ny];
                        if (tmp <= 1) { // 장애물 계산 값이 1이하인 경우만 탐색한다ㅋ
                            // 처음 탐색
                            if (!visited[nx][ny]) {
                                dist[nx][ny] = dist[now[0]][now[1]] + 1;
                                q.add(new Integer[]{nx, ny});
                                visited[nx][ny] = true;
                                copyMap[nx][ny] = tmp;
                            } else if (tmp < copyMap[nx][ny]) { // 탐색했던 경우
                                q.add(new Integer[]{nx, ny});
                                copyMap[nx][ny] = tmp;
                            }

                        }
                    }
                }
            }
        }
    }
}

