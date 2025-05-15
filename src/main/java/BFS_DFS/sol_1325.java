package BFS_DFS;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

//1325 - 효율적인 해킹
// A가 B를 신뢰하는 경우, B를 해킹 -> A도 해킹 O
// 출력 : 한번에 가장 많은 컴퓨터를 해킹할 수 있는 컴퓨터 번호를 오름차순으로 출력
// 연결을 어떻게 하면 잘할까?
// map 배열에서 연결이 된 케이스에만 1을 입력
// dfs로 할 시에 시간복잡도가 O(N^2)가 되면서 시간초과 발생 => BFS
// 여전히 시간초과 발생 -> 배열+리스트로 변경
// 시간초과 발생 -> a -> b 형태로 변경, 즉 a노드를 돌아도 카운팅할 때 b노드의 값으로 올려서 계산
// 막트 - 없는 노드들은 순회 X, bfs를 내부 For문 안에서 처리
// 다시
public class sol_1325 {
    static ArrayList<Integer>[] map;
    static int[] num_cnt;
    static boolean[] visited;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        StringBuilder sb = new StringBuilder();
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        map = new ArrayList[N+1];
        int max = 0;
        num_cnt = new int[N+1];
        int end, start;

        for (int i=0; i<=N; i++) {
            map[i] = new ArrayList<>();
        }

        for(int i=0; i<M; i++) {
            st = new StringTokenizer(br.readLine());
            start = Integer.parseInt(st.nextToken());
            end = Integer.parseInt(st.nextToken());
            map[start].add(end);
        }

        for (int i=1 ; i<=N ; i++) {
            if(!map[i].isEmpty()){
                visited = new boolean[N+1];
                Queue<Integer> q = new LinkedList<>();
                q.offer(i);
                visited[i] = true;
                while(!q.isEmpty()) {
                    int now = q.poll();
                    for (int k : map[now]) {
                        if(!visited[k]) {
                            visited[k] = true;
                            num_cnt[k]++;
                            q.offer(k);
                        }
                    }
                }
            }

        }

        // max 값 구하기
        for (int i=1; i<=N; i++) {
            max = Math.max(max, num_cnt[i]);
        }
        for (int i=1; i<=N; i++) {
            if (num_cnt[i] == max) sb.append(i).append(" ");
        }
        System.out.print(sb);
    }

//    public static void dfs(int v) {
//        for (Integer i: map.get(v)) {
//            if (!visited[i]) {
//                visited[i]= true;
//                dfs(i);
//                cnt++;
//                visited[i]= false;
//            }
//        }
//
//    }

    public static void bfs(int v) {
        Queue<Integer> q = new LinkedList<>();
        q.offer(v);
        visited[v] = true;
        while(!q.isEmpty()) {
            int now = q.poll();
            for (int k : map[now]) {
                if(!visited[k]) {
                    visited[k] = true;
                    num_cnt[k]++;
                    q.offer(k);
                }
            }
        }
    }
}
