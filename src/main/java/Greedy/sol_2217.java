package Greedy;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

// 2217 - 로프
// 각각의 로프는 들 수 있는 물체의 중량이 서로 다를 수 있음
// but k개의 로프를 사용하여 중량 w인 물체를 들어올릴 때 => 각각의 로프에 고르게 w/k만큼 중량이 걸리게 됨
// 출력 : 주어진 로프들을 이용해 들어올릴 수 있는 물체의 최대 중량 구하기
// 주의 : 로프는 다쓸 필요 X & 임의로 몇개의 로프를 골라서 사용해도 됨
// 방법 : 정렬해서 처음부터 돌면서 해당 인덱스의 중량 * 해당인덱스부터 끝까지의 수 갯수에서 최댓값 구하기
// 그리디 - 로프를 정렬한 후 w[n-k]*k의 최댓값
public class sol_2217 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int[] arr = new int[N];
        for (int i=0; i<N; i++) {
            arr[i] = Integer.parseInt(br.readLine());
        }
        Arrays.sort(arr);
        int max = Integer.MIN_VALUE;
        for (int i = 0; i<N; i++) {
            int cnt = N-1 - i +1;
            int weight = arr[i] * cnt;
            if(weight > max) max = weight;
        }
        System.out.println(max);
    }
}
