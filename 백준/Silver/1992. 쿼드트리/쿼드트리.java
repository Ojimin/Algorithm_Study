
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

// 1992 - 쿼드트리
// 0,1 섞여 있으면 왼쪽위, 오른쪽위, 왼쪽아래, 오른쪽아래 4개의 영상으로 나누어 압축 진행
// 압축한 결과, 차례대로 괄호 안에 묶어서 표현
// 출력 : 영상을 압축한 결과 출력 - 괄호로 묶어서
// 압축 진행할때마다 괄호 여는 것 추가 -> 압축끝나고 다 동일하면 괄호 닫는거 추가
// 오른쪽 아래임을 어떻게 판별? + 예외 체크 : 연속해서 압축진행되면 어떻게 괄호 닫을지? - 다른 아이디어는 부모체크
public class Main {
    static int N;
    static int[][] map;
    static StringBuilder sb = new StringBuilder();
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        map = new int[N][N];
        for (int i=0;i<N;i++) {
            String str = br.readLine();
            for (int j=0;j<N;j++) {
                map[i][j] = Character.getNumericValue(str.charAt(j));
            }
        }
        QuadTree(N, 0, 0, true);
        System.out.println(sb);
    }

    public static void QuadTree(int n, int fromRow, int fromCol, boolean isEnd) {
        if (n==1 || isSame(n, fromRow, fromCol)) {
            if (n!=N && isEnd) sb.append(map[fromRow][fromCol]).append(")");
            else sb.append(map[fromRow][fromCol]);
            return;
        }
        sb.append("(");
        for (int i=0; i<2; i++) {
            for (int j=0;j<2; j++) {
                if (i== 1 && j==1) {
                    QuadTree(n/2, fromRow+i*(n/2), fromCol+j*(n/2), true);
                    if (isEnd && n!=N) sb.append(")");
                }
                else QuadTree(n/2, fromRow+i*(n/2), fromCol+j*(n/2), false);
            }
        }
    }

    public static boolean isSame(int n, int fromRow, int fromCol) {
        for (int i=fromRow;i<fromRow+n; i++) {
            for (int j=fromCol; j<fromCol+n; j++) {
                if (map[i][j] != map[fromRow][fromCol]) return false;
            }
        }
        return true;
    }

}
