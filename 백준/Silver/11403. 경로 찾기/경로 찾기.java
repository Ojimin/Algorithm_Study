import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// 11403 - 경로 찾기
// i -> j 로 가는 길이의 양수인 경로가 있는지 없는지 구함
// i -> j로 가는 인접행렬 주어지고 1인 경우는 간선이 있음. 0이면 없음
// 출력 : i에서 j로 가는 길이가 양수인 경로가 있으면 i번째 줄의 j번째 숫자를 1로, 없으면 0
public class Main {
    static int n, graph[][], answer[][];
    static boolean[] visited;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        graph = new int[n][n];
        answer = new int[n][n];
        StringBuilder sb = new StringBuilder();
        for (int i=0; i<n; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j=0;j<n;j++) {
                graph[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        for(int i=0; i<n; i++) {
            visited = new boolean[n];
            dfs(i, i);
        }
        for (int i=0; i<n; i++) {
            for (int j=0; j<n; j++) {
                sb.append(answer[i][j] +" ");
            }
            sb.append("\n");
        }
        System.out.println(sb);
    }

    public static void dfs(int start, int now) {
        if (visited[now]) return;
        visited[now] = true;
        for (int i=0; i<n; i++) {
            if (graph[now][i] == 1) {
                answer[start][i] = 1;
                dfs(start, i);
            }
        }
    }
}
