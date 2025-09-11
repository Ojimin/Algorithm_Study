import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// 11723 - 집합
// 연산 수행
// 출력: check 연산이 주어질 때마다 결과 출력
// check - x가 있으면 1을, 없으면 0 출력=> 조회, toggle - x가 있으면 x 제거=>i번째비트 0으로 변경, 없으면 x 추가=>i번쨰 비트 1로 변경
// all : S를 {1,..20} 으로 바꿈 => 모두 1로 변경
// 다시
// 비트마스킹 개념이 해당 자릿수에 체크할 것만 1로 표시
public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int M = Integer.parseInt(br.readLine());
        StringBuilder sb = new StringBuilder();
        int n = 0;
        for (int i=0;i<M; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            String order = st.nextToken();
            int x = 0;
            if (!order.equals("all") && !order.equals("empty") ) x = Integer.parseInt(st.nextToken());
            switch(order) {
                case "add":
                    n = n | (1 <<x);
                    break;
                case "remove":
                    n = n & ~(1 << x);
                    break;
                case "check":
                    int tmp = n & (1<<x);
                    if (tmp >= 1) sb.append(1).append("\n");
                    else sb.append(0).append("\n");
                    break;
                case "toggle": // x 연산을 뒤집어야함
                    n = n ^ (1<<x);
                    break;
                case "all":
                    n = (1 << 21) - 1;
                    break;
                case "empty":
                    n = 0;
                    break;
            }
        }
        System.out.print(sb);
    }
}
