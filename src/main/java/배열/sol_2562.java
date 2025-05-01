package 배열;

import java.io.BufferedReader;
import java.io.InputStreamReader;

// 9개의 자연수 중 최댓값을 찾고 그 최댓값이 몇 번째 수인지 구함
public class sol_2562 {
    static int[] N = new int[9];
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        for (int i= 0; i<9; i++) {
            N[i] = Integer.parseInt(br.readLine());
        }
        int max = N[0];
        int max_idx = 1;
        for (int i=1; i<9;i++) {
            if (max < N[i]) {
                max = N[i];
                max_idx = i+1;
            }
        }
        System.out.println(max);
        System.out.println(max_idx);
    }
}
