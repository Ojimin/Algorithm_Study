
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// 21610 - 마법사 상어와 비바라기
// A[r][c] = r,c에 있는 바구니에 저장되어 있는 물의 양
// 1번 행 - N번 행, 1번 열 - N번 열 연결 => N번행 아래에는 1번, 1번행 위는 N행 , 열도 똑같
// (N, 1), (N, 2), (N-1, 1), (N-1, 2) => 비구름 생김,
// 이동을 M번 명령하려고 함, i번쨰 이동 명령 : d & s , 방향은 총 8개 방향,
// 규칙 -
// 0. (N,1),(N,2), (N-1,1), (N-2,2) 비구름 생김
// 1. 모든 구름이 di방향으로 si칸 이동
// 2. 각 구름이 있는 캄의 바구니 물양이 1 증가
// 3. 구름 모두 사라짐
// 4. 2에서 물이 증가한 칸 R,c에서 물복사버그마법 시전 => 대각선바향으로 거리가 1인 칸에 물 있는 바구니 수만큼 증가함
// 4-1. 이동과 다르게, 경계를 넘어간 카는 대각선 방향으로 1인 칸 X
// 5. 바구니 물의 양이 2이상인 모든 칸에 구름 생김 & 물의 양 2 줄어듦 => 주의! 3번에서 사라진 구름이 아니여야함
// 출력 : M번 이동 끝난후, 물의양 합
// 시뮬 + bfs, 주의 : 조건 잘 거를 것, 구름 기록 주의
public class Main {
    static int N, M, A[][];
//    static boolean[][] cloud; // 매 턴마다 생긴 구름 위치 갱신
    static int[] dx = {0, 0, -1, -1, -1, 0, 1, 1, 1};
    static int[] dy = {0, -1, -1, 0, 1, 1, 1, 0, -1};
    static int result = 0;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        A = new int[N+1][N+1];
        boolean[][] cloud = new boolean[N+1][N+1];
        for (int i = 1; i <= N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 1; j <= N; j++) {
                A[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        for (int i=0; i<M; i++) {
            st = new StringTokenizer(br.readLine());
            int d = Integer.parseInt(st.nextToken()); // 방향 d
            int s = Integer.parseInt(st.nextToken()); // 거리 s
            cloud = moveCloud(d, s, i, cloud);
        }

        getResult();
        System.out.println(result);
    }

    // 첫 Try일 때는 지정된 4칸에 구름 생김
    // 인덱스 이동시킬 때, s가 N보다 얼마나 큰지 체크
    public static boolean[][] moveCloud(int d, int s, int index, boolean[][] cloud) {
        int div = s % N != 0 ? ((s/N)+1)*N : (s/N)*N;
        boolean[][] modifiedCloud = new boolean[N+1][N+1];
        if (index == 0) {
            int cloudX1 = (N+dx[d]*s + div)%N;
            int cloudY1 = (1+dy[d]*s + div)%N;
            int cloudX2 = (N+dx[d]*s + div)%N;
            int cloudY2 = (2+dy[d]*s + div)%N;
            int cloudX3 = (N-1+dx[d]*s + div)%N;
            int cloudY3 = (1+dy[d]*s + div)%N;
            int cloudX4 = (N-1+dx[d]*s + div)%N;
            int cloudY4 = (2+dy[d]*s + div)%N;

            modifiedCloud[cloudX1==0?N:cloudX1][cloudY1==0?N:cloudY1] = true;
            modifiedCloud[cloudX2==0?N:cloudX2][cloudY2==0?N:cloudY2] = true;
            modifiedCloud[cloudX3==0?N:cloudX3][cloudY3==0?N:cloudY3] = true;
            modifiedCloud[cloudX4==0?N:cloudX4][cloudY4==0?N:cloudY4] = true;
            A[cloudX1==0?N:cloudX1][cloudY1==0?N:cloudY1]++;
            A[cloudX2==0?N:cloudX2][cloudY2==0?N:cloudY2]++;
            A[cloudX3==0?N:cloudX3][cloudY3==0?N:cloudY3]++;
            A[cloudX4==0?N:cloudX4][cloudY4==0?N:cloudY4]++;
        } else {
            for (int i=1; i<=N; i++) {
                for (int j=1; j<=N; j++) {
                    if (cloud[i][j]) {
                        int cloudX = (i+dx[d]*s + div) % N == 0? N : (i+dx[d]*s + div) % N;
                        int cloudY = (j+dy[d]*s + div) % N == 0? N : (j+dy[d]*s + div) % N;
//                        cloud[i][j] = false;
                        A[cloudX][cloudY]++;
                        modifiedCloud[cloudX][cloudY] = true;
                    }
                }
            }
        }
        waterMagic(modifiedCloud);
        return makeCloud(modifiedCloud);
    }

    public static void waterMagic(boolean[][] cloud) {
        for (int i=1; i<=N; i++) {
            for (int j=1; j<=N; j++) {
                if(cloud[i][j]) {
                    int cnt = 0;
                    for (int k=2; k<=8; k+=2) {
                        int nx = i+dx[k];
                        int ny = j+dy[k];
                        if (nx <=0 || nx>N || ny <=0 || ny>N) continue;
                        if (A[nx][ny] > 0) cnt++;
                    }
                    A[i][j] += cnt;
                }
            }
        }
    }

    public static boolean[][] makeCloud(boolean[][] cloud) {
        boolean[][] newCloud = new boolean[N+1][N+1];
        for (int i=1; i<=N; i++) {
            for (int j=1; j<=N; j++) {
                if (cloud[i][j]) {
                    newCloud[i][j] = false;
                    continue;
                }
                if (A[i][j] >= 2) {
                    newCloud[i][j] = true;
                    A[i][j] -= 2;
                }
            }
        }
        return newCloud;
    }

    public static void getResult() {
        for (int i=1; i<=N; i++) {
            for (int j=1; j<=N; j++) {
                result += A[i][j];
            }
        }
    }
}
