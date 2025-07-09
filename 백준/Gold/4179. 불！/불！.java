
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

// 4179 - 불!
// 지훈이가 불에 타기전에 탈출할 수 있는지의 여부, 얼마나 빨리 탈출할 수 있는지 결정
// 불은 각 지점에서 네 방향으로 확산됨, 지훈이는 미로의 가장자리에 접한 공간에서 탈출할 수 있음
// 지훈이와 불은 벽이 있는 공간은 통과 X
// #: 벽, . :지나갈수, J:지훈이의 초기위치, F:불이 난 공간
// 출력 : 불이 도달하기 전에 미로를 탈출할 수 없는 경우, IMPOSSIBLE 출력, 탈출가능한 경우 가장 빠른 탈출시간 출력
// 주의 1. 언제가 미로 탈출할 수 없을까? => 더이상 이동할 수 없을 때, 주의2. 불이있는 경우는 2군데 이상일수도 => 날짜가 정해져있기 때문에 바로바로 확산X, time을 기준으로 해야함.
// 탈출의 조건: 가장자리 => 위아래옆이 한번이라도 갈수가 없는 곳이면 가장자리임
// visited - 0은 아직 아무도 방문 X, 1은 지훈이가 방문, 2는 불이 방문
// 내가 자꾸 놓치는 것 - map 바꾸고 그 이후 반복문에서 또 해당되는 것을 생각 X & 먼저 불이 이동했을 경우, 지훈이도 이동할 수 있는데
// 그럼 지훈이동 -> 불 이동이 무조건적으로 선행되어야함
// 기존코드의 문제점 : 시간초과 & 탈출의 기준이 해당 초가 끝났을 때, 지훈이가 탈출한 지점이 불에 탔는지 안탔는지도 확인해야함
// 시간초과의 이유 : while문 한번 돌때, 두번의 map 탐색 x => 한번만에 끝내기
public class Main {
    static int R, C;
    static char[][] map;
    static int[][] visited;
    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1};
    static int exitTime = 0;
    static boolean isImpossible = false;
    static int[][] time;
    static int exitCnt = 0;
    static ArrayList<int[]> jList = new ArrayList<>();
    static ArrayList<int[]> fireList = new ArrayList<>();
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        map = new char[R][C];
        visited = new int[R][C];
        time = new int[R][C];
        for (int i=0; i<R; i++) {
            String str= br.readLine();
            for (int j=0; j<C; j++) {
                map[i][j] = str.charAt(j);
                if (map[i][j] == 'J' || map[i][j] == 'F') time[i][j] = 1;
                if (map[i][j] == 'J') {
                    if (i == 0 || i == R-1 || j == 0 || j == C-1) {
                        exitCnt = 1;
                    }
                }
            }
        }

        while(true) {
            // 지훈이가 위치한 부분 가장자리 체크 &
            exitTime++;

            if (exitCnt > 0) break;
            if (isImpossible) break;
            exitCnt = 0;
            jList.clear();
            fireList.clear();

            // 지훈 & 불 이동
            boolean isJihoonMove = false;
            for (int i =0; i<R; i++) {
                for (int j=0; j<C; j++) {
                    if (time[i][j] != exitTime) continue;
                    if(visited[i][j]==0 && map[i][j]=='J') {
                        isJihoonMove = true;
                        jList.add(new int[]{i, j});
                    }
                    else if(visited[i][j] != 2 && map[i][j] == 'F') {
                        fireList.add(new int[]{i, j});
                    }
                }
            }
            if(!jList.isEmpty()) for (int[] arr: jList) moveJ(arr[0], arr[1]);
            if(!fireList.isEmpty()) for (int[] arr: fireList) moveFire(arr[0], arr[1]);

            if (!isJihoonMove) isImpossible = true;
            // 불 이동 => 이미 체크한 불의 경우 앞으로 제외 => 새롭게 추가된 불은 추가
        }
        if (isImpossible) System.out.println("IMPOSSIBLE");
        else if (exitCnt > 0) System.out.println(exitTime);
    }
    // isExit & isImpossible 체크
    public static void moveJ(int x, int y) {
//        Queue<int[]> q = new LinkedList<>();
//        q.offer(new int[]{x, y});
        visited[x][y] = 1;
        time[x][y] = exitTime;

        for (int i=0; i<4; i++) {
            int nx = x + dx[i];
            int ny = y + dy[i];

            if (nx<0 || nx>=R || ny<0 || ny>=C || visited[nx][ny] == 1 || visited[nx][ny]==2 || map[nx][ny] == 'F' || map[nx][ny] == '#' || map[nx][ny] == 'J') continue;
            if (map[nx][ny] == '.') {
                map[nx][ny] = 'J';
                time[nx][ny] = exitTime+1;
                if (nx == 0 || nx == R-1 || ny == 0 || ny == C-1) {
                    exitCnt++;
                }
//                q.offer(new int[]{nx, ny});
            }
        }
    }

    public static void moveFire(int x, int y) {
//        Queue<int[]> q = new LinkedList<>();
//        q.offer(new int[]{x, y});
        visited[x][y] = 2;
        time[x][y] = exitTime;

        for (int i = 0; i < 4; i++) {
            int nx = x + dx[i];
            int ny = y + dy[i];

            if (nx < 0 || nx >= R || ny < 0 || ny >= C || visited[nx][ny]==2 || map[nx][ny] == '#' || map[nx][ny] == 'F') continue;
            if (map[nx][ny] == 'J') {
                if (nx == 0 || nx == R-1 || ny == 0 || ny == C-1) {
                    exitCnt--;
                }
            }
            map[nx][ny] = 'F';
            time[nx][ny] = exitTime+1;
//            if (map[nx][ny] == '.') {
//                q.offer(new int[]{nx, ny});
//            }
        }
    }
}
