import java.io.*;
import java.util.*;

class Unit{
    int x, y, distance;
    boolean wall;
    Unit(int x, int y, int distance, boolean wall){
        this.x = x;
        this.y = y;
        this.distance = distance;
        this.wall = wall;
    }
}

public class Main {
    static int N, M;
    static char[][] map;
    static boolean[][][] visited;

    public static int BFS(){
        if (N == 1 && M == 1) return 1;

        int minRoute = Integer.MAX_VALUE;
        Queue<Unit> queue = new LinkedList<>();
        queue.add(new Unit(0, 0, 1, false));
        visited[0][0][0] = true;

        int[] dirX = {0, 0, -1, 1};
        int[] dirY = {-1, 1, 0, 0};
        int curX, curY;
        Unit unit;
        while (!queue.isEmpty()){
            unit = queue.poll();

            if (unit.x == M-1 && unit.y == N-1) {
                minRoute = Math.min(minRoute, unit.distance);
                continue;
            }

            for (int i = 0; i < 4; i++) {
                curX = dirX[i] + unit.x;
                curY = dirY[i] + unit.y;

                if (curX >= 0 && curX < M && curY >= 0 && curY < N){
                    if (unit.wall){
                        if (map[curY][curX] == '0' && !visited[curY][curX][1]){
                            queue.add(new Unit(curX, curY, unit.distance+1, true));
                            visited[curY][curX][1] = true;
                        }
                    }else {
                        if (!visited[curY][curX][0]){
                            if (map[curY][curX] == '0'){
                                queue.add(new Unit(curX, curY, unit.distance+1, false));
                                visited[curY][curX][0] = true;
                            }else {
                                queue.add(new Unit(curX, curY, unit.distance+1, true));
                                visited[curY][curX][1] = true;
                            }
                        }
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
        map = new char[N][M];
        visited = new boolean[N][M][2];

        for (int i = 0; i < N; i++) {
            map[i] = br.readLine().toCharArray();
        }

        bw.write(Integer.toString(BFS()));

        bw.flush();
        bw.close();
    }
}