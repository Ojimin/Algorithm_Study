package Simulation;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

// 15685 - 드래곤커브
// 주의 : x좌표가 열, y좌표가 행
// 시작 점, 시작 방향, 세대
// k세대 드래곤 커브 = k-1세대 드래곤 커브 끝점 + 90도 시계방향 회전
// 입력의 시작방향 g의 0:오른쪽, 1: 위쪽, 2: 왼쪽, 3: 아래쪽
// 출력 : 정사각형의 네 꼭짓점이 모두 드래곤 커브의 일부인 것의 개수
// 시뮬레이션 - 문제 분해 필요 & 주의 : x,y는 코드 구현과 반대로 되어있음
// 문제 1. 해당 조건대로 세대수만큼 드래곤 커브 만들기 - 90도 시계방향 회전 => 이거 어떻게?
// 문제 1 주의. 매 세대별로 구해서 붙이면 삽질 -> 규칙 먼저 찾아야 함
// 문제 1 주의. 드래곤 커브의 방향을 전부 구한 뒤에 한번에 다 그리는 로직
// 문제에서 0세대 방향 : 0, 1세대: 0 1, 2세대 : 0 1 2 1, 3세대: 0 1 2 1 2 3 2 1
// 문제 1-1. 규칙 1. 방향 개수는 이전세대*2, 규칙2. 방향의 경우 앞세대의 방향+(뒤집은 앞세대 방향+1)임
// 문제 1-2. 커브 그리기 => 방향 리스트를 하나씩 뺴서 그리면 됨
// 주의 : 이때 드래곤 커브는 격자밖으로 벗어나지 X
// 문제 2. 드래곤 커브 꼭짓점이 포함된 배열에 매 꼭짓점을 오른쪽,왼쪽,대각선해서 해당되는 3개의 꼭짓점이 존재하는지 체크하고 있으면 카운팅
// 다시(복습 필요)
public class sol_15685 {
    static int[] dx = {1, 0, -1, 0}; //x가 증가, y감소, x감소, y 증가 방향 순
    static int[] dy = {0, -1, 0, 1};
    static final boolean[][] map = new boolean[101][101]; //x,y>=0, <=100 이라 했으므로..
    static int cnt = 0;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        StringTokenizer st;

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            int d = Integer.parseInt(st.nextToken());
            int g = Integer.parseInt(st.nextToken());
            getDirection(x, y, d, g);
        }
        countSquare();
        System.out.println(cnt);
    }

    // 문제 1-1. 세대별 방향 구하기
    public static void getDirection(int x, int y, int d, int g) {
        ArrayList<Integer> directions = new ArrayList<>();
        directions.add(d); //첫번쨰 방향 추가

        for (int j=1; j<=g; j++) {
            for (int k=directions.size()-1; k>=0; k--) {
                directions.add((directions.get(k)+1)%4);
            }
        }
        // 드래곤커브 그리기
        drawDragon(x, y, directions);
    }

    //문제 1-2. 드래곤 커브 그리기
    public static void drawDragon(int x, int y, ArrayList<Integer> directions) {
        map[x][y] = true;
        int nx = x;
        int ny = y;

        for (int j: directions) {
            nx += dx[j];
            ny += dy[j];
            map[nx][ny] = true;
        }
    }

    // 문제 2. 해당 꼭짓점 기준으로 사각형을 이루는지 계산
    public static void countSquare() {
        for (int i = 0; i < 100; i++) { //이때 0~100까지 있으므로 범위를 위해서 99까지만 체크
            for (int j = 0; j < 100; j++) {
                if(map[i][j] && map[i+1][j] && map[i][j+1] && map[i+1][j+1]) cnt++;
            }
        }
    }
}
