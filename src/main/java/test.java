import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class test {
    static int n;
    static int[][] graph;
    static boolean[][] visited;
    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1};

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        n = Integer.parseInt(br.readLine());
        graph = new int[n][n];
        visited = new boolean[n][n];
        List<Integer> result = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < n; j++) {
                graph[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (!visited[i][j]) {
                    int cnt = bfs(i, j);
                    if (cnt != 0) {
                        result.add(cnt);
                    }
                }
            }
        }
        if (result.size() == 0) {
            System.out.println(0);
        } else {
            Collections.sort(result);
            System.out.println(result.size());
            for (int num : result) System.out.print(num + " ");
        }
    }

    public static int bfs(int x, int y) {
        Queue<List<Integer>> queue = new LinkedList<>();
        queue.offer(Arrays.asList(x, y));
        int cnt = 0;
        while (!queue.isEmpty()) {
            List<Integer> now = queue.poll();
            int now_x = now.get(0);
            int now_y = now.get(1);
            if (graph[now_x][now_y]==1 && !visited[now_x][now_y]) {
                cnt++;
            }
            visited[now_x][now_y] =true;

            for (int i = 0; i < 4; i++) {
                int nx = now_x + dx[i];
                int ny = now_y + dy[i];
                if (nx >= 0 && nx < n && ny >= 0 && ny < n) {
                    if (graph[nx][ny] == 1 && !visited[nx][ny]) {
                        cnt++;
                        visited[nx][ny] = true;
                        queue.offer(Arrays.asList(nx, ny));
                    }

                }
            }
        }
        return cnt;
    }
}
