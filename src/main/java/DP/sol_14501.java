package DP;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// 14501 - 퇴사
// N+1일재 되는날 퇴사를 하기 위해서 N일 동안 최대 많은 상담
// 주의 : 상담 소요 기간은 당일 포함해서 계산 & N+1일부터 회사X, 넘어가면 계산 X
// 출력 : 최대 수익
// dp? 그리디? 완탐?
// 배낭 문제 처럼?
// 스스로 풀었지만 시간 오래 걸림. 누적합 다시
public class sol_14501 {
    static int N;
    static int[][] arr;
    static int[] dp;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        arr = new int[N+1][2];
        dp = new int[N+1];
        for (int i=1; i<=N; i++){
            StringTokenizer st = new StringTokenizer(br.readLine());
            arr[i][0] = Integer.parseInt(st.nextToken()); // 걸리는 시간
            arr[i][1] = Integer.parseInt(st.nextToken()); // 받을 수 있는 금액
        }
        solve();
        System.out.println(dp[N]);
    }
    // 그냥 담았을 때 그전것들이랑 비교해서 더 큰 것 선택
    public static void solve() {
       // N=1
        if (arr[1][0] <= N) dp[arr[1][0]] = arr[1][1];
        for (int i=2; i<=N; i++) {
           if (arr[i][0]+i-1 <=N) {
               int day = arr[i][0]+(i-1); // 날짜 계산시 -1 해줘야함
               dp[day] = Math.max(dp[day], arr[i][1]+dp[i-1]); //i번째 날짜부터 시작해서~day날에 끝나는가치+i-1번째날까지 최대의 가치값, 기존 저장되어이 있는 dp 값 비교
           }
           // 계산 후, i번재 날짜와 i-1번째 날짜와 비교 후 더 큰 값으로 갱신
           dp[i] = Math.max(dp[i], dp[i-1]);
       }
    }
}
