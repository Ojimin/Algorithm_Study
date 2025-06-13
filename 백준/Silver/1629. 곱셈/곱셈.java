import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// 1629 - 곱셈
// 자연수 A를 B번 곱한 수에 C로 나눈 나머지 구하기
// 출력 : A를 B번 곱한 수 % C
// 주의 A, B, C : 10^9 정도 되는 듯
// 단순 반복문을 써서는 안됨 => 분할정복? 사용해야하는듯
// 분할정복 vs Dp 차이점 : 분할정복은 각 부분문제를 독립적으로 해결하고 합침
// 다시
// 지수법치 & 모듈로 연산
public class Main {
    static int A, B, C;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        A = Integer.parseInt(st.nextToken());
        B = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());


        long result = pow(A, B);
        System.out.println(result);
    }

    public static long pow(long x, int n) {
        // 기저 조건
        if (n == 0) return 1;

        long result = pow(x, n/2);
        return (n % 2 == 0) ? result * result % C : (result * result % C) * x % C;
    }
}
