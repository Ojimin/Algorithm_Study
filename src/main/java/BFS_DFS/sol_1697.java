package BFS_DFS;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

// 1697 - 숨바꼭질
// 1차원 BFS
// 수빈이는 1초 후 X-1, X+1, 2X 위치로 이동 가능
// 출력 : 수빈이가 동생을 찾을 수 있는 가장 빠른 시간은 몇초인지 구함
// 백트래킹으로는 안되나?
// 논리 오류 : 수빈이가 10만 밖을 넘어가지 않는다는 가정이 없음. 저기서 최대 2배인 20만까지 생각을 해봐야함
public class sol_1697 {
    static int N, K;
    static int[] dx = {-1, 1, 2};
    static int[] time;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        time = new int[100001];
        Arrays.fill(time,Integer.MAX_VALUE);
        bfs();
        System.out.println(time[K]);
    }

    public static void bfs() {
        Queue<Integer> q = new LinkedList<>();
        q.offer(N);
        time[N] = 0;
        while(!q.isEmpty()) {
            int now = q.poll();
            for (int i=0; i<3; i++) {
                int nx;
                if (i==2) nx = now*dx[i];
                else nx = now + dx[i];
                if (nx <0 || nx > 100000 || time[nx] <= time[now]+1) continue;
                if (nx == K) {
                    time[K] = time[now] + 1;
                    break;
                } else {
                    time[nx] = time[now] + 1;
                    q.offer(nx);
                }
            }
        }
    }
}
