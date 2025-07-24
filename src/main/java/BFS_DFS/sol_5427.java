package BFS_DFS;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

// 5427 - 불
// 4179와 유사한 문제
// 왜 visited 안해도 되는지.. 고민
public class sol_5427 {
    static int W, H;
    static char[][] map;
    static boolean[][] visited;
    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1};
    static int exitTime = 0;
    static boolean isExit = false;
    static int[][] fireTime, jTime;
    static Queue<int[]> jQ, fireQ;
    //    static int exitCnt = 0;
//    static ArrayList<int[]> jList = new ArrayList<>();
//    static ArrayList<int[]> fireList = new ArrayList<>();
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());
        StringBuilder sb = new StringBuilder();
        for (int t=0; t<T; t++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            W = Integer.parseInt(st.nextToken());
            H = Integer.parseInt(st.nextToken());
            map = new char[H][W];
            visited = new boolean[H][W];
            fireTime = new int[H][W];
            jTime = new int[H][W];
            exitTime = 0;
            isExit = false;
            jQ = new LinkedList<>();
            fireQ = new LinkedList<>();
            for (int i=0; i<H; i++) {
                String str= br.readLine();
                for (int j=0; j<W; j++) {
                    map[i][j] = str.charAt(j);
                    if (map[i][j] == '@') {
                        jQ.offer(new int[]{i, j});
                        jTime[i][j] = 0;
                    }
                    if (map[i][j] == '*') {
                        fireQ.offer(new int[]{i, j});
                        fireTime[i][j] = 0;
                    }
                }
            }

            fireBFS();
            jBFS();
            if (isExit) sb.append(exitTime).append("\n");
            else sb.append("IMPOSSIBLE").append("\n");
        }
        System.out.println(sb);
    }

    public static void jBFS() {
        while(!jQ.isEmpty()) {
            int[] now = jQ.poll();
            for (int i=0; i<4; i++) {
                int nx = now[0] + dx[i];
                int ny = now[1] + dy[i];
                if (nx<0 || nx>=H || ny<0 || ny>=W ) {
                    isExit = true;
                    exitTime = jTime[now[0]][now[1]]+1;
                    return;
                }
                if (visited[nx][ny] || map[nx][ny] == '#' || map[nx][ny] == '*' ) continue;
                int time = jTime[now[0]][now[1]] +1;
                visited[nx][ny] = true;
                if (fireTime[nx][ny] != 0 && time >= fireTime[nx][ny]){
                    continue;
                }
                jTime[nx][ny] = time;
                jQ.offer(new int[]{nx, ny});
            }
        }

    }

    public static void fireBFS() {
        while(!fireQ.isEmpty()) {
            int[] now = fireQ.poll();
            for (int i=0; i<4; i++) {
                int nx = now[0] + dx[i];
                int ny = now[1] + dy[i];

                if (nx<0 || nx>=H || ny<0 || ny>=W || map[nx][ny] == '#' || map[nx][ny] == '*') continue;
                if (fireTime[nx][ny] != 0 && fireTime[nx][ny] <= fireTime[now[0]][now[1]] + 1) continue;
                fireTime[nx][ny] = fireTime[now[0]][now[1]] + 1;
                fireQ.offer(new int[]{nx, ny});
            }
        }
    }
}
