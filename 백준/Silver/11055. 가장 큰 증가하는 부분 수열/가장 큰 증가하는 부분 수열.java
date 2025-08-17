
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

// 11055 - 가장 큰 증가하는 부분 수열
// 수열 A에서 증가하는 부분 수열 중 합이 가장 큰 것
// 출력 : 가장 큰 증가하는 부분 수열 합
// 다시 - dp 테이블을 잘 정의 : 해당 인덱스를 마지막 원소로 하는 최대 합 저장
public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int arr[] = new int[N];
        int dp[] = new int[N];
        int answer = Integer.MIN_VALUE;
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i=0; i<N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }
        dp[0] = arr[0];
        if (answer < dp[0]) answer = dp[0];
        for (int i=1; i<N; i++) {
            boolean isChanged = false;
            int max = Integer.MIN_VALUE;
            int maxIdx = 0;
            for (int j=0; j<i; j++) {
                if (arr[i] > arr[j] && max < dp[j]) {
                    max = dp[j];
                    maxIdx = j;
                    isChanged = true;
                }
            }
            if (isChanged) dp[i] = arr[i] + dp[maxIdx];
            else dp[i] = arr[i];
            if (answer < dp[i]) answer = dp[i];
        }
        System.out.println(answer);
    }
}
