package BFS_DFS;

//11725 - 트리의 부모 찾기 : 트리의 루트를 1이라고 정했을때, 각 노드의 부모를 구하는 프로그램 작성
//트리, dfs로 구현해야할것같음, 인접리스트로 해야할 것 같은데?, 부모노드: 바로 전 노드
//인접리스트를 리스트+리스트로 구현해볼게
//다시
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class sol_11725 {
    static int N;
    static ArrayList<ArrayList<Integer>> list;
    static boolean[] visited;
    static int[] result;
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
        //나를 호출한 직전 애가 부모노드라고
        visited[node] = true;
        for (int v : list.get(node)) {
            if(!visited[v]) {
                dfs(v);
                result[v]=node;
            }
        }
    }
}
