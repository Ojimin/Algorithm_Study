import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.StringTokenizer;

// 1026 - 보물
// S = A[0] × B[0] + ... + A[N-1] × B[N-1]
// S의 값을 가장 작게 만들기 위해 A의 수를 재배열, B의 수는 재배열 X
// 출력 : S의 최솟값 출력
// 최솟값이 되기 위해서 B의 큰값은 A의 제일 작은값. 이런식으로 연결해야할 듯
public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        Integer A[] = new Integer[N];
        Integer B[] = new Integer[N];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i=0; i<N; i++) {
            A[i] =Integer.parseInt(st.nextToken());
        }
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i<N; i++) {
            B[i] = Integer.parseInt(st.nextToken());
        }
        Arrays.sort(A);
        Arrays.sort(B, Collections.reverseOrder());
        int sum = 0;
        for (int i=0; i<N; i++) {
            sum += A[i] * B[i];
        }
        System.out.println(sum);
    }
}
