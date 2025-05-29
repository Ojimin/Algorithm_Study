import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

// 2146 - 다리 만들기
// 가장 짧은 다리 하나를 놓아 두 대륙을 연결하는 방법 찾기
// 짧다 : 격자에서 차지하는 칸의 수가 가장 작은 다리
// 0 - 바다, 1- 육지
// 다리를 연결한다의 기준 : 변과 변을 맞댛었을 때
// bfs
// 1. 각 섬들은 같은 영토면 같은 숫자, 그게 아니면 다른 숫자로
// 2. 0이 지나갈 있는 거리라 했을때, 각 영토에서 다른 숫자인 섬에 도달하는 최소 거리 구하기 갱신해서
// 최적화 필요
public class Main {
    static int N;
    static int[][] graph;
    static boolean[][] visited;
    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1};
    static int min = Integer.MAX_VALUE;
    static int[][] dist;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        StringTokenizer st;
        graph = new int[N][N];
        visited = new boolean[N][N];
        dist = new int[N][N];

        // 입력 받기
        for (int i =0; i<N; i++){
            st = new StringTokenizer(br.readLine());
            for (int j =0; j<N; j++){
                graph[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        // 구역 나누기
        int order = 0;
        for (int i =0; i<N; i++){
            for (int j =0; j<N; j++){
                if ( graph[i][j] == 1 && !visited[i][j]){
                    order++;
                    bfs(i, j, order);
                }
            }
        }

        // 다시 돌면서 짧은 다리 구하기
        for (int i=0;i<N;i++) {
            for (int j=0;j<N;j++) {
                if (graph[i][j] != 0){
                    visited = new boolean[N][N];
                    dist = new int[N][N];
                    getShortest(i, j);
                }
            }
        }

        System.out.println(min-1);
    }

    public static void bfs(int i, int j, int order) {
        Queue<Integer[]> queue = new LinkedList<>();
        queue.add(new Integer[]{i,j});
        visited[i][j] = true;
        graph[i][j] = order;

        while (!queue.isEmpty()){
            Integer[] now = queue.poll();
            for (int k = 0; k < 4; k++){
                int nx = now[0] + dx[k];
                int ny = now[1] + dy[k];

                if (nx < 0 || ny < 0 || nx >= N || ny >= N) continue;
                if (graph[nx][ny] == 1 && !visited[nx][ny]){
                    visited[nx][ny] = true;
                    graph[nx][ny] = order;
                    queue.add(new Integer[]{nx, ny});
                }
            }
        }
    }
    public static void getShortest(int i, int j) {
        Queue<Integer[]> queue = new LinkedList<>();
        queue.add(new Integer[]{i,j});
        visited[i][j] = true;

        while (!queue.isEmpty()){
            Integer[] now = queue.poll();
            for (int k = 0; k < 4; k++){
                int nx = now[0] + dx[k];
                int ny = now[1] + dy[k];

                if (nx < 0 || ny < 0 || nx >= N || ny >= N) continue;
                if (graph[nx][ny] == 0 && !visited[nx][ny]){
                    visited[nx][ny] = true;
                    queue.add(new Integer[]{nx, ny});
                    dist[nx][ny] = dist[now[0]][now[1]] + 1;
                }
                else if (graph[nx][ny] != 0 && graph[nx][ny] != graph[i][j]){
                    dist[nx][ny] = dist[now[0]][now[1]] + 1;
                    if (dist[nx][ny] < min){
                        min = dist[nx][ny];
                    }
                }

                if (dist[nx][ny] > min) return;
            }

        }
    }

}
