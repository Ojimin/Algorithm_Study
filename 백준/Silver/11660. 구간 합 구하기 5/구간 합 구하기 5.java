import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// 11660 - 구간 합 구하기5
// x1, y1 -> x2, y2 까지 합 구하는 프로그램
// 다시 - 아이디어가 생각이 안남
// 누적합, 구간합
// sum[1][1] = sum[0][1]+sum[1][0] -sim[0][0] + map[1][1];
public class Main {
    static int N, M;
    static int[][] arr;
    static int[][] sum;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        StringBuilder sb = new StringBuilder();
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        arr = new int[N+1][N+1];
        sum = new int[N+1][N+1];

        for (int i = 1;i<=N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j=1;j<=N;j++){
                arr[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        // 누적합 구하기
        for (int i=1; i<=N; i++) {
            for (int j=1; j<=N; j++) {
                sum[i][j] = sum[i-1][j] + sum[i][j-1] -sum[i-1][j-1] + arr[i][j];
            }
        }

        for (int i=1; i<=M; i++) {
            st = new StringTokenizer(br.readLine());
            int x1 = Integer.parseInt(st.nextToken());
            int y1 = Integer.parseInt(st.nextToken());
            int x2 = Integer.parseInt(st.nextToken());
            int y2 = Integer.parseInt(st.nextToken());
            // 구간합 구하기
            int answer = sum[x2][y2] - sum[x2][y1-1] - sum[x1-1][y2] + sum[x1-1][y1-1];
            sb.append(answer).append("\n");
        }
        System.out.println(sb);
    }
}
