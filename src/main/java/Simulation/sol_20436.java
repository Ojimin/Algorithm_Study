package Simulation;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// 20436 - ZOAC 3
// 출력 : 문자열을 출력하는데 걸리는 시간의 최솟값
// a(x1, y1) -> b(x2, y2) 위치로 손가락 이동하는데 걸리는 시간 : |x1-x1| + |y1-y2|
// 각 키를 누르는데 1의 시간 걸림
// 문제 조건. 왼손으로 입력할 것, 오른손으로 입력하는 것이 정해져있음
// 주의!! : 첫번째 입력값이 무조건 왼쪽, 오른쪽 순서가 아님
// 와우,, 문제 조건 제대로 읽자. 특히 왼손 오른손 입력이 무조건 왼쪽 자음, 오른쪽 모음 배열을 확저짓고하지 X
// 다시 - map 사용해서 key값은 문자, value는 위치로 저장해도 풀 수 있을 듯
public class sol_20436 {
    static char left[][] = {
            {'q', 'w', 'e', 'r', 't'},
            {'a', 's', 'd', 'f', 'g'},
            {'z', 'x', 'c', 'v'}
    };
    static char right[][] = {
            {'.', 'y', 'u', 'i', 'o', 'p'},
            {'.', 'h', 'j', 'k', 'l'},
            {'b', 'n', 'm'}
    };
    static int leftX, leftY, rightX, rightY, inputX, inputY;
    static int time = 0;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        char sL = st.nextToken().charAt(0);
        char sR = st.nextToken().charAt(0);
        char[] input = br.readLine().toCharArray();

        getInputPosition(sL);
        leftX = inputX;
        leftY = inputY;

        getInputPosition(sR);
        rightX = inputX;
        rightY = inputY;

        calculate(input);
        System.out.println(time);
    }
    //걸리는 시간을 최솟값으로 구하기 위해, y좌표 기준으로 0~4: 왼손, y좌표: 5~9는 오른손
    // 아니면 왼손, 오른손이 위치해 있는 좌푯값 비교해서 더 가까운 쪽이 이동!
    public static void calculate(char[] input) {
        for (int i=0; i<input.length; i++) {
            boolean isLeft = getInputPosition(input[i]);
            int length =0;
            if (isLeft) {
                length = Math.abs(leftX - inputX) + Math.abs(leftY - inputY);
                leftX = inputX;
                leftY = inputY;
            } else {
                length = Math.abs(rightX - inputX) + Math.abs(rightY - inputY);
                rightX = inputX;
                rightY = inputY;
            }
            time += length;
            time++;
        }
    }

    public static boolean getInputPosition(char input) {
        for (int j=0; j<left.length; j++) {
            for (int k=0; k<left[j].length; k++) {
                if (left[j][k] == input) {
                    inputX = j;
                    inputY = k;
                    return true;
                }
            }
        }
        for (int j=0; j<right.length; j++) {
            for (int k=0; k<right[j].length; k++) {
                if (right[j][k] == input) {
                    inputX = j;
                    inputY = k;
                    return false;
                }
            }
        }
        return false;
    }
}
