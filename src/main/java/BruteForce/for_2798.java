package BruteForce;

import java.io.*;
import java.util.StringTokenizer;
//문제 설명 : 블랙잭 - 카드개수 n개중 3장을 뽑아 m을 넘지않는 최대한 가까운 합 구하기
//핵심 개념 : 3중 for 문을 돌려 중복되지 않게 뽑기
public class for_2798 {
    public static void sol(int n, int m, int[] nums) {
        int max = 0;
        int sum = 0;
        //3장의 카드를 중복 x 뽑아야하므로
        for (int i = 0; i < n - 2; i++) {
            for (int j = i + 1; j < n - 1; j++) {
                for (int k = j + 1; k < n; k++) {
                    sum = nums[i] + nums[j] + nums[k];
                    if (max < sum && sum <= m) { //max와 sum 비교 & m을 넘지 않는지 비교
                        max = sum; //max 값 업데이트
                    }
                }
            }
        }
        System.out.println(max);
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] input = br.readLine().split(" ");
        int n = Integer.parseInt(input[0]);
        int m = Integer.parseInt(input[1]);

        StringTokenizer st = new StringTokenizer(br.readLine());
        int[] nums = new int[st.countTokens()];
        for (int i = 0; i < nums.length; i++) {
            nums[i] = Integer.parseInt(st.nextToken());
        }
        sol(n, m, nums);
    }
}
