
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

// 4803 - 트리
// 그래프가 주어졌을 때, 트리 개수 세기 => 트리 : 사이클이 없는 연결 요소 & 정점 n개, 간선이 n-1, 임의의 두 정점에 대한 경로 유일
// 입력 마지막 줄은 0이 2개 주어짐
// 출력 : 테스트 케이스 번호 + 트리가 없다면 "No trees.", 한개라면 "There is one tree.", T개라면 "A forest of T trees"
// 모든 정점이 해당 트리 이루고 있음 => 트리 1개, 아니면 나머지 정점이 각각 트리 1개로 침
// 포인트 : 트리 만들고 개수 세기 => 개수 셀때는 연결되어있는게 1개, 남은 정점별로 1개, 그리고 에외상황
// 트리가 아닌 경우 : 1. 싸이클이 되는 경우 2. 임의의 두 정점에 대한 경로가 유일하지 않을 때
// 다시 - 꼭 트리로 구현해서 풀려고 안해도됨 => 보통 dfs, bfs랑 엮어서 많이 나옴
public class Main {
    static StringBuilder sb = new StringBuilder();
    static ArrayList<Integer>[] graph;
    static int cnt = 0;
    static boolean[] visited;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int t=0;
        while(true) {
            t++;
            StringTokenizer st = new StringTokenizer(br.readLine());
            int n = Integer.parseInt(st.nextToken());
            int m = Integer.parseInt(st.nextToken());
            if (n==0 && m==0) break;
            graph = new ArrayList[n+1];
            cnt = 0;
            visited = new boolean[n+1];
            for (int i=1; i<=n; i++) {
                graph[i] = new ArrayList<>();
            }
            for (int i=0; i<m; i++) {
                st = new StringTokenizer(br.readLine());
                int x = Integer.parseInt(st.nextToken());
                int y= Integer.parseInt(st.nextToken());
                graph[x].add(y);
                graph[y].add(x);
            }
            for (int i=1;i<=n; i++) {
                if (!visited[i]){
                    bfs(i);
                }
            }
            sb.append("Case "+t+": ");
            switch(cnt) {
                case 0:
                    sb.append("No trees.").append("\n");
                    break;
                case 1 :
                    sb.append("There is one tree.").append("\n");
                    break;
                default:
                    sb.append("A forest of "+cnt+" trees.").append("\n");
                    break;
            }
        }
        System.out.println(sb);
    }
    // bfs
    public static void bfs(int n) {
        Queue<Integer> q = new LinkedList<>();
        q.offer(n);
        visited[n] = true;
        int edge = 0;
        int v = 1;
        while(!q.isEmpty()){
            int now = q.poll();
            for (int i=0;i<graph[now].size(); i++) {
                if (!visited[graph[now].get(i)]) {
                    visited[graph[now].get(i)] = true;
                    v++;
                    q.offer(graph[now].get(i));
                }
                edge++;
            }
        }
        if (edge/2 +1 == v) cnt++;
    }
}
