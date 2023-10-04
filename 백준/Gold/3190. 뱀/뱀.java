import java.io.*;
import java.util.*;

class Pair {
    int x, y, X;
    char C;

    Pair(int x, int y) {
        this.x = x;
        this.y = y;
    }

    Pair(int X, char C) {
        this.X = X;
        this.C = C;
    }
}

public class Main {
    static int N;
    static int K;
    static int L;
    static int[][] map;
    static Queue<Pair> command = new LinkedList<>();

    public static int snake() {
        Deque<Pair> deque = new LinkedList<>();
        deque.add(new Pair(0, 0));
        map[0][0] = 5;
        int second = 0;
        int[] dirX = {1, 0, -1, 0};
        int[] dirY = {0, 1, 0, -1};
        int direct = 0;
        int curX, curY;
        Pair pair;

        while (true) {
            pair = deque.peekFirst();
            curX = pair.x + dirX[direct];
            curY = pair.y + dirY[direct];

            if (curY >= N || curY < 0 || curX >= N || curX < 0)
                return second + 1;
            else if (map[curY][curX] == 1) {
                deque.addFirst(new Pair(curX, curY));
                map[curY][curX] = 5;
            } else if (map[curY][curX] == 5)
                return second + 1;
            else {
                deque.addFirst(new Pair(curX, curY));
                map[curY][curX] = 5;
                pair = deque.pollLast();
                map[pair.y][pair.x] = 0;
            }
            if (!command.isEmpty() && second == command.peek().X-1){
                if (command.peek().C == 'L') direct = (direct - 1 < 0) ? 3 : direct - 1;
                else direct = (direct + 1 > 3) ? 0 : direct + 1;
                command.poll();
            }
            second++;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        N = Integer.parseInt(br.readLine());
        K = Integer.parseInt(br.readLine());
        map = new int[N][N];

        StringTokenizer st;
        for (int i = 0; i < K; i++) {
            st = new StringTokenizer(br.readLine());
            int y = Integer.parseInt(st.nextToken());
            int x = Integer.parseInt(st.nextToken());
            map[y - 1][x - 1] = 1;
        }

        L = Integer.parseInt(br.readLine());
        for (int i = 0; i < L; i++) {
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            char c = st.nextToken().charAt(0);
            command.add(new Pair(x, c));
        }

        bw.write(Integer.toString(snake()));

        bw.flush();
        bw.close();
    }
}