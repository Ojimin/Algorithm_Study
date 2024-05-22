package 문자열;

import java.util.Arrays;
import java.util.Scanner;

//10809-알파벳찾기,각각의 알파벳에 대해 단어가 포함되어있는경우, 처음 등장하는 위치, 포함되지 않은 경웨는 -1 추력
//알파벳 소문자로 이루어짐
//문자열
public class sol_10809 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        char[] str = sc.next().toCharArray();
        int[] cnt = new int[26];
        StringBuilder sb = new StringBuilder();
        Arrays.fill(cnt, -1);
        for (int i = 0; i < str.length; i++) {
            int a = str[i] - 97;
            if (cnt[a] == -1) {
                cnt[a] = i;
            }
        }
        for (int i = 0; i < 26; i++) {
            sb.append(cnt[i] + " ");
        }
        System.out.println(sb);
    }
}
