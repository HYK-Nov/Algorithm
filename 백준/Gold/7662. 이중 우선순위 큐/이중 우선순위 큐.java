import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        TreeMap<Integer, Integer> treeMap = new TreeMap<>();
        StringBuffer sb = new StringBuffer();
        int T = Integer.parseInt(br.readLine());
        int k, value;
        char command;

        for (int i = 0; i < T; i++) {
            k = Integer.parseInt(br.readLine());
            for (int j = 0; j < k; j++) {
                StringTokenizer st = new StringTokenizer(br.readLine());
                command = st.nextToken().charAt(0);
                value = Integer.parseInt(st.nextToken());
                if (command == 'I'){
                    treeMap.put(value, treeMap.getOrDefault(value, 0)+1);
                } else {
                    if (!treeMap.isEmpty()){
                        if (value == -1){
                            treeMap.put(treeMap.firstKey(), treeMap.get(treeMap.firstKey())-1);
                            if (treeMap.get(treeMap.firstKey()) == 0) treeMap.pollFirstEntry();
                        }else {
                            treeMap.put(treeMap.lastKey(), treeMap.get(treeMap.lastKey())-1);
                            if (treeMap.get(treeMap.lastKey()) == 0) treeMap.pollLastEntry();
                        }
                    }
                }
            }
            if (treeMap.isEmpty()) sb.append("EMPTY\n");
            else sb.append(treeMap.lastKey()).append(" ").append(treeMap.firstKey()).append("\n");
            treeMap.clear();
        }

        bw.write(sb.toString());

        bw.flush();
        bw.close();
    }
}