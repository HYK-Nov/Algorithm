import java.io.*;
import java.util.*;

public class Main {
    static int M; static int N;
    static char[][] grid;
    static boolean[][] check;
    static int[] dirX = {0, 0, -1, 1}; // 상 하 좌 우
    static int[] dirY = {-1, 1, 0, 0};
    static int maxY = 0;

    public static void DFS(int y, int x){
        check[y][x] = true;
        
        int curX = 0; int curY = 0;
        for (int i = 0; i < 4; i++) {
            curX = x + dirX[i];
            curY = y + dirY[i];
            if(curX >= 0 && curY >= 0 && curX < N && curY < M){
                if(grid[curY][curX] == '0' && !check[curY][curX]) {
                    if(curY > maxY) maxY = curY;
                    DFS(curY, curX);
                }
            };
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());
        M = Integer.parseInt(st.nextToken());
        N = Integer.parseInt(st.nextToken());
        grid = new char[M][N];
        check = new boolean[M][N];
        for (int i = 0; i < M; i++) {
            grid[i] = br.readLine().toCharArray();
        }

        for (int i = 0; i < N; i++) {
            if(grid[0][i] == '0' && !check[0][i]) {
                DFS(0, i);
            }
        }

        if(maxY == (M-1))bw.write("YES");
        else bw.write("NO");

        bw.flush();
        bw.close();
    }
}