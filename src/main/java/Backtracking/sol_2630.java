package Backtracking;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// 2630 - 색종이 만들기
// 종이의 개수와 비슷한 문제
//  0- 흰색, 1- 파란색, 같은색으로 색칠해져 있찌 않으면 N/2로 자르기
// 출력 : 잘라진 하얀색 색종이 개수 + 파란색 색종이 개수
public class sol_2630 {
    static int[][] map;
    static int white=0, blue = 0;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        map = new int[N][N];
        for (int i=0; i<N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j=0;j<N; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        ColorPaper(N, 0, 0);
        System.out.println(white);
        System.out.println(blue);
    }

    public static void ColorPaper(int n, int fromRow, int fromCol) {
        if (n==1 || isSame(n, fromRow, fromCol)) {
            if (map[fromRow][fromCol] == 0) white++;
            else blue++;
            return;
        }
        for (int i=0; i<2; i++) {
            for (int j=0; j<2; j++) {
                ColorPaper(n/2,fromRow+i*(n/2),fromCol+j*(n/2));
            }
        }
    }

    public static boolean isSame(int n, int fromRow, int fromCol) {
        for(int i=fromRow; i<fromRow+n; i++) {
            for (int j=fromCol; j<fromCol+n; j++) {
                if (map[i][j] != map[fromRow][fromCol]) return false;
            }
        }
        return true;
    }
}
