package BFS_DFS;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;
import java.util.StringTokenizer;

//2178 - 미로탐색,1,1에서 출발하여 N,M의 위치로 이동할 때 지나야하는 최소 칸 수 구하기
//인접행렬, 최소칸수 => 최단경로-방문거리를 저장할배열이 필요, bfs,큐
//다시볼점 - 스캐너 이용해서 입력받음, 최단거리이므로 bfs로 구현
//방문거리 테이블 - 출발지점부터 해당 좌표지점까지의 거리 계산
public class sol_2178 {
    static int N, M;
    static int[][] graph;//인접한 것들 다 더해서 따로 dist배열 따로 두지 않고 해결할 수도 있다
    static boolean[][] visited;
    static int[][] dist; //방문 거리를 저장할 배열
    static int[] dx = {-1, 0, 1, 0}; //상우하좌
    static int[] dy = {0, 1, 0, -1}; //상우하좌

    public static void main(String[] args) throws IOException {
        Scanner sc = new Scanner(System.in);
        N = sc.nextInt();
        M = sc.nextInt();
        graph = new int[N + 1][M + 1];
        visited = new boolean[N + 1][M + 1];
        dist = new int[N + 1][M + 1];

        for (int i = 1; i < N + 1; i++) {
            String input = sc.next(); //bufferedreader로도 할 수 있을듯
            for (int j = 1; j < M + 1; j++) {
                graph[i][j] = Character.getNumericValue(input.charAt(j - 1));
            }
        }
        bfs(1, 1);
        System.out.println(dist[N][M] + 1); //출발지점 좌표 포함시켜야함
    }

    public static void bfs(int x, int y) {
        //방문하면 해당 노드의 visitied는 true
        //범위 넘는지 체크&visited 체크
        Queue<int[]> queue = new LinkedList<>();
        queue.offer(new int[]{x, y}); //1,1 좌표 큐에 넣기
        visited[x][y] = true;

        while (!queue.isEmpty()) {
            int[] now = queue.poll(); //출발지점 포함x
            for (int i = 0; i < 4; i++) {
                int nx = now[0] + dx[i];
                int ny = now[1] + dy[i];
                if (nx >= 1 && nx <= N && ny >= 1 && ny <= M) { //범위 체크
                    if (graph[nx][ny] == 1 && !visited[nx][ny]) {
                        visited[nx][ny] = true;
                        dist[nx][ny] = dist[now[0]][now[1]] + 1;
                        queue.add(new int[]{nx, ny});
                    }
                }
            }
        }
    }
}
