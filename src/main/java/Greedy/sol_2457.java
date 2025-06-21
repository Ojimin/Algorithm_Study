package Greedy;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

// 2457 - 공주님의 정원
// 조건 1. 3/1 ~ 11/30까지 매일 꽃이 한가지 이상 피어 있어야 함
// 조건 2. 정원에 심는 꽃들의 수를 가능한 적게!
// 출력 : 선택한 꽃들의 최소 개수 출력 & 두조건을 만족할 수 없다면 0 출력
// N<= 10^5
// 4, 6, 9, 11 => 30일
// 1, 3, 5, 7, 8, 10, 12 => 31일
// 2 => 28일
// 정렬(오름차순(월,일, 월,일) -> 꽃이 지는 시점부터 선택 처음 조건 1.
// 주의: 달이 넘어가는 경계에 걸친 경우
// 다시 - 그리디 관점에서 현재 시점에서 피어있는 꽃 중, 가장 마지막에 지는 꽃 선택 ㄱㄱ
// 핵심 : MMDD를 그대로 4자리 숫자로 활용, 3월1일 -. 301, 11월30일 -> 1130
// 과정 : 시작일 빠른순 정렬 -> 종료일 빠른순 정렬 -> 시작일이 3/1보다 빠르거나 같은것들 중 종료일이 가장 큰 수 선택하여 Max 갱신 -> 갱신 후 꽃개수1증가 -> 종료일이 11월30일 넘어가면 꽃개수 출력
public class sol_2457 {
    static class Node {
        int a, b;
        public Node(int a, int b) {
            this.a = a;
            this.b = b;
        }
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        List<Node> list = new ArrayList<>();

        for (int i=0; i<N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken()) * 100 + Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken()) * 100 + Integer.parseInt(st.nextToken());
            list.add(new Node(a,b));
        }
        Collections.sort(list, new Comparator<Node>() {
            @Override
            public int compare(Node o1, Node o2) {
                if (o1.a == o2.a) return o1.b - o2.b;
                return o1.a - o2.a;
            }
        });
        // max - 시간 갱신, ans - 꽃개수
        int max = 0, ans = 0;
        int start = 301;
        int idx = 0;

        while(start<1201) {
            boolean ch = false; // 갱신 여부 체크
            for (int i=idx; i<N; i++) {
                if (list.get(i).a > start) break;
                // a 값이 max 값보다 작은 것 중 b의 값이 가장 큰 것을 선택
                if (list.get(i).a <= start && max<list.get(i).b) {
                    max = list.get(i).b;
                    idx = i+1;
                    ch = true;
                }
            }
            if (ch) {
                start = max; //끝나는 시각이 최대였던 것을 start 대입
                ans++;
            } else break;
        }
        // 종료 후 max 값이 1201보다 작으면 0 출력
        if (max<1201) System.out.println(0);
        else System.out.println(ans);
    }
}
