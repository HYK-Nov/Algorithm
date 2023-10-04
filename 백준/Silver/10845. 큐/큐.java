import java.io.*;
import java.util.*;

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

		int N = Integer.parseInt(br.readLine());
		List<Integer> list = new ArrayList<>();

		for (int i = 0; i < N; i++) {
			String[] command = br.readLine().split(" ");

			switch (command[0]) {
			case "push":
				list.add(Integer.parseInt(command[1]));
				break;
			case "pop":
				if (list.size() > 0) {
					bw.write(list.get(0) + "\n");
					list.remove(0);
				} else
					bw.write("-1\n");
				break;
			case "size":
				bw.write(list.size() + "\n");
				break;
			case "empty":
				if (list.size() > 0)
					bw.write("0\n");
				else
					bw.write("1\n");
				break;
			case "front":
				if (list.size() > 0)
					bw.write(list.get(0) + "\n");
				else
					bw.write("-1\n");
				break;
			case "back":
				if (list.size() > 0)
					bw.write(list.get(list.size()-1) + "\n");
				else
					bw.write("-1\n");
				break;
			}
		}

		bw.flush();
		bw.close();
	}
}