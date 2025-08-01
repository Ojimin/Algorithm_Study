package BFS_DFS;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

public class 프로그래머스_네트워크 {
    ArrayList<Integer>[] map;
    boolean[] visited;
    public int solution(int n, int[][] computers) {
        map = new ArrayList[n];
        visited = new boolean[n];
        int answer = 0;
        for (int i=0;i<n;i++) {
            map[i] = new ArrayList<>();
            for (int j=0;j<n;j++) {
                if (i == j) continue;
                if(computers[i][j] == 1) map[i].add(j);
            }
        }
        for (int i=0;i<n; i++) {
            if (!visited[i]) {
                answer++;
                bfs(i);
            }
        }
        return answer;
    }
    public void bfs(int v) {
        Queue<Integer> q = new LinkedList<>();
        visited[v] = true;
        q.offer(v);

        while(!q.isEmpty()) {
            int now = q.poll();
            for (int i=0;i<map[now].size(); i++) {
                int next = map[now].get(i);
                if(!visited[next]) {
                    visited[next] = true;
                    q.offer(next);
                }
            }
        }
    }
}
