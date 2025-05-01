package DP;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

//2839-설탕배달,N킬로그램 배달 - 봉지는 3킬로, 5킬로 존재, 최대한 적은 봉지로 배달하고자함
//출력: N킬로 배달해야할 떄, 최소 봉지개수 출력
//dp
public class sol_2839 {
    static int N;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N=Integer.parseInt(br.readLine());
        int[] dp = new int[5001];
        dp[0]=-1;
        dp[1]=-1;
        dp[2]=-1;
        dp[3]=1;
        dp[4]=-1;
        dp[5]=1;
        for (int i=6; i<=N;i++) {
            //i-3이나 i-5에 있는 dp 값이 있으면 둘중 제일 최솟값이 적은 거를 적용
            //만약 i-3과 i-5 했는데 둘다 값이 -1
            if(dp[i-3]==-1) {
                if (dp[i-5]!=-1) {
                    dp[i] = dp[i - 5] + 1;
                    continue;
                }
                else {
                    dp[i] = -1;
                    continue;
                }
            }
            if (dp[i-5]==-1) {
                if (dp[i-3]!=-1) {
                    dp[i] = dp[i - 3] + 1;
                    continue;
                }
                else {
                    dp[i]=-1;
                    continue;
                }
            }
            dp[i] = Math.min(dp[i-5],dp[i-3])+1;
        }
        System.out.println(dp[N]);
    }
}
