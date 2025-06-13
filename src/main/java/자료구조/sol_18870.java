package 자료구조;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

// 18870 - 좌표 압축
// X1~XN 존재 => 좌표 압축
// Xi 좌표 압축 후 Xi'= Xi > Xj를 만족하는 서로 다른 좌표 Xj의 개수와 같아야함
// 출력 : 좌표 압축을 적용한 X'1~X'N 출력
// 단순 구현? => 10^12 시간초과 발생..? 2*10*^9까지 가능
// Xi => 범위가 +-10^9, N: ~10^6
// 다시 - 단순 구현
// 방법: 배열상태에서 정렬하고 map 통해 중복제거 + 순서 인덱스 저장, 이후 원배열 돌면서 찾기 => 출려
// 정렬 + Map
public class sol_18870 {
    static int N, arr[];
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());
        arr = new int[N];
        HashMap<Integer, Integer> map = new HashMap<>();
        StringBuilder sb = new StringBuilder();

        for (int i=0; i<N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }
        int[] copyArr = arr.clone();
        Arrays.sort(copyArr);
        int idx = 0;
        for (int i=0; i<N; i++) {
            if (!map.containsKey(copyArr[i])) {
                map.put(copyArr[i], idx);
                idx++;
            }
        }

        for (int i=0; i<N; i++) {
//            for (int j=0; j<tempSet.size(); j++) {
//                if (arr[i] == tempSet.get(j)) {
//                    sb.append(j).append(" ");
//                }
//            }
            if (map.containsKey(arr[i])) {
                sb.append(map.get(arr[i]) + " ");
            }
        }
        System.out.println(sb);
    }
}
