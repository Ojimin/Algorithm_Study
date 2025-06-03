
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

//5212 - 지구온난화, 인접한 세칸 또는 네칸에 바다가 있는 땅은 모두 잠겨버림
// 출력 : 50년 후 지도 출력
// x = 땅, . = 바다
// 1. map을 돌면서 잠길 땅을 확인 - 땅이면 findSeaCnt 호출해서 주위 바다 확인, 바다를 aroundsea에 저장
// 2. 인접 바다가 3칸 이상이면 가라않기 - removeLand : aroundSea 값이 3이상이면 0으로 변경
// 3. 지도에서 잠길부분 줄이기 - map을 돌면서 땅인 부분에 minX, minY, maxX, maxY 값 저장 => map[minX][minY] ~ map[maxX][maxY] 출력
// 바다는 0, 땅은 1
public class Main {
    static int R, C, minX = Integer.MAX_VALUE, minY = Integer.MAX_VALUE, maxX = -1, maxY = -1;
    static int[][] map, aroundSea;
    static int[] dx = {-1, 0, 0, 1};
    static int[] dy = {0, -1, 1, 0};

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer stringTokenizer = new StringTokenizer(br.readLine());

        R = Integer.parseInt(stringTokenizer.nextToken());
        C = Integer.parseInt(stringTokenizer.nextToken());

        aroundSea = new int[R][C];
        map = new int[R][C];
        for (int i = 0; i < R; i++) {
            char[] arr = br.readLine().toCharArray();
            for (int j = 0; j < C; j++) {
                map[i][j] = arr[j] == '.' ? 0 : 1;
            }
        }

        for (int i = 0; i < R; i++) {
            for (int j = 0; j < C; j++) {
                if (map[i][j] == 1) findSeaCnt(i, j);
            }
        }
        removeLand();
        makeMap();
        /**
         * 결과 출력
         */
        for (int i = minX; i <= maxX; i++) {
            for (int j = minY; j <= maxY; j++) {
                System.out.print(map[i][j] == 0 ? '.' : 'X');
            }
            System.out.println();
        }
    }

    private static void makeMap() {
        for (int i = 0; i < R; i++) {
            for (int j = 0; j < C; j++) {
                if (map[i][j] == 1) {
                    minX = minX > i ? i : minX;
                    minY = minY > j ? j : minY;
                    maxX = maxX < i ? i : maxX;
                    maxY = maxY < j ? j : maxY;
                }
            }
        }
    }

    private static void removeLand() {
        for (int i = 0; i < R; i++) {
            for (int j = 0; j < C; j++) {
                if (aroundSea[i][j] >= 3) {
                    map[i][j] = 0;
                }
            }
        }
    }

    private static void findSeaCnt(int x, int y) {
        for (int i = 0; i < 4; i++) {
            int nx = x + dx[i];
            int ny = y + dy[i];

            if (nx < 0 || ny < 0 || R <= nx || C <= ny || map[nx][ny] == 0) aroundSea[x][y] += 1;
        }
    }
}
