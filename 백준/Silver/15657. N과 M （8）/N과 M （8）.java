
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

// 15657 - N과M(8)
// N개중 M개 & 중복
// 출력: 오름차순으로 중복조합
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
        dfs(0,0,new int[M]);
        System.out.println(sb);
    }

    public static void dfs(int depth, int start, int[] tmp) {
        if (depth == M) {
            for (int num:tmp) {
                sb.append(num).append(" ");
            }
            sb.append("\n");
            return;
        }
        for (int i=start; i<N; i++) {
            tmp[depth] = arr[i];
            dfs(depth+1, i, tmp);
        }
    }
}
