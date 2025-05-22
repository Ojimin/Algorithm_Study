package swea;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// 1206 - 1알처 view
// 왼쪽&오른쪽으로 창문을 열었을 떄, 양쪽 모두 거리 2이상의 공간이 확보될때 조망권이 확보됨
// 출력 : 조망권이 확보된 세대 수 반환
// 4<=가로 길이<=1000, 빌딩 높이<=255
public class sol_1206 {
    static int[] buildings;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();
        for (int i=0; i<10; i++) {
            int N = Integer.parseInt(br.readLine());
            buildings = new int[N+1];
            st = new StringTokenizer(br.readLine());
            for (int j=0;j<N; j++) {
                buildings[j] = Integer.parseInt(st.nextToken());
            }
            int answer = solve(N);
            sb.append("#"+(i+1)+" ").append(answer).append("\n");
        }
        System.out.println(sb);
    }

    public static int solve(int N) {
        int cnt = 0;
        if (buildings[0] > buildings[1] && buildings[0] > buildings[2]) {
            if (buildings[1] >= buildings[2]) cnt += buildings[0] - buildings[1];
            else cnt += buildings[0] - buildings[2];

        }
        if (buildings[1] >= buildings[0] && buildings[1] > buildings[2] && buildings[1] > buildings[3]) {
            int max = Math.max(buildings[0], buildings[2]);
            max = Math.max(max, buildings[3]);
            cnt += buildings[1] - max;
        }
        // 마지막 2개 처리
        if (buildings[N-2] > buildings[N-4] && buildings[N-2] > buildings[N-3] && buildings[N-2] > buildings[N-1]) {
            int max = Math.max(buildings[N-3], buildings[N-4]);
            max = Math.max(max, buildings[N-1]);
            cnt += buildings[N-2] - max;
        }

        if (buildings[N-1] > buildings[N-3] && buildings[N-1] > buildings[N-2]) {
            int max = Math.max(buildings[N-2], buildings[N-3]);
            cnt += buildings[N-1] - max;
        }

        for (int i=2; i<N-2; i++) {
            if (buildings[i] > buildings[i-2] && buildings[i] > buildings[i-1] && buildings[i] > buildings[i+1] && buildings[i] > buildings[i+2]) {
                int max = Math.max(buildings[i-1], buildings[i-2]);
                max = Math.max(max, buildings[i+1]);
                max = Math.max(max, buildings[i+2]);
                cnt += buildings[i] - max;
            }
        }
        return cnt;
    }
}
