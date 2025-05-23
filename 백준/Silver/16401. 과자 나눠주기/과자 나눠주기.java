import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

// 16401 - 과자 나눠주기
// M명의 조카, N개의 과자가 있을 때, 조카 1명에게 줄 수 있는 막대과자의 최대 길이
// 조카에게는 모두 같은 과자의 길이를 줘야함
// 출력 : 1명에게 줄 수 있는 막대 과자의 최대 길이 & 같은 길이를 줄 수 없다면 0 출력
// 주어지는 과자 길이는 오름차순으로 정렬되어서 주어짐 => 아니여서 틀렸음. 이분탐색은 꼭 오름차순 정렬이 필요
// 이분탐색 문제 => mid와 key 값의 결과에 따라 low, high 조절
public class Main {
    static int M, N;
    static int[] arr;
    static int max = Integer.MIN_VALUE;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        M = Integer.parseInt(st.nextToken()); // 조카 수
        N = Integer.parseInt(st.nextToken()); // 과자 수
        arr = new int[N];
        st = new StringTokenizer(br.readLine());
        long sum = 0;
        for (int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
            sum += arr[i];
        }

        //나눌 수 없는 case
        if (M > sum) {
            max = 0;
            System.out.println(max);
            return;
        }
        //오름차순 정렬
        Arrays.sort(arr);
        int low = 1;
        int high = arr[N-1];
        int mid;

        while (low <= high) {
            mid = (low+high)/2;
            // 가능하면 low= mid+1
            int cnt = checkCnt(mid);
            if (cnt >= M) {
                if (mid > max) max = mid;
                low = mid+1;
            } else {
                high = mid-1;
            }
            // 불가능하면 high = mid-1
        }
        System.out.println(max);
    }
    // 내 코드
    public static boolean isPossible(int mid) {
        int cnt = 0;
        boolean isPossible = false;
        for (int i=0; i<N; i++) {
            if (cnt >= M) {
                isPossible = true;
                break;
            }
            if (arr[i] < mid) continue;
            if (arr[i] == mid) cnt++;
            else {
                cnt += arr[i]/mid;
            }
        }
        if (cnt >= M) {
            isPossible = true;
            if (mid > max) max = mid;
        }

        return isPossible;
    }

    // 개선된 코드
    public static int checkCnt(int mid) {
        int cnt = 0;
        for (int i=0; i<N; i++) {
            if (arr[i] < mid) continue;
            else {
                cnt += arr[i]/mid;
            }
        }
        return cnt;
    }
}
