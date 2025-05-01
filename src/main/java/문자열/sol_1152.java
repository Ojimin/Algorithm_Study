package 문자열;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.NoSuchElementException;
import java.util.StringTokenizer;

//1152-단어의 개수,몇개의 단어가 있는지, 한 단어가 여러번 등장하면 등장한 횟수만큼 모두 세어야함
//단어는 공백으로 구분
//출력 : 단어의 개수
//scanner의 next: 공백전까지 입력받은 문자열을 리턴
//scanner의 nextLine() : 엔터를 치기전까지 쓴 문자열을 모두 리턴
//string tokenizer : 공백을 기준으로 나누어 토큰을 저장
public class sol_1152 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        //그냥
        //System.out.println(st.countTokens());
        //이것만 해도 됨
        String tmp;
        int result=0;
        try{
            while(true) {
                tmp=st.nextToken();
                result++;
            }
        }catch (NoSuchElementException e) {
            System.out.println(result);
        }
    }
}
