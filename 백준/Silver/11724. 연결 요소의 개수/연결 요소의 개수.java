
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// 11724 - 연결 요소의 개수
// 출력: 연결 요소의 개수
// 하나로 연결되어있는지 판단하는 건데 dfs?
public class Main {
    static int N, M;
    static int[][] map;
    static boolean[] visited;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        map= new int[N+1][N+1];
        visited = new boolean[N+1];
        int cnt = 0;
        for (int i=0;i<M; i++) {
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            map[x][y] = 1;
            map[y][x] = 1; //방향 없는 그래프이므로
        }

        for (int i=1;i<=N; i++) {
            if (!visited[i]) {
                visited[i]=true;
                dfs(i);
                cnt++;
            }
        }

        System.out.println(cnt);
    }

    public static void dfs(int x) {
        for (int i=1; i<=N; i++) {
            if(map[x][i]==1 && !visited[i]) {
                visited[i] = true;
                dfs(i);
            }
        }

    }
}
