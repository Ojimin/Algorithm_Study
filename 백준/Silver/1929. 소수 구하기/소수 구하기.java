import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

// 1929 - 소수 구하기
// 출력 : M이상 N이하의 소수 모두 출력
// 그냥 반복문으로 돌리면 시간 초과 발생할듯 => 10^12
// 그래서 에라토스테네스의 체 활용
// 에라토스테네스의 체 : 자신을 제외한 2의배수 ~ N배수까지 순회하며 값을 걸러내어 소수 판별함
public class Main {
    static int M, N;
    static int[] isPrime;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        StringBuilder sb = new StringBuilder();
        M = Integer.parseInt(st.nextToken());
        N = Integer.parseInt(st.nextToken());
        isPrime = new int[N+1];
        for (int i=2; i<=N; i++) {
            isPrime[i] = i;
        }
        solve();
        for (int i=M; i<=N; i++) {
            if (isPrime[i] != 0) sb.append(isPrime[i]).append("\n");
        }
        System.out.println(sb);
    }

    public static void solve() {
        for (int i=2; i<=N; i++) {
            // i 배수들 0으로 모두 지우기
            if (isPrime[i] != 0) {
                for (int j=i+i; j<=N; j+=i) {
                    isPrime[j] = 0;
                }
            }
        }
    }
}
