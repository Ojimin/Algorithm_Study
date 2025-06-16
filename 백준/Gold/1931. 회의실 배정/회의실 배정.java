import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.StringTokenizer;

// 1931 - 회의실 배정
// 한개의 회의실에 N개의 회의가 겹치지 않게 회의실을 사용할 수 있는 회의의 최대개수 찾기
// 각 회의에 대한 시작시간과 끝시간이 주어짐
// 출력 : 최대 사용할 수 있는 회의의 최대 개수 출력
// 주의 : 회의 시작 = 끝나는 시간 => 시작하자마자 끝남
// 항상 그리디 문제는 잘 정렬해야하는 것이 핵심!
// 주의 : 시작 시간 & 끝나는 시간 : 2^31 -1, N<= 10^5
// 정렬 후 반복문 한바퀴 돌면서 끝나는 시간 기준 더 짧게 끝나는 것이 있으면 교체 -> 아니면 끝나는 시간 이후 가 시작이면 추가
public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        long[][] time = new long[N][2];
        ArrayList<Long[]> timeTable = new ArrayList<>();
        for (int i=0; i<N; i++) {
            StringTokenizer st= new StringTokenizer(br.readLine());
            time[i][0] = Long.parseLong(st.nextToken());
            time[i][1] = Long.parseLong(st.nextToken());
        }
        Arrays.sort(time, Comparator.comparingLong((long[] arr) -> arr[0]).thenComparing(arr -> arr[1] ));
       // 1. 시작시간 확인 => 시작시간이 타임테이블 마지막 리스트의 끝나느시간보다 크면 넣기
       // 2 끝나는 시간 확인 => 그게아니면 끝나는 시간 확인해서 일찍끝나면 바꿔치기!!
        for (int i=0; i<N; i++) {
            if (!timeTable.isEmpty()) {
                // 리스트에 저장한 끝날시간이 시작시간보다 큰경우
                if (timeTable.get(timeTable.size()-1)[1] > time[i][0]) {
                    if (time[i][1] < timeTable.get(timeTable.size()-1)[1]) {
                        timeTable.remove(timeTable.size()-1);
                        timeTable.add(new Long[]{time[i][0], time[i][1]});
                    }
                } else timeTable.add(new Long[]{time[i][0], time[i][1]});
            }
            else timeTable.add(new Long[]{time[i][0], time[i][1]});
        }
        System.out.println(timeTable.size());
    }
}
