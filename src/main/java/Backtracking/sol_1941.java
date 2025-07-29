package Backtracking;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

// 1941 - 소문난 칠공주
// 규칙1. 7명의 여학생
// 규칙2. 7명의 자리는 가로나 세로가 반드시 인접
// 규칙3. 반드시 이다솜파의 학생들로만 구성될 필요는 없음
// 규칙4. 그러나 적어도 7명 학생중 이다솜파의 학생이 4명이상은 반드시 포함
// 출력 : 규칙에 맞는 소문난 칠공주를 결성할 수 있는 모든 경우의 수
// S - 이다솜, Y -임도연
// 대신 그 전줄거는 포함안되게
// 내 기존 방식 : 각각 map의 시작하는 부분을 정해서 돌아가면서 백트래킹 => 이렇게 하면 한 방향으로만 지속해서 찾을 수 밖에 없음
// 테트리스 + N-Queen? => 테트리스는 백트래킹 하나만으로도 해결가능, but 변형이 여러개이므로
// 다시 - (방법 생각x) backtracking(조합) + bfs => 여러 방향으로도 탐색 가능
// 1. 25개 중 7개 뽑기, 이떄 Y가 4개 이상이면 안되게
// 2. 7개 뽑은 것들이 연결되는지 확인하기
// 3. 연결되면 카운팅
// 핵심 : 2차원 배열을 중복없이 조합하기 위해 일차원 배열로 풀것!!!
// 조합과 순열의 차이 : start를 체크하냐 아니냐의 차이
public class sol_1941 {
    static char[][] map = new char[5][5];
    static boolean[] visited;
    static int result=0;
    static int[] dx = {-1,1, 0, 0};
    static int[] dy = {0, 0, -1, 1};
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        for(int i=0;i<5;i++) {
            String str = br.readLine();
            for (int j=0;j<5;j++) {
                map[i][j] = str.charAt(j);
            }
        }
        int[] arr = new int[7];
        backtracking(0, arr, 0, 0);
        System.out.println(result);
    }
    // cnt는 임도연파 개수는 최대 3개
    // 중요 : 좌표 변환 = 13/5 = 2(행값), 13%5=3(열값)
    public static void backtracking(int depth, int[] arr, int start, int cntY) {
        if (cntY>=4) return;
        if (depth == 7) {
            visited = new boolean[25];
            bfs(arr);
            return;
        }

        for (int i=start; i<25; i++) {
            arr[depth] = i;
            if (map[i/5][i%5] == 'Y') {
                backtracking(depth+1, arr, i+1, cntY+1);
            } else {
                backtracking(depth+1, arr, i+1, cntY);
            }
        }
    }

    public static void bfs(int[] arr) {
        Queue<Integer> q = new LinkedList<>();
        q.offer(arr[0]);
        visited[arr[0]] = true;
        int arrCnt = 1;

        while(!q.isEmpty()) {
            int now = q.poll();
            int nowX = now / 5;
            int nowY = now % 5;
            for (int i=0;i<4;i++) {
                int nx = nowX + dx[i];
                int ny = nowY + dy[i];
//                System.out.println("num: "+ num);
                if (nx <0 || nx>=5 || ny<0 || ny>=5) continue;
                int num = 5*nx + ny;
                if (visited[num]) continue;
                visited[num] = true;
                for (int tmp:arr) {
                    if (tmp == num) {
                        arrCnt++;
                        q.offer(num);
                        break;
                    }
                }
            }
        }
//        System.out.println("arrCnt 값: " + arrCnt);
        if (arrCnt == 7) {
            result++;
        }
    }

}
