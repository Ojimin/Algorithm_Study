package Backtracking;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// 1182 - 부분수열의 합
// N개의 정수로 이루어진 수열에서, 크기가 양수인 부분수열 중 그 수열의 원소를 다더한 값이 S가 되는 경우의수
public class sol_1182 {
    static int N,S;
    static boolean[] visited;
    static int[] arr;
    static int result = 0;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        S = Integer.parseInt(st.nextToken());
        visited = new boolean[N];
        arr = new int[N];
        st = new StringTokenizer(br.readLine());
        for (int i=0;i<N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }
        backtracking(0,0, 0);
        System.out.println(result);
    }

    static void backtracking(int cnt, int depth, int pick) {
        if(depth == N) return;

        backtracking(cnt+arr[depth],depth+1, pick+1);
        if (S == cnt+arr[depth] && pick+1>0) result++;
        backtracking(cnt, depth+1, pick);
    }
}
