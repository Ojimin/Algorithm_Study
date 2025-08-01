
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

// 1003 - 피보나치 함수
// 출력 : fibonacci(N)을 호출했을 때, 0과 1이 각각 몇번 호출되는지 출력
public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());
        StringBuilder sb = new StringBuilder();
        int[][] dp = new int[41][2];
        dp[0][0] = 1;
        dp[0][1] = 0;
        dp[1][0] = 0;
        dp[1][1] = 1;
        for (int i=2; i<=40; i++) {
            dp[i][0] = dp[i-1][0] + dp[i-2][0];
            dp[i][1] = dp[i-1][1] + dp[i-2][1];
        }
        for (int i=0; i<T; i++) {
            int num = Integer.parseInt(br.readLine());
            sb.append(dp[num][0] +" " + dp[num][1]).append("\n");
        }
        System.out.println(sb);
    }
}
