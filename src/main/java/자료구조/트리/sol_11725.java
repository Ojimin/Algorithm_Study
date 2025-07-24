package 자료구조.트리;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

// 11725 - 트리의 부모 찾기
// 트리의 루트를 1이라고 정했을 때, 각 노드의 부모를 구하는 프로그램
// 출력 : 각 노드의 부모 노드 번호를 2번 노드부터 순서대로 출력
// dfs로 하니까 스택오버플로우,, bfs로 풀어야하나?
public class sol_11725 {
    static int N;
    static LinkedList<Integer>[] arr;
    static int[] result;
    static boolean visited[];
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        N = Integer.parseInt(br.readLine());
        arr = new LinkedList[N+1];
        result = new int[N+1];
        visited = new boolean[N+1];
        for (int i=0; i<=N; i++) {
            arr[i] = new LinkedList<>();
        }
        for (int i=0; i<N-1; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int n1 = Integer.parseInt(st.nextToken());
            int n2 = Integer.parseInt(st.nextToken());
            arr[n1].add(n2);
            arr[n2].add(n1);
        }
//        dfs(1);
        bfs(1);
        for (int i=2; i<=N; i++) {
            sb.append(result[i]).append("\n");
        }
        System.out.println(sb);
    }

    public static void bfs(int num) {
        Queue<Integer> q = new LinkedList<>();
        visited[num] = true;
        q.offer(num);

        while(!q.isEmpty()) {
            int now = q.poll();
            for (int i=0; i<arr[now].size(); i++) {
                if (!visited[arr[now].get(i)]) {
                    result[arr[now].get(i)] = now;
                    visited[arr[now].get(i)] = true;
                    q.offer(arr[now].get(i));
                }
            }
        }
//        if (visited[num]) return;
//        visited[num] = true;
//        for (int i=0; i<arr[num].size(); i++) {
//            if (!visited[arr[num].get(i)] && arr[num].get(i) != 1) {
//                result[arr[num].get(i)] = num;
//                dfs(arr[num].get(i));
//            }
//        }
    }
}
