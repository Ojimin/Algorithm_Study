import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

//15656 - N과M(7)
// N개의 자연수 중 M개를 고른 수열 & 중복 가능
// 출력 : 사전 순으로 증가하는 순서로 출력
// 중복 순열
public class Main {
    static int N, M;
    static int[] arr;
    static StringBuilder sb = new StringBuilder();
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        arr = new int[N];
        st = new StringTokenizer(br.readLine());
        for (int i=0;i<N;i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }
        Arrays.sort(arr);
        dfs(0, new int[M]);
        System.out.println(sb);
    }
    public static void dfs(int depth, int[] tmp) {
        if (depth == M) {
            for (int num : tmp) {
                sb.append(num).append(" ");
            }
            sb.append("\n");
            return;
        }

        for (int i=0;i<N;i++) {
            tmp[depth] = arr[i];
            dfs(depth+1, tmp);
        }

    }
}
