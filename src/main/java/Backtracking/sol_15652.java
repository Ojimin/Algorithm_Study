package Backtracking;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

// 15652 - N과 M(4)
// 중복 조합& N개 중 M개 고름 & 오름차순으로
public class sol_15652 {
    static int N, M;
    static ArrayList<Integer> list;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        list = new ArrayList<>();
        dfs(0, 1);
    }
    public static void dfs(int depth, int start) {
        if (depth == M) {
            for (int n: list) {
                System.out.print(n + " ");
            }
            System.out.println();
            return;
        }
        for (int i=start; i<=N; i++) {
            list.add(i);
            dfs(depth+1, i);
            list.remove(list.size()-1);
        }
    }
}
