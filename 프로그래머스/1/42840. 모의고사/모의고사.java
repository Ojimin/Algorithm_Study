// 출력: 패턴에 맞게 수학문제를 찍었을 떄, 가장 많은 문제를 맞힌 사람이 누구인지 번호로 출력
// 주의. 가장 높은 점수를 받은 사람이 여럿일 경우, return 하는 값을 오름차순 정렬
// 1: 1,2,3,4,5
// 2: 2, 1, 2, 3, 2, 4, 2, 5
// 3: 3,3, 1,1, 2,2,4,4,5,5
// 최대 100000문제 => 10^4, 로 해결할 수 있을듯
import java.util.*;
class Solution {
    public int[] solution(int[] answers) {
        int[] answer;
        Map<Integer, Integer> map = new HashMap<>();
        int idx1 = 0, idx2 = 0, idx3 = 0;
        int[] arr1 = {1,2,3,4,5}, arr2 = {2, 1, 2, 3, 2, 4, 2, 5}, arr3={3,3, 1,1, 2,2,4,4,5,5};
        int max = Integer.MIN_VALUE;
        int answersIdx = 0;
        map.put(1, 0);
        map.put(2, 0);
        map.put(3, 0);
        
        while(true) {
            if(answersIdx == answers.length) break;
            if(answers[answersIdx] == arr1[idx1]) {
                int org = map.get(1);
                map.put(1, org+1);
                if(max < org+1) max = org+1;
            }
            if(answers[answersIdx] == arr2[idx2]) {
                int org = map.get(2);
                map.put(2, org+1);
                if(max < org+1) max = org+1;
            }
            if(answers[answersIdx] == arr3[idx3]) {
                int org = map.get(3);
                map.put(3, org+1);
                if(max < org+1) max = org+1;
            }
            idx1 = (idx1+1) % arr1.length;
            idx2 = (idx2+1) % arr2.length;
            idx3 = (idx3+1) % arr3.length;
            answersIdx += 1;
        }
        ArrayList<Integer> list = new ArrayList<>();
        for (Map.Entry<Integer, Integer> elem : map.entrySet()) {
            if (max == elem.getValue()) {
                list.add(elem.getKey());
            }   
        }
        Collections.sort(list);
        answer = new int[list.size()];
        for(int i=0;i<list.size(); i++) {
            answer[i] = list.get(i);
        }
        return answer;
    }
}