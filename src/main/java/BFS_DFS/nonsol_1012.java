package BFS_DFS;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

//유기농 배추 : 지렁이는 인접한 다른 배추 상하좌우로 이동 가능, 심어놓은 배추에서 총 몇 마리의 지렁이가 필요한지 출력
//dfs?
//다시
public class nonsol_1012 {
    static int M,N, K;
    static int[][] graph;
    static int[] dx = {0,0,-1,1}; //상하좌우
    static int[] dy = {1,-1,0,0,};
    static boolean[][] visited;
    static int cnt;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        int T = Integer.parseInt(br.readLine());
        for (int i=0; i<T; i++) {
            cnt = 0;
            String[] input = br.readLine().split(" ");
            M = Integer.parseInt(input[0]);
            N = Integer.parseInt(input[1]);
            K = Integer.parseInt(input[2]);
            graph = new int[M][N];
            visited = new boolean[M][N];

            for (int j=0; j<K; j++) {
                st = new StringTokenizer(br.readLine());
                int x = Integer.parseInt(st.nextToken());
                int y = Integer.parseInt(st.nextToken());
                graph[x][y] = 1;
            }

            //dfs 호출 및 결과 출력
            for (int j=0; j<M; j++) {
                for (int k=0; k<N; k++) {
                    if (graph[j][k] == 1 & !visited[j][k]) {
                        dfs(j,k);
                        cnt+=1;
                    }
                }
            }
            System.out.println(cnt);
        }
    }
    public static void dfs(int x, int y) {
        visited[x][y] = true;
        for (int i=0; i<4; i++) {
            int next_x = x+ dx[i];
            int next_y = y+dy[i];
            if (next_x >=0 && next_y>=0 && next_x<M && next_y<N) {
                if (graph[next_x][next_y] == 1 && !visited[x][y]) {
                    dfs(next_x, next_y);
                }
            }
        }
    }
}
