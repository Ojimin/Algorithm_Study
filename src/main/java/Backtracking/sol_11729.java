package Backtracking;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

// 11729 - 하노이 탑 이동 순서
// 반경이 큰 순서대로 쌓여 있음, 첫번째 장대에서 세번째 장대로 옮기려 함
// 규칙1. 한번에 한개의 원판만을 다른 탑으로 옮길 수 있음
// 규칙2. 쌓아놓은 원판은 항상위의것이 아래것보다 작아야함
// 출력 : 옮긴 횟수, 수행 과정 => 옮긴 횟수가 최소가 되도록, A번째 탑 가장 위에 있는 원판을 B번째 탑의 가장 위로 옮긴다는 뜻
// 필승조합 : 일단, 제일 마지막에 가야하는 것이 3번째에 먼저 가도록 해야함
// 다시
// 임시기둥 - 마지막기둥 옮기기 - 나머지 기둥 옮기기 => n==1이면 종료
// n-1개의 원판을 처리할 수 있다면 마지막 n도 옮길 수 있음 => 재귀!
public class sol_11729 {
    static int N;
    static StringBuilder sb = new StringBuilder();
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        sb.append((int) Math.pow(2,N) -1).append("\n");
        hanoi(N, 1, 2, 3);
        System.out.println(sb);
    }

    public static void hanoi(int n, int from, int tmp, int to) {
        if (n==1) {
            sb.append(from+" " +to).append("\n");
            return;
        }
        hanoi(n-1, from, to, tmp);
        sb.append(from + " " +to).append("\n");
        hanoi(n-1, tmp, from, to);
    }
}
