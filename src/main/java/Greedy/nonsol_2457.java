package Greedy;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Comparator;
import java.util.StringTokenizer;

// 2457 - 공주님의 정원
// 조건 1. 3/1 ~ 11/30까지 매일 꽃이 한가지 이상 피어 있어야 함
// 조건 2. 정원에 심는 꽃들의 수를 가능한 적게!
// 출력 : 선택한 꽃들의 최소 개수 출력 & 두조건을 만족할 수 없다면 0 출력
// N<= 10^5
// 4, 6, 9, 11 => 30일
// 1, 3, 5, 7, 8, 10, 12 => 31일
// 2 => 28일
// 정렬(오름차순(월,일, 월,일) -> 꽃이 지는 시점부터 선택 처음 조건 1. 
public class nonsol_2457 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int[][] arr = new int[N][4];
        int[] month = {31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};

        for (int i=0; i<N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            arr[i][0] = Integer.parseInt(st.nextToken());
            arr[i][1] = Integer.parseInt(st.nextToken());
            arr[i][2] = Integer.parseInt(st.nextToken());
            arr[i][3] = Integer.parseInt(st.nextToken());
        }
        Arrays.sort(arr, Comparator.comparingInt((int[] a) -> a[0]).thenComparing(a -> a[1]).thenComparing(a -> a[2]).thenComparing(a -> a[3]));
        boolean isPossible = false;
        int cnt = 0;
        int last_month = 0;
        int last_day = 0;
        for (int i = N-1; i>=0; i--) {
            // 초기화
            if (last_month == 0 && last_day == 0) {



            }
            if () {
                // 마지막 조건 : 3/1 이전이 되면 true로 하고 탈출
                if (arr[i][0]<3 || arr[i][0] == 3 &&  ) {
                    isPossible = true;
                    break;
                }
            }

        }
        if (!isPossible) System.out.println(0);
        else System.out.println(cnt);
    }
}
