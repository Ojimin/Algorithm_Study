
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;

// 18808 - 스티커 붙이기
// 주황색 - 스티커가 붙은 칸, 하얀색 - 스티커가 붙지 않은 칸
// 올바르지 않은 모눈종이 : 불필요한 full 비어있는 행 & 열, 상화좌우로 연결되어 있지 않은 스티커
// 스티커 붙이는 방법 : 1. 회전X고 모눈종이에서 떼기 2. 스티커를 노트북 범위 안 & 겹치지 않게 해서 가장 위, 왼쪽부터 붙이기
// 3. 자리 없다면 스티커 시계방향으로 회전한뒤 2번 과정 반복
// 4. 0, 90, 180, 270도 회전시켰는데도 스티커 못 붙이면 버림
// 출력: 노트북에서 몇개의 칸이 채워졌는지 출력
// <풀이>-1. 0~3로 반복해서 0-0도, 1-90도, 2-180도, 3-270도로 회전 => 회전을 어떻게... 스티커 배열 자체를 변경보다는 읽는 방향을 변경? & 매번 배열을 만들어서하기에는 메모리 낭비...
// 1-1. 좌표값의 규칙을 찾자
// 2. 모눈종이에 배치했을 때, 일단 0행0열 ~열방향으로 증가한 후, 행방향 증가하면서 위치탐색
// 3. 가능하면 그 상태로 배치 아니면 다시 1번으로 돌아가서 회전 => 배치를 어떻게?. 스티커 방향대로 이동했을때, 자리가 있으면 go?
// 다시 - 직사각형 회전!! & 새롭게 배치 시, 기존 스티커에 +row, column 값 더하기
public class Main {
    static int N, M, K;
    static int[][] map;
    static int[] dx = {-1, 0, 1, 0}; // 상 우 하 좌
    static int[] dy = {0, 1, 0, -1};
    static int[][] sticker;
    static int answer = 0;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        map = new int[N][M];

        for (int i=0; i<K; i++) {
            st = new StringTokenizer(br.readLine());
            int row = Integer.parseInt(st.nextToken());
            int col = Integer.parseInt(st.nextToken());
            sticker = new int[row][col];
            for (int j=0; j<row; j++) {
                st = new StringTokenizer(br.readLine());
                for (int x=0; x<col; x++) {
                    sticker[j][x] = Integer.parseInt(st.nextToken());
                }
            }
            // sticker 배치 진행
            for (int dir=0; dir<4; dir++) {
                if (dir != 0) {
                    // 회전 진행
                    rotateSticker(sticker.length, sticker[0].length);
                }
                boolean result = arrangeSticker();
                if (result) {
                    break;
                }
            }
        }
        // 결과 체크
        countResult();
        System.out.println(answer);
    }

    // 회전
    public static void rotateSticker(int row, int col) {
        int tmp[][] = new int[row][col];
        for (int i=0; i<row; i++) {
            tmp[i] = Arrays.copyOf(sticker[i], col);
        }
        sticker = new int[col][row];
        for (int i=0; i<row; i++) {
            for (int j=0; j<col; j++) {
                sticker[j][row-1-i] = tmp[i][j];
            }
        }
    }

    // 스티커 배치를 위한 시작점 체크 => 시작한 위치만큼 더해서 비교 => 위 -> 왼
    // 예외 - 시작점은 차지하고 있지만, 스티커의 시작점은 비어있을 때
    public static boolean arrangeSticker() {
        for (int i=0; i<N; i++) {
            for (int j=0; j<M; j++) {
                if(map[i][j] == 0 || (map[i][j]== 1 && sticker[0][0] == 0)) {
                    if (comparison(i, j)) return true;
                }
            }
        }
        return false;
    }
    // 배치되는지 비교
    public static boolean comparison(int row, int col) {
        for (int i=0; i<sticker.length; i++) {
            for (int j=0; j<sticker[i].length; j++) {
                if (sticker[i][j] == 0) continue;
                if(i+row >= N || j+col >= M) return false;
                if (map[i+row][j+col] == 1) return false;
            }
        }
        // return 하지 않는다면 아래 코드 진행
        for (int i=0; i<sticker.length; i++) {
            for (int j=0; j<sticker[i].length; j++) {
                if (map[i+row][j+col] == 0 && sticker[i][j] == 1) map[i+row][j+col] = 1;
            }
        }
        return true;
    }
    public static void countResult() {
        for (int i=0; i<N; i++) {
            for (int j=0; j<M; j++) {
                if (map[i][j] == 1) answer++;
            }
        }
    }
}
