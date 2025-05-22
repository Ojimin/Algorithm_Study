package 문자열;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

// 13022 - 늑대와 올바른 단어
// 조건 1. n이 있다면, w가 n번, o가 n번, ㅣ이 n번, f가 n번 나오면 올바른 단어
// 조건 2. 올바른 단어 두개를 이은 단어도 올바른 단어
// 조건 3. w -> o -> l -> f -> w -> o -> l -> f 순서로 나와야함 =>
// 조건 4. 각 글자가 "n번씩" 나와야함. 각 글자가 n개 미만 or 초과로 나오면 안됨
// 출력 : 올바른단어이면 1, 아니면 0
// 정리 : 순서 체크, 개수 체크,
// 다시 => 예외 case 하나 하나 찾아서 해결한거라 더러움. 깔쌈하게 푸는 걸로 수정
public class sol_13022 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String str = br.readLine();
        boolean isCorrect = true;
        int cnt = 1;
        int tmp = 1;
        char now = ' ', before = ' ';
        // 조건 체크
        for (int i=1; i<str.length(); i++) {
            now = str.charAt(i);
            before = str.charAt(i-1);
            if(i==1 && before != 'w') { // 첫번째가 w가 아닌 경우
                isCorrect = false;
                break;
            }
            if (now == 'w' && before == 'w'){
                cnt++;
                tmp++;
            }
            else if (now == before) {
                tmp++;
                //만약 마지막 case면 다시 count 체크
                if(i==str.length()-1 && cnt != tmp) isCorrect = false;
            }
            else { // 현재와 이전이 다를 때
                // 개수가 n개로 동일한지
                if (cnt != tmp) {
                    isCorrect = false;
                    break;
                }
                // 순서 체크
                if ((now=='w' && before != 'f') || (now == 'o' && before != 'w') || (now == 'l' && before != 'o') || (now == 'f' && before != 'l') ) {
                    isCorrect = false;
                    break;
                }
                // f->w가 되면 cnt값 초기화
                if (now == 'w') cnt=1;
                tmp=1;
                //만약 마지막 case면 다시 count 체크
                if(i==str.length()-1 && cnt != tmp) isCorrect = false;
            }
        }
        // 마지막이 f가 아닌 경우 체크
        if (str.charAt(str.length()-1) != 'f') isCorrect = false;
        if (!isCorrect) System.out.println("0");
        else System.out.println("1");
    }

    public static void order(char now, char before) {

    }
}
