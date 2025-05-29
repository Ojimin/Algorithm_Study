import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// 1074 - Z
// 2^N * 2^N인 2차원 배열을 Z모양으로 탐색 => N>1이면 2N-1*2N-1로 4등분 후 재귀적으로 순서대로 방문
// 출력 : r행 c열을 몇번쨰로 방문하는지 출력
// 방문하는 순서가 : 왼위 -> 오위 -> 왼아 -> 오아
// 재귀임 & 분할정복
// 꼭 다시 해보기
// 1. 배열을 4분면으로 나누고 입력받은 r,cr가 몇번째 사분면에 속하는지 체크
// 2. 재귀 호출할 때마다 현재 r,c의 위치에 따라 앞의 몇번의 방문을 했는지 더하는 변수 count 선언
// 3. 각 사분면에 맞게 조건을 나누어서 처리 => 이 때 좌표를 상대좌표롤 변환해서 호출!!
// 모든 배열을 채울 필요 없이 찾고자 하는 좌표 기준으로만 하면 탐색하면 됨 =>
public class Main {
    static int N, r, c;
    static int count = 0; // 해당 좌표값에 들어가는 숫자를 게산하기 위함
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        r = Integer.parseInt(st.nextToken());
        c = Integer.parseInt(st.nextToken());
        int size = (int) Math.pow(2, N); // 한변의 사이즈
        find(size, r, c);
        System.out.println(count);
    }

    public static void find(int size, int r, int c) {
        if (size == 1) return ;

        if (r <size/2 && c<size/2) {
            find(size/2, r, c);
        }
        else if (r<size/2 && c>=size/2) {
            count += size * size /4;
            find(size/2, r, c-size/2);
        }
        else if(r>=size/2 && c<size/2) {
            count += (size * size /4) *2;
            find(size/2, r-size/2, c);
        }
        else if (r>=size/2 && c>=size/2) {
            count += (size * size /4) *3;
            find(size/2,r-size/2, c-size/2 );
        }

    }



}
