import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

// 11399 - ATM
// 1 - N까지 사람, i번 사람 돈 인출하는데 걸린 시간: Pi
// 줄 서는 순서에 따라 인출하는 시간이 달라짐 => 기다리는 시감을 포함하기 때문
// 출력: 각 사람이 돈 인출하는데 필요한 시간의 합의 최솟값
public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int arr[] = new int[N];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i<N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }
        Arrays.sort(arr);
        int time = 0;
        int tmp = 0;
        for (int i=0; i<N; i++) {
            time += tmp + arr[i];
            tmp += arr[i];
        }
        System.out.println(time);
    }
}
