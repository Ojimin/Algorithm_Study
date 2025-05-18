package BFS_DFS;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

// 12851 - 숨바꼭질
// N -수빈, K - 동생,걸으면 N-1, N+1 / 순간이동 : 2*N
// 출력 : 수빈이가 동생을 찾을 수 있는 가장 빠른 시간 & 빠른 시간으로 찾는 방법 몇가지
// 이게 bfs..?? => <BFS 최적화 문제>
// 같아지는 시간은 구했음 스스로 -> but 최적화 & bfs 이해 더 필요
// 다시 - 경우의 수 구하기.. => 동생을 찾을 경우 종료 시키지 X & 이미 방문한 노드여도(중복방문) 최단 소요시간과 같다면 카운팅
public class sol_12851 {
    static int N, K;
    static int[] dx = {1, -1, 2};
    static int[] time = new int[100001]; // 소요시간
    static int cnt; // 경우의 수

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        bfs();
        System.out.println(time[K]);
        System.out.println(cnt);

    }

    public static void bfs() {
        Queue<Integer> queue = new LinkedList<>();
        queue.offer(N);
        if (N==K) {
            cnt = 1;
            return; // 수빈 & 동생 위치가 같아지면 종료
        }
        int next;

        while (!queue.isEmpty()) {
            int now = queue.poll();

            for (int i = 0; i < 3; i++) {
                if (i == 2) next = now * dx[i]; //순간이동
                else {
                    next = now + dx[i]; //걷기
                }
                if (next >=0 && next <= 100000) {
                    //아직 방문하지 않은 노드
                    if (time[next] == 0 || (time[next] >= time[now]+1)) { // 1->2->4의 경우 노드 2 방문하는 것이 중복됨
                        // 현재 최솟값 > 중복방문한 거여도 +1 했을때 같거나 작을때
                        time[next] = time[now] +1;
                        queue.offer(next);
                        if (next == K) {
                            cnt++;
                        }
                    }
                    //방문한 노드인데 기존 시간보다 오래 걸리지 않는 경우
//                   else if (next == K && time[next] >= time[now]+1 ) {
//                       time[next] = time[now] +1;
//                       queue.offer(next);
//                       cnt++;
//                    }

                }
            }
        }
    }
}
