// 출력 : 모든 명함을 수납할 수 있는 가장 작은 지갑의 크기
// 주의 : 가로, 세로를 각각 변경해서도 수납 가능
class Solution {
    public int solution(int[][] sizes) {
        int answer = 0;
        int maxWidth = Integer.MIN_VALUE;
        int maxHeight = Integer.MIN_VALUE;
        for (int i=0; i<sizes.length; i++) {
            if (sizes[i][0] < sizes[i][1]) {
                int tmp = sizes[i][0];
                sizes[i][0] = sizes[i][1];
                sizes[i][1] = tmp;
            }
            if (maxWidth < sizes[i][0]) maxWidth = sizes[i][0];
            if (maxHeight < sizes[i][1]) maxHeight = sizes[i][1];
        }
        answer = maxWidth * maxHeight;
        return answer;
    }
}