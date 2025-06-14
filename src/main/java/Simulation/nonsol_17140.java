package Simulation;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

// 17140 - 이차원 배열과 연산
// 3*3 배열 A의 인덱스는 1부터 시작 & 1초가 지날때마다 배열에 연산 적용
// R연산 : 행개수>=열개수, 모든 행에 대해서 정렬 수행
// C연산 : 행 개수 < 열개수, 모든 열에 대해 정렬 수행
// 정렬 방법 : 각 수가 몇번 나왔는지 확인 -> 등장횟수 큰, 수가 큰 순으로 정렬-> 정렬 후 결과에는 수와 등장횟수 모두 넣기
// R연산 적용 : 가장 큰행 기준 모든 행 크기 변함
// C연산 적용: 가장 큰 열 기준, 모든 열의 크기 변환
// 주의1. => 행/열 크기가 커진곳에는 0이 채워짐. 정렬 시 0은 무시 ex) 3,2,0,0, => 3,2와 같음
// 주의2. 행/열 크기가 100을 넘어가는 경우, 처음 100개를 제외한 나머지 버림
// 출력 : A[r][c]에 들어있는 값이 k가 되기 위한 최소 시간 || 100초 지나도 되지 않으면 -1 출력
public class nonsol_17140 {
    static int r,c,k;
    static ArrayList<ArrayList<Integer>> A = new ArrayList<>();
    static int time = 0;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        r = Integer.parseInt(st.nextToken());
        c = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());

        for (int i = 0; i < 3; i++) {
            st = new StringTokenizer(br.readLine());
            A.add(new ArrayList<>());
            for (int j = 0; j < 3; j++) {
                int n = Integer.parseInt(st.nextToken());
                A.get(i).add(n);
            }
        }
        startSort();
        System.out.println(time);
    }
    // 1. r, c 좌표에 k 연산 도달했는지 체크 + R연산, C연산 중 어떤 걸 수행할지
    public static void startSort() {
        while(!isKCorrect()) {
            if (time > 100) {
                time = -1;
                return;
            }
            int rowSize = A.size();
            int colSize = A.get(0).size();
            if (rowSize >= colSize) operateR();
            else operateC();
            time++;
        }
    }
    // r-1, c-1 좌표 값이 k가 됐는지 체크
    public static boolean isKCorrect() {
        if (A.get(r-1).get(c-1) == k && time<=100) return true;
        return false;
    }
    // R연산 수행
    // 1. 각 행에서 수가 몇번 나왔는지 체크
    // 2. 등장횟수 오름차순, 수 오름차순으로 정렬
    // 3. 수 등장횟수 순으로 배열에 넣기
    // 4. 가장 큰 행 기준으로 행 size 증가시키기 => 커진곳에는 0 채우기
    // [주의] 행/열 크기가 100이 넘으면 처음 100개 제외 나머지 버리기
    // 이때 0도 처음수로 취급하는가? & 행의 크기 = 한 행에 들어가있는 수의 개수 & 열의크기 = 행의 갯수
    public static void operateR() {
        int maxRowSize = Integer.MIN_VALUE;
        for (int i=0; i<A.size(); i++) {
            HashMap<Integer, Integer> map = new HashMap<>();
            for (int j=0; j<A.get(i).size(); j++) {
                if (A.get(i).get(j) == 0) continue;
                if (map.containsKey(A.get(i).get(j))) {
                    int value = map.get(A.get(i).get(j));
                    map.put(A.get(i).get(j), value+1);
                } else {
                    map.put(A.get(i).get(j), 1);
                }
            }
            // A i번째 행 초기화
            A.get(i).clear();
            List<Integer> keySet = new ArrayList<>(map.keySet());
            Collections.sort(keySet);
            // 값에 대한 오름차순 정리
            keySet.sort((o1,o2) -> map.get(o1).compareTo(map.get(o2)));
            for (int key: keySet) {
                A.get(i).add(key);
                A.get(i).add(map.get(key));
            }
            if (A.get(i).size() > maxRowSize) maxRowSize = A.get(i).size();
        }
        fillZeroRow(maxRowSize);
    }

    // 1. 제일 큰 행 사이즈가 100이 넘는다며 그 상태로 줄이기 => 리턴
    // 애매쓰,,
    public static void fillZeroRow(int maxRowSize) {
        if (maxRowSize >100) {
            int nextMaxRowSize = Integer.MIN_VALUE;
            for (int i=0; i<A.size(); i++) {
                if (A.get(i).size() > 100)  {
                    for (int j=100; j<A.get(i).size(); j++) {
                        A.get(i).remove(j);
                    }
                }
                else if (A.get(i).size() > nextMaxRowSize) nextMaxRowSize = A.get(i).size();
            }
            maxRowSize = nextMaxRowSize;
        }
        for (int i=0; i<A.size(); i++) {
            if (A.get(i).size() != maxRowSize) {
                for (int j = A.get(i).size(); j<maxRowSize; j++) {
                    A.get(i).add(0);
                }
            }
        }
    }
    // C연산 수행
    // 1. 각 열에서 수가 몇번 나왔는지 체크
    // 2. 등장횟수 오름차순, 수 오름차순으로 정렬
    // 3. 수 등장횟수 순으로 배열에 넣기
    // 4. 가장 큰 열 기준으로 열 size(행 갯수) 증가시키기 => 커진곳에는 0 채우기
    // [주의] 행/열 크기가 100이 넘으면 처음 100개 제외 나머지 버리기
    // 이때 0도 처음수로 취급하는가? & 행의 크기 = 한 행에 들어가있는 수의 개수 & 열의크기 = 행의 갯수
    public static void operateC() {
        int maxColCnt = Integer.MIN_VALUE;
        int colSize = A.get(0).size();
        for (int j=0; j<colSize; j++) {
            HashMap<Integer, Integer> map = new HashMap<>();
            for (int i=0; i<A.size(); i++) {
                if (A.get(i).get(j) == 0) continue;
                if (map.containsKey(A.get(i).get(j))) {
                    int value = map.get(A.get(i).get(j));
                    map.put(A.get(i).get(j), value+1);
                } else {
                    map.put(A.get(i).get(j), 1);
                }
            }
            List<Integer> keySet = new ArrayList<>(map.keySet());
            Collections.sort(keySet);
            // 값에 대한 오름차순 정리
            keySet.sort((o1,o2) -> map.get(o1).compareTo(map.get(o2)));
            int i = 0;
            for (int key: keySet) {
                if (i >= A.size()) {
                    A.add(new ArrayList<>(Collections.nCopies(colSize,0)));
                }
                A.get(i).set(j, key);
                i++;
                if (i >= A.size()) {
                    A.add(new ArrayList<>(Collections.nCopies(colSize, 0)));
                }
                A.get(i).set(j, map.get(key));
            }
            if (A.size() > maxColCnt) maxColCnt = A.size();
        }
        fillZeroCol(maxColCnt);
    }

    public static void fillZeroCol(int maxColCnt) {
        if (maxColCnt > 100) {
            for (int i=100; i<maxColCnt; i++)
                A.remove(i);
        }
    }
}
