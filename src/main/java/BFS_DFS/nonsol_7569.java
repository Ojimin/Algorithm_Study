package BFS_DFS;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

//7569-토마토, M,N,H, . 보관 후 하루가 지나면, 익은 토마토들의 인접한 곳에 있는 익지 않은 토마토들은 익은 토마토의 영향을 받아 익게 된다.
// 하나의 토마토에 인접한 곳은 위, 아래, 왼쪽, 오른쪽, 앞, 뒤 여섯 방향에 있는 토마토를 의미한다.
//대각선 방향에 있는 토마토는 영향 x,
//출력 : 며칠이 지나면 토마토를 모두 익는지 최소 일자 구하기
//BFS
//유기농 배추랑 비슷한듯
//다시 - 날짜 세는 것 때문에
//생각해야할 점 : 날짜 세는게 좀 까다롭다
public class nonsol_7569 {
    static int M, N, H;
    static int[][][] graph;
    static boolean[][][] visited;
    static int[] dx = {0, 0, 0, 0, -1, 1}; //위아래왼쪽오른쪽앞뒤
    static int[] dy = {0, 0, -1, 1, 0, 0};
    static int[] dz = {1, -1, 0, 0, 0, 0};
    static int result = 0; //일수세기
    static int cnt =0;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        M = Integer.parseInt(st.nextToken());
        N = Integer.parseInt(st.nextToken());
        H = Integer.parseInt(st.nextToken());
        graph = new int[N][M][H];
        visited = new boolean[N][M][H];

        for (int i = 0; i < H; i++) {
            for (int j = 0; j < N; j++) {
                st = new StringTokenizer(br.readLine());
                for (int k = 0; k < M; k++) {
                    graph[j][k][i] = Integer.parseInt(st.nextToken());
                }
            }
        }
        //토마토 모두 익었는지 체크
        int tmp = 0; //토마토 익은것+토마토 없는 것 개수
        int tomato = 0; //아직 익지 않은 토마토 개수
        for (int i = 0; i < H; i++) {
            for (int j = 0; j < N; j++) {
                for (int k = 0; k < M; k++) {
                    if (graph[j][k][i] == 1 || graph[j][k][i] == -1) {
                        tmp++;
                    } else {
                        tmp--;
                        tomato++;
                    }
                }
            }
        }
        if (tmp == N * M * H) {
            System.out.println(result); //0출력, 다 익음
        } else {
            //종료조건 - cnt 개수가 늘지 않으면
            while(true) {
                int prev_cnt = cnt;
                for (int i = 0; i < H; i++) {
                    for (int j = 0; j < N; j++) {
                        for (int k = 0; k < M; k++) {
                            if (graph[j][k][i] == 1) {//다음날 되면 갔던 곳 다시 방문해야하므로
                                bfs(j, k, i);
                            }
                        }
                    }
                }
                result++;
                if (cnt == prev_cnt) {
                    break;
                }
                if (cnt==tomato) {
                    break;
                }
            }
            if (cnt!=tomato) System.out.println(-1);
            else System.out.println(result);
        }
    }
    //다 탐색후 토마토 익은 개수와 토마토가 들어있는 개수가 동일하면 일수출력, 그렇지 않으면 -1 출력
    public static void bfs(int x, int y, int z) {
        visited[x][y][z] = true;
        Queue<List<Integer>> queue = new LinkedList<>();
        queue.offer(List.of(x, y, z));
        while (!queue.isEmpty()) {
            List<Integer> list = queue.poll();

            for (int i = 0; i < 6; i++) {
                int nx = list.get(0) + dx[i];
                int ny = list.get(1) + dy[i];
                int nz = list.get(2) + dz[i];
                if (nx >= 0 && nx < N && ny >= 0 && ny < M && nz >= 0 && nz < H) {
                    if (graph[nx][ny][nz] == 0 && !visited[nx][ny][nz]) {
                        graph[nx][ny][nz] = 1;
                        visited[nx][ny][nz] = true;
                        cnt++;
                    }
                }
            }
        }
    }
}
