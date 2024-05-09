package BFS_DFS;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

//16234 - 인구이동, 각각의 땅에 나라가 존재,
//두 나라의 인구 차이가 L명 이상, R명 이하이면 두 나라가 공유하는
//국경선을 오늘 하루 동안 연다
//국경선이 열려있어 인접한 칸만을 이용해 이동할 수 있다면 그나라를 오늘 하루동안은 연합이라 한다
//연합을 이루는 각 칸의 인구수는 연합의 인구수/연합을 이루는 칸의 개수, 편의상 소수점 버림
//연합 해체, 모든 국경선 닫음
//인구 이동 없을 때까지 반복
//연합은 하나인듯
//dfs대신 bfs로 풀어야할듯=> 구현과 탐색 알고리즘
//다시
public class sol_16234 {
    static int N, L, R;
    static int[][] graph;
    static int[] dx = {-1, 0, 1, 0};
    static int[] dy = {0, -1, 0, 1};
    static boolean[][] visited;
    static List<Node> list;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        L = Integer.parseInt(st.nextToken());
        R = Integer.parseInt(st.nextToken());
        graph = new int[N][N];
        visited = new boolean[N][N];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                graph[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        System.out.println(move());
    }

    //더 이상 인구이동이 일어나지 않을때까지 반복
    public static int move() {
        int result = 0; // 일수 출력
        while (true) {
            boolean isMove = false;
            visited = new boolean[N][N];
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < N; j++) {
                    if (!visited[i][j]) {
                        int sum = bfs(i, j); //dfs탐색으로 열릴 수 있는 국경선 확인하며 인구 이동할 총 인구수 반환
                        if (list.size() > 1) {
                            changePopulation(sum); //열린 국경선 내의 노드들 인구 변경
                            isMove = true;
                        }
                    }
                }
            }
            if (!isMove) return result;
            result++;
        }
    }

    public static int bfs(int x, int y) {
        Queue<Node> q = new LinkedList<>();
        q.offer(new Node(x, y));
        list = new ArrayList<>();
        list.add(new Node(x, y));
        visited[x][y] = true;
        int sum = graph[x][y];
        while (!q.isEmpty()) {
            Node now = q.poll();
            for (int i = 0; i < 4; i++) {
                int nx = now.x + dx[i];
                int ny = now.y + dy[i];
                if (nx >= 0 && nx < N && ny >= 0 && ny < N && !visited[nx][ny]) {
                    int sub = Math.abs(graph[now.x][now.y] - graph[nx][ny]);
                    if (sub >= L && sub <= R) {
                        q.offer(new Node(nx, ny));
                        list.add(new Node(nx, ny));
                        sum += graph[nx][ny];
                        visited[nx][ny] = true;
                    }
                }
            }
        }
        return sum;
    }
//    public static int dfs(int x, int y) {
//        list = new ArrayList<>();
//        list.add(new Node(x, y));
//        visited[x][y] = true;
//        int sum = graph[x][y];
//        for (int i = 0; i < 4; i++) {
//            int nx = x + dx[i];
//            int ny = y + dy[i];
//            if (nx >= 0 && nx < N && ny >= 0 && ny < N) {
//                int sub = Math.abs(graph[x][y] - graph[nx][ny]);
//                if (sub >= L && sub <= R && !visited[nx][ny]) {
//                    list.add(new Node(nx, ny));
//                    dfs(nx,ny);
//                    sum += graph[nx][ny];
//                }
//            }
//        }
//        return sum;
//    }

    public static void changePopulation(int sum) {
        int avg = sum / list.size();
        for (Node n : list) {
            graph[n.x][n.y] = avg;
        }
    }

    public static class Node {
        int x;
        int y;

        public Node(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
}
