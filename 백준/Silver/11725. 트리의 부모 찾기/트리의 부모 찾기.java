import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Main {
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
        visited[node] = true;
        for (int v : list.get(node)) {
            if(!visited[v]) {
                dfs(v);
                result[v]=node;
            }
        }
    }
}