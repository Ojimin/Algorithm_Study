package Greedy;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Collections;

// 1744 - 수 묶기
// 수열의 두수를 묶기 - 자기자신 불가 & 묶은 수는 서로 곱한 후 더함
// 주의: 수열의 모든 수는 단 한번만 묶거나 묶지 않아야 함
// 출력 : 수열의 합이 최대
// 내림차순 정렬 ->
// 음수 : 음수끼리 곱하기, 음수가 본인 제외 없으면 0이랑 곱하기, 없으면 그냥 더하기
// 0 : 음수가 1개 이상 있으면 제일 값이 작은 음수와 곱하기, 없으면 묶지 않기(그냥 더하기)
// 양수 : 값에 제일 큰 것 끼리 곱하기, 그러다 1개만 남으면(0, 음수 제외) 그냥 더하기
public class sol_1744 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        Integer[] arr = new Integer[N];
        boolean[] visited = new boolean[N];
        for (int i=0; i<N; i++) {
            arr[i] = Integer.parseInt(br.readLine());
        }
        Arrays.sort(arr, Collections.reverseOrder());
        long result = 0;
        for (int i = 0; i<N-1; i++) {
            if (visited[i]) continue;
            // 양수 처리
            if (arr[i] > 0 ) {
                if(arr[i+1] > 0) {
                    if (arr[i]*arr[i+1] > arr[i]+arr[i+1])  {
                        result += arr[i] * arr[i+1];
                        visited[i] =true;
                        visited[i+1] = true;
                    } else {
                        result += arr[i];
                        visited[i] = true;
                    }
                }
                else if (arr[i+1] <= 0) {
                    result += arr[i];
                    visited[i] = true;
                }
            }
            // 0 처리
            else if (arr[i] == 0) {
                visited[i] = true;
                if (arr[i+1] < 0) {
                    if ((N-i) % 2 == 0) {
                        result += arr[i] * arr[i+1];
                        visited[i+1] = true;
                    } /*else {
                        if ((N-i) > 3) {
                            result += arr[i] * arr[i+2];
                            visited[i+2] = true;
                        }
                    } */
                }
            }
            // 음수 처리
            else {
                // 음수 값이(현재값 제외) 2개 이상 남았으면 - 짝수 개, 홀수 개
                if (N - i >=3) {
                    //현재값 포함 짝수개 남으면
                    if ((N-i) % 2 == 0) {
                        // 그 다음수가 방문하지 않은 거면
                        if (!visited[i+1]) {
                            result += arr[i] * arr[i+1];
                            visited[i] = true;
                            visited[i+1] = true;
                        } else { // 다음수가 방문한 수이면 그냥 현재값 더하기만,,
                            result += arr[i];
                            visited[i] = true;
                        }

                    } else {// 홀수개 남으면
                        result += arr[i];
                        visited[i] = true;
                    }
                } // 2개 일때
                else if (N-i == 2){
                    visited[i] = true;
                    visited[i+1] = true;
                    result += arr[i] * arr[i+1];
                } else { // 1개일때
                    visited[i] = true;
                    result += arr[i];
                }
            }
        }
        // 마지막 값 처리
        if (!visited[N-1]) {
            result += arr[N-1];
        }
        System.out.println(result);
    }
}
