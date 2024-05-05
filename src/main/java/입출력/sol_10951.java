package 입출력;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
//10951 - 파일의 끝(EOF)를 올바르게 처리하는 방법에 대한 문제
//EOF 발생시키려면 윈도우에서는 ctrl+z
public class sol_10951 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String str;
        StringTokenizer st;
        int a, b;
        //str=br.readLine())!=null : 백준과 같이 입력자체가 파일로 들어오면 EOF 처리
        //!str.isEmpty() : IntelliJ 같은 IDE에서 EOF 처리 가능
        while ((str=br.readLine())!=null && !str.isEmpty()) {
            st = new StringTokenizer(str);
            a = Integer.parseInt(st.nextToken());
            b = Integer.parseInt(st.nextToken());
            System.out.println(a+b);
        }
    }
}
