package DP;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

//11727-2*n 타일링2, 2*n직사각형을 1*2, 2*1과 2*2 타일로 채우는 방법의 수를 구하기
//출력 : 방법의 수를 10007로 나눈 나머지를 출력
//dp, bottom up 방식
//dp[n]=2*dp[n-2]+dp[n-1]
public class sol_11727 {
    static int n;
    static  int[] dp;
    static int result;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        dp = new int[1001]; //n이 최대 1000개이므로
        dp[1]=1;
        dp[2]=3;
        for (int i = 3; i <= n; i++) {
            int temp = (dp[i - 1] + 2 * dp[i - 2]) % 10007; // 수른 너무 과도하게 넘어버리니까 저장할 떄 나누기
            dp[i] = temp;
        }
        result = dp[n];
        System.out.println(result);
    }
}
