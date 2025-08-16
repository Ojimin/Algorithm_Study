
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

// 1238 - 파티
// N개의 숫자로 구분된 각 마을에 한명의 학생이 살고있음==N명
// N명 학생이 X번 마을에 모여서 파티.  M개의 단방향 도로들 i번째 길을 지나는데 Ti의 사간을 소비함
// 도로들은 모두 단방향, N명의 학생들 중 오고 가는데 가장 많은 시간 소비하는 학생 누구?
// 출력: N명의 학생중, X번 마을까지 오고가는데(왕복) 가장 오래 걸린 학생의 소요시간
// A->B로 가는 도로는 최대 1개로 제한
// 다시 : 역방향 그래프!!!! => 2번만 다익스트라 계산해서 최단거리 계산 가능
public class Main {
    static class Node {
        int idx;
        int cost;

        public Node(int idx, int cost) {
            this.idx = idx;
            this.cost = cost;
        }
    }
    static int N, M, X;
    static int[] distX;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        X = Integer.parseInt(st.nextToken());
        ArrayList<Node>[] graph = new ArrayList[N+1];
        ArrayList<Node>[] reGraph = new ArrayList[N+1];
        int answer = Integer.MIN_VALUE;
        for (int i=0; i<=N; i++) {
            graph[i] = new ArrayList<>();
            reGraph[i] = new ArrayList<>();
        }
        for (int i=0; i<M; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int cost = Integer.parseInt(st.nextToken());
            graph[a].add(new Node(b, cost));
            reGraph[b].add(new Node(a, cost));
        }

        distX = new int[N+1];
        int dist[] = new int[N+1];
        dijkstra(X, distX, graph);
        dijkstra(X, dist, reGraph);
        for (int i=1; i<=N; i++) {
            int time = dist[i] + distX[i];
            answer = Math.max(answer, time);
        }
        System.out.println(answer);
    }

    public static void dijkstra(int start, int[] dist, ArrayList<Node>[] graph) {
        PriorityQueue<Node> pq = new PriorityQueue<>(new Comparator<Node>() {
            @Override
            public int compare(Node o1, Node o2) {
                return o1.cost - o2.cost;
            }
        });
        boolean[] check = new boolean[N+1];
        pq.offer(new Node(start, 0));
        Arrays.fill(dist, 1_000_000_000);
        dist[start] = 0;

        while(!pq.isEmpty()) {
            Node now = pq.poll();
            int nowIdx = now.idx;
            if(check[nowIdx]) continue;
            check[nowIdx] = true;

            for (Node next : graph[nowIdx]) {
                if (dist[next.idx] > dist[nowIdx] + next.cost) {
                    dist[next.idx] = dist[nowIdx] + next.cost;
                    pq.offer(new Node(next.idx, dist[next.idx]));
                }
            }
        }
    }
}
