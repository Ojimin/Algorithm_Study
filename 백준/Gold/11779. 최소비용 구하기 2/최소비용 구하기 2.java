
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

// 11779 - 최소비용 구하기2
// n개의 도시 & m개의 버스, A -> B 도시로 가는데 비용 최소화
// 출력: 특정 출발점-도착점이 주어질때, 최소 비용 & 최소비용을 갖는 경로에 포함되어있는 도시 개수 & 최소 비용을 갖는 경로를 방문하는 도시 순서대로 출력
// 다시 - (주의) 동일 경로에 대해 다른 비용 값 들어올 수 있음
public class Main {
    static class Node {
        int idx;
        int cost;

        public Node(int idx, int cost) {
            this.idx = idx;
            this.cost = cost;
        }
    }
    static Deque<Integer> deque;
    static int n, m;
    static int[][] graph;
    static int[] dist;
    static int[] prev;
    static int start, end;
    static StringBuilder sb;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        m = Integer.parseInt(br.readLine());
        graph = new int[n+1][n+1];
        dist = new int[n+1];
        prev = new int[n+1];
        sb = new StringBuilder();
        deque = new ArrayDeque<>();
        for (int i=0; i<=n; i++) {
            Arrays.fill(graph[i], Integer.MAX_VALUE);
        }
        // 중복돼서 나오나?
        for (int i=0; i<m; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int cost = Integer.parseInt(st.nextToken());
            graph[a][b] = Math.min(graph[a][b], cost);
        }

        StringTokenizer st = new StringTokenizer(br.readLine());
        start = Integer.parseInt(st.nextToken());
        end = Integer.parseInt(st.nextToken());

        dijkstra();
        traceRoute();
        sb.append(dist[end]).append("\n");
        sb.append(deque.size()).append("\n");

        while(!deque.isEmpty()) {
            sb.append(deque.pollLast()+" ");
        }
        System.out.print(sb);
    }

    public static void dijkstra() {
        PriorityQueue<Node> pq = new PriorityQueue<>(new Comparator<Node>() {
            @Override
            public int compare(Node o1, Node o2) {
                return o1.cost - o2.cost;
            }
        });
        boolean[] check = new boolean[n+1];
        Arrays.fill(dist, 1_000_000_000);
        dist[start] = 0;
        pq.offer(new Node(start, 0));

        while(!pq.isEmpty()) {
            Node now = pq.poll();
            int nowIdx = now.idx;
            if (check[nowIdx]) continue;
            check[nowIdx] = true;

            for (int i=1; i<=n; i++) {
                if (graph[nowIdx][i] == Integer.MAX_VALUE) continue;
                if (dist[i] > dist[nowIdx] + graph[nowIdx][i]) {
                    dist[i] = dist[nowIdx] + graph[nowIdx][i];
                    prev[i] = nowIdx;
                    pq.offer(new Node(i, dist[i]));
                }
            }
        }
    }

    public static void traceRoute() {
        deque.offer(end);
        int prevIdx = prev[end];
        while(true) {
            deque.offer(prevIdx);
            if(prevIdx==start) {
                break;
            }
            prevIdx = prev[prevIdx];
        }
    }
}
