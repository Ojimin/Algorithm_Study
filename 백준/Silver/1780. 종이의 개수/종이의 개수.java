
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// 1780 - 종이의 개수
// 종이가 모두 같은 수로 되어있다면 이 종이 그대로 사용 -> 아닌경우는 종이를 같은 크기의 종이 9개로 자르고 각각의 잘린 종이에 대해서 1의 과정 반복
// 출력 : -1로만 채워진 종이의 개수, 0으로만 채워진 종이의 개수, 1로만 채워진 종이의 개수
//
public class Main {
    static int first = 0, second=0, third=0;
    static int[][] map;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        map = new int[N][N];
        for (int i=0; i<N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j=0; j<N; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        paper(N, 0, 0);
        System.out.println(first);
        System.out.println(second);
        System.out.println(third);
    }
    public static void paper(int n, int rowFrom, int colFrom) {
        if (n==1 || isSame(n, rowFrom, colFrom)) {
            switch (map[rowFrom][colFrom]) {
                case -1:
                    first++;
                    break;
                case 0:
                    second++;
                    break;
                case 1:
                    third++;
                    break;
            }
            return;
        }
        else {
            for (int i=0; i<3; i++) {
                for (int j=0; j<3; j++) {
                    paper(n / 3, rowFrom + i *(n/ 3), colFrom + j * (n/3));
                }
            }
        }
    }

    public static boolean isSame(int n, int rowFrom, int colFrom) {
        int value = map[rowFrom][colFrom];
        for (int i=rowFrom; i<rowFrom+n; i++) {
            for (int j=colFrom; j<colFrom+n; j++) {
                if (map[i][j] != value) return false;
            }
        }
        return true;
    }
}
