import java.io.*;
import java.util.*;

class Monkey {
    int x, y, move, jump;

    Monkey(int x, int y, int move, int jump) {
        this.x = x;
        this.y = y;
        this.move = move;
        this.jump = jump;
    }
}

public class Main {
    static int K, W, H;
    static char[][] map;
    static boolean[][][] visited;

    public static int BFS() {
        if (W == 1 && H == 1) return 0;

        int minRoute = Integer.MAX_VALUE;
        Queue<Monkey> queue = new LinkedList<>();
        queue.add(new Monkey(0, 0, 0, 0));
        visited[0][0][0] = true;

        int[] dirX = {0, 0, -1, 1};
        int[] dirY = {-1, 1, 0, 0};
        int[] jumpX = {-1, -2, -2, -1, 1, 2, 2, 1};
        int[] jumpY = {-2, -1, 1, 2, 2, 1, -1, -2};
        int curX, curY;
        Monkey monkey;
        while (!queue.isEmpty()) {
            monkey = queue.poll();

            if (monkey.jump < K) {
                for (int i = 0; i < 8; i++) {
                    curX = jumpX[i] + monkey.x;
                    curY = jumpY[i] + monkey.y;

                    if (curX == W-1 && curY == H-1) {
                        minRoute = Math.min(monkey.move+1, minRoute);
                        continue;
                    }

                    if (curX >= 0 && curX < W && curY >= 0 && curY < H) {
                        if (map[curY][curX] == '0' && !visited[monkey.jump + 1][curY][curX]){
                            queue.add(new Monkey(curX, curY, monkey.move + 1, monkey.jump + 1));
                            visited[monkey.jump + 1][curY][curX] = true;
                        }
                    }
                }
            }
            for (int i = 0; i < 4; i++) {
                curX = dirX[i] + monkey.x;
                curY = dirY[i] + monkey.y;

                if (curX == W-1 && curY == H-1) {
                    minRoute = Math.min(monkey.move+1, minRoute);
                    continue;
                }

                if (curX >= 0 && curX < W && curY >= 0 && curY < H) {
                    if (map[curY][curX] == '0' && !visited[monkey.jump][curY][curX]){
                        queue.add(new Monkey(curX, curY, monkey.move + 1, monkey.jump));
                        visited[monkey.jump][curY][curX] = true;
                    }
                }
            }
        }

        if (minRoute == Integer.MAX_VALUE) minRoute = -1;
        return minRoute;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        K = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());
        W = Integer.parseInt(st.nextToken());
        H = Integer.parseInt(st.nextToken());
        map = new char[H][W];
        visited = new boolean[K + 1][H][W];

        for (int i = 0; i < H; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < W; j++) {
                map[i][j] = st.nextToken().charAt(0);
            }
        }

        bw.write(Integer.toString(BFS()));

        bw.flush();
        bw.close();
    }
}