package BFS_DFS;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

// 13023 - ABCDE
// 0~N-1로 번호가 매겨져 있음
// 출력 : 서로 다른 5개 연속된 노드가 존재하는지(or 순환되는 간선 존재?)  => 있으면 1, 없으면 0
// 인접 리스트의 dfs?
// a->b 이면 a와 b가 친구라는 뜻. 즉 양방향 그래프
// set으로 풀면 엄청 오래걸림 => depth로 풀자!!
public class sol_13023 {
    static ArrayList<Integer>[] graph;
    static boolean[] visited;
//    static HashSet<Integer> set;
    static boolean isExisted = false;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        graph = new ArrayList[n];
        for (int i = 0; i < n; i++) {
            graph[i] = new ArrayList<>();
        }

        for (int i=0; i<m; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            graph[a].add(b);
            graph[b].add(a);
        }

        for (int i=0; i<n; i++) {
            visited = new boolean[n];
//            set = new HashSet<>();
            dfs(i, 1);
            if (isExisted) break;
        }
        if (isExisted) System.out.println(1);
        else System.out.println(0);
    }

    public static void dfs(int v, int depth) {
        visited[v] = true;
        //add하는 기준이 있어야 하는데, 이때 전에서 이어지는가?
//        set.add(v);
        if (depth == 5) {
            isExisted = true;
            return;
        }
        if (depth > 5) return;

        for (int i=0; i<graph[v].size(); i++) {
            if (!visited[graph[v].get(i)]){
                dfs(graph[v].get(i), depth+1);
                visited[graph[v].get(i)] = false;
            }
        }
    }
}
