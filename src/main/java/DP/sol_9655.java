package DP;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

//9655-돌게임, 탁자 위 돌 n개를 둘이서 번갈아가며 돌을 1개 또는 3개를 가져갈 수 있음
//마지막 돌을 가져가는 사람이 이기게 됨, 상근이가 먼저 시작
//이기는 사람을 출력
//dp - 규칙이 그냥 홀수면 상근, 짝수면 창영
public class sol_9655 {
    static int N;
//    public static void main(String[] args) throws IOException {
//        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
//        N = Integer.parseInt(br.readLine());
//        if (N%2==1) {
//            System.out.println("SK");
//        } else {
//            System.out.println("CY");
//        }
//    }
        public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        int[] dp = new int[1001];
        //dp[n]이 홀수이면 상근이,짝수이면 창영이가 이김
        dp[1]=1;
        dp[2]=2;
        dp[3]=1;
        for(int i=4; i<=N;i++) {
            dp[i] = Math.min(dp[i-1],dp[i-3])+1; // Math.min을 사용한 이유는 최솟값을 구해야하므로
        }
        if (dp[N]%2==1) {
            System.out.println("SK");
        } else {
            System.out.println("CY");
        }
    }
}
