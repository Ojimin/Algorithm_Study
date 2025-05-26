import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

// 5547 - 일루미네이션
// 출력 : 조명을 장식할 벽면의 길이의 합
// 건물이 있을 땐 1, 없을 땐 0
// 주의 : x,y 좌표가 서로 반대임 & y가 홀수일때, 아래에 있는 좌표는 x,y+1 & y=짝수, 오른쪽 아래가 x,y+1
// bfs
// 고민 : 어떻게 인접한 걸로 체크를 할지?
// 6방향 이동 :왼쪽&오른쪽 이동은 똑같음. y=짝수일 때, 왼쪽 위 : x-1, y-1, 오른쪽 위: x,y-1, 왼쪽 아래: x-1, y+1, 오른쪽 아래: x,y+1
// y=홀수일때, 왼쪽위: x,y-1, 오른쪽위: x+1, y-1, 왼쪽아래: x,y+1, 오른쪽아래: x+1, y+1
// 다시 - 반대로 생각해야함. 0에서 시작해서 옆에 1이면 length
public class Main {
    static int W, H;
    static int[][] map;
    static boolean[][] visited;
    static int[] oddDx = {1, -1, 0, 1, 0, 1};
    static int[] oddDy = {0, 0, -1, -1, 1, 1};
    static int[] evenDx = {1,-1, -1, 0, -1, 0};
    static int[] evenDy = {0, 0, -1, -1, 1, 1};
    static int length = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        W = Integer.parseInt(st.nextToken());
        H = Integer.parseInt(st.nextToken());
        map = new int[H+2][W+2]; //왼쪽, 끝쪽 각각 +1 씩
        visited = new boolean[H+2][W+2];

        // 입력받기
        for (int i=1; i<=H; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j=1; j<=W; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        bfs(0,0);

        System.out.println(length);
    }
    // 좌푯값 헷갈리니까 먼저 x,y 변환 -> y가 짝수, 홀수인지 체크 -> dx, dy 더하고 해당 좌표 다시 x,y 변환 -> visited 체크
    public static void bfs(int y, int x) {
        Queue<Integer[]> q = new LinkedList<>();
        q.offer(new Integer[]{y, x});
        visited[y][x] = true;

        while(!q.isEmpty()) {
            Integer[] now = q.poll();
            // 변환
            int nowY = now[0];
            int nowX = now[1];

            int[] dx = nowY % 2 == 0 ? evenDx : oddDx;
            int[] dy = nowY % 2 == 0 ? evenDy : oddDy;
            for (int i=0; i<6; i++) {
                int nx = nowX + dx[i];
                int ny = nowY + dy[i];

                if(nx<0 || nx>W+1 || ny<0 || ny>H+1) continue;
                if(map[ny][nx] == 1) length++;
                else if (!visited[ny][nx] && map[ny][nx] == 0) {
                    visited[ny][nx] = true;
                    q.offer(new Integer[]{ny, nx});
                }
            }
        }

    }
}
