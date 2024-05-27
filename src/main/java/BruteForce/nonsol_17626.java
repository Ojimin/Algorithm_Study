package BruteForce;

import java.io.BufferedReader;
import java.io.InputStreamReader;

//17626-fourth squares, 라그랑주는 넷 이하의 제곱수의 합으로 표현 가능
// 출력 : n을 최소 개수의 제곱수 합으로 표현하는 것 출력하기
// 완탐 - 모든 경우의 수 탐색 + dp 였음...
//1. n보다 작은 제곱수들의 조합을 잘 해봐야지..
//2. 이 때 큰 수부터 제곱근 만들어야할듯
//다른방법 : N에서 점점 제곱근을 빼서 0만들기?
//dp[i] = min (dp[i-j*j])+1;
//다시
public class nonsol_17626 {
    static int N;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        int dp[] = new int[N+1];
        dp[1]=1;
        int min=0;
        for(int i=2; i<=N; i++) {
            min=Integer.MAX_VALUE;
            //i에서 i보다 작은 제곱수에서 뺀 dp[i-j*j] 중 최소 선택
            for (int j=1; j*j<=i; j++) {
                int tmp=i-j*j;
                min=Math.min(min,dp[tmp]);
            }
            dp[i] = min +1; //i=(dp[]=min개수인 제곱수)+j*j 이므로 1개를 더 더해줌
        }
        System.out.println(dp[N]);
    }

//    //조합
//    public static void combination(int start, int sum, int cnt) {
//        if (sum>= N) return;
//
//        for (int i=start; i >= 1; i--) {
//            if (!visited[i]) {
//                visited[i] = true;
//                cnt++;
//                sum += Math.pow(i, 2);
//                combination(start - 1, sum, cnt);
//                if (sum==N) {
//                    if (min>cnt) min=cnt;
//                }
//                visited[i]=false;
//                cnt--;
//                sum-=Math.pow(i, 2);
//            }
//        }
//    }
}
