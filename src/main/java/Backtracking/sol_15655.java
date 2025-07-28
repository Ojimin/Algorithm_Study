package Backtracking;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

// 15655 - N과 M(6)
// N개의 자연수 중 M개를 고른 수열 & 오름차순
// 출력 : 중복되는 수열 X & 사전순으로
// 조합
public class sol_15655 {
    static int N, M;
    static boolean[] visited;
    static int[] arr;
    static StringBuilder sb = new StringBuilder();
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        visited = new boolean[N];
        arr = new int[N];
        st = new StringTokenizer(br.readLine());
        for (int i=0; i<N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }
        int[] result = new int[M];
        Arrays.sort(arr);
        dfs(0,0, result);
        System.out.println(sb);
    }
    public static void dfs(int depth, int start, int[] result) {
        if (depth == M) {
            for (int i=0;i<M; i++) {
                sb.append(result[i]).append(" ");
            }
            sb.append("\n");
            return;
        }
        for (int i=start; i<N; i++) {
            if (!visited[i]) {
                visited[i] = true;
                result[depth] = arr[i];
                dfs(depth+1, i+1, result);
                visited[i] = false;
            }

        }
    }
}
