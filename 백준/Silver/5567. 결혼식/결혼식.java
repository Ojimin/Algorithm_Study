
//5567 - 결혼식
// 학번: 1~N, 상근이는 1
// 출력: 결혼식에 초대할 사람 수 구하기 => 자신의 친구-친구-친구
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.StringTokenizer;

public class Main {
    static int n, m;
    static ArrayList<Integer>[] graph;
    static HashSet<Integer> cnt=new HashSet<>();
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        m = Integer.parseInt(br.readLine());
        graph = new ArrayList[n+2];
        for (int i=1; i<=n+1; i++) {
            graph[i] = new ArrayList<>();
        }
        for (int i=0; i<m; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            graph[a].add(b);
            graph[b].add(a);
        }
        dfs(1,0);

        if (cnt.contains(1)) System.out.println(cnt.size()-1);
        else System.out.println(cnt.size());
    }

    public static void dfs(int num, int depth) {
        if (depth>=2) return;

        for (int i: graph[num]) {
            cnt.add(i);
            dfs(i,depth+1);
        }
    }
}
