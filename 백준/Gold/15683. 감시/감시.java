import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.Array;
import java.util.*;

// 15683 - 감시
// CCTV가 감시할 수 있는 방법 : 5가지 & 벽 통과X & CCTV 통과 O
// CCTV가 감시할 수 없는 영역 : 사각지대
// CCTV 회전 가능 : 90도 방향, 가로 또는 세로 방향
// 0은 빈칸, 6은 벽, 1~5는 CCTV => 각 CCTV 번호별로 방향 지정
// 출력 : CCTV의 방향을 적절히 정해서 사각지대의 최소 크기
// 주의 : 개수 셀때, 벽 & cctv 있는곳은 제외
// 푸는 방법 : 1.각 위에서부터 단계별로 CCTV의 방향성 정하기 -> 2. 마지막 CCTV 도달하면 개수세고 사각지대 최솟값 갱신!! => 방향으로 해야하나?
// 다시 - 방향정하기 => 변수들이 가질 수 있는 값이 여러개이고 모든 조합을 확인하고 싶고 각각 독립적일 때, 진법 사용!!
// ex) 왜 4^k?
// 역시나 이동하는 부분자체를 보는게 아니라 방향을 조합함 & 시뮬레이션은 적절히 쪼개기 중요
// 1. 방향 조합 => cctv 개수대로 배열을 만들고 해당 개수까지 조합 완성
// 2. 각 cctv 번호대로 방향을 => 예를 들면 1번 cctv면 0,1,2,3에 따라 어케할건지
// 3. 이동해 cctv 안에 감시되는 구역들 표시 => 왜 bfs?
// 4. 사각지대 영역 구하기
// 우(0): 0,1 하(1): 1,0 , 좌(2): 0,-1 상(3): -1, 0
public class Main {
    static int N, M;
    static int map[][];
    static ArrayList<CCTV> cctvs = new ArrayList<>(); // 위치 기록
    static int depth = 0;
    static int[] dx = {0, 1, 0, -1};
    static int[] dy = {1, 0, -1, 0};
    static int[][] directions1 = {{-1, 0}, {0, 1}, {1,0}, {0, -1}}; // 4가지
    static int[][] directions2 = {{0, -1, 0, 1}, {-1, 0, 1, 0}}; // 2가지
    static int[][] directions3 = {{-1, 0, 0, 1},{0, 1, 1, 0}, {1,0,0,-1}, {0,-1,-1,0}}; //4가지
    static int[][] directions4 ={{0, -1, -1, 0, 0,1}, {-1,0, 0,1, 1,0}, {0,1, 1,0, 0, -1}, {1,0, 0,-1, -1, 0}}; //4가지
    static int[] directions5 = {0,1, 0, -1, 1, 0, -1, 0}; // 1가지
    static int answer = Integer.MAX_VALUE;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        map = new int[N][M];
        for (int i=0; i<N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j=0; j<M; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
                if (map[i][j] >= 1 && map[i][j] <6) {
                    cctvs.add(new CCTV(map[i][j], i, j));
                }
            }
        }
        depth = cctvs.size();
        dfs(0, new int[depth]);
        System.out.println(answer);
    }
    // 각 순서별로 방향의 조합을 배열로 저장해서 한번에 계산!!
    public static void dfs(int level, int[] dirComb) {
        if (level == depth) {
            // 배열 카운팅하는 함수
            int[][] copyMap = new int[N][M];
            for (int i=0; i<N; i++) {
                copyMap[i] = Arrays.copyOf(map[i], M);
            }
            for (int i=0; i<depth; i++) {
                checkComb(cctvs.get(i), dirComb[i], copyMap);
            }

            int cnt = count(copyMap);
            // 최솟값 갱신
            if (cnt < answer) answer = cnt;
            return;
        }

        for (int i=0; i<4; i++) {
            dirComb[level] = i;
            dfs(level+1, dirComb);
        }
    }
    // 방향 조합 => 이동을 아래 함수에서 진행하자
    // 우, 하, 좌, 상
    public static void checkComb(CCTV cctv, int dir, int[][] copyMap) {
        if (cctv.num == 1) { // 4
            move(copyMap, cctv, dir);
        } else if (cctv.num == 2) { // 2
            if (dir == 0 || dir == 2) {
                move(copyMap, cctv, 0);
                move(copyMap, cctv, 2);
            } else if (dir == 1 || dir == 3) {
                move(copyMap, cctv, 1);
                move(copyMap, cctv, 3);
            }
        } else if (cctv.num == 3) { // 4
            // 우, 상
            if (dir == 0) {
                move(copyMap, cctv, 0);
                move(copyMap, cctv, 3);
            } else if (dir == 1) { // 우, 하
                move(copyMap, cctv, 0);
                move(copyMap, cctv, 1);
            } else if (dir == 2) { // 하, 좌
                move(copyMap, cctv, 1);
                move(copyMap, cctv, 2);
            } else if (dir == 3) { // 좌, 상
                move(copyMap, cctv, 2);
                move(copyMap, cctv, 3);
            }
        } else if (cctv.num == 4) { // 4
            // 우, 상, 좌
            if (dir == 0) {
                move(copyMap, cctv, 0);
                move(copyMap, cctv, 2);
                move(copyMap, cctv, 3);
            }
            // 우, 상, 하
            else if (dir == 1) {
                move(copyMap, cctv, 0);
                move(copyMap, cctv, 1);
                move(copyMap, cctv, 3);
            }
            // 우, 하, 좌
            else if (dir == 2) {
                move(copyMap, cctv, 0);
                move(copyMap, cctv, 1);
                move(copyMap, cctv, 2);
            }
            // 하, 좌, 상
            else if (dir == 3) {
                move(copyMap, cctv, 1);
                move(copyMap, cctv, 2);
                move(copyMap, cctv, 3);
            }
        } else if (cctv.num == 5) { // 1
            // 우, 하, 상, 좌
            move(copyMap, cctv, 0);
            move(copyMap, cctv, 1);
            move(copyMap, cctv, 2);
            move(copyMap, cctv, 3);
        }
    }
    // 이동 - cctv 숫자, 위치, 방향 필요
    public static void move(int[][] copyMap, CCTV cctv, int dir) {
        Queue<CCTV> q = new LinkedList<>();
        q.offer(cctv);
        boolean[][] visited = new boolean[N][M];
        visited[cctv.x][cctv.y] = true;

        while(!q.isEmpty()) {
            CCTV now = q.poll();
            int nextX = now.x + dx[dir];
            int nextY = now.y + dy[dir];
            if(nextX < 0 || nextX >= N || nextY<0 || nextY>=M || visited[nextX][nextY]) continue;
            if (copyMap[nextX][nextY] == 6) continue;
            if (copyMap[nextX][nextY] == 0) {
                copyMap[nextX][nextY] = 10;
                visited[nextX][nextY] = true;
                q.offer(new CCTV(10, nextX, nextY));
            } else { // 1, 2, 3, 4, 5
                visited[nextX][nextY] = true;
                q.offer(new CCTV(copyMap[nextX][nextY], nextX, nextY));
            }
        }
    }

    // 사각지대 카운팅
    public static int count(int[][] copyMap) {
        int cnt = 0;
        for (int i=0; i<N; i++) {
            for (int j=0; j<M; j++) {
                if (copyMap[i][j] == 0) cnt++;
            }
        }
        return cnt;
    }

    public static class CCTV {
        int num;
        int x, y;

        public CCTV(int num, int x, int y) {
            this.num = num;
            this.x = x;
            this.y = y;
        }
    }

}
