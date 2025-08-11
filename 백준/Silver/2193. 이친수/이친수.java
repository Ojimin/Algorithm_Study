
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.Buffer;

// 2193 - 이친수
// 0과 1로 이루어져있고 이친수는 0으로 시작x & 1이 두번 연속 나타나지 않음
// 출력 : N자리의 이친수 갯수 구하기
// 이전해에 이어서 값을 구하기에 이전 해의 값을 저장해서 활용
public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        long[] dp = new long[n+1];
        dp[1] = 1;
        for (int i=2; i<=n;i++) {
            dp[i] = dp[i-1] + dp[i-2];
        }
        System.out.println(dp[n]);
    }
}
