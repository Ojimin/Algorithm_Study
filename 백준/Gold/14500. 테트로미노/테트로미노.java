
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// 14500 - 테트로미노
// 폴리오미노의 조건
// 조건 1. 정사각형은 서로 겹치면 X
// 조건 2. 도형은 모두 연결되어 있어야함
// 조건 3. 정사각형은 변끼리 연결되어 있어야 함
// 테트로미노 : 정사각형 4개를 이어붙인 폴리오미노 => 5종류 존재
// 출력 : 테트로미노 하나를 적절히 놓아 테트로미노가 놓인 칸의 수 합이 최대가 되도록 하기 => 백트래킹?
// 주의 : 회전이나 대칭 O
// 어떤 방식으로 구현? 백트래킹 & 시뮬, dfs
// 회전과 대칭을 어떻게 구현? => dfs식으로 풀면 가운데 손가락 모양의 테트로미노일때 예외발생 => 동시에 양옆 체크해서 계산,,
// 예외조건 하나더 추가? 만약 이동이 세방향 이상 가능할때, 굳이 방문하지 않고 계산 ㄱ
// 반례 : 중간에 2,3방향으로 가는 케이스 생각 못했음
public class Main {
    static int N, M, map[][];
    static boolean[][] visited;
    static int max = Integer.MIN_VALUE;
    static int[] dx = {-1, 0, 1, 0};
    static int[] dy = {0, 1, 0, -1};
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        map = new int[N][M];
        visited = new boolean[N][M];

        for (int i=0; i<N; i++){
            st = new StringTokenizer(br.readLine());
            for (int j=0; j<M; j++){
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        for (int i=0; i<N; i++) {
            for (int j=0; j<M; j++) {
                dfs(i, j, 1, map[i][j]);
                visited[i][j] = false;
            }
        }
        System.out.println(max);
    }

    public static void dfs(int x, int y, int depth, int cnt) {
        if (depth == 1) {
            visited[x][y] = true;
        }
        if (depth == 4) {
            if (cnt > max) max = cnt;
            return;
        }

//        int threeWay = 0;
        for (int i=0; i<4; i++) {
            int nx = x + dx[i];
            int ny = y + dy[i];
            if (nx < 0 || ny < 0 || nx >= N || ny >= M || visited[nx][ny]) continue;
            if (depth == 2) {
                visited[nx][ny] = true;
                dfs(x, y, depth+1, cnt+map[nx][ny]);
                visited[nx][ny] = false;
            }
            visited[nx][ny] = true;
            dfs(nx, ny, depth+1, cnt+map[nx][ny]);
            visited[nx][ny] = false;
//            threeWay++;
        }
//        if (depth == 2 && threeWay >=2) isThreeWay(x, y, depth, cnt);
    }

//    public static void isThreeWay(int x, int y, int depth, int cnt) {
//        for (int i=0; i<3; i++) {
//            int nx = x + dx[i];
//            int ny = y + dy[i];
//            if (nx < 0 || ny < 0 || nx >= N || ny >= M || visited[nx][ny]) continue;
//            for (int j=i+1; j<4; j++) {
//                int nx2 = x + dx[j];
//                int ny2 = y + dy[j];
//                if (nx2 < 0 || ny2 < 0 || nx2>=N || ny2 >= M || visited[nx2][ny2]) continue;
//                if (!visited[nx][ny] && !visited[nx2][ny2]) {
//                    cnt += map[nx][ny];
//                    cnt += map[nx2][ny2];
//                    if (cnt > max) max = cnt;
//                    cnt -= map[nx][ny];
//                    cnt -= map[nx2][ny2];
//                }
//            }
//        }
//    }
}
