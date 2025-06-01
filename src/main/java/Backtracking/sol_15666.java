package Backtracking;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

//15666 - N과 M(12)
// 중복 조합 & 내림차순 출력
// 중복되는 수열 있으면 안됨
public class sol_15666 {
    static int N, M;
    static int[] arr;
    static Set<ArrayList<Integer>> set = new LinkedHashSet<>();
    static ArrayList<Integer> tmp = new ArrayList<>();
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        arr = new int[N];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }
        Arrays.sort(arr);
        combination(0, 0);
        for (ArrayList<Integer> list : set) {
            for (Integer num : list) {
                System.out.print(num + " ");
            }
            System.out.println();
        }


    }

    public static void combination(int depth, int start) {
        if (depth == M) {
            set.add(new ArrayList<>(tmp));
            return;
        }
        for (int i=start; i<N; i++) {
            tmp.add(arr[i]);
            combination(depth+1, i);
            tmp.remove(tmp.size()-1);
        }
    }
}
