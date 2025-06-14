package Simulation;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

// 16236 - 아기 상어
// N*N 크기에 물고기 M마리 & 아기상어 1마리
// 아기상어 크기 : 2, 1초에 상하좌우로 인접한 한칸씩 이동
// 아기상어는 자기보다 큰 물고기있는 칸 이동X & 자기보다 크기가 작은 물고기만 먹을 수 있음 & 크기가 같은 물고기는 먹지는 못하고 이동만 가능
// 규칙1. 더이상 먹을 수있는 물고기가 없으면 엄마 상어에게 도움 요청
// 규칙2. 먹을 수 있는 물고기가 1마리이면 그 물고기 먹으러 감
// 규칙3. 먹을 수 있는 물고기가 1마리보다 많으면 거리가 가장 가까운 물고기 먹으러 감
// 규칙4. 거리 : 지나가야한ㄴ 칸의 개수의 최솟값
// 규칙5. 거리가 가까운 물고기 많다면 가장 위 -> 왼쪽 순으로 먹음
// 규칙6. 자신의 크기와 같은 수의 물고기를 먹을때마다 크기가 1 증가함
// 출력 : 몇초동안 엄마에게 요청하지않고 물고기를 잡아먹는지
// 주의 : 9가 아기상어의 위치, 나머지 1~6은 물고기 크기
// 반례: 상어엄마 요청 체크 조건 => 다 0 or 다 0은 아니지만 나머지가 상어크기보다 같거나 클 경우 or
// 정리 : 그냥 아기상어크기보다 작은 곳에 도달 못하면 break!!
public class sol_16236 {
    static int N, map[][];
    static int[][] visited;
    static int time = 0;
    static int[] dx = {-1, 0, 1, 0};
    static int[] dy = {0, -1, 0, 1};
    static int startX, startY;
    static int sharkSize = 2;
    static int eatingCnt = 0;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        map = new int[N][N];
        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
                if (map[i][j] == 9) {
                    startX = i;
                    startY = j;
                    map[i][j] = 0;
                }
            }
        }
        eatFish();
        System.out.println(time);
    }



    // 1. 아기상어 먹이 먹기 => 거리가 가까우면서 x값, y값 체크?거리가 가장 가까운 것(값이 같은것) x,y 리스트로 다 넣고 그중 가장 x,y 값이 적은 걸로 ㄱㄱ, 매턴 반복
    // 1-1. 가장 거리가 가까운 물고기가 있는 쪽 가기, 이때 이동할 때마다 자기보다 크기가 작거나 같은 경우만 이동 가능
    // 1-2. 거리가 가까운 물고기가 여러개인 경우, x값이 작을수록, y값이 작을수록 우선순위 높음
    // 1-3. 먹을 때 체크: 1-3-1. 아기 상어 크기 체크, 상어 크기만틈 먹으면 +1 증가 => 1-3-2. 몇개째 먹는지 체크
    public static void eatFish() {
        boolean isStop = false;
        while(!isStop) {
            visited = new int[N][N];
            for (int i = 0; i < N; i++) {
                Arrays.fill(visited[i], -1);
            }
            Queue<Integer[]> q = new LinkedList<>();
            q.offer(new Integer[]{startX, startY});
            visited[startX][startY] = 0;
            ArrayList<Integer[]> eatList = new ArrayList<>();
            int minDist = Integer.MAX_VALUE;
            while(!q.isEmpty()) {
                Integer[] now = q.poll();
                for (int i=0; i<4; i++) {
                    int nx = now[0] + dx[i];
                    int ny = now[1] + dy[i];
                    if (nx < 0 || ny < 0 || nx >= N || ny >= N || map[nx][ny] > sharkSize || visited[nx][ny]!=-1) continue;
                    if (map[nx][ny] == sharkSize || map[nx][ny] == 0) {
                        visited[nx][ny] = visited[now[0]][now[1]] + 1;
                        q.offer(new Integer[]{nx, ny});
                    } else if (map[nx][ny] != 0 && map[nx][ny] < sharkSize) {
                        isStop = true;
                        visited[nx][ny] = visited[now[0]][now[1]] + 1;
                        q.offer(new Integer[]{nx, ny});
                        if (minDist > visited[nx][ny]) {
                            eatList.clear();
                            minDist = visited[nx][ny];
                            eatList.add(new Integer[]{nx, ny});
                        } else if (minDist == visited[nx][ny]) eatList.add(new Integer[]{nx, ny});
                    }
                }
            }
            if (isStop) isStop = false;
            else return;
            findBestAndEat(eatList);
            time += minDist;

        }
        return;
    }

    // 1-1. 엄마 도움 요청 체크
    public static boolean checkHelp() {
        // 매번 돌면서 더이상 먹을 수 있는 것이 남았는지 체크
        // 조건 1. 다 0
        // 조건 2. 다 0은 아니지만 남아있는 것들이 모두 0 + 상어크기보다 같거나 큰 것들
        boolean isEating = false;
        boolean visited[][] = new boolean[N][N];

        for (int i=0; i<N; i++) {
            for (int j=0; j<N; j++) {
                if (map[i][j] != 0 && map[i][j] < sharkSize) {
                    isEating = true;
                    break;
                }
            }
            if (isEating) break;
        }
        return isEating;
    }

    // 최적의 물고기 먹고 & 상어크기 조정
    // x 값 작고 -> y값 작은 것 기준
    public static void findBestAndEat(ArrayList<Integer[]> eatList) {
        if (eatList.size() == 0) return;
        else if (eatList.size() == 1) {
            startX = eatList.get(0)[0];
            startY = eatList.get(0)[1];
            eatingCnt++;
        } else {
            int minX = Integer.MAX_VALUE;
            int minY = Integer.MAX_VALUE;

            for (Integer[] fish : eatList) {
                if (minX > fish[0]) minX = fish[0];
            }
            for (Integer[] fish : eatList) {
                if (fish[0] == minX) {
                    if (minY > fish[1]) minY = fish[1];
                }
            }
            startX = minX;
            startY = minY;
            eatingCnt++;
        }
        map[startX][startY] = 0;
        if (eatingCnt == sharkSize) {
            sharkSize++;
            eatingCnt = 0;
        }
    }
}
