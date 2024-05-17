package DP;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

//10844-쉬운 계단 수
//출력 : 길이가 N인 계단 수가 총 몇개인지, 0으로 시작하는 수는 계단수가 아님
//dp인가
public class nonsol_10844 {
    static long result=0;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        int[] dp = new int[101];
        dp[1]=9;
        dp[2]=17;
        for (int i = 3; i<=n; i++) {
            dp[i]= (dp[i-1]+8*(i-1)) % 1000000000;
        }
        System.out.println(dp[n]);
    }
}
