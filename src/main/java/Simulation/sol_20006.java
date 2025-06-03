package Simulation;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

// 20006 - 랭킹전 대기열
// 플레이어 간 매칭 시스템
// 1. 플레이어 입장 신청했을 때, 매칭 가능한 방 없다 -> 새로운 방 생성 후 입장 & 처음 입장 플에이어의 -10 ~ +10
// 2. 입장 가능한 방 O -> 방 정원 모두 찰때까지 대기
// 3. 입장 가능이 여러개면 먼저 생성된 방 입장
// 4. 방 정원이 모두 차면 게임 시작
// 출력 : 모든 생성된 방에 대해서 게임의 시작 유무 & 방에 들어있는 플레이어들의 레벨&아이디 출력 & 방은 생성된 순서대로 출력
// 출력주의 : 방은 생성된 순서대로 & 닉네임이 사전순으로 앞서는 플레이어부터 & 방이 시작되었으면 started, 아니면 waiting 출력
public class sol_20006 {
    static int p, m; // 플레이어 수, 방 정원
    static Map<String, Integer> members;
    static ArrayList<ArrayList<String>> roomList = new ArrayList<>();
    static ArrayList<Integer> roomAvgLv = new ArrayList<>();
    static StringBuilder sb = new StringBuilder();

    public static void main (String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        p = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        members = new LinkedHashMap<>();

        for (int i=0; i<p; i++) {
            st = new StringTokenizer(br.readLine());
            int l = Integer.parseInt(st.nextToken());
            String n = st.nextToken();
            members.put(n, l);
        }
        // 플레이어 방 입장
        enterPlayer();
        // 출력
        printRoomPlayer();
        System.out.println(sb);
    }

    // 플레이어 방 입장
    public static void enterPlayer() {
       Set<String> keys = members.keySet();
       boolean isEntered = false;
       for (String key: keys) {
           int value = members.get(key);
           if(!roomList.isEmpty()) {
               for (int i=0; i<roomAvgLv.size(); i++) {
                   if (isEntered) break;
                   if ( value >= roomAvgLv.get(i) -10 && value<= roomAvgLv.get(i)+10 && roomList.get(i).size() < m ) {
                       roomList.get(i).add(key);
                       isEntered = true;
                   }
               }
               if (!isEntered) makeRoom(key, value);
           } else makeRoom(key, value);
           isEntered = false;
       }

    }

    // 방 생성
    public static void makeRoom(String name, int level) {
        roomAvgLv.add(level); // 처음 입장한 플레이어의 레벨 넣기
        ArrayList<String> room = new ArrayList<>();
        room.add(name);
        roomList.add(room);
    }

    // 출력
    public static void printRoomPlayer() {
        for (int i=0; i<roomAvgLv.size(); i++) {
            Collections.sort(roomList.get(i));
            int size = roomList.get(i).size();
            if (size == m) {
                sb.append("Started!").append("\n");
            } else sb.append("Waiting!").append("\n");
            for (int j=0; j<size; j++) {
                String n = roomList.get(i).get(j);
                int lv = members.get(n);
                sb.append(lv).append(" ").append(n).append("\n");
            }
        }
    }

}
