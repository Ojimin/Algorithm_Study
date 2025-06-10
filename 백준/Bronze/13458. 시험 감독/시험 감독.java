import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// 13458 - 시험 감독
// N개 시험장, i번 시험장 응시자 수: Ai
// 총감독관 1명- B명 감시, 부감독관(여러명O) - C명
//출력 : 필요한 감독관 수의 최솟값
// 무조건 총감독관 배정 -> 부감독관 배정
public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine()); // 시험장개수
        int[] arr = new int[N];
        StringTokenizer st = new StringTokenizer(br.readLine());

        for (int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        st = new StringTokenizer(br.readLine());
        int B = Integer.parseInt(st.nextToken());
        int C = Integer.parseInt(st.nextToken());

        long result = 0;
        for (int i=0; i<N; i++) {
            if (arr[i] > B) {
                result += 1;
                arr[i] -= B;
                if (arr[i] % C == 0) result += arr[i] / C;
                else result += arr[i] / C + 1;
            } else result ++;
        }
        System.out.println(result);
    }
}
