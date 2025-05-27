package Backtracking;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

// 15649 - N과 M(1)
// 1~ N 자연수 중 중복없이 M개를 고른 수열
// 출력 : 구한 수열을 모두 출력함 사전식으로
// 중복 없는 순열
public class sol_15649 {
    static int N, M;
    static boolean[] visited;
    static ArrayList<Integer> arr;
    static StringBuilder sb = new StringBuilder();
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        visited = new boolean[N+1];
        arr = new ArrayList<>();
        dfs(0);
        System.out.println(sb);
    }

    public static void dfs(int depth) {
        if (depth == M) {
            for (int num : arr) {
                sb.append(num).append(" ");
            }
            sb.append("\n");
            return;
        }
        for (int i=1; i<=N; i++) {
            if (!visited[i]) {
                visited[i] = true;
                arr.add(i);
                dfs(depth+1);
                visited[i] = false;
                arr.remove(arr.size()-1);
            }

        }
    }
}
