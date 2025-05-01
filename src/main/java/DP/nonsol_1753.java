//package DP;
//
//import java.io.BufferedReader;
//import java.io.InputStreamReader;
//import java.util.StringTokenizer;
//
//// 최단경로(다익스트라) - 1753
//// 출발노드 설정 - 출방노드 기준으로 각 노드의 최소 비용 저장 - 방문x노드 중 가장 비용이 적은 노드 선택 - 해당 노드를 거쳐서 특정한 노드로 가는 경우를 고려하여 최소 비용 갱신 - 이걸 반복
//public class nonsol_1753 {
//    static int V, E;
//    static int K;
//    static boolean[] visited;
//    static int[] distance;
//    static int[][] graph;
//
//    public static void main(String[] args) throws Exception {
//        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
//        StringTokenizer st = new StringTokenizer(br.readLine());
//        V=Integer.parseInt(st.nextToken());
//        E=Integer.parseInt(st.nextToken());
//        K= Integer.parseInt(br.readLine());
//
//        visited = new boolean[V+1];
//        distance = new int[V+1];
//        for (int i=0; i<V+1;i++) {
//            distance[i]=Integer.MAX_VALUE;
//        }
//        graph = new int[V+1][V+1];
//        // 모든 간선 정보 입력받기
//        for (int i=0; i<E; i++) {
//            st = new StringTokenizer(br.readLine());
//            int u = Integer.parseInt(st.nextToken());
//            int v = Integer.parseInt(st.nextToken());
//            int w = Integer.parseInt(st.nextToken());
//            graph[u][v] = w;
//        }
//
//    }
//
//    public static int getSmallestV() {
//        int min_value = Integer.MIN_VALUE;
//        int index=0;
//        for (int i=1; i<V+1; i++) {
//            if (distance[i] < min_value && !visited[i]) {
//                min_value=distance[i];
//                index = i;
//            }
//        }
//        return index;
//    }
//
//    public static void dijkstra(int start) {
//        distance[start] = 0;
//        visited[start] = true;
//        for (int j =0; j<3; j++) {
//            distance[]
//        }
//        for
//    }
//
//
//}
