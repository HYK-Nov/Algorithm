import java.io.*;
import java.util.*;

public class Main {
    static int N;
    static boolean[] check;
    static int maxVale;
    static int minValue;
    static ArrayList<ArrayList<Integer>> list = new ArrayList<>();
    static int[] population;

    public static void divide(int idx, int sum) {
        if (idx > N) {
            if (bfs(true) && bfs(false)) {
                minValue = Math.min(minValue, Math.abs(maxVale - sum * 2));
            }
            return;
        }
        check[idx] = true;
        divide(idx + 1, sum + population[idx]);

        check[idx] = false;
        divide(idx + 1, sum);
    }

    public static boolean bfs(boolean mark) {
        Queue<Integer> queue = new LinkedList<>();
        boolean[] visited = check.clone();
        for (int i = 1; i <= N; i++) {
            if (visited[i] == mark) {
                queue.add(i);
                visited[i] = !mark;
                break;
            }
        }

        if (queue.isEmpty()) return false;

        int cur;
        while (!queue.isEmpty()) {
            cur = queue.poll();
            for (int i : list.get(cur)) {
                if (visited[i] == mark) {
                    queue.add(i);
                    visited[i] = !mark;
                }
            }
        }
        for (int i = 1; i <= N; i++) {
            if (visited[i] == mark) return false;
        }

        return true;
    }

    public static void main(String[] args) throws IOException {
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());
        population = new int[N + 1];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= N; i++) population[i] = Integer.parseInt(st.nextToken());
        for (int i = 0; i <= N; i++) list.add(new ArrayList<>());

        for (int i = 1; i <= N; i++) {
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            for (int j = 0; j < x; j++) {
                list.get(i).add(Integer.parseInt(st.nextToken()));
            }
        }

        maxVale = Arrays.stream(population).sum();
        minValue = maxVale;

        if (N == 2) minValue = Math.abs(population[1] - population[2]);
        else {
            check = new boolean[N + 1];
            divide(1, 0);
        }

        if (minValue == maxVale) minValue = -1;
        bw.write(Integer.toString(minValue));

        bw.flush();
        bw.close();
    }
}