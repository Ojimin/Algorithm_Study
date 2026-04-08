
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

// 11724 - 연결 요소의 개수
// 출력: 연결 요소의 개수
// 하나로 연결되어있는지 판단하는 건데 dfs?
// 해당 문제는 인접리스트 형태가 더 시간복잡도 낮음.
public class Main {
    static int N, M;
    static int[][] map;
    static ArrayList<Integer>[] graph;
    static boolean[] visited;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        map= new int[N+1][N+1];
        graph= new ArrayList[N+1];
        visited = new boolean[N+1];
        int cnt = 0;
        for (int i=1; i<=N; i++) {
            graph[i] = new ArrayList<>();
        }
        for (int i=0;i<M; i++) {
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            map[x][y] = 1;
            map[y][x] = 1; //방향 없는 그래프이므로
            graph[x].add(y);
            graph[y].add(x);
        }

        for (int i=1;i<=N; i++) {
            if (!visited[i]) {
                dfs(i);
                cnt++;
            }
        }

        System.out.println(cnt);
    }

    public static void dfs(int x) {
        visited[x]=true;
        for (int y: graph[x]) {
            if(!visited[y]) {
                dfs(y);
            }
        }

    }
}
