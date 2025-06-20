package Greedy;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// 11501 - 주식
// 매일 세가지 행동 중 하나를 함 : 주식 하나를 삼 - 가지고있는 주식 팜 - 아무것도 안함
// 출력 : 각 테스트 케이스 별로 최대 이익을 나타내는 정수 하나 출력
// 날 : N <= 10^6, 최대이익 : 64비트 정수형 표현 가능
// 다시 - 순방향 탐색으로 하면 정렬하고 해야하니까 시간초과 뜸
// 최대 이익을 위해 구입한 가격-판매하는 가격 차이가 커야함
// 역방향으로 탐색하면서 현재 최대 max 값이면서 max보다 작을 때는 max만큼 팔아서 수익 & max보다 크면 변경하고 그 이후 탐색하는 것들은 max로 팔기
public class sol_11501 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T= Integer.parseInt(br.readLine());
        StringBuilder sb = new StringBuilder();
        for (int i =0; i<T; i++) {
            int N = Integer.parseInt(br.readLine());
            StringTokenizer st = new StringTokenizer(br.readLine());
            int arr[] = new int[N];
            for (int j=0; j<N; j++) {
                arr[j] = Integer.parseInt(st.nextToken());
            }
            long profit = 0;
            int max = arr[N-1];
            for (int j=N-2;j>=0; j--) {
                if (max > arr[j]) {
                    profit += max - arr[j];
                } else {
                    max = arr[j];
                }
            }
            sb.append(profit+"\n");
        }
        System.out.print(sb);
    }
}
