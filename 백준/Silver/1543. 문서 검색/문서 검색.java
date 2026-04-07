
import java.io.BufferedReader;
import java.io.InputStreamReader;

// 1543 - 문서 검색
// 검색하려는 단어가 주어졌을 떄, 그 단어가 최대 몇번 중복되지 않게 등장하는지 구하기
// 완탐
// 1. 먼저 첫번째 위치 찾고, 그 위치가 str 끝이면 ㅂㅇ
// 2. startIdx 기준으로 word자체를 탐색 - word가 잘 찾아지면 startIdx를 word 개수만큼 더해서
// 2-1. 만약 word 자체 탐색이 안되면 just startIdx+1
public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String str = br.readLine();
        String word = br.readLine();
        int cnt = 0;
        int startIdx = 0;
        if (str.indexOf(word) != -1) {
            startIdx = str.indexOf(word) + word.length();
            cnt++;
        } else {
            System.out.println(cnt);
            return;
        }
        while(startIdx<str.length()) {
            String tmp = str.substring(startIdx);
            if (tmp.indexOf(word) == -1) break;
            else {
                startIdx = tmp.indexOf(word) +word.length() + startIdx;
                cnt++;
            }
        }
        System.out.println(cnt);
    }
}
