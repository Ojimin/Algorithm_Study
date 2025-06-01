package Backtracking;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

// 15650 - N과 M(2)
// 조합, 오름차순
public class sol_15650 {
    static int N, M;
    static ArrayList<Integer> array = new ArrayList<>();
    static boolean[] visited;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        visited = new boolean[N+1];
        combination(0,1);
    }

    public static void combination(int depth, int start) {
        if (depth == M) {
            for (int i : array) {
                System.out.print(i + " ");
            }
            System.out.println();
            return;
        }
        for (int i=start; i<=N; i++) {
            if(!visited[i]){
                visited[i] = true;
                array.add(i);
                combination(depth+1, i+1);
                visited[i] = false;
                array.remove(array.size()-1);
            }
        }
    }
}
