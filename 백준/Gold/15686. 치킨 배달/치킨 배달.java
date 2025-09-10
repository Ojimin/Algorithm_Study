import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

// 15686 - 치킨 배달
// 치킨 거리 = 집과 가장 가까운 치킨집 사이의 거리 = 좌표차의 절댓값의 합
// 도시의 치킨 거리 = 모든 집의 치킨 거리 합
// 도시에 있는 치킨집 중 최대 M개 고르고 나머지 폐업 & 도시의 치킨 거리가 가장 작게될지
// 출력: 도시의 치킨 거리의 최솟값
// 치킨집 >= M <=13
// 치킨집 중복x조합 => 최대이므로 1~M까지의 조합 + 거리계산
public class Main {
    static int N, M, map[][], length;
    static ArrayList<Integer> chickList = new ArrayList<>();
    static ArrayList<Integer> homeList = new ArrayList<>();
    static int answer = Integer.MAX_VALUE;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        map = new int[N][N];

        for (int i=0; i<N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j=0;j<N; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
                if (map[i][j] == 1) {
                    homeList.add(i*N+j);
                } else if (map[i][j] == 2) {
                    chickList.add(i*N+j);
                }
            }
        }
        length = chickList.size();
        // for (int i=1; i<=M; i++) {
            // dfs(0, i, 0, new int[i]);
        // }
        dfs(0, M, 0, new int[M]);
        System.out.println(answer);
    }

    // 조합
    public static void dfs(int depth, int maxDepth, int start, int[] arr) {
        if (depth == maxDepth) {
            calcDist(arr);
            return;
        }

        for (int i=start; i<length; i++) {
            arr[depth] = chickList.get(i);
            dfs(depth+1, maxDepth, i+1, arr);
        }
    }

    // 거리 계산
    public static void calcDist(int[] arr) {
        int cnt = 0;
        for (int i=0; i<homeList.size(); i++) {
            int tmp = Integer.MAX_VALUE;
            int x = homeList.get(i) / N;
            int y = homeList.get(i) % N;
            for (int j=0; j<arr.length; j++) {
                int chX = arr[j] / N;
                int chY = arr[j] % N;
                if (tmp > Math.abs(x-chX) + Math.abs(y-chY)) {
                    tmp = Math.abs(x-chX) + Math.abs(y-chY);
                }
            }
            cnt+=tmp;
        }
        if (answer > cnt) answer = cnt;
    }
}
