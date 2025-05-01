package 배열;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// 최댓값 - 9*9 고정 => 해당 배열에서 최댓값구하기
public class sol_2566 {
    static int[][] map = new int[9][9];
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();
        int max = 0;
        int max_row = 1;
        int max_col = 1;
        for (int i=0; i<9; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j=0; j<9; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
                if (map[i][j] > max) {
                    max = map[i][j];
                    max_row = i+1;
                    max_col = j+1;
                }
            }
        }
        sb.append(max).append("\n");
        sb.append(max_row + " " + max_col);
        System.out.println(sb);
    }
}
