
// 2170 - 선 긋기
// 출력: 그려진 선의 총 길이 => 선이 여러번 그려진 곳은 한번씩만 계산

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int arr[][] = new int[N][2];
        int[][] compare = new int[1][2];
        for (int i=0; i<N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            arr[i][0] = Integer.parseInt(st.nextToken());
            arr[i][1] = Integer.parseInt(st.nextToken());
        }
        Arrays.sort(arr, Comparator.comparingInt((int[] tmp) -> tmp[1]).thenComparing(tmp -> tmp[0]));

        compare[0][0] = arr[N-1][0];
        compare[0][1] = arr[N-1][1];
        long result = 0;
        for (int i = N-2; i>=0; i--) {
            if (arr[i][1] == compare[0][1]) {
                if (arr[i][0] < compare[0][0]) {
                    compare[0][0] = arr[i][0];
                    compare[0][1] = arr[i][1];
                }
            } else if (arr[i][1] < compare[0][1]) {
                if (arr[i][0] >= compare[0][0]) continue;
                else {
                    if (compare[0][0] <= arr[i][1]) {
                        compare[0][0] = arr[i][0];
                    } else {
                        result += compare[0][1] - compare[0][0];
                        compare[0][0] = arr[i][0];
                        compare[0][1] = arr[i][1];
                    }
                }
            }
        }
        result += compare[0][1] - compare[0][0];
        System.out.println(result);
    }
}
