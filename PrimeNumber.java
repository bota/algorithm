package programing.java;

import java.util.ArrayList;
import java.util.List;

public class PrimeNumber {
	
	public static void main(String[] args) {
		int num = Integer.parseInt(args[0]);
		outLog(num);
	}
	/**
	 * 
	 * @param num
	 */
	public static void outLog(int num) {
		int[] array = new int[num];
		List<Integer> list = new ArrayList<Integer>();
		for (int i = 2; i < num; i++) {array[i] = i;};
		//‘f”‚Å‚È‚¢”‚ð0‚É•ÏX‚·‚é
		for (int i = 2; i < num; i++) {
			for (int j = i + i; j < num; j+=i) {
				array[j] = 0;
			}
			if (array[i] != 0) {
				System.out.println(array[i]);
			}
		}
		for (int i = 0; i < num; i++) {if (array[i] != 0) {list.add(array[i]);}}
		System.out.println(list.size());
	}
}
