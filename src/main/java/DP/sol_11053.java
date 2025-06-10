package DP;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

// 11053 - 가장 긴 증가하는 부분 수열
// 시작하는 수로 작은걸로 저장하고 0에는 이전 작은 수 기준으로 길이 수 센것, 1은 최신 수로 업데이트 도니걸로 길이 세서
// 기존 길이 앞서면 바로 0으로 바꿔치기
// 포인트 : 무조건 크다고 담는것이 아님. 뒤에 나오는 숫자가 작으면 담지 않는 것이 좋음
// 다시
// LIS : 해당 수보다 작은 값들 중, 부분 최적해 중 최대길이가 되는 값에서 이어 붙여 최적해 구하기
// LIS[i] = max(LIS[j]) + 1, i>j && A[i] > A[j}
// 재귀 or 반복문(=>1부터 하나씩 증가시키면서 해당 인덱스 전까지 돌면서 기준이 된 값보다 작으면 길이 비교해서 갱신)
public class sol_11053 {
    static int N;
    static Integer[] dp, arr;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        arr = new Integer[N+1];
        dp = new Integer[N+1];
        StringTokenizer st = new StringTokenizer(br.readLine());
        ArrayList<ArrayList<Integer>> arrList = new ArrayList<>();

        for (int i = 1; i <= N; i++){
            arr[i] = Integer.parseInt(st.nextToken());
        }

        dp[1] = 1;

        for (int i=2; i<=N; i++) {
            dp[i] = LIS(i);
        }
        int max = Integer.MIN_VALUE;
        for (int i=1; i<=N; i++){
            max = Math.max(max, dp[i]);
        }
        System.out.println(max);
    }

    public static Integer LIS(int n) {
        if(dp[n] == null) {
            dp[n] = 1;
            for (int i=n; i>=1; i--) {
                if (arr[i] < arr[n]) {
                    dp[n] = Math.max(dp[n], LIS(i)+1);
                }
            }

        }
        return dp[n];
    }
}
