// 두 단어 - begin, target과 단어 집합 words 있음 
// begin -> target 으로 변환하는 가장 짧은 변환 과정 찾으려고 함
// 규칙1. 한번에 한개의 알파벳만 바꿀 수 규칙2. words에 있는 단어로만 변환 가능
// 출력: 최소 몇단계의 과정을 거쳐 변환 가능한지 수를 반환 & 반환할 수 없는 경우, 0을 리턴 => 
// 각 단어 길이 <=10, word 길이<=50
// 0이 나올 수 있는 경우 : word에 target이 없을때 & 있지만 더이상 다음단계로 한개만 차이나는 단어 없을떄?
// 그래프가 아닌데 bfs/dfs로 풀수있따? => bfs로 해결해보자
import java.util.*;
class Solution {
    public int solution(String begin, String target, String[] words) {
        int answer = 0;
        boolean isPossible = false;
        for(int i=0; i<words.length; i++) {
            if (target.equals(words[i])) {
                isPossible = true;
                break;
            }
        }
        if (!isPossible) return answer;
        else answer = bfs(begin, target, words);
        return answer;
       
    }
    
    public int bfs(String begin, String target, String[] words) {
        Queue<String> q = new LinkedList<>();
        q.offer(begin);
        Map<String, Integer> map = new HashMap<>();
        map.put(begin, 0);
        boolean isFind = false;
        
        while (!q.isEmpty()) {
            String now = q.poll();
            for (int i=0; i<words.length; i++) {
                if (map.containsKey(words[i])) continue;
                if (isOne(now, words[i])) {
                    int nowCnt = map.get(now);
                    map.put(words[i], nowCnt+1);
                    if (words[i].equals(target)) {
                        isFind = true;
                        break;
                    }
                    q.offer(words[i]);
                }
            }
            if (isFind) break;
        }      
        if(isFind) return map.get(target);
        return 0;
    }
    
    public boolean isOne(String now, String comp) {
        int cnt = 0;
        for (int i=0; i<now.length(); i++) {
            if (now.charAt(i) != comp.charAt(i)) cnt++;
            if (cnt >1) {
                return false;
            }
        }
        return true;
    }
}