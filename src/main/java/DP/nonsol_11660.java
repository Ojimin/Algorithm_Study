package DP;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// 11660 - 구간 합 구하기5
// x1, y1 -> x2, y2 까지 합 구하는 프로그램
public class nonsol_11660 {
    static int N, M;
    static int[][] arr;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        StringBuilder sb = new StringBuilder();
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        arr = new int[N+1][N+1];
        for (int i = 1;i<=N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j=1;j<=N;j++){
                arr[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        for (int i=1; i<=M; i++) {
            st = new StringTokenizer(br.readLine());
            int x1 = Integer.parseInt(st.nextToken());
            int y1 = Integer.parseInt(st.nextToken());
            int x2 = Integer.parseInt(st.nextToken());
            int y2 = Integer.parseInt(st.nextToken());

        }
    }
}
