
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

// 11780 - 플로이드2
// 출력 : 먼저 각 도시별로 가는 비용 출력, 그 다음 경로를 출력, 갈 수 없는 경우 0 출력
// 플로이드의 경로 복원 문제
// 다시 - 출력을 위한 덱 사용!!
public class Main {
    static final int INF = 1_000_000_000;
    static StringBuilder sb;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        int m = Integer.parseInt(br.readLine());
        int dist[][] = new int[n+1][n+1];
        int next[][] = new int[n+1][n+1];
        sb = new StringBuilder();
        for (int i=1;i<=n; i++) {
            for (int j=1;j<=n;j++) {
                if (i==j) {
                    dist[i][j] = 0;
                } else dist[i][j] = INF;
            }
            Arrays.fill(next[i], 0);
        }
        for (int i=0; i<m; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int cost = Integer.parseInt(st.nextToken());
            if (dist[a][b] > cost) {
                dist[a][b] = cost;
                next[a][b] = b; // 직접 연결된 간선이 업데이트 될때 마다 바로 연결될 경로저장에도 업데이트 함
            }
        }

        for (int k=1; k<=n; k++) {
            for (int i=1; i<=n; i++) {
                for (int j=1; j<=n; j++) {
                    if (dist[i][j] > dist[i][k] + dist[k][j]) {
                        dist[i][j] = dist[i][k]+dist[k][j];
                        next[i][j] = next[i][k]; // 직접 연결된 바로 다음 노드로 저장
                    }
                }
            }
        }

        for (int i=1;i<=n; i++) {
            for (int j=1;j<=n; j++) {
                if(dist[i][j] == 1_000_000_000) sb.append(0+" ");
                else sb.append(dist[i][j] +" ");
            }
            sb.append("\n");
        }

        for (int i=1; i<=n; i++) {
            for (int j=1; j<=n;j++) {
                if (next[i][j] == 0) {
                    sb.append(0+"\n");
                    continue;
                }
                Deque<Integer> deque = new ArrayDeque<>();
                int arrive = next[i][j];
                deque.offer(i);
                while(true) {
                    if (arrive == j) {
                        deque.offer(arrive);
                        break;
                    }
                    deque.offer(arrive);
                    arrive = next[arrive][j];
                }
                sb.append(deque.size()+" ");
                while(!deque.isEmpty()) {
                    sb.append(deque.poll()+" ");
                }
                sb.append("\n");
            }
        }
        System.out.print(sb);
    }
}
