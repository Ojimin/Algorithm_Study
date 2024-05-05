package BFS_DFS;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

//2606 웜바이러스 - 1번컴퓨터가 웜 바이러스에 걸렸을 때 이를 통해 걸리게 되는 컴퓨터의 수 출력
//그래프 탐색,dfs
//다른분들 hashset, set
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
    //1번 노드와 연결된 것들만 찾기
    public static int dfs(int now) {
        if (visited[now]) return 0;
        visited[now] = true;
        int cnt = 1;

        for (int i=1;i<node+1;i++) {
            if(Graph[now][i]==1 && !visited[i]) {
                cnt+=dfs(i);
            }
        }
        return cnt;
    }
}

