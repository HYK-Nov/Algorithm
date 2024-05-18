import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());
        long result = 0;

        Stack<Long> stack = new Stack<>();
        for (int i = 0; i < N; i++) {
            long cur = Long.parseLong(br.readLine());
            if (!stack.isEmpty()){
                while (!stack.isEmpty()){
                    if (stack.peek() <= cur) stack.pop();
                    else {
                        result += stack.size();
                        break;
                    }
                }
            }
            stack.push(cur);
        }

        bw.write(Long.toString(result));

        bw.flush();
        bw.close();
    }
}