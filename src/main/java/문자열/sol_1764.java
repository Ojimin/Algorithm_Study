package 문자열;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

//1764-듣보잡,듣도 못한사람 명단과, 보도 못한 사람 명단이 주어질 때, 듣도보도못한 사람 명단 구하는 프로그램 작성
//듣보잡의 수와 명단을 사전순으로 출력
//첫시도 : 이중 for문의 시간초과 발생으로 HashSet 사용
//기존 이중for문으로 일일히 검사하는 것보다 HasSet의 contains 써서 비교
//시간초과의 원인: ArrayList의 contains(내가 구현한 이중for문과 유사방식)는 O(N)이 걸리고 HashSet의 contains는 O(1)이 걸림
//그 외는 hashmap 이용하는 방법도 있는듯
public class sol_1764 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        HashSet<String> set = new HashSet<>();
        List<String> result = new ArrayList<>();
        for (int i=0; i<n; i++) {
            set.add(br.readLine());
        }
        for (int i=0; i<m; i++) {
            String str = br.readLine();
            if (set.contains(str)) {
                result.add(str);
            }
        }
        Collections.sort(result);
        System.out.println(result.size());
        for (String s : result) {
            System.out.println(s);
        }
    }
}
