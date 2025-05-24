import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

// 9536 - 여우는 어떻게 울지?
// 태스트케이스 개수 입력 -> 여우 제외 동물 goes 소리  * 울음소리는 한 단어
public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());
        StringTokenizer st;
        String sentence, str;
        StringBuilder sb = new StringBuilder();
        for (int i=0; i<T; i++) {
            sentence = br.readLine();
            st = new StringTokenizer(sentence);
            ArrayList<String> soundList = new ArrayList<>();
            Map<String, String> animalSound = new HashMap<>();
            while (st.hasMoreTokens()) {
                str = st.nextToken();
                soundList.add(str);
            }

            while ((str = br.readLine()) != null && !str.isEmpty() ) {
                if (str.equals("what does the fox say?")) break;
                st = new StringTokenizer(str);
                String animal = st.nextToken();
                st.nextToken();
                String sound = st.nextToken();
                animalSound.put(sound, animal);
            }

            for (String s : soundList) {
                if (animalSound.containsKey(s)) continue;
                else sb.append(s).append(" ");
            }
            sb.append("\n");
        }
        System.out.print(sb);
    }
}
