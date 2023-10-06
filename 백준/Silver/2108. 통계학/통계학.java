import java.io.*;
import java.util.*;

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		int N = Integer.parseInt(br.readLine());
		int[] arr = new int[N];
		TreeMap<Integer, Integer> map = new TreeMap<>();
		
		for (int i = 0; i < N; i++) {
			arr[i] = Integer.parseInt(br.readLine());
			if(map.containsKey(arr[i])) map.put(arr[i], map.get(arr[i])+1);
			else map.put(arr[i], 1);
		}
		Arrays.sort(arr);
		
		// 산술평균
		bw.write((int)Math.round(Arrays.stream(arr).average().getAsDouble()) + "\n");
		// 중앙값
		bw.write(arr[N/2] + "\n");
		// 최빈값
		List<Integer> list = new ArrayList<>();
		for(Map.Entry<Integer, Integer> entry : map.entrySet()) {
			if(entry.getValue() == Collections.max(map.values())) list.add(entry.getKey());
		}
		if(list.size() > 1) bw.write(list.get(1) + "\n");
		else bw.write(list.get(0) + "\n");
		// 범위
		bw.write((arr[arr.length-1]-arr[0]) + "\n");
		
		bw.flush();
		bw.close();
	}
}