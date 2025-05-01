package DP;

import java.io.IOException;
import java.util.Scanner;

//1463-1로만들기, 3,2로 나누어 떨어지면 나누고 1을 빼서 적절히 잘 어케해서 1로 만들기
//출력 : 연산을 하는 횟수의 최솟값 출력
//dp
public class sol_1463 {
    static int N;

    public static void main(String[] args) throws IOException {
        Scanner sc = new Scanner(System.in);
        N = sc.nextInt();
        int[] dp = new int[1000001];

        dp[1] = 0;
        dp[2] = 1;
        dp[3] = 1;
        for (int i = 4; i <= N; i++) {
            //이것도 비교형식으로 들어가기
            //2로도 나누지고 3으로도 나눠지면 비교
            //둘중에 하나로 나눠지면 그냥 기존 dp+1
            //그게아니면 일단 dp[i-1]+1 = 1뺸거랑 같은 원리
            if (i % 2 == 0) {
                if (i % 3 == 0) {
                    dp[i] = Math.min(dp[i / 2], dp[i / 3]) + 1;
                    continue;
                } else {
                    dp[i] = Math.min(dp[i-1],dp[i / 2]) + 1;
                    continue;
                }
            }
            if (i % 3 == 0) {
                dp[i] = Math.min(dp[i / 3],dp[i-1]) + 1;
                continue;
            }
            dp[i] = dp[i - 1] + 1;
        }
        System.out.println(dp[N]);
    }
}
