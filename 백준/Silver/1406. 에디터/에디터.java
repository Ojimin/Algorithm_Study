import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.ListIterator;
import java.util.StringTokenizer;

// 1406 - 에디터
// 커서 - 문장 맨 앞, 문장 맨 뒤, 문장 중간 임의의 곳 => 길이가 L인 문자열의 커서 위치 가능 경우의 수는 L+1
// 출력 : 모든 명령어 수행 후, 편집기에 입력되어있는 문자열
// 주의 : 명령어 수행되기 전 커서 - 문장 맨 뒤 위치
// 만약 배열로 구현한다면 => 특정 위치에 추가 및 삭제는 O(N),즉 10^10.
// 주의 : 예외처리 - 첫째, 끝쪽 처리 필요
// 헷갈 : 문자 추가해도 커서의 왼쪽은 추가한 문자가 존재하도록 해야함
// linkedlist - 시간초과 발생, 여기서 인덱스로 삭제하면 O(N) 시간복잡도 발생
// 다시 - listIterator 활용 & or stack
public class Main {
//    static class Cursor {
////        int idx; // prev의 위치
//        int prev;
//        int next;
//        public Cursor(int prev, int next) {
////            this.idx = idx;
//            this.prev = prev;
//            this.next = next;
//        }
//    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String input = br.readLine();
        LinkedList<Character> strList = new LinkedList<>();
        for (int i=0; i<input.length(); i++) {
            strList.add(input.charAt(i));
        }
        int M = Integer.parseInt(br.readLine());
        char[][] order = new char[M][2];
        ListIterator<Character> listIterator = strList.listIterator(strList.size()); // iterator의 시작위치를 끝으로 초기화
        // 명령어를 받는 순간 처리하자
// L : 왼쪽으로 한 칸 옮김(맨앞이면 무시), D:오른쪽으로(맨뒤면 무시), B:왼쪽 문자 삭제(맨앞 무시,커서의 오른쪽 문자는 그대로), P$: $라는 문자를 커서 왼쪽에 추가
        for (int i=0; i<M; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int idx = 0;
            while(st.hasMoreTokens()) {
               order[i][idx] = st.nextToken().charAt(0);
               idx++;
            }
            switch (order[i][0]) {
                case 'L' :
                    if (!listIterator.hasPrevious()) continue;
                    else listIterator.previous();
                    break;
                case 'D' :
                    if (!listIterator.hasNext()) continue;
                    else listIterator.next();
                    break;
                case 'B' :
                    if(!listIterator.hasPrevious()) continue;
                    else {
                        listIterator.previous();
                        listIterator.remove();
                    }
                    break;
                case 'P' :
                    listIterator.add(order[i][1]);
                    break;
            }
        }

//        Cursor cursor = new Cursor(input.length()-1, -1);
//        for (int i=0; i<M; i++) {
//            switch (order[i][0]) {
//                case 'L':
//                    if (cursor.prev == -1) continue;
//                    else {
//                        cursor.next = cursor.prev;
//                        cursor.prev = cursor.prev - 1;
//                    }
//                    break;
//                case 'D':
//                    if (cursor.next == -1) continue;
//                    else {
//                        cursor.prev = cursor.next;
//                        cursor.next = (cursor.next + 1) == strList.size() ? -1 : cursor.next+1;
//                    }
//                    break;
//                case 'B':
//                    if (cursor.prev == -1) continue;
//                    else {
//                        int removeIdx = cursor.prev;
//                        cursor.prev = cursor.prev - 1;
//                        cursor.next = cursor.next == -1 ? -1 : cursor.next - 1;
//                        strList.remove(removeIdx);
//                    }
//                    break;
//                case 'P' :
//                    if (strList.isEmpty()) {
//                        strList.add(order[i][1]);
//                        cursor.prev = 0;
//                        cursor.next = -1;
//                    }
//                    else if (cursor.prev == -1) {
//                        strList.add(cursor.next, order[i][1]);
//                        cursor.prev += 1;
//                        cursor.next += 1;
//                    }
//                    else if (cursor.next == -1) {
//                        strList.add(order[i][1]);
//                        cursor.prev = strList.size()-1;
//                    } else {
//                        strList.add(cursor.next, order[i][1]);
//                        cursor.prev = cursor.next;
//                        cursor.next+=1;
//                    }
//                    break;
//            }
////            System.out.println(order[i][0] + " " + cursor.prev + " " + cursor.next);
////            System.out.println(strList);
//        }
        StringBuilder sb = new StringBuilder();
        for (char c : strList) {
            sb.append(c);
        }
        System.out.println(sb);
    }
}
