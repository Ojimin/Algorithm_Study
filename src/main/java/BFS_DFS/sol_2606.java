package BFS_DFS;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

//웜바이러스 - 1번컴퓨터가 웜 바이러스에 걸렸을 때 이를 통해 걸리게 되는 컴퓨터의 수 출력
//그래프 탐색 문제인듯
//다시
public class sol_2606 {
    static int node, edge;
    static boolean[] visited;
    static int[][] Graph;

    public static void main(String args[]) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        node = Integer.parseInt(br.readLine());
        edge = Integer.parseInt(br.readLine());
        Graph = new int[node + 1][node + 1];
        visited = new boolean[node + 1];

        for (int i = 0; i < edge; i++) {
            st = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            Graph[u][v] = Graph[v][u] = 1;
        }
        System.out.println(dfs(1)-1);
    }

    public static int dfs(int now) {
        if(visited[now]) return 0;
        visited[now] = true;
//        System.out.println(now + " "); //현재노드 출력
        int cnt = 1;

        for (int i = 1; i < node+1; i++) {
            //아직 방문하지 않은 간선 존재하면
            if (!visited[i]) {
                cnt += dfs(i);
            }
        }
        return cnt;
    }
}
