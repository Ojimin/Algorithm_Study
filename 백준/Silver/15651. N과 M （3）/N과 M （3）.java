
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

// 15651 - N과 M(3)
// 1~N까지 자연수 중 M개를 고른 수열 & 같은 수 여러번 골라도 됨
// 출력 : 중복되는 수열은 출력 X & 사전순으로 증가하는 순서로 출력
public class Main {
    static int N, M;
    static StringBuilder sb = new StringBuilder();
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        ArrayList<Integer> arr = new ArrayList<>();
        backtracking(0, arr);
        System.out.println(sb);
    }
    public static void backtracking(int depth, ArrayList<Integer> arr) {
        if (depth == M) {
            for (int i=0; i<arr.size(); i++) {
                sb.append(arr.get(i)).append(" ");
            }
            sb.append("\n");
            return;
        }
        for (int i=1; i<=N; i++) {
            arr.add(i);
            backtracking(depth+1,arr);
            arr.remove(arr.size()-1);
        }
    }
}
