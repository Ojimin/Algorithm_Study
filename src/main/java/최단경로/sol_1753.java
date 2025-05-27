package 최단경로;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

// 1753 - 최단경로
// 방향그래프 & 시작저에서 다른 모든 정점까지의 최단경로 & 경로가 존재하지 않으면 INF 출력
// 전형적인 다익스트라 문제 - 가중치가 양수 & 하나의 정점 - 다른 모든 정점까지
// 출력 : i번쨰 줄에 i번 정점으로의 최단경로 경로값 출력, 출발점 자신은 0, 경로없으면 INF 출력
// 인접리스트로 풀음
// 다시! - 왜 우선순위 큐를 썼어야만 했을까?
public class sol_1753 {
    public static class Node implements Comparable<Node>{
        int index;
        int cost;

        public Node(int index, int cost) {
            this.index = index;
            this.cost = cost;
        }

        @Override
        public int compareTo(Node o) {
            return Integer.compare(this.cost, o.cost); // 오름차순 우선순위가 높음
        }
    }

    static int[] dist; // 거리 기록 배열
    static boolean[] check; //해당 정점 방문 배열
    static ArrayList<Node>[] graph;
    static int INF = Integer.MAX_VALUE;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        StringBuilder sb = new StringBuilder();
        int V = Integer.parseInt(st.nextToken());
        int E = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(br.readLine()); // 시작 정점
        graph = new ArrayList[V+1];
        dist = new int[V+1];
        check = new boolean[V+1];
        Arrays.fill(dist, INF); // 거리 배열 초기화
        dist[K] = 0; //시작점 정점 거리 초기화

        for (int i = 1; i <= V; i++) {
            graph[i] = new ArrayList<>();
        }
        for (int i=1; i<=E; i++) {
            st = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            int w = Integer.parseInt(st.nextToken());
            graph[u].add(new Node(v, w));
        }
        dijkstra(K);

         // 처음 시작점
        for (int i=1; i<=V; i++) {
            if (i==K) sb.append(0).append("\n");
            else if (dist[i] == INF) sb.append("INF").append("\n");
            else sb.append(dist[i]).append("\n");
        }
        System.out.println(sb);
    }

    public static void dijkstra(int K) {
        PriorityQueue<Node> pq = new PriorityQueue<>(); //꺼낸 정점에 인접한 정점 + 거리값 넣기
        pq.add(new Node(K, 0)); // 시작점 우선순위 큐에 넣기

        while(!pq.isEmpty()) {
            Node nowVertex = pq.poll();
            if (check[nowVertex.index]) continue;
            check[nowVertex.index] = true; //방문 처리

            // 해당 노드의 인접 노드 탐색
            for (Node node : graph[nowVertex.index]) {
                if (dist[node.index] > dist[nowVertex.index] + node.cost) { //이 정점을 거쳐야하는 경우에 최소면 갱신 + 우선순위큐에 넣기
                    dist[node.index] = dist[nowVertex.index] + node.cost;
                    pq.offer(new Node(node.index, dist[node.index]));
                }
            }
        }
    }
}
