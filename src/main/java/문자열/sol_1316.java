package 문자열;

import java.io.BufferedReader;
import java.io.InputStreamReader;

//1316-그룹단어체커, 그룹단어는 각 문자가 연속해서 나타나는 경우만을 말함
//단 하나의 글자도 연속되지 않을경우, 그룹단어가 아님, 알파벳이 소문자로 되어있음
//그룹단어의 개수를 출력
//문자열
//연속체크 - 직관적인 방법은 등장하는 알파벳마다의 개수를 다세고 그게 다르면 out,
//다른 방법으로는 전후로 겹치는게없을 경우-단 하나만인경우, 그 뒤에 또 나오면 바로 fasle..굳이?
//한번에 풀긴했는데 아이디어 생각하는데 시간이 좀 걸림
//문자열은 보통 알파벳 배열 선언해서 비교하는 경우가 많은 것 같음
public class sol_1316 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int result = 0;
        for (int i = 0; i < N; i++) {
            String str = br.readLine();
            if (check(str)) result++;
        }
        System.out.println(result);
    }

    public static boolean check(String str) {
        char[] arr = str.toCharArray();
        int[] alphabet = new int[26];
        boolean isGroup = true;
        for (int i = 0; i < arr.length; i++) {
            int idx = arr[i] - 'a';
            if (alphabet[idx] > 0) {
                if (arr[i - 1] != arr[i]) {
                    isGroup = false;
                    break;
                }
            } else {
                alphabet[idx]++;
            }
        }
        return isGroup;
    }
}
