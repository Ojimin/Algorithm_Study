import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// 16987 - 계란으로 바위치기
// 한번씩만 다른계란을 쳐서 최대한 많은 계란 깨기
// 출력 : 꺨 수 있는 계란의 최대 개수
// 순서 - 가장 왼쪽 계란 들음 -> 깨지지 않은 다른 계란 중 하나 침 -> 다른 게란 없으면 넘어감 -> 손에 든 계란은 제자리에 두고 -> 최근에 든 계란의 오른쪽계란 손에 들고 다시
// 종료: 가장 최근에 든 계란이 가장 오른쪽에 위치할 경우 종료
// 내구도가 0이하 되는 순간 꺠짐, 내구도는 상대 깨는(지는) 계란의 무게만큼 감소함
// 백트래킹
public class Main {
    static int N;
    static int[][] arr;
    static int[] tmp; // 깎인 내구도 담아놓는 배열
    static int max = Integer.MIN_VALUE;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        arr = new int[N][2];
        tmp = new int[N];
        for (int i=0; i<N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            arr[i][0] = Integer.parseInt(st.nextToken()); // 내구도
            arr[i][1] = Integer.parseInt(st.nextToken()); // 무게
            tmp[i] = arr[i][0];
        }

        if (N == 1) {
            max = 0;
            System.out.println(max);
            return;
        }
        dfs(0);
        System.out.println(max);
    }

    public static void dfs(int depth) {
        if (depth == N) {
            int cnt = 0;
            for (int i=0; i<N; i++) {
                if (tmp[i] <= 0) cnt++;
            }
            if (cnt > max) max = cnt;
            return;
        }
        if (tmp[depth] <=0) {
            dfs(depth+1);
            return;
        }

        for (int i=0; i<N; i++) {
            // tmp[i] 값이 0보다 작으면 넘기기(깨뜨리지 말기)
            if (depth == i || tmp[i]<=0) continue;
            countEgg(depth, i);
            dfs(depth+1);
            recoverEgg(depth, i);
        }
    }

    public static void countEgg(int i, int j) {
        tmp[i] -= arr[j][1];
        tmp[j] -= arr[i][1];
        int cnt = 0;
        for (int k=0; k<N; k++) {
            if (tmp[k] <= 0) cnt++;
        }
        if (cnt > max) max = cnt;
    }

    public static void recoverEgg(int i, int j) {
        tmp[i] += arr[j][1];
        tmp[j] += arr[i][1];
    }

}
