import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

// 1700 - 멀티탭 스케줄링
// 출력 : 하나씩 플러그를 뺴는 최소의 횟수
// 다시 - 핵심 : 가장 나중에 사용되거나 더이상 사용되지 않는 기기부터 뺴야 최소 횟수로 플러그를 뺄 수 있음
// 기존 내 방식 : 뒤에서 몇번 쓰이는지 횟수로만 따짐
public class Main {
    public static void main (String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());
        st = new StringTokenizer(br.readLine());
        int[] arr = new int[K];
        Map<Integer, Integer> map = new HashMap<>();
        for (int i =0; i<K; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
            if(!map.containsKey(arr[i])) {
                map.put(arr[i], 1);
            }
            else {
                int value = map.get(arr[i]);
                map.replace(arr[i], ++value);
            }
        }
        HashSet<Integer> set = new HashSet<>();
        int ans = 0;
        for (int i=0; i<K; i++) {
            if (set.size() < N) {
                set.add(arr[i]);
                int value = map.get(arr[i]);
                map.replace(arr[i], --value);
            }
            else {
                if (set.contains(arr[i])) {
                    int value = map.get(arr[i]);
                    map.replace(arr[i], --value);
                }
                else {
                    // 가장 나중에 사용되거나 사용이 되지 않으면 뺴라
                    int max = Integer.MIN_VALUE;
                    int minNum = Integer.MAX_VALUE;
                    HashMap<Integer, Integer> idx = new HashMap<>();
                    for (Integer num : set) {
                        boolean isExisted = false;
                        for (int j=i+1; j<K; j++) {
                            if(num == arr[j]) {
                                idx.put(num, j);
                                isExisted = true;
                                break;
                            }
                        }
                        if (!isExisted) {
                            minNum = num;
                            break;
                        } else {
                            if (idx.get(num) > max) {
                                max = idx.get(num);
                                minNum = num;
                            }
                        }
                    }
//                    System.out.println("num: "+ arr[i] +" minNum: " + minNum);
                    set.remove(minNum);
                    ans++;
                    set.add(arr[i]);
                    int value = map.get(arr[i]);
                    map.replace(arr[i], --value);
                }
            }
        }
        System.out.println(ans);
    }
}
