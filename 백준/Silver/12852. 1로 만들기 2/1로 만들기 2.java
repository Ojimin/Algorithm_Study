
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

// 12852 - 1로 만들기2
// 세개의 연산 (3으로 나누어떨어지면 3으로나누고 2로나누어떨어지면 2로 나누고 1을빼고) 적절히 사용하여 1만들기
// 연산을 사용하는 횟수가 최솟값이 되어야함
// 출력 : 첫째줄은 연산을 하는 횟수의 최솟값, 둘째줄은 1로 만드는 방법에 포함되어 있는 수를 공백 구분해서 순서대로 출력
// dp + backtracing으로?
public class Main {
    static int N;
    static int[][] dp;
    static boolean isFound = false;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        dp = new int[N+1][2];
        StringBuilder sb = new StringBuilder();
        for (int i=1;i<=N; i++) {
            Arrays.fill(dp[i], Integer.MAX_VALUE);
        }
        dp[N][0] = 0;
        dp[N][1] = N;
        int last = 0; // 연산 횟수
        if (N == 1) {
            System.out.println(last);
            System.out.println(N);
            return;
        }
        while(true) {
            boolean isEnd = false;
            for (int i=N; i>=1; i--) {
                if (dp[i][0] == last) {
                    if (i % 3 == 0) {
                        if (dp[i/3][0] > last+1) {
                            dp[i/3][0] = last+1;
                            dp[i/3][1] = i;
                            if (i/3 == 1) {
                                isEnd = true;
                                break;
                            }
                        }
                    }
                    if (i % 2 ==0) {
                        if (dp[i/2][0] > last+1) {
                            dp[i/2][0] = last+1;
                            dp[i/2][1] = i;
                            if (i/2 == 1) {
                                isEnd = true;
                                break;
                            }
                        }
                    }
                    if (dp[i-1][0] > last+1) {
                        dp[i-1][0] = last+1;
                        dp[i-1][1] = i;
                        if (i-1 == 1) {
                            isEnd = true;
                            break;
                        }
                    }
                }
            }
            last++;
            if (isEnd) break;
        }
        System.out.println(last);
        int[] arr = new int[last+1];
        arr[0] = 1;
        for (int i=1;i<last+1;i++) {
            arr[i] = dp[arr[i-1]][1];
        }
        for (int i=last; i>=0; i--) {
            sb.append(arr[i]).append(" ");
        }
        System.out.println(sb);
    }
}
