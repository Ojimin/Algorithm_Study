package Simulation;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// 1138 - 한줄로 서기
// 사람들이 기억하는 정보가 주어질 때, 줄을 어떻게 서야하는지 출력하는 프로그램
// 사람들 기억 : 자기보다 큰 사람이 왼쪽에 몇명있는지
// 입력 : 키가 1인사람주터 차례대로 자기보다 큰 사람이 왼쪽에 몇명 있는지 입력
// 출력 : 줄을 선 순서대로 키 출력
// 구현? 그리디 => 정렬되어있으므로
// 최적화 필요 + 혼자서 풀었지만 다시 풀어보기
public class sol_1138 {
    static int N;
    static int[] arr;
    static int[] result;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());
        arr = new int[N];
        result = new int[N];
        for (int i=0; i<N; i++) {
            result[i] = i+1;
        }
        for (int i=0; i<N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }
        solve();
        for (int i=0; i<N; i++) {
            System.out.print(result[i] + " ");
        }
    }

    public static void solve() {
        for (int i=0; i<N; i++) {
            if (i==0) {
                int tmp = result[arr[i]];
                result[arr[i]] = i+1;
                result[i] = tmp;
            }
            else {
                int cnt=0;
                int idx = 0;
                for (int j=0; j<N; j++) {
                    if (result[j] == i+1) {
                        idx = j;
                        break;
                    }
                }
                for (int j=0; j<idx; j++) {
                    if (result[j] > (i+1)) cnt++;
                }
                boolean isPossible = false;
                if (cnt != arr[i]) {
                    cnt = 0;
                    for (int j=0; j<N; j++) {
                        if ((i+1) > result[j]) continue;
                        if (arr[i] == 0) {
                            if (result[j] > i+1) {
                                int tmp = result[j];
                                result[j] = i+1;
                                result[idx] = tmp;
                                break;
                            }
                        }
                        if (isPossible && result[j] > (i+1)) {
                            int tmp = result[j];
                            result[j] = i+1;
                            result[idx] = tmp;
                            isPossible = false;
                        }
                        if (result[j] > i+1) cnt++;
                        if (cnt == arr[i] && result[j] > i+1 && j > idx){
                            int tmp = result[j];
                            result[j] = i+1;
                            result[idx] = tmp;
                        } else if (cnt == arr[i] && result[j] > i+1 && j < idx) isPossible = true;
                    }
                }
            }
        }
    }




}
