package swea;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// 5215 - 햄버거 다이어트
// 재료 별 점수, 칼로리 존재
// 정해진 칼로리 아하의 조합 + 점수가 가장 높도록 조합 => 같은 재료 중복 x
// 출력 : 제한칼로리 이하중, 가장 맛에 대한 점수가 높은 햄버거 점수?
// 다시 : 부분집합 or knapsack 알고리즘
public class sol_5215 {
    static int ingCnt, N;
    static int max;
    static int[][] ing;//재료 배열
    static boolean[] visited;
    public static void main (String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();

        for (int i=0; i<T; i++) {
            st = new StringTokenizer(br.readLine());
            ingCnt = Integer.parseInt(st.nextToken()); //재료 개수
            N = Integer.parseInt(st.nextToken()); // 제한 칼로리
            max = Integer.MIN_VALUE; // 맛
            ing = new int[ingCnt][2];
            visited = new boolean[ingCnt];
            for (int j=0; j<ingCnt; j++) {
                st = new StringTokenizer(br.readLine());
                ing[j][0] = Integer.parseInt(st.nextToken()); // 맛
                ing[j][1] = Integer.parseInt(st.nextToken()); // 칼로리
            }
            dfs(0);
            sb.append("#" + (i+1)+" ").append(max).append("\n");
        }
        System.out.println(sb);

    }

    public static void dfs(int index) {

        if (index == ingCnt) {
            int calories = 0;
            int favor = 0;
            for (int i=0; i<ingCnt; i++) {
                if (visited[i]) {
                    favor += ing[i][0];
                    calories += ing[i][1];
                }
            }
            if (calories > N) return;
            if (favor > max) max = favor;
            return;
        }

        //뽑거나 안뽑거나
        visited[index] = true;
        dfs(index+1);

        visited[index] = false;
        dfs(index+1);


    }
}
