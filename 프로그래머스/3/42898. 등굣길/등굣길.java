// 물에 잠기지 않은 지역을 통해 학교를 가려고 함
// 오른쪽 & 아래쪽으로만 움직여 집에서 학교까지 갈 수 있는 최단거리
// 기존 코드 : answer 직전 지점에서만 개수 셈 => dp라기보다는 뭔가 경로찾기 느낌
// 변경: 해당 지점의 (이전)왼쪽과 위쪽을 더해서 계산. 즉, 이전의 최적해르 합해서 계산. dp원리 활용
import java.util.*;
import java.lang.*;

class Solution {
    static final int mod = 1_000_000_007;
    public int solution(int m, int n, int[][] puddles) {
        int answer = 0;
        int[][] dp = new int[n+1][m+1];
        boolean[][] blocked = new boolean[n+1][m+1];
        
        for (int i=0;i<puddles.length; i++) {
            blocked[puddles[i][1]][puddles[i][0]] = true;
        }
        dp[1][1] = 1;
                
        for (int i=1; i<=n; i++) {
            for (int j=1; j<=m; j++) {
                if (i==1 && j==1) continue;
                if(blocked[i][j]) {
                    dp[i][j] = 0;
                    continue;
                }
                int fromTop = dp[i-1][j];
                int fromLeft = dp[i][j-1];
                dp[i][j] = (fromTop+fromLeft) % mod;
            }
        }
        answer = dp[n][m];
        return answer;
    }
}