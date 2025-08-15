import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

// 11404 - 플로이드
// n개의 도시, 한 도시 -> 다른 도시에 도착하는 m개의 버스가 있고 버스 탈때마다 비용 존재
// 모든 도시 A-B로 가는데 필요한 비용의 최솟값
// 출력 : i-j로 가는데 필요한 최소 비용, 갈 수 없는 경우에 그 자리에 0을 출력
public class Main {
    static int n, m;
    static int dp[][];
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        m = Integer.parseInt(br.readLine());
        dp = new int[n+1][n+1];
        StringBuilder sb = new StringBuilder();
        for (int i=1; i<=n; i++) {
            for (int j=1; j<=n; j++) {
                if (i==j) {
                    dp[i][j] = 0;
                } else dp[i][j] = 1_000_000_000;
            }
        }
        for (int i=0; i<m; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int cost = Integer.parseInt(st.nextToken());
            dp[a][b] = Math.min(dp[a][b], cost);
        }

        for (int k=1; k<=n; k++) {
            for (int i=1;i<=n; i++) {
                for (int j=1;j<=n; j++) {
                    if (i==j) continue;
                    dp[i][j] = Math.min(dp[i][j], dp[i][k] + dp[k][j]);
                }
            }
        }

        for (int i=1;i<=n; i++) {
            for (int j=1;j<=n; j++) {
                if(dp[i][j] == 1_000_000_000) {
                    sb.append(0+" ");
                } else sb.append(dp[i][j] +" ");
            }
            sb.append("\n");
        }
        System.out.println(sb);
    }
}
