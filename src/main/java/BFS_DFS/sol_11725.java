package BFS_DFS;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

//11725- 트리의 부모 찾기, 트리의루트를 1이라고 가정, 각 노드의 부모 구하기
//그래프탐색(인접연결리스트), dfs
//visited[true]이면 돌아가는 시점에서 result[i]=now;
public class sol_11725 {
    static int N;
    static ArrayList<ArrayList<Integer>> list; //인접리스트생성
    static boolean[] visited;
    static int[] result; //각 부모노드 저장할 배열
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        N = Integer.parseInt(br.readLine());
        list = new ArrayList<>();
        visited = new boolean[N+1];
        result = new int[N+1];

        for (int i=0; i<=N; i++) list.add(new ArrayList<>());

        for (int i=0; i<N-1; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            list.get(a).add(b);
            list.get(b).add(a);
        }
        dfs(1);

        for (int i=2; i<N+1; i++) {
            System.out.println(result[i]);
        }
    }

    public static void dfs(int node) {
        if(visited[node]) return ;
        visited[node] = true;
        for (int v : list.get(node)) {
            if(!visited[v]) {
                dfs(v);
                result[v]=node;
            }
        }
    }
}
