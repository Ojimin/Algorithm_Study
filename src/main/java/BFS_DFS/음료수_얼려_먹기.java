package BFS_DFS;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

//동빈쓰 p149 음료수얼려먹기 - 생성되는 총 아이스크림 개수 구하기
//유기농 배추와 비슷
//1. dfs로 풀어볼 것
//2. bfs로도 풀어볼 것
//인접행렬로!
public class 음료수_얼려_먹기 {
    static int N, M, cnt;
    static int[][] graph;
    static boolean[][] visited;
    static int[] dx = {-1, 0, 1, 0};
    static int[] dy = {0, 1, 0, -1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        graph = new int[N + 1][M + 1];
        visited = new boolean[N + 1][M + 1];
        cnt = 0;
//        Scanner sc = new Scanner(System.in);
        for (int i = 1; i < N + 1; i++) {
//            String[] str = sc.next().split(""); 요방식 말고도
              String str = br.readLine();
              char[] ch = str.toCharArray(); //이 방식 존재
            for (int j = 1; j < M + 1; j++) {
                graph[i][j] = Character.getNumericValue(ch[j-1]);
//                graph[i][j] = Integer.parseInt(str[j-1]);
            }
        }

        for (int i = 1; i < N + 1; i++) {
            for (int j = 1; j < M + 1; j++) {
                if(graph[i][j] == 0 & !visited[i][j]) {
                    //dfs(i,j);
                    bfs(i,j);
                    cnt++;
                }
            }
        }
        System.out.println(cnt);
    }

    public static void dfs(int x, int y) {
        visited[x][y] = true;
        for(int i=0;i<4;i++) {
            int nx = x + dx[i];
            int ny = y + dy[i];
            if (nx>=1 && nx<=N && ny>=1 && ny<=M) {
                if (graph[nx][ny] == 0 && !visited[nx][ny]) {
                    dfs(nx,ny);
                }
            }
        }
    }
    public static void bfs(int x, int y) {
        Queue<List<Integer>> q= new LinkedList<>();
        q.offer(Arrays.asList(x,y));
        visited[x][y] = true;

        while(!q.isEmpty()) {
            List<Integer> list = q.poll();
            for (int i=0;i<4;i++) {
                int nx = list.get(0)+dx[i];
                int ny = list.get(1)+dy[i];
                if (nx>=1 && nx<=N && ny>=1 && ny<=M) {
                    if (graph[nx][ny] == 0 && !visited[nx][ny]) {
                        visited[nx][ny] = true;
                        q.offer(Arrays.asList(nx,ny));
                    }
                }
            }
        }
    }

}
