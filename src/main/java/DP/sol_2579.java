package DP;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

// 2579 - 게단 오르기
// 계단 오르기 규칙 -
// 규칙 1. 한번에 1 or 2 계단씩 오를 수 있음
// 규칙 2. 연속된 세 계단을 모두 밟아서는 안됨. 시작점은 계단x
// 규칙 3. 마지막 도착 계단은 반드시 밟아야 함
// 출력 : 얻을 수 있는 점수의 최댓값
// 1<= 계단 개수 <= 300
// 다시 - 케이스 분리가 안됐음 & 접근 방식을 해당 계단을 밟는데, 어떤 케이스로 밟는지를 생각함
// 1. 마지막 계단 밟았을때 최대점수 = 이전밟은 계단의 최대 점수 + 현재 계단 점수
// 2. 이전 밟은 계단의 최대 점수 = max(1번째 계단 최대 점수, 2번째 전 계단 최대점수)
// jump(k) : k번째 계단을 첫번째로 밟는 경우의 최대 점수값 - k-2번째 계단의 jum/step 케이스 중 큰 값을 부분해로 포함
// step(k) : k번째 계단을 연속해서 두번째로 밟는 경우의 최대 점수값 - k-1번째 계단의 Jump 케이스를 부분해로 포함
public class sol_2579 {
    static int[][] dp; // 0은 최댓값 저장, 1은 해당 숫자 포함?
    static int[] arr;
    static int N;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        arr = new int[N+1];
        dp = new int[N+1][2];
        for (int i=1; i<=N; i++) {
            arr[i] = Integer.parseInt(br.readLine());
        }

        if (N==1) {
            System.out.println(arr[1]);
            return;
        } else if (N==2) {
            System.out.println(arr[1]+arr[2]);
            return;
        } else {
            solve();
        }
        int answer = Math.max(dp[N][0],dp[N][1]);
        System.out.println(answer);

    }
    // 0 - jump, 1 - step
    public static void solve() {
        dp[1][0] = arr[1];
        dp[1][1] = 0;
        dp[2][0] = arr[2];
        dp[2][1] = dp[1][0] + arr[2];

        for (int i=3; i<=N; i++) {
            // jump : 2번째 이전 계단의 최대 점수 + 현재 점수
            dp[i][0] = Math.max(dp[i-2][0], dp[i-2][1]) + arr[i];
            // step : 1번째 이전 jump 점수 + 현재 점수
            dp[i][1] = dp[i-1][0] + arr[i];
        }
    }
}
