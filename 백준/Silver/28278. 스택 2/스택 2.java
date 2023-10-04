import java.io.*;
import java.util.Stack;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        int X;
        int Y;
        int N = Integer.parseInt(br.readLine());
        Stack<Integer> stack = new Stack<Integer>();

        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            X = Integer.parseInt(st.nextToken());

            switch (X){
                case 1:
                    Y = Integer.parseInt(st.nextToken());
                    stack.push(Y);
                    break;
                case 2:
                    if (!stack.isEmpty()) bw.write(stack.pop() + "\n");
                    else bw.write("-1\n");
                    break;
                case 3:
                    bw.write(stack.size() + "\n");
                    break;
                case 4:
                    if (!stack.isEmpty()) bw.write("0\n");
                    else bw.write("1\n");
                    break;
                case 5:
                    if (!stack.isEmpty()) bw.write(stack.peek() + "\n");
                    else bw.write("-1\n");
                    break;
            }
        }

        bw.flush();
        bw.close();
    }
}