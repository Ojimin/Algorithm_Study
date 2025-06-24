package 연결리스트;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.ListIterator;

// 5397 - 키로거
// 강산이가 입력한 키: 알파벳 대문자, 소문자, 숫자, 백스페이스, 화살표
// 백스페이스 : '-' , 커서 바로 앞 글자가 존재하면 해당 글자 삭제
// 화살표 입력 : '<', '>', 커서 위치를 왼쪽, 오른쪽으로 1만큼 움직임
// 나머지 문자는 비밀번호 일부
// 출력 : 비밀번호 추력
public class sol_5397 {
    static StringBuilder sb= new StringBuilder();
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());
        for (int i = 0; i<T; i++) {
            String str = br.readLine();
            solve(str);
        }
        System.out.println(sb);
    }
    public static void solve(String str) {
        LinkedList<Character> list = new LinkedList<>();
        ListIterator<Character> listIterator = list.listIterator();
        for (int i=0; i<str.length(); i++) {
            switch (str.charAt(i)) {
                case '-' :
                    if(!listIterator.hasPrevious()) continue;
                    char del = listIterator.previous();
                    listIterator.remove();
                    break;
                case '<' :
                    if(!listIterator.hasPrevious()) continue;
                    listIterator.previous();
                    break;
                case '>' :
                    if(!listIterator.hasNext()) continue;
                    listIterator.next();
                    break;
                default:
                    if (list.isEmpty()) {
                        list.add(str.charAt(i));
                        listIterator = list.listIterator(list.size()); // 핵심
                    }
                    else {
                        listIterator.add(str.charAt(i));
                    }
            }
        }
        for (char c: list) {
            sb.append(c);
        }
        sb.append("\n");
    }
}
