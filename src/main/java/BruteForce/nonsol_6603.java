package BruteForce;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

//6603 - 1~49에서 수 6개를 고름. 49 중 k개의 수를 골라 집합 s를 만든 다음 그 수만 가지고 6개의 번호를 선택하는 것.
//조합으로 6개 뽑고 사전순으로 출력
//이해 안됨...ㅠㅜ
//backtracking or dfs
public class nonsol_6603 {
    static int K;
    static ArrayList<Integer> arrayList;
    static boolean[] visited;
    static int[] selectedArr;
    static StringBuilder sb;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        sb = new StringBuilder();
        while (true) {
            st = new StringTokenizer(br.readLine());
            K = Integer.parseInt(st.nextToken());
            if (K == 0) break;
            arrayList = new ArrayList<>();
            visited = new boolean[K];
            selectedArr = new int[6];
            for (int i = 0; i < K; i++) {
                arrayList.add(Integer.parseInt(st.nextToken()));
            }
            combination(0, 0);
            sb.append("\n");
        }
        System.out.println(sb);
    }

    //k개 중에 6개 뽑기
    public static void combination(int cnt, int idx) {
        //자릿수, 방문테이블
        if (cnt == 6) {
            printResult();
            return;
        }
        for (int i = idx; i < K; i++) {
            if (!visited[i]) {
                visited[i] = true;
                selectedArr[cnt] = arrayList.get(i);
                combination(cnt + 1, i + 1);
//                System.out.println("cnt값:" + cnt);
                visited[i] = false;
            }
        }
    }

    public static void printResult() {
        for (int i = 0; i < 6; i++) {
            sb.append(selectedArr[i] + " ");
        }
        sb.append("\n");
    }
}
