package 연결리스트;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.StringTokenizer;

// 1406 - 에디터
// 커서 - 문장 맨 앞, 문장 맨 뒤, 문장 중간 임의의 곳 => 길이가 L인 문자열의 커서 위치 가능 경우의 수는 L+1
// L : 왼쪽으로 한 칸 옮김(맨앞이면 무시), D:오른쪽으로(맨뒤면 무시), B:왼쪽 문자 삭제(맨앞 무시,커서의 오른쪽 문자는 그대로), P$: $라는 문자를 커서 왼쪽에 추가
// 출력 : 모든 명령어 수행 후, 편집기에 입력되어있는 문자열
// 주의 : 명령어 수행되기 전 커서 - 문장 맨 뒤 위치
// 만약 배열로 구현한다면 => 특정 위치에 추가 및 삭제는 O(N),즉 10^10.
public class nonsol_1406 {
    static class Cursor {
        int idx; // prev의 위치
        char prev;
        char next;
        public Cursor(int idx, char prev, char next) {
            this.idx = idx;
            this.prev = prev;
            this.next = next;
        }
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String input = br.readLine();
        LinkedList<Character> strList = new LinkedList<>();
        for (int i=0; i<input.length(); i++) {
            strList.add(input.charAt(i));
        }
        int M = Integer.parseInt(br.readLine());
        char[][] order = new char[M][2];
        for (int i=0; i<M; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int idx = 0;
            while(st.hasMoreTokens()) {
               order[i][idx] = st.nextToken().charAt(0);
               idx++;
            }
        }
        Cursor cursor = new Cursor(input.length()-1, input.charAt(input.length()-1), '0');
        for (int i=0; i<M; i++) {
            switch (order[i][0]) {
                case 'L':
                    if (cursor.prev == '0') continue;
                    else {
                        cursor.prev = strList.
                    }
                    break;
                case 'D':
                    break;
                case 'B':
                    break;
                case 'P' :
                    break;
            }


        }

    }
}
