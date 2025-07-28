
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

// 15664 - N과M(10)
// N개중 M개 & 중복x
// 배열에 중복된 수도 존재
public class Main {
    static int N, M, arr[];
    static HashSet<List<Integer>> set = new LinkedHashSet<>();
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
        dfs(0,0, new ArrayList<>());
        for (List<Integer> list: set) {
            for (int i=0; i<M; i++) {
                sb.append(list.get(i)).append(" ");
            }
            sb.append("\n");
        }
        System.out.println(sb);
    }
    public static void dfs(int depth, int start, List<Integer> list) {
        if (depth == M) {
            set.add(new ArrayList<>(list));
            return;
        }
        for (int i=start; i<N; i++) {
            list.add(arr[i]);
            dfs(depth+1, i+1, list);
            list.remove(list.size()-1);
        }
    }

}
