import java.io.*;
import java.util.*;

public class Main {
    static StringBuffer sb = new StringBuffer();
    static Queue<Integer> queue = new LinkedList<>();
    public static void BFS(int N){
        queue.clear();
        queue.add(0);

        int cur;
        int count = 0;
        while (!queue.isEmpty()){
            cur = queue.poll();

            if (cur+1 == N || cur+2 == N || cur+3 == N) count++;
            if (cur+1 < N) queue.add(cur+1);
            if (cur+2 < N) queue.add(cur+2);
            if (cur+3 < N) queue.add(cur+3);
        }
        sb.append(count).append("\n");
    }

    public static void main(String[] args) throws IOException {
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int T = Integer.parseInt(br.readLine());
        int n = 0;

        for (int i = 0; i < T; i++) {
            n = Integer.parseInt(br.readLine());
            BFS(n);
        }

        bw.write(sb.toString());

        bw.flush();
        bw.close();
    }
}