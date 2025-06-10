
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;

// 12015 - 가장 긴 증가하는 부분 수열2
// 수열1 문제보다 N 크기, 주어지는 수열 길이가 10^6 으로 증가함
// 그냥 반복문 돌려서 구하면 시간초과 발생..
// 다시
// LIS + 이진탐색
// 매번 값 기록 - dp 테이블의 경우 길이가 1일때의 최솟값 저장
// 방법 : 수열 길이에 해당하는 최솟값을 dp 배열에 저장 + 이를 기반으로 최장 길이 찾기
// 순서1. 해당 값 이전에 올수있는 수열의 최대길이를 dp에서 이진탐색을 통해 찾음
// 순서2. dp[i]를 기존 값과 현재값을 비교하여 최솟값으로 갱신
// 순서3. dp에 들어있는 수열의 길이의 최댓값을 계산하여 이를 이진 참색 범위 및 최종결과 LIS로 사용함 => dp 테이블에 저장되는 값은 항상 오름차순임
public class Main {
    static int N;
    static int[] arr;
    static ArrayList<Integer> dp = new ArrayList<>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        arr = new int[N+1];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 1; i<=N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        dp.add(0);
        dp.add(arr[1]);

        for (int i=2; i<=N; i++) {
            if (dp.get(dp.size()-1) < arr[i] ) dp.add(arr[i]); // 마지막 수보다 크면 추가
            else {
                int idx = solve(i);// 현재 인덱스에 있는 값보다 크거나 같은 인덱스
                dp.set(idx, arr[i]);
            }
        }
        System.out.println(dp.size()-1);
    }
    // 조건 1. 해당 인덱스보다 이전일 것 => 메모리제이션 하면 항상 이것은 참
    // 조건 2. 이분 탐색을 하여 해당 인덱스 값보다 작으면서 최댓값인 인덱스를 구하고
    // 조건 3. 만약 해당 값이 작으면서 최댓값인 인덱스보다 크면 길이 +1인 곳에 기존 값보다 작으면 저장, 아니면 pass
    // 조건 4. 만약 인덱스 1보다 작으면 인덱스 1인 곳에 갱신
    public static int solve(int i) {
        int start = 1;
        int end = dp.size() - 1;
        int ans = dp.size() - 1; // 못 찾았을 때

        while (start <= end) {
            int mid = (start+end)/2;
            if (dp.get(mid) >= arr[i]) {
                ans = mid;
                end= mid-1;
            }
            else start = mid+1;
        }
        
        return ans;
    }
}
