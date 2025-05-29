import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;

// 15654 - N과 M(5)
// 주어진 숫자 중에 m개 고르기
// 순열
// 주의 : 사전순으로 증가하는 순서로 출력
public class Main {
    static int N, M;
    static int[] arr;
    static boolean[] visited;
    static ArrayList<Integer> list;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        arr = new int[N];
        st = new StringTokenizer(br.readLine());
        for (int i=0; i<N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }
        Arrays.sort(arr);
        visited = new boolean[N];
        list = new ArrayList<>();
        permutation(0);
    }
    public static void permutation(int depth) {
        if (depth == M) {
            for (int num : list) {
                System.out.print(num + " ");
            }
            System.out.println();
            return;
        }
        for (int i = 0; i<N; i++) {
            if (!visited[i]) {
                visited[i] = true;
                list.add(arr[i]);
                permutation(depth+1);
                visited[i] = false;
                list.remove(list.size()-1);
            }
        }
    }
}
