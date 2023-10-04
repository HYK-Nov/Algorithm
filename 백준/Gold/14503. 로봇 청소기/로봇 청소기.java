import java.io.*;
import java.util.*;

public class Main {
    static int N, M;
    static int[][] room;

    public static int cleaning(int r, int c, int d) {
        int[] coord = {r, c};
        int dir = d;
        int count = 0;

        int[][] scan = {{-1, 0}, {0, 1}, {1, 0}, {0, -1}};
        int curX, curY;
        boolean hasNearCell = false;
        while (true) {

            if (room[coord[0]][coord[1]] == 0) {
                room[coord[0]][coord[1]] = 2;
                count++;
            }

            for (int i = 0; i < 4; i++) {
                curX = coord[1] + scan[i][1];
                curY = coord[0] + scan[i][0];

                if (room[curY][curX] == 0) {
                    hasNearCell = true;
                    break;
                }
            }
            if (hasNearCell) {
                for (int i = 0; i < 4; i++) {
                    if (dir - 1 < 0) dir = 3;
                    else dir--;

                    curX = coord[1] + scan[dir][1];
                    curY = coord[0] + scan[dir][0];
                    if (room[curY][curX] == 0) {
                        coord[1] = curX;
                        coord[0] = curY;
                        break;
                    }
                }
            } else {
                curX = coord[1] + (scan[dir][1] * -1);
                curY = coord[0] + (scan[dir][0] * -1);
                if (room[curY][curX] != 1) {
                    coord[1] = curX;
                    coord[0] = curY;
                } else break;
            }
            hasNearCell = false;
        }

        return count;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        room = new int[N][M];

        st = new StringTokenizer(br.readLine());
        int r = Integer.parseInt(st.nextToken());
        int c = Integer.parseInt(st.nextToken());
        int d = Integer.parseInt(st.nextToken());

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                room[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        bw.write(Integer.toString(cleaning(r, c, d)));

        bw.flush();
        bw.close();
    }
}