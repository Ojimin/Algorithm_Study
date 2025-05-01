package BruteForce;

import java.io.*;
import java.util.StringTokenizer;
import java.util.Arrays;
//문제 설명 : n보다 작거나 같은 자연수 중 k의 원소로만 구성된 가장 큰 수 출력
//핵심 개념 : 재귀 활용
public class recursion_18511 {
    static int N, K, ans;
    static int[] nums;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] input = br.readLine().split(" ");
        N = Integer.parseInt(input[0]);
        K = Integer.parseInt(input[1]);

        StringTokenizer st = new StringTokenizer(br.readLine());
        nums = new int[st.countTokens()];
        for (int i = 0; i < K; i++) {
            nums[i] = Integer.parseInt(st.nextToken());
        }
        Arrays.sort(nums);
        dfs(0);
        System.out.println(ans);
    }

    public static void dfs(int now) {
        if (now > N) return;
        if (ans < now) ans = now; //n에 제일 가까운 수를 찾기 위함

        for (int i = K - 1; i >= 0; i--) {//큰수부터 만들어 체크
            dfs(now * 10 + nums[i]); //ex)7,77,777,776,775...
        }
    }
}
