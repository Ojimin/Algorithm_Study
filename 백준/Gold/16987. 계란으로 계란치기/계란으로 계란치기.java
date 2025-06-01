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
// 푸는데 오래걸림. 다시 풀어봄직함
public class Main {
    static int N;
    static int[][] arr;
    static int max = Integer.MIN_VALUE;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        arr = new int[N][2];
//        tmp = new int[N];
        for (int i=0; i<N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            arr[i][0] = Integer.parseInt(st.nextToken()); // 내구도
            arr[i][1] = Integer.parseInt(st.nextToken()); // 무게
//            tmp[i] = arr[i][0];
        }

        if (N == 1) {
            max = 0;
            System.out.println(max);
            return;
        }
        dfs(0, 0);
        System.out.println(max);
    }

    public static void dfs(int depth, int cnt) {
        if (depth == N) {
            max = Math.max(max, cnt);
            return;
        }
        // 현재 깨뜨릴려고 하는 egg가 깨져있을 경우 & cnt가 N-1개일 때도 넘김(2개 이상 남아있어야 다른 걸 꺨 수 있기 때문)
        if (arr[depth][0] <=0 || cnt == N-1) {
            dfs(depth+1, cnt);
            return;
        }

        int tmp=cnt;
        for (int i=0; i<N; i++) {
            // 깨트릴 것과 깨트림 당할 인덱스가 같거나 arr[i] 값이 0보다 작으면(이미 깨져있으면) 넘기기(깨뜨리지 말기)
            if (depth == i || arr[i][0]<=0) continue;
            countEgg(depth, i);
            if (arr[depth][0] <= 0) tmp++;
            if (arr[i][0] <= 0) tmp++;
            dfs(depth+1, tmp);
            recoverEgg(depth, i);
            tmp = cnt;
        }
    }

    public static void countEgg(int i, int j) {
        arr[i][0] -= arr[j][1];
        arr[j][0] -= arr[i][1];
//        int cnt = 0;
//        for (int k=0; k<N; k++) {
//            if (arr[k][0] <= 0) cnt++;
//        }
//        if (cnt > max) max = cnt;
    }

    public static void recoverEgg(int i, int j) {
        arr[i][0] += arr[j][1];
        arr[j][0] += arr[i][1];
    }

}
