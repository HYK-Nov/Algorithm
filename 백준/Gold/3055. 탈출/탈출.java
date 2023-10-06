import java.io.*;
import java.util.*;

class Coords {
    int x;
    int y;

    Coords(int x, int y) {
        this.x = x;
        this.y = y;
    }
}

public class Main {
    static char[][] map;
    static boolean[][] check;
    static Queue<Coords> hedgehog = new LinkedList<>();
    static Queue<Coords> water = new LinkedList<>();
    static StringBuffer sb = new StringBuffer();

    public static void BFS(int R, int C) {
        int[] dirX = {0, 0, -1, 1};
        int[] dirY = {-1, 1, 0, 0};
        int curX; int curY;
        int size; int count = 0;
        Coords coords;

        while (!hedgehog.isEmpty()) {
            // 고슴도치
            size = hedgehog.size();
            for (int i = 0; i < size; i++) {
                coords = hedgehog.remove();
                if (map[coords.y][coords.x] != '*') {
                    for (int j = 0; j < 4; j++) {
                        curX = coords.x + dirX[j];
                        curY = coords.y + dirY[j];

                        if (curX < C && curX >= 0 && curY < R && curY >= 0) {
                            if (map[curY][curX] == 'D') {
                                sb.append(count + 1);
                                return;
                            } else if (map[curY][curX] == '.' && !check[curY][curX]) {
                                hedgehog.add(new Coords(curX, curY));
                                check[curY][curX] = true;
                            }
                        }
                    }
                }
            }

            // 홍수
            size = water.size();
            for (int i = 0; i < size; i++) {
                coords = water.remove();
                for (int j = 0; j < 4; j++) {
                    curX = coords.x + dirX[j];
                    curY = coords.y + dirY[j];

                    if (curX < C && curX >= 0 && curY < R && curY >= 0) {
                        if (map[curY][curX] == 'S' || map[curY][curX] == '.') {
                            water.add(new Coords(curX, curY));
                            map[curY][curX] = '*';
                        }
                    }
                }
            }
            count++;
        }
        sb.append("KAKTUS");
    }

    public static void main(String[] args) throws IOException {
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());
        int R = Integer.parseInt(st.nextToken());
        int C = Integer.parseInt(st.nextToken());
        check = new boolean[R][C];
        map = new char[R][C];

        for (int i = 0; i < R; i++) {
            map[i] = br.readLine().toCharArray();
            for (int j = 0; j < C; j++) {
                if (map[i][j] == 'S') {
                    hedgehog.add(new Coords(j, i));
                    check[i][j] = true;
                } else if (map[i][j] == '*') water.add(new Coords(j, i));
            }
        }

        BFS(R, C);

        bw.write(sb.toString());

        bw.flush();
        bw.close();
    }
}