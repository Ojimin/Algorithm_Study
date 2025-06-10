package Simulation;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// 5212 - 지구온난화
// 'X' = 땅, '.' = 바다
// 인접한 세네칸에 바다가 있는 땅은 모두. ㅏㅁ겨버림
// 출력 : 50년 후의 지도 출력 => 지도 크기는 모든 섬을 포함하는 가장 작은 직사각형
// 주의. 50년 뒤 지도에 섬을 포함한 최소 직사각형 => 섬의 최대 x, 최대 y값 구하기
public class review_5212 {
    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1};
    static int R, C, maxR, maxC, minR, minC;
    static char[][] map, newMap;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        StringBuilder sb = new StringBuilder();
        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        map = new char[R][C];
        newMap = new char[R][C];
        maxR = 0;
        maxC = 0;
        minR = Integer.MAX_VALUE;
        minC = Integer.MAX_VALUE;

        for (int i=0; i<R; i++) {
            String line = br.readLine();
            char[] chars = line.toCharArray();
            for (int j=0; j<C; j++) {
                map[i][j] = chars[j];
            }
        }

        //그래프 탐색
        for (int i=0; i<R; i++) {
            for (int j=0; j<C; j++) {
                if (map[i][j] == 'X') {
                    boolean isChanged =checkChange(i, j);
                    if (isChanged) {
                        newMap[i][j] = '.';
                    } else{
                        maxR = Math.max(maxR, i);
                        maxC = Math.max(maxC, j);
                        minR = Math.min(minR, i);
                        minC = Math.min(minC, j);
                        newMap[i][j] = 'X';
                    }
                } else newMap[i][j] = map[i][j];
            }
        }
        for (int i=minR; i<=maxR; i++) {
            for (int j=minC; j<=maxC; j++) {
                sb.append(newMap[i][j]);
            }
            sb.append("\n");
        }
        System.out.println(sb);

    }

    public static boolean checkChange(int i, int j) {
        int cnt = 0;
        for (int k=0; k<4; k++) {
            int nx = i + dx[k];
            int ny = j + dy[k];

            if (nx <0 || nx>=R || ny<0 || ny>=C || map[nx][ny] == '.') cnt++;
        }
        if (cnt >= 3) return true;
        return false;
    }
 }
