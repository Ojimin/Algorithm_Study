package 정렬;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

// 10814 - 나이순 정렬
// 회원들 나이가 증가하는 순, 먼저 가입한 사람 오름차순 정렬
// N<= 10^5
// 틀렸던 이유. 같은 이름이 들어오는 경우 생각 못함
public class sol_10814 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        HashMap<Integer, Integer> map = new LinkedHashMap<>();
        String[] nameList = new String[N];
        StringBuilder sb = new StringBuilder();
        for (int i=0; i<N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int age = Integer.parseInt(st.nextToken());
            String name = st.nextToken();
            map.put(i, age);
            nameList[i] = name;
        }

        List<Integer> keySet = new ArrayList<>(map.keySet());
        keySet.sort((o1, o2) -> map.get(o1) - map.get(o2));
        for (Integer idx : keySet) {
            int age = map.get(idx);
            String name = nameList[idx];
            sb.append(age).append(" "). append(name).append("\n");
        }
        System.out.println(sb);
    }
}
