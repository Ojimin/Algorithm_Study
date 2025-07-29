// 출력 : 네트워크 개수
// 연결되어있으면 1개임. 연결 안된 각 노드는 각각의 네트워크 개수로 셈
// bfs+연결(인접) 그래프로 풀기
import java.util.*;
class Solution {
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