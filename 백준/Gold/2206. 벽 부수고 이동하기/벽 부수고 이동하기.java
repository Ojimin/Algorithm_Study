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
// 내가 고려하지 못했던 것 - 최단거리일때만 재방문가능하게 바꾼것 => 그래서 gpt가 추천한 방법은 visited를 3차원 배열로 두어 벽을 부수고 방문/ 부수지 않고 방문
public class Main {
    static int N, M;
    static int[][] map;
    static boolean[][][] visited;
//    static int[][] dist;
    //    static int cnt = 1; //벽 부수기
    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1};
//    static boolean isPossible = false;

    static class Node {
        int x, y, dist, broken;
        Node(int x, int y, int dist, int broken) {
            this.x = x;
            this.y = y;
            this.dist = dist;
            this.broken = broken;
        }
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        map = new int[N][M];
        visited = new boolean[N][M][2]; // 0이면 안부숨, 1이면 부숨
//        dist = new int[N][M];
//        dist[N-1][M-1] = Integer.MAX_VALUE;
        for (int i=0; i<N; i++) {
            String line = br.readLine();
            for (int j=0; j<M; j++) {
                map[i][j] = Character.getNumericValue(line.charAt(j));
            }
        }
        int result = bfs();
//        if(isPossible) System.out.prrintln(dist[N-1][M-1]);
        System.out.println(result);
    }

    public static int bfs() {
        Queue<Node> q = new LinkedList<>();
        q.add(new Node(0,0, 1, 0));
        visited[0][0][0] = true;
//        dist[0][0] = 1;
//        int[][] copyMap = new int[N][M]; // 벽 개수 셀것
//        for (int i=0; i<N; i++) {
//            copyMap[i] = Arrays.copyOf(map[i], M);
//        }
//        if(N==1 && M==1) {
//            isPossible = true;
//            return;
//        }
        while(!q.isEmpty()) {
           Node now = q.poll();
           if (now.x == N-1 && now.y == M-1) {
               return now.dist;
           }
            for (int i=0; i<4; i++) {
                int nx = now.x + dx[i];
                int ny = now.y + dy[i];
                // 방문은 했지만 최단거리일 경우에는 한번더 탐색을 하도록 허용을 해줌
                if (nx >= 0 && ny >=0 && nx<N && ny<M ) {
                    // 빈 공간
                    if (map[nx][ny] == 0 && !visited[nx][ny][now.broken]) {
                        visited[nx][ny][now.broken] = true;
                        q.add(new Node(nx, ny, now.dist+1, now.broken));
                    }

                    // 벽인데 아직 안 부쉈을 때
                    if (map[nx][ny] == 1 && now.broken == 0 && !visited[nx][ny][now.broken]) {
                        visited[nx][ny][now.broken] = true;
                        q.add(new Node(nx, ny, now.dist+1, 1));
                    }
                }
            }
        }
        return -1;
    }
}

