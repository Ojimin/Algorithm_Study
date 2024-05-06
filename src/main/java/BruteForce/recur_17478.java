package BruteForce;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

//17478-재귀함수가 뭔가요?
//재귀함수 활용
public class recur_17478 {
    static int cnt = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader((new InputStreamReader((System.in))));
        int N = Integer.parseInt(br.readLine());
        StringBuilder sb = new StringBuilder();

        sb.append("어느 한 컴퓨터공학과 학생이 유명한 교수님을 찾아가 물었다.\n" +
                "\"재귀함수가 뭔가요?\"\n" +
                "\"잘 들어보게. 옛날옛날 한 산 꼭대기에 이세상 모든 지식을 통달한 선인이 있었어.\n" +
                "마을 사람들은 모두 그 선인에게 수많은 질문을 했고, 모두 지혜롭게 대답해 주었지.\n" +
                "그의 답은 대부분 옳았다고 하네. 그런데 어느 날, 그 선인에게 한 선비가 찾아와서 물었어.\"\n");
        recur(N,sb);
        sb.append("라고 답변하였지.");
        System.out.println(sb);
    }

    public static void recur(int N, StringBuilder sb) {
        if (cnt == N) return;

        if (cnt < N) {
            String str = "____";
            for (int i = 0; i < cnt; i++) {
                str += "____";
            }
            if(cnt==N-1) {
                sb.append(str+ "\"재귀함수가 뭔가요?\"\n" + str +"\"재귀함수는 자기 자신을 호출하는 함수라네\"\n");
            } else {
                sb.append(str + "\"재귀함수가 뭔가요?\"\n" + str +
                        "\"잘 들어보게. 옛날옛날 한 산 꼭대기에 이세상 " +
                        "모든 지식을 통달한 선인이 있었어.\n" + str +
                        "마을 사람들은 모두 그 선인에게 수많은 질문을 했고, " +
                        "모두 지혜롭게 대답해 주었지.\n" + str + "그의 답은 대부분 " +
                        "옳았다고 하네. " + "그런데 어느 날, 그 선인에게 한 선비가 찾아와서 물었어.\"\n");
            }
            cnt+=1;
            recur(N,sb);
            sb.append(str+"라고 답변하였지.\n");
        }
    }
}
