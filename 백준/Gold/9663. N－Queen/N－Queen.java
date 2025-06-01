
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

// 9663 - N-Queen
// N*N 체스판 위 퀸 N개를 서로 공격할 없게 놓는 문제
// 출력 : 퀸 n개를 서로 공격할 수 없게 놓는 경우의 수 출력
// 퀸 : 수직, 수평, 대각선 이동 가능, 몇칸이든 제한없이 이동 가능
// 체스 잘 모름,,
// 안겹치게 놓을려면 이미 위치해 있는 퀸의 행과 열, 대각선에는 다른 퀸이 위치할 수 없다
// 백트래킹 .. => 깊이 = row, 뽑는게 col
// 다시요,,
// 1. 좌푯값을 증가시키면서 탐색 시작
// 2. 하나 고르면 그 다음 행에서 탐색 + 같은열,대각선 X
// 3. (중요) 퀸 열의 위치만 저장해서 탐색 -> 1차원 배열로 저장
// 4. 대각선 검사 : 행 번호 차이 == 열 번호 차이 => 대각선상 위치한 것
public class Main {
    static int N;
    static int[] arr;
    static int cnt = 0;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        arr = new int[N];
        backtracking(0);
        System.out.println(cnt);
    }

    public static void backtracking(int row) {
        if (row == N) {
            cnt++;
            return;
        }

        for (int col=0; col<N; col++) {
            arr[row] = col; // row, col에 퀸을 둠
            if (checkPosition(row)) backtracking(row+1);
        }
    }

    // 같은 열, 대각선 상에 퀸이 놓여있으면 false
    public static boolean checkPosition(int row) {
        for (int i=0; i<row; i++) {
            // 행이 다른데 같은 열에 뒀거나 || 행의 차이 == 열의 차이일때
            if (arr[row] == arr[i] || row - i == Math.abs(arr[row] - arr[i])) return false;
        }
        return true;
    }


}
