import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

// 1932 - 정수 삼각형
// 출력: 위에서부터 내려오면서 합이 최대가 되는 경로에 있눈 수의 합
// 이동 가능 : 현재 층에서 선택된 수의 대각선 왼쪽 / 대각선 오른쪽
// 1<= N <= 500
// 백트래킹 or dp => 백트래킹 하기에는 n이 너무 큼
// dp테이블을 어떤 방식으로 만들까?
public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int arr[][] = new int[N][N];
        int[][] dp = new int[N][N];

        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j=0; j<i+1; j++) {
                arr[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        // dp 테이블 - 위에를 기준으로 아래를 채움
        dp[0][0] = arr[0][0];

        for (int i=0; i<N-1; i++) {
            for (int j=0; j<=i; j++) {
                dp[i+1][j] = Math.max(dp[i+1][j], dp[i][j] + arr[i+1][j]);
                dp[i+1][j+1] = Math.max(dp[i+1][j+1], dp[i][j] + arr[i+1][j+1]);
            }
        }

        int max = Integer.MIN_VALUE;
        for (int i=0; i<N; i++) {
            max = Math.max(max, dp[N-1][i]);
        }
        System.out.println(max);
    }
}
