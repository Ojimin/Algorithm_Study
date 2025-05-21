import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.Set;
import java.util.StringTokenizer;

// 19583 - 싸이버개강총회
// 참석 여부 - 시작하기 이전 대화 & 시작하자마 채팅 기록
// 퇴장 여부 - 끝내고 나서 ~ 스트리밍 끝날떄까지 대화 한적 잇는지 퇴장 여부(끝나자마자 & 스트리밍 끝나자마자도 ㅇㅈ)
// 출력 : 입장~퇴장까지 모두 확인된 학회원은 몇명인지
// 시간-HH:MM 형식으로
// 밤 10시 ~ 새벽을 간주 X
// 다시 - 시간 형식이 모두 동일하기 떄문에 해시의 compareTo를 사용해서 시간비교를 해야함
public class Main {
    static String S, E, Q;
    static int cnt=0;//출석이 확인된 인원수
    static HashSet<String> memberList = new HashSet<>();
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        S = st.nextToken(); //개강총회 시작한 시간
        E = st.nextToken(); //개강총회 끝낸 시간
        Q = st.nextToken(); //개강총회 스트리밍 끝낸 시간
        String str = "";
//        String[] startList = S.split(":");
//        String[] endList = E.split(":");
//        String[] qList = Q.split(":");
//        int startHour = Integer.parseInt(startList[0]);
//        int startMinute = Integer.parseInt(startList[1]);
//        int endHour = Integer.parseInt(endList[0]);
//        int endMinute = Integer.parseInt(endList[1]);
//        int qHour = Integer.parseInt(qList[0]);
//        int qMinute = Integer.parseInt(qList[1]);

        while ((str = br.readLine())!=null && !(str.isEmpty())){ //
            st = new StringTokenizer(str);
            String time = st.nextToken();
            String member = st.nextToken();
            if (time.compareTo(S) <= 0) // time - S
                memberList.add(member);
            else if(time.compareTo(E) >=0 && time.compareTo(Q) <= 0) {
                if (memberList.contains(member)) {
                    cnt++;
                    memberList.remove(member);
                }
            }
//            String[] timeList = time.split(":");
//            int hour = Integer.parseInt(timeList[0]);
//            int minute = Integer.parseInt(timeList[1]);
//            if (hour <startHour || (hour == startHour && minute <=startMinute)) memberList.add(member);
//            if((hour==endHour && minute>=endMinute && (hour<qHour || (hour==qHour && minute<=qMinute))) || (hour>endHour && hour<qHour) || (hour>endHour && hour==qHour && minute<=qMinute)) {
//                if (memberList.contains(member)) {
//                    memberList.remove(member);
//                    cnt++;
//                }
//            }
        }

        System.out.println(cnt);
    }
}
