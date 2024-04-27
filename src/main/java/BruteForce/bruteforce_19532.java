package BruteForce;

import java.io.*;

//문제설명 : 연립방정식 해 구하기
//핵심 개념 : 반복문을 활용한 브루트포스
public class bruteforce_19532 {
    public static void sol(int a, int b, int c, int d, int e, int f) {
        int x = -1000;
        int y = -1000;
        //x와 y 범위가 -999부터 999이하이므로 해당 범위에서 반복문을 돌려 대입해서 해를 찾음
        for (int i = -999; i <= 999; i++) {
            for (int j = -999; j <= 999; j++) {
                //해가 되는지 체크
                if (c == a * i + b * j) {
                    if (f == d * i + e * j) {
                        x = i;
                        y = j;
                        break;
                    }
                }
            }
        }
        System.out.println(x + " " + y);
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] input = br.readLine().split(" ");
        int a = Integer.parseInt(input[0]);
        int b = Integer.parseInt(input[1]);
        int c = Integer.parseInt(input[2]);
        int d = Integer.parseInt(input[3]);
        int e = Integer.parseInt(input[4]);
        int f = Integer.parseInt(input[5]);
        sol(a, b, c, d, e, f);
    }
}
