package BFS_DFS;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

//7562-나이트의 이동,
//나이트는 몇번 움직이면 칸 이동 가능?
//bfs로 접근, 이동위차값을 갱신해서 이동하여 값 출력
//댜시
public class sol_7562 {
    static int[] dx = {-1, -2, -2, -1, 1, 2, 2, 1};
    static int[] dy = {-2, -1, 1, 2, 2, 1, -1, -2};
    static int l;
    static int[][] graph; //그래프와 방문체크 동시에
    static int result;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        int t = Integer.parseInt(br.readLine());
        StringBuilder sb = new StringBuilder();

        for (int i=0;i<t;i++) {
            result=0;
            l = Integer.parseInt(br.readLine());
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            st= new StringTokenizer(br.readLine());
            int to_x = Integer.parseInt(st.nextToken());
            int to_y = Integer.parseInt(st.nextToken());
            graph = new int[l][l];
            if (to_x == x && to_y == y) {
                graph[to_x][to_y] = 0;
            } else {
                bfs(x,y, to_x, to_y);
            }
            sb.append(graph[to_x][to_y]+"\n");
        }
        System.out.println(sb);
    }

    public static void bfs(int x, int y, int to_x, int to_y) {
        Queue<Node> queue = new LinkedList<>();
        queue.offer(new Node(x, y));
        graph[x][y] = 0;
        while(!queue.isEmpty()) {
            Node n = queue.poll();
            if (to_x==n.x && to_y==n.y){
                break;
            }
            for (int i=0;i<8;i++) {
                int nx = n.x + dx[i];
                int ny = n.y + dy[i];
                if (nx>=0 && nx<l && ny>=0 && ny<l) {
                    if (graph[nx][ny]==0 || graph[nx][ny] > graph[n.x][n.y]+1) {
                        graph[nx][ny]=graph[n.x][n.y]+1;
                        queue.offer(new Node(nx, ny));
                    }
                }
            }
        }
    }

    public static class Node {
        int x;
        int y;
        public Node(int x, int y) {
            this.x= x;
            this.y= y;
        }
    }
}
