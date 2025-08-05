
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.StringTokenizer;

// 15665 - N과M(11)
// N개 중 M개 고름, 중복 가능
// 중복 순열
public class Main {
    static int N, M, list[];
    static HashSet<String> set = new LinkedHashSet<>();
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        list = new int[N];
        st = new StringTokenizer(br.readLine());
        for (int i=0; i<N; i++) {
            list[i] = Integer.parseInt(st.nextToken());
        }
        Arrays.sort(list);
        dfs(0,new int[M]);
        StringBuilder sb = new StringBuilder();
        for (String s : set) {
            s = s.replaceAll("\\[","");
            s = s.replaceAll("\\]","");
            for (int i=0; i<s.length(); i++) {
                if (s.charAt(i) != ',') sb.append(s.charAt(i));
            }
            sb.append("\n");
        }
        System.out.println(sb);
    }

    public static void dfs(int depth, int[] arr) {
        if (depth == M) {
            set.add(Arrays.toString(arr));
            return;
        }
        for (int i=0;i<N; i++) {
            arr[depth] = list[i];
            dfs(depth+1, arr);
        }
    }
}
