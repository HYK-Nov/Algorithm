import java.io.*;
import java.util.*;

class Coord {
    int x, y, distance;

    Coord(int x, int y){
        this.x = x;
        this.y = y;
    }
    Coord(int x, int y, int distance) {
        this.x = x;
        this.y = y;
        this.distance = distance;
    }
}

public class Main {
    static int N;
    static char[][] map;
    static int[] dirX = {0, 0, -1, 1};
    static int[] dirY = {-1, 1, 0, 0};

    public static int BFS(int x, int y, int landX, int landY){
        Queue<Coord> queue = new LinkedList<>();
        queue.add(new Coord(x, y, 1));
        Queue<Coord> queueCheck = new LinkedList<>();
        queueCheck.add(new Coord(landX, landY));
        boolean[][] visited = new boolean[N][N];
        visited[y][x] = true;
        visited[landY][landX] = true;

        int curX, curY;
        Coord coord;

        while (!queueCheck.isEmpty()){
            coord = queueCheck.poll();

            for (int i = 0; i < 4; i++) {
                curX = dirX[i] + coord.x;
                curY = dirY[i] + coord.y;

                if (curX >= 0 && curX < N && curY >= 0 && curY < N && !visited[curY][curX]) {
                    if (map[curY][curX] == '1'){
                        queueCheck.add(new Coord(curX, curY));
                        visited[curY][curX]  = true;
                    }
                }
            }
        }

        while (!queue.isEmpty()){
            coord = queue.poll();
            for (int i = 0; i < 4; i++) {
                curX = dirX[i] + coord.x;
                curY = dirY[i] + coord.y;

                if (curX >= 0 && curX < N && curY >= 0 && curY < N && !visited[curY][curX]) {
                    if (map[curY][curX] == '1') return coord.distance;
                    else {
                        queue.add(new Coord(curX, curY, coord.distance+1));
                        visited[curY][curX] = true;
                    }
                }
            }
        }
        return 1;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer st;
        int minLength = Integer.MAX_VALUE;
        N = Integer.parseInt(br.readLine());
        map = new char[N][N];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                map[i][j] = st.nextToken().charAt(0);
            }
        }

        if (N == 2) minLength = 1;
        else {
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < N; j++) {
                    if (map[i][j] == '0') {
                        for (int k = 0; k < 4; k++) {
                            int curX = dirX[k] + j;
                            int curY = dirY[k] + i;

                            if (curX >= 0 && curX < N && curY >= 0 && curY < N && map[curY][curX] == '1') {
                                minLength = Math.min(BFS(j, i, curX, curY), minLength);
                            }
                        }
                    }
                }
            }
        }

        bw.write(Integer.toString(minLength));

        bw.flush();
        bw.close();
    }
}