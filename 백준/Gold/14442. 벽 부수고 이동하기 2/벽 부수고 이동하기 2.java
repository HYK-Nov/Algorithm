import java.io.*;
import java.util.*;

class Unit {
    int x, y, distance, wall;

    Unit(int x, int y, int distance, int wall) {
        this.x = x;
        this.y = y;
        this.distance = distance;
        this.wall = wall;
    }
}

public class Main {
    static int N, M, K;
    static char[][] map;
    static boolean[][][] visited;

    public static int BFS() {
        if (N == 1 && M == 1) return 1;

        int minRoute = Integer.MAX_VALUE;
        Queue<Unit> queue = new LinkedList<>();
        queue.add(new Unit(0, 0, 1, 0));
        visited[0][0][0] = true;

        int[] dirX = {0, 0, -1, 1};
        int[] dirY = {-1, 1, 0, 0};
        int curX, curY;
        Unit unit;
        while (!queue.isEmpty()) {
            unit = queue.poll();

            for (int i = 0; i < 4; i++) {
                curX = dirX[i] + unit.x;
                curY = dirY[i] + unit.y;

                if (curX == M-1 && curY == N-1){
                    minRoute = Math.min(minRoute, unit.distance+1);
                    continue;
                }

                if (curX >= 0 && curX < M && curY >= 0 && curY < N) {
                    if (unit.wall < K && map[curY][curX] == '1' && !visited[unit.wall + 1][curY][curX]) {
                        queue.add(new Unit(curX, curY, unit.distance + 1, unit.wall + 1));
                        visited[unit.wall + 1][curY][curX] = true;
                    }
                    if (map[curY][curX] == '0' && !visited[unit.wall][curY][curX]) {
                        queue.add(new Unit(curX, curY, unit.distance + 1, unit.wall));
                        visited[unit.wall][curY][curX] = true;
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

        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        map = new char[N][M];
        visited = new boolean[K + 1][N][M];

        for (int i = 0; i < N; i++) {
            map[i] = br.readLine().toCharArray();
        }

        bw.write(Integer.toString(BFS()));

        bw.flush();
        bw.close();
    }
}