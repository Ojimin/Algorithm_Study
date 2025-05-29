
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

// 15663 - N과 M(9)
// 출력 : 사전순, 중복되는 수열 X
// 주어진 입력에서 중복되는 숫자가 나올 수도 있음
// 중복 X 순열
public class Main {
    static int N, M;
    static int[] arr;
    static boolean[] visited;
    static Set<ArrayList<Integer>> set = new LinkedHashSet<>();
    static ArrayList<Integer> tmp;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        arr = new int[N];
        visited = new boolean[N];
        tmp = new ArrayList<>();
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }
        Arrays.sort(arr);
        permutation(0);
        for (ArrayList<Integer> list : set) {
            for(int i : list) {
                System.out.print(i + " ");
            }
            System.out.println();
        }
    }
    public static void permutation(int depth) {
        if (depth == M) {
            set.add(new ArrayList<>(tmp));
            return;
        }
        for (int i=0; i<N; i++) {
            if (!visited[i]) {
                visited[i] = true;
                tmp.add(arr[i]);
                permutation(depth+1);
                visited[i] = false;
                tmp.remove(tmp.size()-1);
            }
        }
    }
}
