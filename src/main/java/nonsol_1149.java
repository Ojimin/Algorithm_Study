import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// 1149 - RGB거리
// 1~N번 집, 빨강&초록&파랑 중 하나로 색칠할것
// 출력 : 모든집을 칠하는 비용의 최솟값
// 규칙 1. 1번 색 != 2번 색 - 첫번째
// 규칙 2. N번 색 != N-1번색 - 끝번째
// 규칙 3. i번 색 != i+1 != i-1 => 2~N-1
// 최솟값 : 그리디, dp,
//
public class nonsol_1149 {
    static int N;
    static int[][] arr;
    static int[][] dp;
    static int cnt = 0;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(br.readLine());
        arr = new int[N][N];

        for (int i=0; i<N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j=0; j<N; j++) {
                arr[i][j] = Integer.parseInt(st.nextToken()); // 빨강 초록 파랑
            }
        }
    }

    public static void getMinimum() {

    }
}
