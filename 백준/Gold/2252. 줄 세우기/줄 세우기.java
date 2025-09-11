
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

// 2252 - 줄 세우기
// 일부 학생들의 키를 비교한 결과 주어졌을 때, 줄 세우기
// 출력: 줄세우기한 학생 순서대로
public class Main {
    static int N, M, indegree[];
    static StringBuilder sb = new StringBuilder();
    static ArrayList<Integer>[] graph;
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        graph = new ArrayList[N+1];
        indegree = new int[N+1];
        for (int i=0; i<=N; i++) {
            graph[i] = new ArrayList<>();
        }
        for (int i=0; i<M; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            indegree[b] += 1;
            graph[a].add(b);
            
        }
        topologySort();
        System.out.println(sb);
    }
    public static void topologySort() {
        Queue<Integer> q = new LinkedList<>();
        for (int i=1; i<=N; i++) {
            if (indegree[i] == 0) q.offer(i);
        }
        while(!q.isEmpty()) {
            int now = q.poll();
            sb.append(now+" ");
            for (int i=0; i<graph[now].size(); i++) {
                indegree[graph[now].get(i)] -= 1;
                if (indegree[graph[now].get(i)] == 0) q.offer(graph[now].get(i));
            }
        }
    }

}
