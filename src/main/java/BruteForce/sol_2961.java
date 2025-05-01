package BruteForce;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

//2961 - 재료 N개, 곱: 신맛, 합: 쓴맛, 재료를 적어도하나, 신맛과 쓴맛이 차이가 가장 작은 요리를 만들어야함
//최소차 구하기
//브루트포스
//1. 배열 N*2에서
public class sol_2961 {
    static int N;
    static int[][] arr;
    static boolean[] visited;
    static int Min = Integer.MAX_VALUE;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        N = Integer.parseInt(br.readLine());
        arr = new int[N][2];
        visited = new boolean[N];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            arr[i][0] = Integer.parseInt(st.nextToken());
            arr[i][1] = Integer.parseInt(st.nextToken());
        }
        sol(0, 0);
        System.out.println(Min);
    }

    public static void sol(int depth, int start) {
        int sour = 1;
        int bitter = 0;
        for (int j = 0; j < N; j++) {
            if (visited[j]) {
                sour *= arr[j][0];
                bitter += arr[j][1];
                if (Min > Math.abs(sour - bitter)) {
                    Min = Math.abs(sour - bitter);
                }
            }
        }
        if (depth > N) {
            return;
        }
        for (int i = start; i < N; i++) {
            if (!visited[i]) {
                visited[i] = true;
                sol(depth++, start++);
                visited[i] = false;
            }
        }
    }
}
